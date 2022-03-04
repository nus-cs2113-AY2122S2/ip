package duke.storage;

import duke.tasklist.task.Deadline;
import duke.tasklist.task.Event;
import duke.tasklist.task.Task;
import duke.tasklist.task.Todo;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;

import static duke.storage.ReadWriteUtil.findFile;

/**
 * Methods that write the task list to the data file of the current home directory.
 */
public class WriteTaskList {

    private static String encodeStatus(Boolean isDone) {
        if (isDone) {
            return "1";
        } else {
            return "0";
        }
    }

    private static String encodeTask(Task t) {
        Boolean isDone = t.isDone();
        String encodedStatus = encodeStatus(isDone);
        String description = t.getDescription();
        if (t instanceof Todo) {
            return String.format("T | %s | %s\n", encodedStatus, description);
        } else if (t instanceof Deadline) {
            String by = ((Deadline) t).getByTime();
            return String.format("D | %s | %s | %s\n", encodedStatus, description, by);
        } else {
            String at = ((Event) t).getAtTime();
            return String.format("E | %s | %s | %s\n", encodedStatus, description, at);
        }
    }

    private static String listToString(ArrayList<Task> list) {
        String listAsString = "";
        for (Task t : list) {
            try {
                listAsString = listAsString.concat(encodeTask(t));
            } catch (NullPointerException e) {
                break;
            }
        }
        return listAsString;
    }

    private static void writeListToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }

    /**
     * Writes the task list to the data file.
     *
     * @param list The task list.
     */
    public static void writeList(ArrayList<Task> list) {
        try {
            Path tasksPath = findFile();
            String stringOfList = listToString(list);
            writeListToFile(tasksPath.toString(), stringOfList);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
