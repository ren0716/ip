public class Ui {
    private static final String separation = "_________________________________________________";
    private static final int TODO = 0;
    private static final int DEADLINE = 1;
    private static final int EVENT = 2;
    private static final int MARK = 3;
    private static final int UNMARK = 4;
    private static final int DELETE = 5;
    private static final int UNKNOWN = 6;
    private static final int MISSING = 7;
    private TaskList tasks;

    public Ui(TaskList tasks) {
        this.tasks = tasks;
    }

    public static void start() {
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
        System.out.println("Woof! Nice to meet you" + logo + "\n" + separation);
    }

    public static void end() {
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
        System.out.println("Come play again!" + exit + "\n" + separation);
    }

    public void success(int code) {
        switch (code) {
            case TODO: // 0
                System.out.println("🐶 Hachi fetched a new task for you!");
                break;

            case DEADLINE: // 1
                System.out.println("🐶 Hachi noted your deadline carefully!");
                break;

            case EVENT: // 2
                System.out.println("🐶 Hachi added your event to the calendar!");
                break;

            case MARK: // 3
                System.out.println("🐶 Hachi wags his tail proudly: 'Task complete!'");
                break;

            case UNMARK: // 4
                System.out.println("🐶 Hachi whines softly: 'Did we bark too soon?'");
                break;

            case DELETE: // 5
                System.out.println("🐶 Hachi dug a hole and buried that task. It’s gone!");
                break;
        }

        // after every success, show the total count
        System.out.println(String.format("You now have (%d) task%s",
                tasks.tasks.size(),
                tasks.tasks.size() == 1 ? "" : "s"));
        System.out.println(separation);
    }


    public static void failure(int error) {
        switch (error) {
            case TODO:
                System.out.println("Woof! You can't just do nothing!" + "\n" + separation);
                break;

            case DEADLINE:
                System.out.println("🐶 Hachi paws at you: 'I need both a task and a deadline! "
                        + "Use it like: deadline <task> /by <time>'");
                printTimeNote();
                break;

            case EVENT:
                System.out.println("🐶 Hachi tilts his head: 'I need a task, a start time, and an end time! "
                        + "Use it like: event <task> /from <start> /to <end>'");
                printTimeNote();
                break;

            case MARK:
                System.out.println("🐶 Hachi is confused: 'which task should i mark?'" + "\n" + separation);
                break;

            case UNMARK:
                System.out.println("🐶 Hachi is confused: 'which task should i unmark?'" + "\n" + separation);
                break;

            case DELETE:
                System.out.println("🐶 Hachi is confused: 'which task should i remove?'" + "\n" + separation);
                break;

            case UNKNOWN:
                System.out.println("🐶 Hachi tilts his head: 'I don’t understand that command.'\n" + separation);
                break;

            case MISSING:
                System.out.println("🐶 Hachi sniffed everywhere, but no task found with that number.");
                System.out.println("Maybe fetch another number?\n" + separation);
                break;
        }
    }

    private static void printTimeNote() {
        System.out.println("Note: Input your time as d/M/yyyy HHmm so Hachi can understand"
                + "\n" + separation);
    }
}


