package main.java.duke.ui;

/**
 * The Ui class handles all print statements. It contains all methods for printing 
 * the result of various commands.
 */

import java.util.ArrayList;
import main.java.duke.task.Task;
import main.java.duke.Duke;
import main.java.duke.exception.DukeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Ui {

    private final static String HORIZONTAL_LINE = "____________________________________" +
            "________________________";

    /**
     * This method is the main method used format outputs such that they appear within 2
     * horizontal lines.
     * 
     * @param args A vararg that takes in Strings that are to be printed on a new horizontal line
     *             between the 2 horizontal lines.
     */
    public static void printFormat(String... args) {
        System.out.println(HORIZONTAL_LINE);
        for (String arg : args) {
            System.out.println(arg);
        }
        System.out.println(HORIZONTAL_LINE);
    }

    /**
     * Prints out the DUKE ASCII art, and welcomes the user.
     */
    public static void printIntro() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        printFormat("Hello! I'm Duke",
                "What can I do for you?",
                "type 'commands' for the list of commands");
    }

    /**
     * Prints out all tasks description (and date) and numbers them.
     */
    public static void printList() {
        System.out.println(HORIZONTAL_LINE);
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < Duke.tasks.size(); i++) {
            System.out.println(String.valueOf(i + 1) + "." + Duke.tasks.get(i).toString());
        }
        System.out.println(HORIZONTAL_LINE);
    }

    /**
     * Searches for task description based on a keyword, and prints out the list of those tasks.
     * 
     * @param keyword A String that is used to search in the description of the tasks.
     */
    public static void printFind(String keyword) {
        Boolean foundTask = false;
        System.out.println(HORIZONTAL_LINE);
        System.out.println("Here are the matching tasks in your list:");
        for (int i = 0; i < Duke.tasks.size(); i++) {
            Task task = Duke.tasks.get(i);
            if (task.getDescription().toLowerCase().contains(keyword.toLowerCase())) {
                foundTask = true;
                System.out.println(String.valueOf(i + 1) + "." + task.toString());
            }
        }
        if (!foundTask) {
            System.out.println("There are no matching task in your list");
        }
        System.out.println(HORIZONTAL_LINE);
    }

    /**
     * Prints a sentence that lets the user know that the task has been unmarked.
     * 
     * @param markInt Integer number of the task in the list.
     * @param task The task that has been unmarked.
     */
    public static void printUnmark(int markInt, Task task) {
        printFormat("OK, I've marked this task as not done yet:", 
                task.toString());
    }

    /**
     * Prints a sentence that lets the user know that the task has been marked.
     * 
     * @param markInt Integer number of the task in the list.
     * @param task The task that has been marked.
     */
    public static void printMark(int markInt, Task task) {
        printFormat("Nice! I've marked this task as done:",
                task.toString());
    }
    
    /**
     * Prints a sentence that lets the user know the task has been deleted, and lets the 
     * user know how many tasks are left in the task list.
     * 
     * @param task Task that has been removed from the list.
     */
    public static void printDelete(Task task) {
        printFormat("Noted. I've removed this task:", task.toString(), 
                "Now you have " + String.valueOf(Duke.taskCounter) + " tasks in the list.");
    }

    /**
     * Method used to let the user know that the task has been added onto the list. It also shows
     * the total number of tasks in the list.
     * 
     * @param task Task that had been added into the list.
     */
    public static void printTask(Task task) {
        printFormat("Got it. I've added this task:", 
                "  " + task.toString(),
                "Now you have " + String.valueOf(Duke.taskCounter) + " tasks in the list.");
    }

    /**
     * Prints out possible commands the user can use.
     */
    public static void printCommand() {
        printFormat("List of valid commands:",
                "'list' - lists out all tasks and its details",
                "'mark NUMBER' - marks item NUMBER on the list as done",
                "   e.g. 'mark 3' marks item 3 on the list",
                "'unmark NUMBER' - unmarks item NUMBER on the list as done",
                "   e.g. 'unmark 3' unmarks item 3 on the list",
                "'delete NUMBER' - deletes item NUMBER on the list",
                "   e.g. 'delete 3' deletes item 3 on the list",
                "'find KEYWORD' - lists items in the list that contain KEYWORD",
                "   e.g. 'find book' lists items in the list that contain 'book'",
                "'todo DESC' - ToDos are tasks without specific deadlines",
                "   e.g. 'todo buy shampoo' adds the task 'buy shampoo' to the list",
                "'deadline DESC /by DD/MM/YYYY TIME' - Deadlines are tasks that need to" +
                        "be done before a specific date and time in 24-hour format",
                "   e.g. 'deadline math homework /by 2/3/2022 1400' adds a " +
                        "task with deadline Mar 2 2022 1400 to the list",
                "'event DESC /at DD/MM/YYYY TIME /to DD/MM/YYYY TIME' - Events are tasks that" +
                        "start at a specific date and time and ends at a specific date and time",
                "   e.g. 'event project meeting /at 3/3/2022 0900' /to 4/3/2022 1800 " +
                        "adds a task with a time range",
                "'check date DD/MM/YYYY' - checks if there are tasks due or occuring" +
                        " on that date",
                "   e.g. 'check date 04/03/2022' - checks if anything is on 04/03/2022",
                "'bye' - saves changes and closes Duke");
    }

    /**
     * Prints out tasks that are due or happening on a specific date.
     * 
     * @param localDate The date which the user wants to know if there is anything happening.
     */
    public static void printCheckDate(LocalDate localDate) {
        System.out.println(HORIZONTAL_LINE);
        System.out.println("Here are the tasks on " + 
                localDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")).toString() + ":");
        for (int i = 0; i < Duke.tasks.size(); i++) {
            Task task = Duke.tasks.get(i);
            if (isOnDate(task, localDate)) {
                System.out.println(String.valueOf(i + 1) + "." + task.toString());
            }
        }
        System.out.println(HORIZONTAL_LINE);
    }

    /**
     * Method used to check if a task is due or happening on a specific date.
     * 
     * @param task The task that is being checked.
     * @param localDate The date that the task is being checked on.
     * @return A boolean value whether the task is happening on the date.
     */
    private static boolean isOnDate(Task task, LocalDate localDate) {
        if (task.getType().equals("D")) {
            if (localDate.equals(task.getStartDate())) {
                return true;
            }
        } else if (task.getType().equals("E")) {
            if (localDate.equals(task.getStartDate()) || localDate.equals(task.getEndDate()) ||
            (localDate.isAfter(task.getStartDate()) && localDate.isBefore(task.getEndDate()))) {
                return true;
            }
        }
        return false;
    }

    /**
     * Prints out a goodbye before shutting the bot down.
     */
    public static void printBye() {
        printFormat("Bye. Hope to see you again soon!");
    }

    /**
     * Prints out a message to let the user know his command was invalid, and suggests the user
     * to use the 'command' command if the user is unsure of commands availiable.
     */
    public static void printInvalid() {
        printFormat("OOPS!!! I'm sorry, but I don't know what that means :-(",
                "Please type in 'commands' if you are not sure of the commands");
    }

    /**
     * Method used to print messages from Exceptions. Usually indicates what the user did wrong.
     * 
     * @param e A DukeException that was thrown during processing of user input.
     */
    public static void printError(DukeException e) {
        System.out.println(e.getMessage());
    }

}