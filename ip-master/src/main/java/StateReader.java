import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;

public class StateReader {
    File state;
    int taskIdx = 2;
    int statusIdx = 5;
    int descIdx = 8;


    public StateReader(File state) {
        this.state = state;
    }

    public ArrayList<Task> unpack() throws IOException {
        if (!Files.isRegularFile(state.toPath()) || Files.size(state.toPath()) == 0L) {
            return new ArrayList<>(); // nothing to parse
        }
        ArrayList<Task> tasks = new ArrayList<>();
        String content = Files.readString(state.toPath());
        String[] parts = content.split(",");
        for (int i = 0; i < parts.length; i++) {
            String section = parts[i];
            char taskCode = section.charAt(taskIdx);
            boolean status = false;
        switch (taskCode) {
            case ('T'): {
                if (section.charAt(statusIdx) == 'X') {
                    status = true;
                }
                String desc = section.substring(descIdx);
                tasks.add(new ToDo(desc, status));
                break;
            }

            case ('D'): {
                if (section.charAt(statusIdx) == 'X') {
                    status = true;
                }
                String descStart = section.substring(descIdx);
                String[] fields = descStart.split(":");
                String desc = fields[0].substring(0, fields[0].length() - 2);
                String deadline = fields[1].trim();
                tasks.add(new Deadline(desc, status, deadline));
                break;
            }

            default: {
                if (section.charAt(statusIdx) == 'X') {
                    status = true;
                }
                String descStart = section.substring(descIdx);
                String[] fields = descStart.split(":");
                String desc = fields[0].substring(0, fields[0].length() - 4);
                String from = fields[1].substring(0, fields[1].length() - 3).trim();
                String to = fields[2].trim();
                tasks.add(new Event(desc, status, from, to));
                break;
            }

        }
        }

        return tasks;


    }
}
