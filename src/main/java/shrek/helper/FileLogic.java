package shrek.helper;

import shrek.data.CurrentDirectory;
import shrek.task.UserContent;

import java.io.FileWriter;
import java.io.IOException;

public class FileLogic {
    public static String convertMark(UserContent task) {
        String mark;
        if (task.getMark()) {
            mark = "marked";
        } else {
            mark = "unmarked";
        }
        return mark;
    }

    public static void writeToFile(String task) throws IOException {
        FileWriter outputFile = new FileWriter(CurrentDirectory.OUTPUT_FILE_PATH, true);
        outputFile.write(task + System.lineSeparator());
        outputFile.close();
    }

    public static void clearOutput() throws IOException {
        FileWriter outputFile = new FileWriter(CurrentDirectory.OUTPUT_FILE_PATH, false);
        outputFile.write("");
        outputFile.close();
    }
}
