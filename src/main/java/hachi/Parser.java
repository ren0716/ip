package hachi;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
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
    private static final int TODO = 0;
    private static final int DEADLINE = 1;
    private static final int EVENT = 2;
    private static final int MARK = 3;
    private static final int UNMARK = 4;
    private static final int DELETE = 5;
    private static final int UNKNOWN = 6;
    private static final int MISSING = 7;

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
    public static void start(TaskList tasks, Storage storage) {


        String separation = "_________________________________________________";
        String cmd = "";
        Scanner scanner = new Scanner(System.in);
        Ui ui = new Ui(tasks);
        Ui.start();

        while (true) {
            cmd = scanner.nextLine();
            Command command = Command.from(cmd);

            switch (command) {
            case LIST:
                for (int i = 0; i < tasks.tasks.size(); i++) {
                    Task current = tasks.tasks.get(i);
                    System.out.println((i + 1) + ". " + current.toString());
                }
                break;

            case BYE:
                try {
                    storage.write(tasks.tasks);
                } catch (IOException e) {
                    System.out.println("file not storage");
                }
                Ui.end();
                return;

            case UNMARK: {
                String[] parts = cmd.split(" ");
                if (parts.length > 1) {
                    int taskNumber = Integer.parseInt(parts[1]);
                    if (taskNumber >= 1 && taskNumber <= tasks.tasks.size()) {
                        tasks.tasks.get(taskNumber - 1).unmark();
                        ui.success(UNMARK);
                    } else {
                        Ui.failure(UNMARK);
                    }
                } else {
                    Ui.failure(UNMARK);
                }
                break;
            }

            case MARK: {
                String[] parts = cmd.split(" ");
                if (parts.length > 1) {
                    int taskNumber = Integer.parseInt(parts[1]);
                    if (taskNumber >= 1 && taskNumber <= tasks.tasks.size()) {
                        tasks.tasks.get(taskNumber - 1).mark();
                        ui.success(MARK);
                    } else {
                        Ui.failure(MISSING);
                    }
                } else {
                    Ui.failure(MARK);
                }
                break;
            }

            case TODO: {
                String[] parts = cmd.split(" ", 2);
                if (parts.length > 1) {
                    tasks.tasks.add(new ToDo(parts[1]));
                    ui.success(TODO);
                } else {
                    Ui.failure(TODO);
                }
                break;
            }

            case DEADLINE: {
                Pattern pattern = Pattern.compile(
                        "^deadline\\s+(.*?)\\s+/by\\s+(\\d{1,2}/\\d{1,2}/\\d{4}\\s+\\d{4})$",
                        Pattern.CASE_INSENSITIVE
                );
                Matcher matcher = pattern.matcher(cmd);

                if (matcher.matches()) {
                    String task = matcher.group(1);
                    String by = matcher.group(2);
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
                    LocalDateTime dateTime = LocalDateTime.parse(by, formatter);
                    tasks.tasks.add(new Deadline(task, dateTime));
                    ui.success(DEADLINE);

                } else {
                    Ui.failure(DEADLINE);
                }
                break;
            }


            case EVENT: {
                Pattern pattern = Pattern.compile(
                        "^event\\s+(.*?)\\s+/from\\s+(\\d{1,2}/\\d{1,2}/\\d{4}\\s+\\d{4})\\s+/to\\s+(\\d{1,2}/\\d{1,2}/\\d{4}\\s+\\d{4})$",
                        Pattern.CASE_INSENSITIVE
                );
                Matcher matcher = pattern.matcher(cmd);

                if (matcher.matches()) {
                    String task = matcher.group(1);
                    String fromStr = matcher.group(2);
                    String toStr = matcher.group(3);

                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
                    LocalDateTime from = LocalDateTime.parse(fromStr, formatter);
                    LocalDateTime to = LocalDateTime.parse(toStr, formatter);

                    tasks.tasks.add(new Event(task, from, to));
                    ui.success(EVENT);
                } else {
                    Ui.failure(EVENT);

                }
                break;
            }


            case DELETE: {
                String[] parts = cmd.split(" ");
                if (parts.length > 1) {
                    int taskNumber = Integer.parseInt(parts[1]);
                    if (taskNumber >= 1 && taskNumber <= tasks.tasks.size()) {
                        tasks.tasks.remove(taskNumber - 1);
                        System.out.println(
                                "\uD83D\uDC36 \"Hachi dug a hole and buried that task. It’s gone!\"\n" + separation);
                    } else {
                        Ui.failure(MISSING);
                    }
                } else {
                    Ui.failure(DELETE);
                }
                break;
            }

            case FIND: {
                String[] parts = cmd.split(" ", 2);
                if (parts.length > 1) {
                    String desc = parts[1].trim();
                    tasks.findTasksByKeyword(desc);
                } else {
                    System.out.println("Hachi does not know what you want to find");
                }
                break;
            }

            case UNKNOWN:
            default:
                Ui.failure(UNKNOWN);
            }
        }
    }
}
