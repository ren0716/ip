package hachi;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DateFormatter {

    public LocalDateTime parseTime(String time) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy, ha", Locale.ENGLISH);
        return LocalDateTime.parse(time, formatter);
    }

    public String formatTime(LocalDateTime time) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy, ha", Locale.ENGLISH);
        return time.format(formatter);
    }
}
