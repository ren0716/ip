import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Event extends Task {
    LocalDateTime from;
    LocalDateTime to;

    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    public Event(String description, boolean status, LocalDateTime from, LocalDateTime to) {
        super(description, status);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy, ha", Locale.ENGLISH);
        String formattedFrom = from.format(formatter);
        String formattedTo = to.format(formatter);
        return "[E]" + super.toString() + String.format(" | From: %s | To: %s", formattedFrom, formattedTo);
    }
}
