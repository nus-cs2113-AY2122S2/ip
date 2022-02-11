package duke;

import duke.task.Task;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class FileEditor {

    private static final String FILE_PATH = "./duke.txt";
    public static final String WRONG_TYPE_OF_TASK = "Something is wrong with the typeOfTask";

    private static File file = new File(FILE_PATH);

    public static void saveData(String typeOfTask, Task task, ArrayList<Task> listOfTasks) {
        if (!file.exists()) {
            createFile();
        }
        switch(typeOfTask) {
        case "todo":
        case "deadline":
        case "event":
            saveDataAfterAdd(task, typeOfTask);
            break;
        case "markOrDelete":
            saveDataAfterMarkOrDelete(listOfTasks, typeOfTask);
            break;
        default:
            System.out.println(WRONG_TYPE_OF_TASK);
        }
    }

    private static void saveDataAfterMarkOrDelete(ArrayList<Task> listOfTasks, String typeOfTask) {
        try {
            FileWriter fileWriter = new FileWriter(FILE_PATH);
            writeAllTasksToFile(fileWriter, listOfTasks, typeOfTask);
            fileWriter.close();
        } catch(IOException error) {
            System.out.println("Error finding file");
        }
    }

    private static void writeAllTasksToFile(FileWriter fileWriter, ArrayList<Task> listOfTasks, String typeOfTask)
                throws IOException {
        for (Task task: listOfTasks) {
            if (task == null) {
                break;
            }
            fileWriter.write(typeOfTask + "," + task.isDone() + "," + task.getDescription() + "\n");
        }
    }

    private static void saveDataAfterAdd(Task task, String typeOfTask) {
        try {
            FileWriter fileWriter = new FileWriter(FILE_PATH, true);
            fileWriter.write(typeOfTask + "," + task.isDone() + "," + task.getDescription() + "\n");
            fileWriter.close();
        } catch(IOException error) {
            System.out.println("Error finding file");
        }
    }

    private static void createFile() {
        try {
            file.createNewFile();
        } catch(IOException error) {
            System.out.println("Error creating file");
            error.printStackTrace();
        }
    }
}
