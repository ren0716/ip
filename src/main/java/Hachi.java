import java.io.File;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.ArrayList;
import java.time.LocalDateTime;
import java.util.regex.*;
enum Command {
    LIST, BYE, MARK, UNMARK, TODO, DEADLINE, EVENT, UNKNOWN, DELETE;

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

public class Hachi {
    public static void main(String[] args) {

        String logo =
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                        "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣀⠤⠄⠤⠤⢄⣀⣀⣀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                        "⠀⠀⠀⠀⠀⠀⠀⠀⣠⠔⢒⠟⠉⠑⠀⠀⠀⠀⠀⠉⠣⡀⠙⣂⠀⠀⠀⠀⠀⠀\n" +
                        "⠀⠀⠀⠀⠀⠀⠀⢀⠗⠀⢗⠂⠀⠀⠀⠀⠀⠀⠀⠀⠈⡏⠀⠈⢢⡀⠀⠀⠀⠀\n" +
                        "⠀⠀⠀⠀⠀⠀⠀⡞⠀⠀⢸⠂⢰⣶⡄⠠⠤⠴⠿⣇⢀⣹⡀⠀⠈⢣⠀⠀⠀⠀\n" +
                        "⠀⠀⠀⠀⠀⠀⠰⡀⠀⠀⢨⠦⠉⠁⠀⢾⣿⡆⠀⠀⠈⠉⣃⠀⢀⠟⠀⠀⠀⠀\n" +
                        "⠀⠀⠀⠀⠀⠀⠀⠙⢧⣀⡰⡑⡀⠀⠤⠴⠛⢲⠒⠀⢠⢸⠿⠾⠋⠀⠀⠀⠀⠀\n" +
                        "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠉⠏⠑⠧⢇⣄⢈⡒⣊⣄⠼⠚⠙⢥⠀⠀⠀⠀⠀⠀⠀\n" +
                        "⠀⠀⠀⠀⠀⠀⠀⠀⣠⢺⠂⠀⠀⠠⡀⠉⠁⠀⠀⠀⠀⠀⢪⠀⠀⠀⠀⠀⠀⠀\n" +
                        "⠀⠀⠀⠀⠀⠀⠀⡸⠁⠘⡆⠀⠀⠈⠇⡀⠀⠀⠀⡂⠀⠀⢸⠀⠀⠀⠀⠀⠀⠀\n" +
                        "⠀⠀⠀⠀⣦⣀⣀⣇⠀⠀⢣⡀⠀⠀⠈⠣⣀⠀⣄⠃⠀⠀⡟⠀⠀⠀⠀⠀⠀⠀\n" +
                        "⠀⠀⠀⠀⠑⢅⣠⡃⣀⣀⡀⠁⠀⠀⠀⠀⢫⢹⠁⠀⠀⠀⢦⠀⠀⠀⠀⠀⠀⠀\n" +
                        "⠀⠀⠀⠀⠀⠀⢹⢩⢀⢀⣈⡇⠀⠀⠀⠀⢸⡘⠄⠀⢀⠀⢈⡅⠀⠀⠀⠀⠀⠀\n" +
                        "⠀⠀⠀⠀⠀⠀⠀⠈⠉⠁⠀⠈⠚⠒⠓⠒⠋⠀⠉⠉⠉⠉⠁⠀⠀⠀⠀⠀⠀⠀\n" +
                        "      _   _            _     _ \n" +
                        "     | | | | __ _  ___| |__ (_)\n" +
                        "     | |_| |/ _` |/ __| '_ \\| |\n" +
                        "     |  _  | (_| | (__| | | | |\n" +
                        "     |_| |_|\\__,_|\\___|_| |_|_|\n";

        String exit =
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                        "⠀⠀⠀⠀⠀⡤⠒⠋⠓⠋⠉⠉⠉⠉⠒⢦⣀⡀⠀⠀⠀⠀⠀⠀⢀⣀⠀⠀⠀⠀\n" +
                        "⠀⠀⢀⡤⠞⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠉⢣⡀⠀⢀⡤⠞⠉⠉⠙⡆⠀⠀\n" +
                        "⠀⣰⠃⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡠⠀⠀⠀⠳⣄⣏⠀⠀⠀⠀⠀⠈⢳⡀\n" +
                        "⢀⡏⠀⠀⡎⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡇⠀⠀⠀⠀⢸⢸⡀⠀⠀⠀⠀⠀⡼⠁\n" +
                        "⠀⡇⠀⢀⠗⠀⢠⣤⡀⠀⠀⢀⣀⡀⠀⠃⠀⠀⠀⠀⠘⡾⢄⡀⠀⠀⠀⣠⠇⠀\n" +
                        "⠀⠙⢤⣼⠆⠀⠈⠉⣤⣤⡄⠘⠛⠃⠀⢱⠀⠀⠀⢀⡰⠃⠀⠈⠒⠑⢺⡀⠀⠀\n" +
                        "⠀⠀⠀⠘⢇⡀⠀⠢⡬⠯⣀⡀⠀⠀⠀⠀⢓⠄⠐⠁⠀⠀⠀⠀⠀⠀⠀⢳⠀⠀\n" +
                        "⠀⠀⠀⠀⠀⢯⠢⢄⡈⠉⠀⠀⠀⣀⣀⡠⠃⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⡀⠀\n" +
                        "⠀⠀⠀⠀⠀⠸⡀⠀⠈⠉⠉⠁⠉⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⠂⠀⠀⢠⠇⠀\n" +
                        "⠀⠀⠀⠀⠀⠀⢹⠢⡄⠀⠀⠀⡀⠀⠀⠀⠀⢀⠀⠀⠀⠀⢀⠌⠀⠀⠀⠸⡀⠀\n" +
                        "⠀⠀⠀⠀⠀⠀⡸⠀⠈⠒⢂⣄⠇⠀⠀⠀⠀⡸⠤⢔⡶⠊⠉⢳⡄⠀⠄⠀⡇⠀\n" +
                        "⠀⠀⠀⠀⠀⢰⠃⠀⠀⢀⡞⡼⠀⠀⠀⠀⢀⡟⠚⠋⠀⠀⠀⢯⣄⣄⣠⠞⠀⠀\n" +
                        "⠀⠀⠀⠀⠀⠈⠛⠓⠒⠋⠸⣠⣠⣀⣀⡖⠋⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                        "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n";


        String separation = "_________________________________________________";

        String cmd = "";
        Scanner scanner = new Scanner(System.in);
        File f = new File("output/output.txt");
        ArrayList<Task> tasks = new ArrayList<>();
        Storage storage = new Storage(f);

        try {
            tasks = storage.unpack();
            System.out.println(tasks);

        } catch (IOException e) {
            System.out.println("Hachi has no prior memories");
        }

        System.out.println("Woof! Nice to meet you" + logo + "\n" + separation);

        while (true) {
            cmd = scanner.nextLine();
            Command command = Command.from(cmd);

            switch (command) {
                case LIST:
                    for (int i = 0; i < tasks.size(); i++) {
                        Task current = tasks.get(i);
                        System.out.println((i + 1) + ". " + current.toString());
                    }
                    break;

                case BYE:
                    try {
                        storage.write(tasks);
                    } catch (IOException e) {
                        System.out.println("file not storage");
                    }
                    System.out.println("Come play again!" + exit + "\n" + separation);
                    return;

                case UNMARK: {
                    String[] parts = cmd.split(" ");
                    if (parts.length > 1) {
                        int taskNumber = Integer.parseInt(parts[1]);
                        if (taskNumber >= 1 && taskNumber <= tasks.size()) {
                            tasks.get(taskNumber - 1).unmark();
                            System.out.println("🐶 Hachi whines softly: 'Did we bark too soon?'\n" + separation);
                        } else {
                            System.out.println("🐶 Hachi sniffed everywhere, but no task found with that number.");
                            System.out.println("Maybe fetch another number?\n" + separation);
                        }
                    } else {
                        System.out.println("🐶 Hachi is confused: 'which task should i unmark?'" + "\n" + separation);
                    }
                    break;
                }

                case MARK: {
                    String[] parts = cmd.split(" ");
                    if (parts.length > 1) {
                        int taskNumber = Integer.parseInt(parts[1]);
                        if (taskNumber >= 1 && taskNumber <= tasks.size()) {
                            tasks.get(taskNumber - 1).mark();
                            System.out.println("🐶 Hachi wags his tail proudly: 'Task complete!'\n" + separation);
                        } else {
                            System.out.println("🐶 Hachi sniffed everywhere, but no task found with that number.");
                            System.out.println("Maybe fetch another number?\n" + separation);
                        }
                    } else {
                        System.out.println("🐶 Hachi is confused: 'which task should i mark?'" + "\n" + separation);
                    }
                    break;
                }

                case TODO: {
                    String[] parts = cmd.split(" ", 2);
                    if (parts.length > 1) {
                        tasks.add(new ToDo(parts[1]));
                        System.out.println("🐕 Paw-some! I’ve added this task to the list:");
                        System.out.println(tasks.get(tasks.size() - 1).toString());
                        System.out.println(String.format("You now have (%d) task", tasks.size()) + "\n" + separation);
                    } else {
                        System.out.println("Woof! You can't just do nothing!" + "\n" + separation);
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
                        LocalDateTime dateTime = LocalDateTime.parse(matcher.group(2), formatter);
                        tasks.add(new Deadline(task, dateTime));
                        System.out.println("🐕 Paw-some! I’ve added this task to the list:");

                    } else {
                        System.out.println
                                ("🐶 Hachi paws at you:" +
                                        " 'I need both a task and a deadline! Use it like: deadline <task> /by <time>'");
                        System.out.println("Note: Input your time as d/M/yyyy HHmm so Hachi can understand" + "\n" + separation);
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

                        tasks.add(new Event(task, from, to));
                        System.out.println("🐕 Paw-some! I’ve added this task to the list:");
                    } else {
                        System.out.println(
                                "🐶 Hachi tilts his head:" +
                                        " 'I need a task, a start time, and an end time! Use it like: event <task> /from <start> /to <end>'"
                        );
                        System.out.println("Note: Input your time as d/M/yyyy HHmm so Hachi can understand"
                                + "\n" + separation);

                    }
                    break;
                }


                case DELETE: {
                    String[] parts = cmd.split(" ");
                    if (parts.length > 1) {
                        int taskNumber = Integer.parseInt(parts[1]);
                        if (taskNumber >= 1 && taskNumber <= tasks.size()) {
                            tasks.remove(taskNumber - 1);
                            System.out.println("\uD83D\uDC36 \"Hachi dug a hole and buried that task. It’s gone!\"\n" + separation);
                        } else {
                            System.out.println("🐶 Hachi sniffed everywhere, but no task found with that number.");
                            System.out.println("Maybe fetch another number?\n" + separation);
                        }
                    } else {
                        System.out.println("🐶 Hachi is confused: 'which task should i remove?'" + "\n" + separation);
                    }
                    break;
                }

                case UNKNOWN:
                default:
                    System.out.println("🐶 Hachi tilts his head: 'I don’t understand that command.'\n" + separation);
            }
        }
    }
}
