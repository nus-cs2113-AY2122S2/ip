package main.java.duke.ui;

/**
 * The Ui class handles all print statements
 */

import java.util.ArrayList;
import main.java.duke.task.Task;
import main.java.duke.Duke;
import main.java.duke.exception.DukeException;

public class Ui {

    private final static String HORIZONTAL_LINE = "____________________________________________________________";

    public static void printFormat(String... args) {
        System.out.println(HORIZONTAL_LINE);
        for (String arg : args) {
            System.out.println(arg);
        }
        System.out.println(HORIZONTAL_LINE);
    }

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

    public static void printList() {
        System.out.println(HORIZONTAL_LINE);
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < Duke.tasks.size(); i++) {
            System.out.println(String.valueOf(i + 1) + "." + Duke.tasks.get(i).toString());
        }
        System.out.println(HORIZONTAL_LINE);
    }

    public static void printUnmark(int markInt, Task task) {
        printFormat("OK, I've marked this task as not done yet:", 
                task.toString());
    }

    public static void printMark(int markInt, Task task) {
        printFormat("Nice! I've marked this task as done:",
                task.toString());
    }

    public static void printDelete(Task task) {
        printFormat("Noted. I've removed this task:", task.toString(), 
                "Now you have " + String.valueOf(Duke.taskCounter) + " tasks in the list.");
    }

    public static void printTask(Task task) {
        printFormat("Got it. I've added this task:", 
                "  " + task.toString(),
                "Now you have " + String.valueOf(Duke.taskCounter) + " tasks in the list.");
    }

    public static void printCommand() {
        printFormat("List of valid commands:",
                "'list' - lists out all tasks and its details",
                "'mark X' - marks item X on the list as done",
                "   (X is a number) e.g. 'mark 3' marks item 3 on the list",
                "'unmark X' - unmarks item X on the list as done",
                "   (X is a number) e.g. 'unmark 3' unmarks item 3 on the list",
                "'todo X' - ToDos are tasks without specific deadlines",
                "   (X is a string) e.g. 'todo buy shampoo' adds the task 'buy shampoo' to the list",
                "'deadline X /by date time' - Deadlines are tasks that need to be done before a specific date/time",
                "   e.g. 'deadline math homework /by tues 2pm' adds a task with a deadline to the list",
                "'event X /at date time' - Events are tasks that start at a specific time and ends at a specific time",
                "   e.g. 'event project meeting /at sunday 8-10pm' adds a task with a time range");
    }

    public static void printBye() {
        printFormat("Bye. Hope to see you again soon!");
    }

    public static void printInvalid() {
        printFormat("OOPS!!! I'm sorry, but I don't know what that means :-(",
                "Please type in 'commands' if you are not sure of the commands");
    }

    public static void printError(DukeException e) {
        System.out.println(e.getMessage());
    }

}