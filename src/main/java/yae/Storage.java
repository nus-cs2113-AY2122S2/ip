package yae;

import yae.task.Deadline;
import yae.task.Event;
import yae.task.Task;
import yae.task.ToDo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Handles saving and loading of data.
 */
public class Storage {

    public static final String PATH = "data/Yae.txt";

    /**
     * Reads save data from text file.
     *
     * @throws FileNotFoundException If save file is not found
     */
    public void readSaveData() throws FileNotFoundException {
        File loadData = new File(PATH);
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

    /**
     * Loads save data into the arraylist.
     *
     * @param command Type of task
     * @param isDone Whether task is done
     * @param description Description of task
     * @param date Date of task
     */
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

    /**
     * Saves tasks into a text file.
     *
     * @throws IOException If IO operation fails
     */
    public void saveData() throws IOException {
        createSaveDirectory();
        createSaveFile();
        FileWriter writer = new FileWriter(PATH, false);
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

    /**
     * Creates new save directory.
     */
    public void createSaveDirectory() {
        File saveDirectory = new File("data");
        if (!saveDirectory.exists() && !saveDirectory.mkdir()) {
            System.out.println("Failed to create new directory.");
        }
    }

    /**
     * Creates new text save file.
     *
     * @throws IOException If IO operation fails
     */
    public void createSaveFile() throws IOException {
        File saveFile = new File(PATH);
        if (saveFile.createNewFile()){
            System.out.println("Save file created.");
        }
    }
}
