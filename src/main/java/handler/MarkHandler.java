package handler;

import hachi.CommandCode;
import hachi.TaskList;
import hachi.Ui;

public class MarkHandler implements Manager{
    private final String input;
    private final Ui ui;

    public MarkHandler(String input, Ui ui) {
        this.input = input;
        this.ui = ui;
    }

    @Override
    public String execute(TaskList tasks) {
        String[] parts = input.split(" ");
        if (parts.length > 1) {
            int taskNumber = Integer.parseInt(parts[1]);
            if (taskNumber >= 1 && taskNumber <= tasks.getTasks().size()) {
                tasks.getTasks().get(taskNumber - 1).mark();
                return ui.success(CommandCode.MARK);
            } else {
                return Ui.failure(CommandCode.MISSING);
            }
        } else {
            return Ui.failure(CommandCode.MARK);
        }
    }
}
