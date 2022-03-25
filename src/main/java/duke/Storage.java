package duke;

import duke.tasks.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents a <code>Storage</code> object that contains support for
 * saving tasks/loading tasks from storage (ensures persistent storage
 * of tasks)
 */

public class Storage {
    protected static String filePath;
    protected static final char TODO = 'T';
    protected static final char EVENT = 'E';
    protected static final char DEADLINE = 'D';

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Helper for loadTaskList
     * Loads tasks from duke.txt (file of saved tasks)
     * Returns ArrayList of Task objects
     */
    public static ArrayList<Task> loadFile() throws IOException {
        try {
            File f = new File(filePath);
            f.getParentFile().mkdirs();
            f.createNewFile();
            Scanner s = new Scanner(f);
            ArrayList<Task> listArray = new ArrayList<>();
            while (s.hasNext()) {
                boolean isDone = true;
                String message = s.nextLine();
                String[] splitMessage = message.split(" \\| ");
                char type = splitMessage[0].charAt(0);
                int status = Integer.parseInt(splitMessage[1]);
                if (status == 0) {
                    isDone = false;
                }
                String description = splitMessage[2];
                switch (type) {
                    case TODO:
                        listArray.add(new ToDo(description, isDone));
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
            System.out.println("IO exception");
        }
        return null;
    }
    /**
     * Helper for processTasks
     * Saves the list of tasks to the filepath of the Storage object
     * @param listArray an ArrayList of tasks to write to file
     */
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
