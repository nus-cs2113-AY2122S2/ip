package duke.storage;

import duke.task.Task;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class FileWriterForMarkOrDelete extends Storage {

    public static void saveDataAfterMarkOrDelete(ArrayList<Task> listOfTasks) {
        try {
            FileWriter fileWriter = new FileWriter(FILE_PATH);
            writeAllTasksToFile(fileWriter, listOfTasks);
            fileWriter.close();
        } catch(IOException error) {
            System.out.println("Error finding file");
        }
    }

    private static void writeAllTasksToFile(FileWriter fileWriter, ArrayList<Task> listOfTasks)
            throws IOException {
        for (Task task: listOfTasks) {
            if (task == null) {
                break;
            }
            FileWriterForAdd.writeDataToFile(task, task.getTypeOfTask(), fileWriter);
        }
    }

}
