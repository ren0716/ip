import java.io.File;
import java.io.IOException;

public class Hachi {
    private TaskList tasks;
    private File file;
    private Storage storage;
    private Ui ui;

    public Hachi(String filePath) {
        file = new File(filePath);
        storage = new Storage(file);
        try {
            tasks = new TaskList(storage.unpack());
            System.out.println(tasks.tasks);

        } catch (IOException e) {
            System.out.println("Hachi has no prior memories");
            tasks = new TaskList();
        }
        file = new File(filePath);
    }

    public void run() {
        Parser.start(tasks, storage);
    }

    public static void main(String[] args) {
        new Hachi("output/output.txt").run();
    }
}
