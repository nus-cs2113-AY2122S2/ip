package Duke.Helper;

import Duke.Tasks.Deadline;
import Duke.Tasks.Event;
import Duke.Tasks.Task;
import Duke.Tasks.Todo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileCommand {

    protected static final char TODO = 'T';
    protected static final char EVENT = 'E';
    protected static final char DEADLINE = 'D';

    public static void loadFile(ArrayList<Task> listArray) throws IOException {
        try {
            File f = new File("data/duke.txt");
            f.getParentFile().mkdirs();
            f.createNewFile();
            Scanner s = new Scanner(f);
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
        } catch (IOException e) {
            System.out.println("IO exception");
        }
    }

    public static void saveFile(ArrayList<Task> listArray) throws IOException {
        try {
            FileWriter fw = new FileWriter("data/duke.txt");
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < listArray.size(); i++) {
                sb.append(listArray.get(i).saveTasks());
            }
            fw.write(sb.toString());
            fw.close();
        } catch (IOException e) {
            System.out.println("IO exception");
        }
    }
}
