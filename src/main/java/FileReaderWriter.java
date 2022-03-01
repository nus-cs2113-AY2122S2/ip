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
            FileWriter fileWriter = new FileWriter(FILE_NAME);
            for (Task task : taskList) {
                fileWriter.write(task.toString() + System.lineSeparator());
            }
            fileWriter.close();
        } catch (IOException exception) {
            System.out.println("Something went wrong: " + exception.getMessage());
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
            File file = new File(FILE_NAME);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                if (!line.equals("")) {
                    oldList.add(line);
                }
            }
        } catch (FileNotFoundException exception) {
            System.out.println("File not found!");
            return oldList;
        }
        return oldList;
    }

}
