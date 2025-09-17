package handler;

import hachi.*;
import javafx.animation.PauseTransition;
import javafx.util.Duration;

import java.io.IOException;

public class ByeHandler implements Manager {
    private final Storage storage;

    public ByeHandler(Storage storage) {
        this.storage = storage;
    }

    @Override
    public String execute(TaskList tasks) {
        try {
            storage.write(tasks.getTasks());
        } catch (IOException e) {
            Ui.failure(CommandCode.BYE);
        }
        PauseTransition delay = new PauseTransition(Duration.seconds(2));
        delay.setOnFinished(event -> Main.getPrimaryStage().close());
        delay.play();
        return Ui.end();
    }
}
