package duke.Storage;

import duke.DukeException;
import duke.Parser.TaskString;
import duke.TaskList.task.Task;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;

import static duke.Storage.ReadWriteUtil.findFile;

public class ReadTaskList {
    private static final ArrayList<Task> list = new ArrayList<>(100);

    private static void readFileToList(String filePath) throws FileNotFoundException {
        File f = new File(filePath);
        Scanner s = new Scanner(f);
        while (s.hasNext()) {
            String line = s.nextLine();
            try {
                list.add(TaskString.decodeTask(line));
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
