package hachi;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Enumeration of all available commands. Commands are parsed from user input
 * and mapped to one of these values.
 */
enum Command {
    LIST, BYE, MARK, UNMARK, TODO, DEADLINE, EVENT, UNKNOWN, DELETE, FIND;

    /**
     * Converts a string input into a corresponding Command.
     *
     * @param input the user command input as a string
     * @return the corresponding Command
     */
    static Command from(String input) {
        if (input == null || input.isBlank()) return UNKNOWN;
        String first = input.trim().split("\\s+", 2)[0].toUpperCase();
        try {
            return Command.valueOf(first);
        } catch (IllegalArgumentException e) {
            return UNKNOWN;
        }
    }
}

/**
 * The Parser class is responsible for interpreting and executing user commands.
 * It listens for user input and processes different types of commands such as
 * listing tasks, marking/unmarking tasks, adding new tasks, and deleting tasks.
 */
public class Parser {

    /**
     * Starts the command loop for interacting with the user.
     * The loop reads commands, executes them, and updates the task list accordingly.
     *
     * <p>This method is responsible for handling commands such as LIST, MARK, UNMARK, TODO,
     * DEADLINE, EVENT, DELETE, and BYE, as well as providing appropriate feedback to the user.</p>
     *
     * @param tasks   the task list to manage
     * @param storage the storage object for saving tasks to a file
     */
    public static String parse(TaskList tasks, Storage storage, String input) {

        Ui ui = new Ui(tasks);
        Ui.start();
        Command command = Command.from(input);

        switch (command) {
            case LIST:
                StringBuilder sb = new StringBuilder();
                if (tasks.tasks.size() == 0) {
                    return "Looks like you are all done!";
                }
                sb.append("Here you go!\n");
                for (int i = 0; i < tasks.tasks.size(); i++) {
                    Task current = tasks.tasks.get(i);
                    sb.append(String.format("%d. %s", i + 1, current.toString()));
                    sb.append("\n");
                }
                return sb.toString();

            case BYE:
                try {
                    storage.write(tasks.tasks);
                } catch (IOException e) {
                    System.out.println("file not storage");
                }
                return Ui.end();

            case UNMARK: {
                String[] parts = input.split(" ");
                if (parts.length > 1) {
                    int taskNumber = Integer.parseInt(parts[1]);
                    if (taskNumber >= 1 && taskNumber <= tasks.tasks.size()) {
                        tasks.tasks.get(taskNumber - 1).unmark();
                        return ui.success(CommandCode.UNMARK);
                    } else {
                        return Ui.failure(CommandCode.MISSING);
                    }
                } else {
                    return Ui.failure(CommandCode.UNMARK);
                }
            }

            case MARK: {
                String[] parts = input.split(" ");
                if (parts.length > 1) {
                    int taskNumber = Integer.parseInt(parts[1]);
                    if (taskNumber >= 1 && taskNumber <= tasks.tasks.size()) {
                        tasks.tasks.get(taskNumber - 1).mark();
                        return ui.success(CommandCode.MARK);
                    } else {
                        return Ui.failure(CommandCode.MISSING);
                    }
                } else {
                    return Ui.failure(CommandCode.MARK);
                }
            }

            case TODO: {
                String[] parts = input.split(" ", 2);
                if (parts.length > 1) {
                    tasks.tasks.add(new ToDo(parts[1]));
                    return ui.success(CommandCode.TODO);
                } else {
                    return Ui.failure(CommandCode.TODO);
                }
            }

            case DEADLINE: {
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
                    tasks.tasks.add(new Deadline(task, dateTime));
                    return ui.success(CommandCode.DEADLINE);

                } else {
                    return Ui.failure(CommandCode.DEADLINE);
                }
            }


            case EVENT: {
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

                    tasks.tasks.add(new Event(task, from, to));
                    return ui.success(CommandCode.EVENT);
                } else {
                    return Ui.failure(CommandCode.EVENT);

                }
            }


            case DELETE: {
                String[] parts = input.split(" ");
                if (parts.length > 1) {
                    int taskNumber = Integer.parseInt(parts[1]);
                    if (taskNumber >= 1 && taskNumber <= tasks.tasks.size()) {
                        tasks.tasks.remove(taskNumber - 1);
                        return ui.success(CommandCode.DELETE);
                    } else {
                        return Ui.failure(CommandCode.MISSING);
                    }
                } else {
                    return Ui.failure(CommandCode.DELETE);
                }
            }

            case FIND: {
                String[] parts = input.split(" ", 2);
                if (parts.length > 1) {
                    String desc = parts[1].trim();
                    return tasks.findTasksByKeyword(desc);
                } else {
                    return Ui.failure(CommandCode.FIND);
                }
            }

            case UNKNOWN:
            default:
                return Ui.failure(CommandCode.UNKNOWN);
        }
    }
}

