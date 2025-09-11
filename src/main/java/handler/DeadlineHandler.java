package handler;

import hachi.CommandCode;
import hachi.Deadline;
import hachi.TaskList;
import hachi.Ui;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DeadlineHandler implements Manager {
    private final String input;
    private final Ui ui;

    public DeadlineHandler(String input, Ui ui) {
        this.input = input;
        this.ui = ui;
    }

    @Override
    public String execute(TaskList tasks) {
        Pattern pattern = Pattern.compile(
                "^deadline\\s+(.*?)\\s+/by\\s+(\\d{1,2}/\\d{1,2}/\\d{4}\\s+\\d{4})$",
                Pattern.CASE_INSENSITIVE
        );
        Matcher matcher = pattern.matcher(input);

        if (matcher.matches()) {
            String task = matcher.group(1);
            String by = matcher.group(2);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
            LocalDateTime dateTime = LocalDateTime.parse(by, formatter);
            tasks.getTasks().add(new Deadline(task, dateTime));
            return ui.success(CommandCode.DEADLINE);

        } else {
            return Ui.failure(CommandCode.DEADLINE);
        }
    }
}
