package duke.storage;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import java.io.FileWriter;
import java.io.IOException;


public class FileWriterForAdd extends Storage {

    public static void saveDataAfterAdd(Task task) {
        try {
            FileWriter fileWriter = new FileWriter(FILE_PATH, true);
            writeDataToFile(task, task.getTypeOfTask(), fileWriter);
            fileWriter.close();
        } catch(IOException error) {
            System.out.println("Error finding file");
        }
    }

    public static void writeDataToFile(Task task, String typeOfTask, FileWriter fileWriter) throws IOException {
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
                    "|/at " + event.getAt() + "\n");
            break;
        default:
            System.out.println("Error writing data to file");
        }
    }

}
