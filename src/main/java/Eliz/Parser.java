package Eliz;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.nio.file.FileSystemNotFoundException;
import java.util.ArrayList;

/** Represents the handling of commands from the user */
public class Parser {

    public static String line;
    public static TaskList tasks;
    public static String filePath;

    public Parser(String line, TaskList tasks, String filePath) {
        this.line = line;
        this.tasks = tasks;
        this.filePath = filePath;
    }

    /**
     * Prints tasks in the current task list.
     *
     * @param tasks The list of tasks stored.
     */
    public static void printTasks(TaskList tasks) {
        for (int i = 0; i < tasks.getSize(); i++) {
            int numToPrint = i + 1;
            System.out.println(numToPrint + ".[" + tasks.getTask(i).getTaskType() + "]" + "["
                    + tasks.getTask(i).getStatusIcon() + "] " + tasks.getTask(i).description.replace("|", ""));
        }
    }

    /**
     * Marks a task as done.
     * Prints out the task as marked to the user.
     *
     * @param line The index number of the task in the task list.
     * @param tasks The list of tasks stored.
     */
    public static void markATask(String line, TaskList tasks) {
        char taskNumChar = line.charAt(line.length() - 1);
        int taskNumInt = Character.getNumericValue(taskNumChar);
        tasks.getTask(taskNumInt - 1).setAsDone();
        System.out.println("[" + tasks.getTask(taskNumInt - 1).getTaskType() + "]" + "[X] "
                + tasks.getTask(taskNumInt - 1).description.replace("|",""));
    }

    /**
     * Marks a task as not done.
     * Prints out the task as not marked to the user
     *
     * @param line The index number of the task in the task list,
     * @param tasks The list of tasks stored.
     */
    public static void unmarkATask(String line, TaskList  tasks) {
        char taskNumChar = line.charAt(line.length() - 1);
        int taskNumInt = Character.getNumericValue(taskNumChar);
        tasks.getTask(taskNumInt - 1).setAsNotDone();
        System.out.println("[" + tasks.getTask(taskNumInt - 1).getTaskType() + "]" + "[ ] "
                + tasks.getTask(taskNumInt - 1).description.replace("|",""));
    }

    /**
     * Recognises if command is mark or unmark.
     * Makes the relevant method calls.
     *
     * @param line The first word in the command that is either mark or unmark.
     * @param tasks The list of tasks stored.
     */
    public static void markOrUnmark(String line, TaskList tasks) {
        if (line.contains("unmark")) {
            System.out.println("OK, I've marked this task as not done yet:");
            unmarkATask(line, tasks);
        } else {
            System.out.println("Nice! I've marked this task as done:");
            markATask(line, tasks);
        }
    }

    /**
     * Has the command list for users to follow upon keying in /help.
     * Prints out each command and their usage.
     */
    public static void displayCommands() {
        System.out.println("Commands to add tasks: ");
        System.out.println("todo [TASK_NAME]");
        System.out.println("event [TASK_NAME]");
        System.out.println("deadline [TASK_NAME]/[DATE](optional)");
        System.out.println(" ");
        System.out.println("Command to display list: ");
        System.out.println("list");
        System.out.println(" ");
        System.out.println("Commands for todo tasks: ");
        System.out.println("mark [TASK_LIST_NUMBER]");
        System.out.println("unmark [TASK_LIST_NUMBER]");
        System.out.println(" ");
        System.out.println("Commands to find tasks: ");
        System.out.println("find [TASK_NAME]");
        System.out.println(" ");
        System.out.println( "Exiting Eliz: ");
        System.out.println("Bye");
    }

    /**
     * Calls the necessary methods according to the given user input.
     * Deals with certain exceptions and wrong inputs.
     *
     * @throws ElizException If incorrect inputs are given by the user.
     * @throws FileSystemNotFoundException If the file to be written to is not found
     * @throws IOException If the input is not correctly read.
     */
    public static void getInput() throws ElizException, FileSystemNotFoundException, IOException {
        String[] breakTaskNames = line.split(" ", 2);
        if ((breakTaskNames.length < 2)){
            String firstWord = breakTaskNames[0];
            switch (firstWord) {
            case "list" : printTasks(tasks);
                break;
            case "todo":
            case "deadline":
            case "event":
                System.out.println("OOPS!!! The description of a " + firstWord + " cannot be empty.");
                throw new ElizException();
            case "/help":
                displayCommands();
                break;
            default: //for mark, default, other weird spelling words
                System.out.println("OOPS!!! I'm sorry but I do not understand what you mean");
                throw new ElizException();
            }
        } else { //length of breakTaskNames is 2
            //correct inputs for todo, event, deadline and delete
            String firstWord = breakTaskNames[0];
            switch (firstWord) {
            case "mark":
            case "unmark":
                markOrUnmark(line, tasks);
                break;
            case "delete":
                TaskList.delete(line, tasks);
                break;
            case "find":
                TaskList.findTask(line, tasks);
                break;
            case "todo":
            case "event":
            case "deadline":
                if (breakTaskNames[1] == null) {
                    System.out.println("OOPS!!! The description of a " + breakTaskNames[0] + " cannot be empty.");
                    throw new ElizException();
                } else {
                    Task currentTask = tasks.createTask(line);
                    tasks.addTask(currentTask);
                    System.out.println("Got it. I've added this task ");
                    System.out.println(currentTask.getDescription());
                    System.out.println("Now you have " + tasks.getSize() + " tasks in the list.");
                    Storage.appendToFile(filePath, currentTask.getTaskType() + "|"
                                    + currentTask.getStatusIcon() + "|"
                                    + currentTask.getDescription().replace("|","") + System.lineSeparator());
                }
                break;
            default: // for other weird spelling words
                System.out.println("OOPS!!! I'm sorry but I do not understand what you mean");
                throw new ElizException();
            }
        }
    }
}
