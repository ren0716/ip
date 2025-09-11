package hachi;

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
    File taskData;
    File noteData;

    /**
     * Constructs a new Storage object for managing tasks in the specified file.
     *
     * @param taskData the file where task data is stored
     */
    public Storage(File taskData, File noteData) {
        this.taskData = taskData;
        this.noteData = noteData;
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
        if (!Files.isRegularFile(taskData.toPath()) || Files.size(taskData.toPath()) == 0L) {
            return new ArrayList<>(); // nothing to parse
        }
        ArrayList<Task> tasks = new ArrayList<>();
        Scanner scanner = new Scanner(taskData.toPath());
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] parts = line.split("\\|");
            String taskCode = parts[0];
            // Flag to track if the variable is part of any switch case
            boolean isCaseFound = false;
            switch (taskCode) {
            case "T": {
                String desc = parts[1];
                boolean status = parts[2].equals("true");
                tasks.add(new ToDo(desc, status));
                isCaseFound = true;
                break;
            }

            case "D": {
                String desc = parts[1];
                boolean status = parts[2].equals("true");
                LocalDateTime unformatted = LocalDateTime.parse(parts[3]);
                tasks.add(new Deadline(desc, status, unformatted));
                isCaseFound = true;
                break;
            }

            case "E": {
                String desc = parts[1];
                boolean status = parts[2].equals("true");
                LocalDateTime from = LocalDateTime.parse(parts[3]);
                LocalDateTime to = LocalDateTime.parse(parts[4]);
                tasks.add(new Event(desc, status, from, to));
                isCaseFound = true;
                break;
            }
            }
            assert isCaseFound : "invalid switch case found";
        }
        readNoteData(tasks);
        return tasks;
    }

    /**
     * Writes a list of task objects to the file in a specific format.
     * Each task is written on a new line in the file.
     *
     * @param data the list of tasks to write to the file
     * @throws IOException if an error occurs while writing to the file
     */
    public void write(ArrayList<Task> data) throws IOException {
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
            assert current != null : "invalid input not caught";
        }

        fw.close();

        writeNote(data);
    }

    /**
     * Writes all note objects associated to a list of task objects in a specific format
     *
     * @param data the list of tasks to write to the file
     * @throws IOException if an error occurs while writing to the file
     */
    public void writeNote(ArrayList<Task> data) throws IOException {
        FileWriter fw = new FileWriter("output/note.txt");
        int size = data.size();
        for (int i = 0; i < size; i++) {
            Task current = data.get(i);
            String currentNote = String.valueOf(current.printNote());
            if ("null".equals(currentNote)) {
                fw.write("\n");
                continue;
            }
            fw.write(currentNote + "\n");
        }

        fw.close();
    }

    /**
     * Reads notes data from stored file and maps them to associated task
     * The file is read line by line, every 2 lines is parsed to create a corresponding note object
     * If a line is empty, it will be skipped and task will not have a note
     *
     * @param tasks list of task to apply notes to
     * @throws IOException if an error occurs while writing to the file
     */
    public void readNoteData(ArrayList<Task> tasks) throws IOException {
        if (!Files.isRegularFile(noteData.toPath()) || Files.size(noteData.toPath()) == 0L) {
            return;
        }
        Scanner scanner = new Scanner(noteData.toPath());
        int index = 0;
        while (scanner.hasNextLine()) {
            String information = scanner.nextLine();

            //current task does not have a note
            if (information.isEmpty()) {
                index++;
                continue;
            }

            String unFormattedTime = scanner.nextLine();
            LocalDateTime ParsedTime = new DateFormatter()
                    .parseTime(unFormattedTime
                            .substring(unFormattedTime.indexOf(" ")).trim());

            tasks.get(index).addNote(information, ParsedTime); //add note
            index++;
        }
    }
}
