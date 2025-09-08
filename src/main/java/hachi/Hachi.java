package hachi;

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
            tasks = new TaskList();
        }
    }

    public TaskList getTasks() {
        return tasks;
    }

    public Storage getStorage() {
        return storage;
    }
}
