package duke;

import java.util.Scanner;

import duke.task.Task;

/**
 * Represents the class that interfaces with the user
 */
public class Ui {

    private Scanner inputScanner;

    public Ui() {
        inputScanner = new Scanner(System.in);
    }

    /**
     * Prints welcome message
     */
    public void welcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What can I do for you?");
    }

    /**
     * Takes in user input with scanner
     * 
     * @return String representation of input
     */
    public String getUserInput() {
        String userInput = inputScanner.nextLine();
        return userInput;
    }

    /**
     * Prints formatting divider
     */
    public void printDivider() {
        System.out.println("____________________________________________________________");
    }

    /**
     * Prints bye message when user exits the app
     */
    public void bye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Prints error message
     * 
     * @param message
     *            String representation of error message
     */
    public void printError(String message) {
        System.out.println(message);
    }

    /**
     * Prints string representation of task
     * 
     * @param task
     *            Task to be printed
     */
    public void printTask(Task task) {
        System.out.println(task.getTask());
    }

    /**
     * Prints the size of the task list
     * 
     * @param size
     *            Size of the task list
     */
    public void printSize(int size) {
        System.out.println("Now you have " + size + " tasks in the list");
    }

    /**
     * Prints message when user marks and unmarks done
     * 
     * @param isDone
     *            Task is done or not
     */
    public void printMarkMessage(boolean isDone) {
        if (isDone) {
            System.out.println("Nice! I've marked this task as done:");
        } else {
            System.out.println("OK, I've marked this task as not done yet:");
        }
    }

    /**
     * Prints the message when user adds a new task
     * 
     * @param task
     *            The task to be added
     * @param size
     *            The size of the task list
     */
    public void printAddMessage(Task task, int size) {
        System.out.println("Got it. I've added this task:");
        printTask(task);
        printSize(size);
    }

    /**
     * Prints the message when user removes a task
     * 
     * @param task
     *            Task to be removed
     * @param size
     *            The size of the task list
     */
    public void printRemoveMessage(Task task, int size) {
        System.out.println("Noted. I've removed this task:");
        printTask(task);
        printSize(size);
    }

    /**
     * Prints tasks matching a search term used in FindCommand
     * 
     * @param tasks
     *            Tasklist to search
     * @param searchTerm
     *            Search term to match
     */
    public void printMatchingTasks(TaskList tasks, String searchTerm) {
        System.out.println("Here are the matching tasks in your list:");
        for (int i = 0; i < tasks.getSize(); i++) {
            Task task = tasks.getTask(i);
            if (task.getTask().contains(searchTerm)) {
                System.out.println(i + 1 + ". " + task.getTask());
            }
        }
    }

    /**
     * Prints tasks in tasklist
     * 
     * @param tasks
     */
    public void printTaskList(TaskList tasks) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.getSize(); i++) {
            System.out.println(i + 1 + ". " + tasks.getTask(i).getTask());
        }
    }

}
