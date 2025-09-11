package handler;

import hachi.CommandCode;
import hachi.TaskList;
import hachi.ToDo;
import hachi.Ui;

public class TodoHandler implements Manager{
    private final String input;
    private final Ui ui;

    public TodoHandler(String input, Ui ui){
        this.input = input;
        this.ui = ui;
    }
    @Override
    public String execute(TaskList tasks) {
        String[] parts = input.split(" ", 2);
        if (parts.length > 1) {
            tasks.getTasks().add(new ToDo(parts[1]));
            return ui.success(CommandCode.TODO);
        } else {
            return Ui.failure(CommandCode.TODO);
        }
    }
}
