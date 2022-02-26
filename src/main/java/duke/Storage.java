package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Storage {
    public void readSaveData() throws FileNotFoundException {
        File loadData = new File("data/Duke.txt");
        Scanner loadDataScanner = new Scanner(loadData);
        while (loadDataScanner.hasNext()) {
            String line = loadDataScanner.nextLine();
            String[] words = line.split(" \\| ");
            String taskType = words[0];
            boolean isDone = words[1].equals("[X]");
            String taskDescription = words[2];
            String date = "";
            if (words.length > 3) {
                date = words[3];
            }
            loadData(taskType, isDone, taskDescription, date);
        }
        System.out.println("Loaded Save File");
    }

    public void loadData(String command, boolean isDone, String description, String date) {
        switch(command) {
        case "todo":
            TaskList.tasks.add(new ToDo(description));
            if (isDone) {
                TaskList.tasks.get(TaskList.tasks.size() - 1).setDone();
            }
            break;
        case "deadline":
            TaskList.tasks.add(new Deadline(description, date));
            if (isDone) {
                TaskList.tasks.get(TaskList.tasks.size() - 1).setDone();
            }
            break;
        case "event":
            TaskList.tasks.add(new Event(description, date));
            if (isDone) {
                TaskList.tasks.get(TaskList.tasks.size() - 1).setDone();
            }
            break;
        default:
            System.out.println("Cannot load line");
        }
    }

    public void saveData() throws IOException {
        createSaveDirectory();
        createSaveFile();
        FileWriter writer = new FileWriter("data/Duke.txt", false);
        for (Task task : TaskList.tasks) {
            writer.write(task.getTaskType() + " | " + task.getStatusIcon() + "| "
                    + task.getTaskDescription());
            if (task instanceof Deadline || task instanceof Event) {
                writer.write(" | " + task.getTime());
            }
            writer.write("\n");
        }
        writer.close();
    }

    public void createSaveDirectory() {
        File saveDirectory = new File("data");
        if (!saveDirectory.exists() && !saveDirectory.mkdir()) {
            System.out.println("Failed to create new directory.");
        }
    }

    public void createSaveFile() throws IOException {
        File saveFile = new File("data/Duke.txt");
        if (saveFile.createNewFile()){
            System.out.println("Save file created.");
        }
    }
}
