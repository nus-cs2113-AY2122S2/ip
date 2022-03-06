package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Provides methods for storing to and loading from the storage file.
 */
public class FileAccess {

    /**
     * Reads all the existing tasks from a text file and adds them to the TaskList.
     *
     * @throws FileNotFoundException exception if the file does not exist
     * @see Parser
     * @see TaskList
     */
    private static void readFromFile() throws FileNotFoundException {
        File f = new File(Duke.FILE_NAME); // create a File for the given file path
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        while (s.hasNext()) {
            Task task = Parser.lineToTask(s.nextLine());
            TaskList.taskList.add(task);
        }
    }

    /**
     * Saves the entire TaskList to the given text file/
     *
     * @throws IOException exception if file cannot be written to
     * @see Parser
     * @see TaskList
     */
    public static void saveToFile() throws IOException {
        FileWriter fw = new FileWriter(Duke.FILE_NAME);
        String line;
        try {
            for (Task task: TaskList.taskList){
                line = Parser.taskToLine(task);
                fw.write(line + System.lineSeparator());
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

    /**
     * Tries to read from file and prints a message if the file does not exist.
     */
    public static void loadFromFile(){
        try {
            readFromFile();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }




}
