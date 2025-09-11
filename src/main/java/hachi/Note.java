package hachi;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Note {
    private String information;
    private LocalDateTime timeStamp;

    public Note(String information, LocalDateTime timeStamp) {
        this.information = information;
        this.timeStamp = timeStamp;
    }

    @Override
    public String toString() {
        String timeAsString = new DateFormatter().formatTime(timeStamp);
        return String.format("%s\ncreated: %s", this.information, timeAsString);
    }
}
