import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Manages the data or format stored in the file when saving,
 * loading and creating wish task.
 */
public class TaskFileManager {
    private static final String COMPLETED_TASK = "1";
    private static final String SEPARATOR = "\\|";

    public void loadTaskList(String fileName, ArrayList<Task> taskList) throws IOException {
        File file = getFile(fileName);
        Scanner scan = new Scanner(file);
        while (scan.hasNext()) {
            String line = scan.nextLine();
            String[] splitData = line.split(SEPARATOR);
            String description = splitData[2].trim();
            boolean isCompleted = splitData[1].trim().equals(COMPLETED_TASK);
            Task task = null;
            switch (splitData[0].trim()) {
            case "T":
                task = new Todo(description);
                break;
            case "E":
                String eventAt = splitData[3].trim();
                task = new Event(description, eventAt);
                break;
            case "D":
                String deadlineBy = splitData[3].trim();
                task = new Deadline(description, deadlineBy);
                break;
            }
            if (task != null) {
                task.setCompletedNoUpdateRequired(isCompleted);
                taskList.add(task);
            } else {
                System.out.println("--not valid--");
            }
        }
    }

    private File getFile(String fileName) throws IOException {
        File file = new File("data" + File.separator + fileName);
        if (!file.exists()) {
            file.getParentFile().mkdirs();
            file.createNewFile();
        }
        return file;
    }

    public void saveTaskList(String fileName, ArrayList<Task> taskList) throws IOException {
        File file = getFile(fileName);
        FileWriter fileWriter = new FileWriter("data/" + fileName);
        for (Task a : taskList) {
            fileWriter.write(a.toFileString() + System.lineSeparator());
        }
        fileWriter.close();
    }
}
