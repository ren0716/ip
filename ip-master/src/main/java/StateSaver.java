import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class StateSaver {
    ArrayList<Task> data;

    public StateSaver(ArrayList<Task> data) {
        this.data = data;
    }

    public void write() throws IOException{
        if(!data.isEmpty()) {
            FileWriter fw = new FileWriter("ip-master/output/output.txt");
            fw.write(data.toString().substring(0, data.toString().length() - 1));
            fw.close();
        }
    }
}
