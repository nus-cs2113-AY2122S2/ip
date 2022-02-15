import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.util.ArrayList;

import duke.task.Task;

import java.util.Scanner;

public class FileReaderWriter {

    private static final String FILE_NAME = "tasks.txt";

    /**
     * Writes to a file the current representation of tasks in the same format as `list` prompt.
     *
     * @param taskList, an ArrayList of current tasks.
     */
    public static void writeToFile(ArrayList<Task> taskList) {
        try {
            FileWriter fw = new FileWriter(FILE_NAME);
            for (Task task : taskList) {
                fw.write(task.toString() + System.lineSeparator());
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

    /**
     * Reads in a file "tasks.txt" line by line and returns each line as a String in an ArrayList for further processing
     *
     * @return ArrayList<String>
     */
    public static ArrayList<String> readFromFile() {
        ArrayList<String> oldList = new ArrayList<>();
        try {
            File f = new File(FILE_NAME);
            Scanner s = new Scanner(f);
            while (s.hasNext()) {
                String line = s.nextLine();
                if (!line.equals(""))
                    oldList.add(line);
            }
        } catch (FileNotFoundException e) {
            return oldList;
        }
        return oldList;
    }

}
