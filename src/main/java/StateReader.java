import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

public class StateReader {
    File state;


    public StateReader(File state) {
        this.state = state;
    }

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
            System.out.println(parts[0]);
            System.out.println(parts[1]);
            System.out.println(parts[2]);
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
}
