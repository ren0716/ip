package Hachi;

/**
 * Represents a generic task with a description and a completion status. This class provides
 * basic functionality for tasks, including the ability to mark and unmark a task as completed.
 * The task also includes a description of the task and its completion state (either completed or not).
 */
 public class Task {
    Boolean completed = false;
    String description;

    /**
     * Constructs a new task with the specified description. The task is initially marked as incomplete.
     *
     * @param description the description of the task
     */
    public Task(String description) {
        this.description = description;
    }

    /**
     * Constructs a new task with the specified description and completion status.
     *
     * @param description the description of the task
     * @param completed the completion status of the task (true if completed, false if incomplete)
     */
    public Task(String description, Boolean completed) {
        this.description = description;
        this.completed = completed;
    }

    /**
     * Marks the task as completed.
     */
    public void mark() {
        completed = true;
    }

    /**
     * Marks the task as incomplete.
     */
    public void unmark(){
        completed = false;
    }

    /**
     * Returns a string representation of the task. The string includes the completion status
     * and the description of the task. If the task is completed, an "X" is displayed, otherwise
     * a space is shown to indicate that the task is incomplete.
     *
     * @return a string representing the task with its completion status and description
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", completed ? "X" : " ", description );
    }
}
