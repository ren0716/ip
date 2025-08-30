package Hachi;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Manages the reading and writing of task data from/to a file. This class is responsible for
 * unpacking stored task data into task objects and saving modified or newly created task data
 * back into a file.
 */
 public class Storage {
    File state;

    /**
     * Constructs a new Storage object for managing tasks in the specified file.
     *
     * @param state the file where task data is stored
     */
    public Storage(File state) {
        this.state = state;
    }

    /**
     * Unpacks task data from the stored file and converts it into a list of Task objects.
     * The file is read line by line, and each line is parsed to create a corresponding task object.
     * If the file is empty or does not exist, an empty list is returned.
     *
     * @return a list of tasks read from the file
     * @throws IOException if an error occurs while reading the file
     */
    public ArrayList<Task> unpack() throws IOException {
        if (!Files.isRegularFile(state.toPath()) || Files.size(state.toPath()) == 0L) {
            return new ArrayList<>(); // nothing to parse
        }
        ArrayList<Task> tasks = new ArrayList<>();
        Scanner scanner = new Scanner(state.toPath());
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] parts = line.split("\\|");
            String taskCode  = parts[0];
        switch (taskCode) {
            case "T": {
                String desc = parts[1];
                boolean status = parts[2].equals("true");
                tasks.add(new ToDo(desc, status));
                break;
            }

            case "D": {
                String desc = parts[1];
                boolean status = parts[2].equals("true");
                LocalDateTime unformatted = LocalDateTime.parse(parts[3]);
                tasks.add(new Deadline(desc, status, unformatted));
                break;
            }

            case "E": {
                String desc = parts[1];
                boolean status = parts[2].equals("true");
                LocalDateTime from = LocalDateTime.parse(parts[3]);
                LocalDateTime to = LocalDateTime.parse(parts[4]);
                tasks.add(new Event(desc, status, from, to));
                break;
            }

        }
        }
        return tasks;
    }

    /**
     * Writes a list of task objects to the file in a specific format.
     * Each task is written on a new line in the file.
     *
     * @param data the list of tasks to write to the file
     * @throws IOException if an error occurs while writing to the file
     */
    public void write(ArrayList<Task> data) throws IOException{
        FileWriter fw = new FileWriter("output/output.txt");
        int size = data.size();
        for (int i = 0; i < size; i++) {
            Task current = data.get(i);
            if (current instanceof ToDo) {
                String description = current.description;
                boolean status = current.completed;
                fw.write("T|" + description + "|" + status + "\n");
            }
            if (current instanceof Deadline) {
                String description = current.description.trim();
                boolean status = current.completed;
                String deadline = ((Deadline) current).by.toString();
                fw.write("D|" + description + "|" + status + "|" + deadline + "\n");
            }
            if (current instanceof Event) {
                String description = current.description.trim();
                boolean status = current.completed;
                String from = ((Event) current).from.toString();
                String to = ((Event) current).to.toString();
                fw.write("E|" + description + "|" + status + "|" + from + "|" + to + "\n");
            }
        }
        fw.close();
    }
}
