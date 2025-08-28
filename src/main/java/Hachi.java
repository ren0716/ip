import java.io.File;
import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Scanner;
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
        StateReader saved = new StateReader(f);

        try {
            tasks = saved.unpack();
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
                    StateSaver save = new StateSaver(tasks);
                    try {
                        save.write();
                    } catch (IOException e) {
                        System.out.println("file not saved");
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
                    String[] parts = cmd.split(" ", 2);
                    String[] subparts = parts[1].split("/", 2);
                    if (!subparts[0].isEmpty() && subparts.length == 2) {
                        tasks.add(new Deadline(subparts[0], subparts[1].substring(3).trim()));
                        System.out.println("🐕 Paw-some! I’ve added this task to the list:");
                        System.out.println(tasks.get(tasks.size() - 1).toString());
                        System.out.println(String.format("You now have (%d) task", tasks.size()) + "\n" + separation);
                    } else {
                        System.out.println
                                ("🐶 Hachi paws at you:" +
                                        " 'I need both a task and a deadline! Use it like: deadline <task> /by <time>'"
                                        + "\n" + separation);
                    }
                    break;
                }

                case EVENT: {
                    String[] parts = cmd.split(" ", 2);
                    String[] subparts = parts[1].split("/", 3);
                    if (!subparts[0].isEmpty() && subparts.length == 3) {
                        tasks.add(new Event(subparts[0],
                                subparts[1].substring(5).trim(),
                                subparts[2].substring(3).trim()));
                        System.out.println("🐕 Paw-some! I’ve added this task to the list:");
                        System.out.println(tasks.get(tasks.size() - 1).toString());
                        System.out.println(String.format("You now have (%d) task", tasks.size())
                                + "\n" + separation);
                    } else {
                        System.out.println("\uD83D\uDC3E Hachi sniffs around:" +
                                " 'What's the event about? Try: event walk /from Monday /to Tuesday'"
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
