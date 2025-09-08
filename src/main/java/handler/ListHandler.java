package handler;

import hachi.Task;
import hachi.TaskList;

public class ListHandler implements Manager {
    @Override
    public String execute(TaskList tasks) {
        StringBuilder sb = new StringBuilder();
        if (tasks.getTasks().isEmpty()) {
            return "Looks like you are all done!";
        }
        sb.append("Here you go!\n");
        for (int i = 0; i < tasks.getTasks().size(); i++) {
            Task current = tasks.getTasks().get(i);
            sb.append(String.format("%d. %s", i + 1, current.toString()));
            sb.append("\n");
        }
        return sb.toString();
    }
}
