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
import duke.task.Todo;

/**
 * Represents the controller to parse and write to a save file for tasks
 */
public class Storage {
    private String filePath;


    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public void convertListToFile(ArrayList<Task> list) {
        String text = "";
        for (int i = 0; i < list.size(); i++) {
            Task currentTask = list.get(i);
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

    public ArrayList<Task> convertFileToList() {
        ArrayList<Task> list = new ArrayList<>();
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
                        newTask = convertTodo(arrayElements);
                        list.add(newTask);
                        break;
                    case "D":
                        newTask = convertDeadline(arrayElements);
                        list.add(newTask);
                        break;
                    case "E":
                        newTask = convertEvent(arrayElements);
                        list.add(newTask);
                        break;
                    default:
                        break;
                }
            }
            return list;
        } catch (FileNotFoundException e) {
            return list;
        }
    }

    private String getDescription(String[] arrayElements) {
        int descriptionIndex = 2;
        return arrayElements[descriptionIndex].trim();
    }

    private boolean getIsDone(String[] arrayElements) {
        int booleanIndex = 1;
        return arrayElements[booleanIndex].trim().equals("1");
    }

    private String getTime(String[] arrayElements) {
        int timeIndex = 3;
        return arrayElements[timeIndex].trim();
    }

    private Todo convertTodo(String[] arrayElements) {
        return new Todo(getDescription(arrayElements), getIsDone(arrayElements));
    }

    private Deadline convertDeadline(String[] arrayElements) {
        return new Deadline(getDescription(arrayElements), getIsDone(arrayElements), getTime(arrayElements));
    }

    private Event convertEvent(String[] arrayElements) {
        return new Event(getDescription(arrayElements), getIsDone(arrayElements), getTime(arrayElements));
    }
}