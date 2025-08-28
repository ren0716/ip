package Hachi;

import java.util.ArrayList;

public class TaskList {
    ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void add(Task task) {
        this.tasks.add(task);
    }

    public void delete(int position) {
        try {
            this.tasks.remove(position);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }
}
