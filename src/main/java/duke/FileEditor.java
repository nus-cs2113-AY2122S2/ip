package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class FileEditor {

    private static final String FILE_PATH = "./duke.txt";

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
            System.out.println("Something is wrong with the typeOfTask");
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
            writeDataToFile(task, task.getTypeOfTask(), fileWriter);
        }
    }

    private static void saveDataAfterAdd(Task task, String typeOfTask) {
        try {
            FileWriter fileWriter = new FileWriter(FILE_PATH, true);
            writeDataToFile(task, typeOfTask, fileWriter);
            fileWriter.close();
        } catch(IOException error) {
            System.out.println("Error finding file");
        }
    }

    private static void writeDataToFile(Task task, String typeOfTask, FileWriter fileWriter) throws IOException {
        switch(typeOfTask) {
        case "todo":
            fileWriter.write(typeOfTask + "|" + task.isDone() + "|" + task.getDescription() + "\n");
            break;
        case "deadline":
            Deadline deadline = (Deadline) task;
            fileWriter.write(typeOfTask + "|" + task.isDone() + "|" + task.getDescription() +
                    "|/by " + deadline.getBy() + "\n");
            break;
        case "event":
            Event event = (Event) task;
            fileWriter.write(typeOfTask + "|" + task.isDone() + "|" + task.getDescription() +
                    "|/by " + event.getAt() + "\n");
            break;
        default:
            System.out.println("Error writing data to file");
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
