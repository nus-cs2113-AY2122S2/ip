package duke.storage;

import duke.DukeException;
import duke.tasklist.task.Task;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;

import static duke.parser.TaskString.decodeTask;
import static duke.storage.ReadWriteUtil.findFile;

/**
 * Methods that read the task list stored in the data file of the current home directory.
 */
public class ReadTaskList {
    private static final int MAX_SIZE = 100;
    private static final ArrayList<Task> list = new ArrayList<>(MAX_SIZE);

    private static void readFileToList(String filePath) throws FileNotFoundException {
        File f = new File(filePath);
        Scanner s = new Scanner(f);
        while (s.hasNext()) {
            String line = s.nextLine();
            try {
                list.add(decodeTask(line));
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * Returns the task list from reading the data file.
     *
     * @return list Task list.
     */
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
