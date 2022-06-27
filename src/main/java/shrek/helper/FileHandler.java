package shrek.helper;

import shrek.data.CurrentDirectory;
import shrek.task.Task;

import java.io.FileWriter;
import java.io.IOException;

/**
 * Handles file operations.
 */
public class FileHandler {
    /**
     * Converts boolean mark to string.
     *
     * @param task The task to get the mark status.
     * @return "mark" or "unmark".
     */
    public static String convertMark(Task task) {
        String mark;
        if (task.getMark()) {
            mark = "marked";
        } else {
            mark = "unmarked";
        }
        return mark;
    }

    /**
     * Writes the task to the output file.
     *
     * @param task Task to be written.
     * @throws IOException When user input improper data to the program.
     */
    public static void writeToFile(String task) throws IOException {
        FileWriter outputFile = new FileWriter(CurrentDirectory.OUTPUT_FILE_PATH, true);
        outputFile.write(task + System.lineSeparator());
        outputFile.close();
    }

    /**
     * Remove saved list from the output file.
     *
     * @throws IOException When user input improper data to the program.
     */
    public static void clearOutput() throws IOException {
        FileWriter outputFile = new FileWriter(CurrentDirectory.OUTPUT_FILE_PATH, false);
        outputFile.write("");
        outputFile.close();
    }
}
