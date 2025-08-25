import java.util.Objects;
import java.util.Scanner;
import java.util.ArrayList;

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

        String command = "";
        Scanner scanner = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<>();

        System.out.println("Woof! Nice to meet you" + logo + "\n" + separation);

        while (!Objects.equals(command, "bye")) {
            command = scanner.nextLine();
            switch (command) {
                case "list":
                    for (int i = 0; i < tasks.size(); i++) {
                        Task current = tasks.get(i);
                        System.out.println((i + 1) + ". " + current.toString());
                    }
                    break;
                case "bye":
                    // Exit the loop
                    break;
                case "unmark":
                default:
                    if (command.startsWith("unmark")) {
                        String[] parts = command.split(" ");
                        if (parts.length > 1 && Integer.parseInt(parts[1]) < tasks.size() + 1) {
                            int taskNumber = Integer.parseInt(parts[1]);
                            tasks.get(taskNumber - 1).unmark();
                            System.out.println("🐶 Hachi whines softly: 'Did we bark too soon?'" + "\n" + separation);
                        } else {
                            System.out.println("🐶 Hachi sniffed everywhere, but no task found with that number.");
                            System.out.println("Maybe fetch another number?" + "\n" + separation);
                        }
                        continue;
                    }

                    if (command.startsWith("mark")) {
                        String[] parts = command.split(" ");
                        if (parts.length > 1 && Integer.parseInt(parts[1]) < tasks.size() + 1) {
                            int taskNumber = Integer.parseInt(parts[1]);
                            tasks.get(taskNumber - 1).mark();
                            System.out.println("🐶 Hachi wags his tail proudly: 'Task complete!'" + "\n" + separation);
                        } else {
                            System.out.println("🐶 Hachi sniffed everywhere, but no task found with that number.");
                            System.out.println("Maybe fetch another number?" + "\n" + separation);
                        }
                        continue;
                    }

                    tasks.add(new Task(command));
                    System.out.println("🐕 Paw-some! I’ve added this task to the list");
                    break;
            }
        }



        System.out.println("Come play again!" + exit + "\n" + separation);
    }
}
