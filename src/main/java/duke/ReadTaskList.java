package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class ReadTaskList {
    private static ArrayList<Task> list = new ArrayList<>(100);
    private static String currDir = System.getProperty("user.dir");

    private static Boolean decodeStatus(Integer taskStatusNum) throws DukeException {
        switch (taskStatusNum) {
        case 1:
            return true;
            // Fallthrough
        case 0:
            return false;
            // Fallthrough
        default:
            throw new DukeException("Data file contains invalid status :(");
        }
    }

    private static Task createTaskFromFile(String[] taskDetails, String taskDescription,
                                           Boolean taskStatus) throws  DukeException{
        Task t;
        switch (taskDetails[0]) {
        case "T":
            t = new Todo(taskDescription);
            t.setDone(taskStatus);
            break;
        case "D":
            String by = taskDetails[3];
            t = new Deadline(taskDescription, by);
            break;
        case "E":
            String at = taskDetails[3];
            t = new Event(taskDescription, at);
            break;
        default:
            throw new DukeException("Invalid task type in data file :(");
        }

        return t;
    }

    private static Task decodeTask(String line) throws DukeException {
        String[] taskDetails = line.split(" \\| ");

        try {
            Integer taskStatusNum = Integer.parseInt(taskDetails[1]);
            Boolean taskStatus = decodeStatus(taskStatusNum);
            String taskDescription = taskDetails[2];
            Task t = createTaskFromFile(taskDetails, taskDescription, taskStatus);
            return t;
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Missing task information in data file :(");
        }
    }

/*    private static void printTask(String line) {
        try {
            Task t = decodeTask(line);
            System.out.println(t);
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }*/

    private static void printFileContents(String filePath) throws FileNotFoundException {
        File f = new File(filePath); // create a File for the given file path
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        while (s.hasNext()) {
            String line = s.nextLine();
            try {
                list.add(decodeTask(line));
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static ArrayList<Task> readList() {
        Path dataDirPath = Paths.get(currDir, "data");
        Path tasksPath = Paths.get(dataDirPath.toString(), "tasks.txt");
        boolean directoryExists = java.nio.file.Files.exists(dataDirPath);
        boolean fileExists = java.nio.file.Files.exists(tasksPath);
        File tasksFile = new File(tasksPath.toString());

        if (!directoryExists) {
            try {
                Files.createDirectories(dataDirPath);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (!fileExists) {
            try {
                tasksFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try {
            printFileContents(tasksPath.toString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return list;
    }
}
