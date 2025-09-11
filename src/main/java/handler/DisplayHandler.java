package handler;

import hachi.CommandCode;
import hachi.Task;
import hachi.TaskList;
import hachi.Ui;

public class DisplayHandler implements Manager {
    private final String input;
    private final Ui ui;

    public DisplayHandler(String input, Ui ui) {
        this.input = input;
        this.ui = ui;
    }

    @Override
    public String execute(TaskList tasks) {
        String[] parts = input.split(" ");
        if (parts.length > 1) {
            int taskNumber = Integer.parseInt(parts[1]);
            if (taskNumber >= 1 && taskNumber <= tasks.getTasks().size()) {
                Task target = tasks.getTaskAt(taskNumber - 1);
                return target.getNote() != null
                        ? target.printNote()
                        : Ui.failure(CommandCode.DISPLAY);
            } else {
                return Ui.failure(CommandCode.MISSING);
            }
        } else {
            return Ui.failure(CommandCode.MARK);
        }
    }
}
