package handler;

import hachi.CommandCode;
import hachi.TaskList;
import hachi.Ui;

public class FindHandler implements Manager {
    private final String input;
    private final Ui ui;

    public FindHandler(String input, Ui ui) {
        this.input = input;
        this.ui = ui;
    }

    @Override
    public String execute(TaskList tasks) {
        String[] parts = input.split(" ", 2);
        if (parts.length > 1) {
            String desc = parts[1].trim();
            return tasks.findTasksByKeyword(desc);
        } else {
            return Ui.failure(CommandCode.FIND);
        }
    }
}
