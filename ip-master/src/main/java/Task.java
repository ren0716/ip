public class Task {
    Boolean completed = false;
    String description;

    public Task(String description) {
        this.description = description;
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
