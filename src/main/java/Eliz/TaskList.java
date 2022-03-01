package Eliz;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/*Contains the task list e.g has operations to add/delete tasks in the list*/
public class TaskList {

    private static ArrayList<Task> listFromFile;

    public TaskList(ArrayList<Task> taskArrList) {
        this.listFromFile = taskArrList;
    };

    public TaskList() {

    }

    public static int getSize() {
        return listFromFile.size();
    }

    public static Task getTask(int index) {
        return listFromFile.get(index);
    }

    public static void removeTask(int index) {
        listFromFile.remove(index);
    }

    public static Task createTodo(String taskType, String line) {
        Todo newTodo = new Todo(line);
        return newTodo;
    }

    public static Task createDeadline(String taskType, String line) {
        String newDescription = line;
        if (line.contains("/")) {
            String[] splitTwoSections = line.split("/", 2);
            newDescription = splitTwoSections[0] + "(" + splitTwoSections[1] + ")";
        }
        Deadline newDeadline = new Deadline(newDescription);
        return newDeadline;
    }

    public static Task createEvent(String taskType, String line) {
        String newDescription = line;
        if (line.contains("/")) {
            String[] splitTwoSections = line.split("/", 2);
            newDescription = splitTwoSections[0] + "(" + splitTwoSections[1] + ")";
        }
        Event newEvent = new Event(newDescription);
        return newEvent;
    }

    public static void delete(String line, TaskList tasks) throws IOException {
        char taskNumChar = line.charAt(line.length() - 1);
        int taskNumInt = Character.getNumericValue(taskNumChar);
        String deletedTask = tasks.getTask(taskNumInt - 1).getTaskType();
        String deletedTaskDescription = tasks.getTask(taskNumInt - 1).getDescription().replace("|","");
        tasks.removeTask(taskNumInt - 1);
        System.out.println("Noted I have deleted this task");
        System.out.println("[" + deletedTask + "]" + "[ ] " + deletedTaskDescription);
        System.out.println("You have " + tasks.getSize() + " tasks left in the list.");
        Storage.writeToFile("src/main/java/listOfTasks.txt", tasks.getTask(0).getTaskType() + "|"
                + tasks.getTask(0).getStatusIcon() + "|"
                + tasks.getTask(0).getDescription().replace("|","") + System.lineSeparator());
        for (int i = 1; i < tasks.getSize(); i++) {
            Storage.appendToFile("src/main/java/listOfTasks.txt", tasks.getTask(i).getTaskType() + "|"
                    + tasks.getTask(i).getStatusIcon() + "|"
                    + tasks.getTask(i).getDescription().replace("|","") + System.lineSeparator());
        }

    }

    public static Task createTask(String line) {
        Task t;
        String[] splitTwoSections = line.split(" ", 2); //0: task type, 1: rest of the words
        String taskType = splitTwoSections[0];
        switch (taskType) {
        case "todo":
            t = createTodo(taskType, splitTwoSections[1]);
            break;
        case "deadline":
            t = createDeadline(taskType, splitTwoSections[1]);
            break;
        case "event":
            t = createEvent(taskType, splitTwoSections[1]);
            break;
        default:
            return null;
        }
        return t;
    }

    public static void addTask (Task task) {
        listFromFile.add(task);
    }
}
