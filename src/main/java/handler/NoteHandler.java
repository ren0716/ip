package handler;

import hachi.CommandCode;
import hachi.DateFormatter;
import hachi.TaskList;
import hachi.Ui;

import java.time.LocalDateTime;

public class NoteHandler implements Manager {
    private final String input;
    private final Ui ui;

    public NoteHandler(String input, Ui ui) {
        this.input = input;
        this.ui = ui;
    }

    @Override
    public String execute(TaskList tasks) {
        String[] parts = input.split(" ");
        if (parts.length > 2) {
            int taskNumber = Integer.parseInt(parts[1]);
            if (taskNumber >= 1 && taskNumber <= tasks.getTasks().size()) {
                String information = parts[2];
                LocalDateTime creationTime = LocalDateTime.now();
                tasks.getTaskAt(taskNumber - 1).addNote(information, creationTime);
                return ui.success(CommandCode.NOTE);
            } else {
                return Ui.failure(CommandCode.MISSING);//bad index
            }
        }
        return Ui.failure(CommandCode.NOTE);//not following instruction format

    }
}
