package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;
import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;

import static duke.ReadWriteUtil.findFile;

public class ReadTaskList {
    private static final ArrayList<Task> list = new ArrayList<>(100);

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

    private static Task decodeTaskParsing(String[] details, String description,
                                          Boolean status) throws  DukeException {
        Task t;
        switch (details[0]) {
        case "T":
            t = new Todo(description);
            t.setDone(status);
            break;
        case "D":
            String by = details[3];
            t = new Deadline(description, by);
            break;
        case "E":
            String at = details[3];
            t = new Event(description, at);
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
            return decodeTaskParsing(taskDetails, taskDescription, taskStatus);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Missing task information in data file :(");
        }
    }

    private static void readFileToList(String filePath) throws FileNotFoundException {
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

    public static ArrayList<Task> readFile() {
        try {
            Path tasksPath = findFile();
            readFileToList(tasksPath.toString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return list;
    }
}
