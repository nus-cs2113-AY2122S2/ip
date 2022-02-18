package duke.storage;

import duke.task.Task;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Storage {

    protected static final String FILE_PATH = "./duke.txt";

    public static void saveData(String typeOfTask, Task task, ArrayList<Task> listOfTasks) {
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            createFile(file);
        }
        switch(typeOfTask) {
        case "todo":
        case "deadline":
        case "event":
            FileWriterForAdd.saveDataAfterAdd(task);
            break;
        case "markOrDelete":
            FileWriterForMarkOrDelete.saveDataAfterMarkOrDelete(listOfTasks);
            break;
        default:
            System.out.println("Something is wrong with the typeOfTask");
        }
    }

    private static void createFile(File file) {
        try {
            file.createNewFile();
        } catch(IOException error) {
            System.out.println("Error creating file");
            error.printStackTrace();
        }
    }

}
