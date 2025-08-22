public class Task {
    Boolean completed = false;
    String description;

    public Task(String description) {
        description = this.description;
    }

    public void mark() {
        completed = true;
    }

    public void unmark(){
        completed = false;
    }
}
