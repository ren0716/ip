package handler;

import hachi.CommandCode;
import hachi.TaskList;
import hachi.Ui;

public class UnmarkHandler implements Manager {
    private final String input;
    private final Ui ui;

    public UnmarkHandler(String input, Ui ui) {
        this.input = input;
        this.ui = ui;
    }

    @Override
    public String execute(TaskList tasks) {
        String[] parts = input.split(" ");
        if (parts.length > 1) {
            int taskNumber = Integer.parseInt(parts[1]);
            if (taskNumber >= 1 && taskNumber <= tasks.getTasks().size()) {
                tasks.getTasks().get(taskNumber - 1).unmark();
                return ui.success(CommandCode.UNMARK);
            } else {
                return Ui.failure(CommandCode.MISSING);
            }
        } else {
            return Ui.failure(CommandCode.UNMARK);
        }
    }
}

