package handler;

import hachi.CommandCode;
import hachi.Storage;
import hachi.TaskList;
import hachi.Ui;

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
        return Ui.end();
    }
}
