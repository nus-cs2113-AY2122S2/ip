package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public void storeToFile(ArrayList<Task> store) {
        String text = "";
        for (int i = 0; i < store.size(); i++) {
            Task currentTask = store.get(i);
            text = text + currentTask.toString() + System.lineSeparator();
        }
        try {
            FileWriter fw = new FileWriter(filePath);
            fw.write(text);
            fw.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public ArrayList<Task> fileToStore() {
        ArrayList<Task> store = new ArrayList<>();
        File f = new File(filePath);
        try {
            Scanner s = new Scanner(f);
            while (s.hasNext()) {
                String currentLine = s.nextLine();
                String[] arrayElements = currentLine.split("\\|");
                String taskType = arrayElements[0].trim();
                Task newTask;
                switch (taskType) {
                case "T":
                    newTask = new ToDo(arrayElements[2].trim(), arrayElements[1].trim().equals("1"));
                    store.add(newTask);
                    newTask.getTask();
                    break;
                case "D":
                    newTask = new Deadline(arrayElements[2].trim(), arrayElements[1].trim().equals("1"),
                            arrayElements[3].trim());
                    store.add(newTask);
                    break;
                case "E":
                    newTask = new Event(arrayElements[2].trim(), arrayElements[1].trim().equals("1"),
                            arrayElements[3].trim());
                    store.add(newTask);
                    break;
                default:
                    break;
                }
            }
            return store;
        } catch (FileNotFoundException e) {
            return store;
        }
    }
}
