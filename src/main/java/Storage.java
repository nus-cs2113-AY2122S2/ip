import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private static final String DIRECTORY_NAME = "data";
    private static final String FILE_NAME = "Duke.txt";

    public static void storageSetup(TaskList tasks) {
        File directory = new File(DIRECTORY_NAME);
        File file = new File(DIRECTORY_NAME + "/" + FILE_NAME);
        if (!directory.exists()) {
            createDirectory(directory);
            createFile(file);
        } else if (!file.exists()) {
            createFile(file);
        }
        try {
            readTasksFromFile(file, tasks);
        } catch (FileNotFoundException e) {
            // Workaround for now until proper ui class is created
            Ui.printTextBetweenLines("\t File not found!");
        }
    }

    public static void createDirectory(File directory) {
        try {
            directory.mkdir();
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    public static void createFile(File file) {
        try {
            file.createNewFile();
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    public static void readTasksFromFile(File file, TaskList tasks) throws FileNotFoundException {
        Scanner scanner = new Scanner(file);
        while (scanner.hasNext()) {
            loadTaskFromFile(tasks, scanner.nextLine());
        }
    }

    public static void loadTaskFromFile(TaskList tasks, String line) {
        String[] content = line.split("\\|");
        boolean isCompleted = ("1".equals(content[1]));
        switch(content[0]) {
        case "T":
            String taskDescription = content[2];
            tasks.add(new Todo(taskDescription, isCompleted));
            break;
        case "D":
            taskDescription = content[2];
            String deadlineBy = content[3];
            tasks.add(new Deadline(taskDescription, isCompleted, deadlineBy));
            break;
        case "E":
            taskDescription = content[2];
            String eventAt = content[3];
            tasks.add(new Event(taskDescription, isCompleted, eventAt));
            break;
        default:
            break;
        }
    }

    public static void writeTasksToFile(TaskList tasks) {
        try {
            FileWriter file = new FileWriter(DIRECTORY_NAME + "/" + FILE_NAME);
            for (int i = 0; i < tasks.size(); i++) {
                file.write(tasks.get(i).getTaskCode() + "|" + (tasks.get(i).getCompletionStatus() ? "1" : "0")
                        + "|" + tasks.get(i).getTaskDescription() + "|" + tasks.get(i).getExtraInfo() + "\n");
            }
            file.close();
        } catch (IOException e) {
            Ui.printTextBetweenLines("\t File path error! Check again!");
        }
    }
}
