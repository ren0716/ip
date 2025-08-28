package Hachi;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Deadline extends Task{
    LocalDateTime by;

    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    public Deadline(String description, boolean status, LocalDateTime by) {
        super(description, status);
        this.by = by;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy, ha", Locale.ENGLISH);
        String formatted = by.format(formatter);
        return "[D]" + super.toString() + String.format(" | By: %s", formatted);
    }
}
