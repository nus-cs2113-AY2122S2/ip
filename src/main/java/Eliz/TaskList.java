package Eliz;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/** Contains the task list e.g has operations to add/delete tasks in the list */
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

    /**
     * Creates a Todo task.
     *
     * @param taskType The type of task keyed in by the user.
     * @param line The entire command given by the user
     * @return The new todo task to be included into the task list.
     */
    public static Task createTodo(String taskType, String line) {
        Todo newTodo = new Todo(line);
        return newTodo;
    }

    /**
     * Creates a deadline task.
     *
     * @param taskType The type of task keyed in by the user.
     * @param line The entire command given by the user
     * @return The new deadline task to be included into the task list.
     */
    public static Task createDeadline(String taskType, String line) {
        String newDescription = line;
        if (line.contains("/")) {
            String[] splitTwoSections = line.split("/", 2);
            newDescription = splitTwoSections[0] + "(" + splitTwoSections[1] + ")";
        }
        Deadline newDeadline = new Deadline(newDescription);
        return newDeadline;
    }

    /**
     * Creates an event task.
     *
     * @param taskType The type of task keyed in by the user.
     * @param line The entire command given by the user.
     * @return The new event task to be included into the task list.
     */
    public static Task createEvent(String taskType, String line) {
        String newDescription = line;
        if (line.contains("/")) {
            String[] splitTwoSections = line.split("/", 2);
            newDescription = splitTwoSections[0] + "(" + splitTwoSections[1] + ")";
        }
        Event newEvent = new Event(newDescription);
        return newEvent;
    }

    /**
     * Deletes the specified item with the corresponding index in the list.
     *
     * @param line The index of the task to be deleted in the list.
     * @param tasks The list of tasks stored.
     * @throws IOException If the input is not correctly read.
     */
    public static void delete(String line, TaskList tasks) throws IOException {
        char taskNumChar = line.charAt(line.length() - 1);
        int taskNumInt = Character.getNumericValue(taskNumChar);
        String deletedTask = tasks.getTask(taskNumInt - 1).getTaskType();
        String deletedTaskDescription = tasks.getTask(taskNumInt - 1).getDescription().replace("|","");
        tasks.removeTask(taskNumInt - 1);
        System.out.println("Noted I have deleted this task");
        System.out.println("[" + deletedTask + "]" + "[ ] " + deletedTaskDescription);
        System.out.println("You have " + tasks.getSize() + " tasks left in the list.");
        if (tasks.getSize() == 0) {
            Storage.writeToFile("listOfTasks.txt", "");
        } else {
            Storage.writeToFile("listOfTasks.txt", tasks.getTask(0).getTaskType() + "|"
                    + tasks.getTask(0).getStatusIcon() + "|"
                    + tasks.getTask(0).getDescription().replace("|","") + System.lineSeparator());
            for (int i = 1; i < tasks.getSize(); i++) {
                Storage.appendToFile("listOfTasks.txt", tasks.getTask(i).getTaskType() + "|"
                        + tasks.getTask(i).getStatusIcon() + "|"
                        + tasks.getTask(i).getDescription().replace("|","") + System.lineSeparator());
            }
        }
    }

    /**
     * Breaks down the commands from the user and calls the required methods.
     *
     * @param line Entire command as keyed in from the user.
     * @return The task to be added to the task list.
     */
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

    /**
     * When the find command is received, finds the tasks containing the input.
     * Prints all tasks that contain the input.
     *
     * @param line The input by the user that contains find.
     * @param tasks The task list stored.
     */
    public static void findTask(String line, TaskList tasks) {
        String[] namesForTask = line.split(" ", 2);
        ArrayList<String> outputArr = new ArrayList<>();
        for (int i = 0; i < tasks.getSize(); i++) {
            if (tasks.getTask(i).getDescription().contains(namesForTask[1])) {
                String output = i+1 + ".[" + tasks.getTask(i).getTaskType() + "]" + "[" + tasks.getTask(i).getStatusIcon()
                        + "] " + tasks.getTask(i).getDescription().replace("|","");
                outputArr.add(output);
            }
        }
        if (!outputArr.isEmpty()) {
            System.out.println("Here are the matching tasks in your list: ");
            for (int i = 0; i < outputArr.size(); i++) {
                System.out.println(outputArr.get(i));
            }
        } else {
            System.out.println("There are no matching tasks in your list: ");
        }
    }
}
