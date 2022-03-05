package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class FileAccess {
    final static String FILE_NAME = "/Users/aimanimtiaz/software-engineering/ip/data/duke.txt";

    private static void readFromFile() throws FileNotFoundException {
        File f = new File(FileAccess.FILE_NAME); // create a File for the given file path
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        while (s.hasNext()) {
            Task task = Parser.lineToTask(s.nextLine());
            TaskList.taskList.add(task);
        }
    }

    public static void saveToFile() throws IOException {
        FileWriter fw = new FileWriter(FileAccess.FILE_NAME);
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

    public static void loadFromFile(){
        try {
            readFromFile();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }




}
