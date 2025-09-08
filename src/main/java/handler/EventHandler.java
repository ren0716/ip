package handler;

import hachi.CommandCode;
import hachi.Event;
import hachi.TaskList;
import hachi.Ui;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EventHandler implements Manager {
    private final String input;
    private final Ui ui;

    public EventHandler(String input, Ui ui) {
        this.input = input;
        this.ui = ui;
    }

    @Override
    public String execute(TaskList tasks) {
        Pattern pattern = Pattern.compile(
                "^event\\s+(.*?)\\s+/from\\s+(\\d{1,2}/\\d{1,2}/\\d{4}\\s+\\d{4})\\s+/to\\s+(\\d{1,2}/\\d{1,2}/\\d{4}\\s+\\d{4})$",
                Pattern.CASE_INSENSITIVE
        );
        Matcher matcher = pattern.matcher(input);

        if (matcher.matches()) {
            String task = matcher.group(1);
            String fromStr = matcher.group(2);
            String toStr = matcher.group(3);

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
            LocalDateTime from = LocalDateTime.parse(fromStr, formatter);
            LocalDateTime to = LocalDateTime.parse(toStr, formatter);

            tasks.getTasks().add(new Event(task, from, to));
            return ui.success(CommandCode.EVENT);
        } else {
            return Ui.failure(CommandCode.EVENT);

        }
    }
}

