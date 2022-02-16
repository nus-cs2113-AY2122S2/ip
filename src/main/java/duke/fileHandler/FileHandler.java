package duke.fileHandler;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileHandler {
    private static String filePath;

    public static void setfilePath(String filePath) {
        FileHandler.filePath= filePath;
    }

    public static Task stringToTask(String input) {
        int indexOfSpace = input.indexOf(" ");
        String taskType = input.substring(indexOfSpace + 2, indexOfSpace+3);
        String status = input.substring(indexOfSpace + 5, indexOfSpace + 6);
        String nameAndDate = input.substring(indexOfSpace+ 7);
        String name= "", date = "";
        if (!taskType.equals("T")) {
            name = nameAndDate.substring(0, nameAndDate.indexOf("("));
            date = nameAndDate.substring(nameAndDate.indexOf("(") + 1, nameAndDate.indexOf(")"));
        }
        Task task = new Task("");
        switch (taskType) {
        case "T":
            task = new ToDo(nameAndDate);
            break;
        case "D":
            task = new Deadline(name, date);
            break;
        case "E":
            task = new Event(name, date);
            break;
        }
        if (status.equals("X")) {
            task.setCompleted(true);
        }
        return task;
    }

    public static ArrayList<Task> readFile() throws IOException {
        ArrayList<Task> tasks = new ArrayList<>();
        File input = new File(filePath);
        if (input.createNewFile()) {
            System.out.println("Create");
        }
        Scanner s = new Scanner(input);
        while (s.hasNext()) {
            tasks.add(stringToTask(s.nextLine()));
        }
        return tasks;
    }

    public static void writeFile(String textToAdd) throws IOException {
        FileWriter output = new FileWriter(filePath);

        output.write(textToAdd);
        output.close();
    }
}
