package Hachi;

import java.io.File;
import java.io.IOException;

/**
 * The Hachi class represents the main entry point of the application, managing the task list,
 * file storage, and user interface (UI). It handles loading existing tasks from a file,
 * interacting with the user through a parser, and saving tasks back to the file.
 */
 public class Hachi {
    private TaskList tasks;
    private File file;
    private Storage storage;
    private Ui ui;

    /**
     * Creates an instance of the Hachi application.
     *
     * <p>The constructor attempts to load the task list from the specified file. If loading fails,
     * a new empty task list is created.</p>
     *
     * @param filePath the path to the file where tasks are saved
     */
    public Hachi(String filePath) {
        file = new File(filePath);
        storage = new Storage(file);
        try {
            tasks = new TaskList(storage.unpack());
            System.out.println(tasks.tasks);

        } catch (IOException e) {
            System.out.println("Hachi.Hachi has no prior memories");
            tasks = new TaskList();
        }
        file = new File(filePath);
    }

    /**
     * Starts the program and runs the command loop, allowing the user to interact with the task list.
     * This method delegates command processing to the Parser
     */
    public void run() {
        Parser.start(tasks, storage);
    }

    /**
     * The main method that initializes and runs the Hachi application.
     *
     * @param args command-line arguments (not used in this application)
     */
    public static void main(String[] args) {
        new Hachi("output/output.txt").run();
    }
}
