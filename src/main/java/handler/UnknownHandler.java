package handler;

import hachi.CommandCode;
import hachi.TaskList;
import hachi.Ui;

public class UnknownHandler implements Manager{
    @Override
    public String execute(TaskList tasks) {
        return Ui.failure(CommandCode.UNKNOWN);
    }
}
