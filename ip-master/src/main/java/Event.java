public class Event extends Task {
    String from;
    String to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    public Event(String description, boolean status, String from, String to) {
        super(description, status);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + String.format("From: %s To: %s", this.from, this.to);
    }
}
