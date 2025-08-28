package Hachi;

public class Task {
    Boolean completed = false;
    String description;

    public Task(String description) {
        this.description = description;
    }

    public Task(String description, Boolean completed) {
        this.description = description;
        this.completed = completed;
    }

    public void mark() {
        completed = true;
    }

    public void unmark(){
        completed = false;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", completed ? "X" : " ", description );
    }
}
