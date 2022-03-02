package Duke.Storage;

import Duke.DukeException;
import Duke.Tasks.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    protected static final char TODO = 'T';
    protected static final char EVENT = 'E';
    protected static final char DEADLINE = 'D';
    private String filepath;

    public Storage(String filepath) {
        this.filepath = filepath;
    }

    public ArrayList<Task> loadFile() throws DukeException {
        try {
            File f = new File("data/duke.txt");
            f.getParentFile().mkdirs();
            f.createNewFile();
            Scanner s = new Scanner(f);
            ArrayList<Task> listArray = new ArrayList<>();
            while (s.hasNext()) {
                boolean isDone = true;
                String message = s.nextLine();
                String[] splitMessage = message.split(" \\| ");
                char type = splitMessage[0].charAt(0);
                int status = Integer.parseInt(splitMessage[1]) ;
                if (status == 0) {
                    isDone = false;
                }
                String description = splitMessage[2];
                switch (type) {
                case TODO:
                    listArray.add(new Todo(description, isDone));
                    break;
                case EVENT:
                    String at = splitMessage[3];
                    listArray.add(new Event(description, isDone, at));
                    break;
                case DEADLINE:
                    String by = splitMessage[3];
                    listArray.add(new Deadline(description, isDone, by));
                    break;
                default:
                    break;
                }
            }
            s.close();
            return listArray;
        } catch (IOException e) {
            throw new DukeException(e.getMessage());
        }
    }

    public void saveFile(TaskList task) throws IOException {
        try {
            FileWriter fw = new FileWriter("data/duke.txt");
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < task.size(); i++) {
                sb.append(task.get(i).saveTasks());
            }
            fw.write(sb.toString());
            fw.close();
        } catch (IOException e) {
            System.out.println("IO exception");
        }
    }
}
