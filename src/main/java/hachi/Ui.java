package hachi;

/**
 * The Ui class handles the user interface and interaction with the user. It is responsible for
 * displaying messages to the user, such as when tasks are successfully added, marked, unmarked,
 * or deleted. The class also provides feedback on errors and provides a visual representation of
 * the application, such as the welcome and exit screens.
 */
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

    /**
     * Constructs a Ui instance that interacts with the provided TaskList.
     *
     * @param tasks the TaskList this UI will manage interactions for
     */
    public Ui(TaskList tasks) {
        this.tasks = tasks;
    }

    /**
     * Displays the initial greeting message and application logo when the program starts.
     * This includes the welcome message and ASCII art of the application.
     */
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

    /**
     * Displays the exit message and application logo when the program terminates.
     * This includes a goodbye message and ASCII art.
     */
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

    /**
     * Displays a success message based on the action completed.
     * After showing success, it also displays the updated task count.
     *
     * @param code an integer code representing the action (e.g., adding a task, marking a task)
     */
    public void success(int code) {
        switch (code) {
        case TODO: // 0
            System.out.println("🐶 Hachi.Hachi fetched a new task for you!");
            break;

        case DEADLINE: // 1
            System.out.println("🐶 Hachi.Hachi noted your deadline carefully!");
            break;

        case EVENT: // 2
            System.out.println("🐶 Hachi.Hachi added your event to the calendar!");
            break;

        case MARK: // 3
            System.out.println("🐶 Hachi.Hachi wags his tail proudly: 'Hachi.Task complete!'");
            break;

        case UNMARK: // 4
            System.out.println("🐶 Hachi.Hachi whines softly: 'Did we bark too soon?'");
            break;

        case DELETE: // 5
            System.out.println("🐶 Hachi.Hachi dug a hole and buried that task. It’s gone!");
            break;
        }

        // after every success, show the total count
        System.out.println(String.format("You now have (%d) task%s",
                tasks.tasks.size(),
                tasks.tasks.size() == 1 ? "" : "s"));
        System.out.println(separation);
    }


    /**
     * Displays an error message based on the action that failed.
     * Error messages guide the user to the correct format for the input.
     *
     * @param error an integer code representing the error (e.g., missing task, invalid command)
     */
    public static void failure(int error) {
        switch (error) {
        case TODO:
            System.out.println("Woof! You can't just do nothing!" + "\n" + separation);
            break;

        case DEADLINE:
            System.out.println("🐶 Hachi.Hachi paws at you: 'I need both a task and a deadline! "
                    + "Use it like: deadline <task> /by <time>'");
            printTimeNote();
            break;

        case EVENT:
            System.out.println("🐶 Hachi.Hachi tilts his head: 'I need a task, a start time, and an end time! "
                    + "Use it like: event <task> /from <start> /to <end>'");
            printTimeNote();
            break;

        case MARK:
            System.out.println("🐶 Hachi.Hachi is confused: 'which task should i mark?'" + "\n" + separation);
            break;

        case UNMARK:
            System.out.println("🐶 Hachi.Hachi is confused: 'which task should i unmark?'" + "\n" + separation);
            break;

        case DELETE:
            System.out.println("🐶 Hachi.Hachi is confused: 'which task should i remove?'" + "\n" + separation);
            break;

        case UNKNOWN:
            System.out.println("🐶 Hachi.Hachi tilts his head: 'I don’t understand that command.'\n" + separation);
            break;

        case MISSING:
            System.out.println("🐶 Hachi.Hachi sniffed everywhere, but no task found with that number.");
            System.out.println("Maybe fetch another number?\n" + separation);
            break;
        }
    }

    /**
     * Displays a note on how to format date and time input.
     * Used when there are errors in deadline or event input.
     */
    private static void printTimeNote() {
        System.out.println("Note: Input your time as d/M/yyyy HHmm so Hachi.Hachi can understand"
                + "\n" + separation);
    }
}


