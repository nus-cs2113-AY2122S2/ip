package brave.storage;

import brave.data.Deadline;
import brave.data.Event;
import brave.data.Task;
import brave.data.Todo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class is used to manage all things related to File Management
 * Loading and saving the file, decoding and encoding the file format
 */

public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * When the program start, load the file from the storage
     *
     * @return the list of task read from file
     * @throws IOException If an output exception occurs
     */
    public ArrayList<Task> load() throws IOException {
        File dir = new File(String.valueOf(Path.of(System.getProperty("user.dir"), "data")));
        if (!dir.exists()) dir.mkdir();

        File f = new File(filePath); // create a File for the given file path
        if (!f.exists()) {
            System.out.println("file not exist, creating a new file!");
            f.createNewFile();
        }
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        ArrayList<Task> tasks = new ArrayList<>();
        decode(s, tasks);
        return tasks;
    }

    /**
     * Decodes the strings loaded from storage file into a list of tasks.
     *
     * @param s     scanner use to read the file
     * @param tasks the data loaded from storage file as a list of strings
     */
    private static void decode(Scanner s, ArrayList<Task> tasks) {
        int taskIndex = -1;
        while (s.hasNext()) {
            taskIndex++;
            Task selected_task;
            String[] details = s.nextLine().split(" , ", 0);
            switch (details[0]) {
            case "T":
                tasks.add(new Todo(details[2]));
                if (details[1].equals("1")) {
                    selected_task = tasks.get(taskIndex);
                    selected_task.markAsDone();
                }
                break;
            case "D":
                tasks.add(new Deadline(details[2], details[3]));
                if (details[1].equals("1")) {
                    selected_task = tasks.get(taskIndex);
                    selected_task.markAsDone();
                }
                break;
            case "E":
                tasks.add(new Event(details[2], details[3]));
                if (details[1].equals("1")) {
                    selected_task = tasks.get(taskIndex);
                    selected_task.markAsDone();
                }
                break;
            }
        }
    }

    /**
     * Save the list by writing on filepath that we provide
     *
     * @param tasks list of task that will be written to the file
     */
    public void save(ArrayList<Task> tasks) {
        encode(tasks, filePath);
    }

    /**
     * Encodes the list of tasks into a list of strings to be stored in storage file.
     *
     * @param tasks    the list of tasks to be stored
     * @param filePath filepath destination
     */
    private void encode(ArrayList<Task> tasks, String filePath) {
        try {
            FileWriter fw = new FileWriter(filePath);
            StringBuilder toFile = new StringBuilder();
            for (Task task : tasks) {
                toFile.append(task.getSaveFormat()).append("\n");
            }
            fw.write(toFile.toString());
            fw.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
