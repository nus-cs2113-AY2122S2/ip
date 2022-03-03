package brave.storage;

import brave.data.Deadline;
import brave.data.Event;
import brave.data.Task;
import brave.data.Todo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> load() throws IOException {
        File f = new File(filePath); // create a File for the given file path
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        ArrayList<Task> tasks = new ArrayList<>();
        decode(s, tasks);
        return tasks;
    }

    public static void decode(Scanner s, ArrayList<Task> tasks) {
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

    public void save(ArrayList<Task> tasks) throws IOException {
        encode(tasks, filePath);
    }

    public static void encode(ArrayList<Task> tasks, String filePath) {
        try {
            FileWriter fw = new FileWriter(filePath);
            StringBuilder toFile = new StringBuilder();
            for (Task task : tasks) {
                toFile.append(task.getSaveFormat()).append("\n");
            }
            fw.write(toFile.toString());
            fw.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
