package handler;

import hachi.CommandCode;
import hachi.TaskList;
import hachi.Ui;

public class DeleteHandler implements Manager{
    private final String input;
    private final Ui ui;

    public DeleteHandler(String input, Ui ui) {
        this.input = input;
        this.ui = ui;
    }
    @Override
    public String execute(TaskList tasks) {
        String[] parts = input.split(" ");
        if (parts.length > 1) {
            int taskNumber = Integer.parseInt(parts[1]);
            if (taskNumber >= 1 && taskNumber <= tasks.getTasks().size()) {
                tasks.getTasks().remove(taskNumber - 1);
                return ui.success(CommandCode.DELETE);
            } else {
                return Ui.failure(CommandCode.MISSING);
            }
        } else {
            return Ui.failure(CommandCode.DELETE);
        }
    }
}
