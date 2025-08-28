import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;

public class StateSaver {
    ArrayList<Task> data;

    public StateSaver(ArrayList<Task> data) {
        this.data = data;
    }

    public void write() throws IOException{
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
