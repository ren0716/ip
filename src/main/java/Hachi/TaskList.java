package Hachi;

import java.util.ArrayList;

/**
 * Represents a list of tasks, providing methods to add, delete, and manage tasks.
 * This class stores a collection of Task objects in an ArrayList, allowing
 * for dynamic modification of the task list.
 */
public class TaskList {
    ArrayList<Task> tasks;

    /**
     * Constructs a new, empty TaskList.
     * This initializes the tasks list as an empty ArrayList
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

     /**
     * Constructs a TaskList with the specified list of tasks.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a new task to the list.
     *
     * @param task the task to be added to the list
     */
    public void add(Task task) {
        this.tasks.add(task);
    }

    /**
     * Deletes a task from the list at the specified position.
     * If the position is invalid (i.e., out of bounds), a runtime exception is thrown.
     *
     * @param position the index of the task to be removed from the list
     * @throws RuntimeException if the position is invalid or out of bounds
     */
    public void delete(int position) {
        try {
            this.tasks.remove(position);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }
}
