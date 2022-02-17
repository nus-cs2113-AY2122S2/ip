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
    public static void loadFile(ArrayList<Task> array) throws IOException {
        try {
            File f = new File("data/duke.txt");
            f.getParentFile().mkdirs();
            f.createNewFile();
            Scanner s = new Scanner(f);
            while (s.hasNext()) {
                String message = s.nextLine();
                String[] splitMessage = message.split(" \\| ");
                String type = splitMessage[0];
                Integer status = Integer.parseInt(splitMessage[1]);
                String description = splitMessage[2];
                switch (type) {
                case "T":
                    array.add(new Todo(description, status));
                    break;
                case "E":
                    String at = splitMessage[3];
                    array.add(new Event(description, status, at));
                    break;
                case "D":
                    String by = splitMessage[3];
                    array.add(new Deadline(description, status, by));
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

    public static void saveFile(ArrayList<Task> array) throws IOException {
        try {
            FileWriter fw = new FileWriter("data/duke.txt");
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < array.size(); i++) {
                sb.append(array.get(i).saveTasks());
            }
            fw.write(sb.toString());
            fw.close();
        } catch (IOException e) {
            System.out.println("IO exception");
        }
    }
}
