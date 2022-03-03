package main.java.duke.ui;

/**
 * The Ui class handles all print statements
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

    public static void printFind(String keyword) {
        Boolean foundTask = false;
        System.out.println(HORIZONTAL_LINE);
        System.out.println("Here are the matching tasks in your list:");
        for (int i = 0; i < Duke.tasks.size(); i++) {
            Task task = Duke.tasks.get(i);
            if (task.toString().contains(keyword)) {
                foundTask = true;
                System.out.println(String.valueOf(i + 1) + "." + task.toString());
            }
        }
        if (!foundTask) {
            System.out.println("There are no matching task in your list");
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
                "'delete X' - deletes item X on the list",
                "   (X is a number) e.g. 'delete 3' deletes item 3 on the list",
                "'find X - lists items in the list that contain the keyword X",
                "   (X is a string) e.g. 'find book' lists items in the list that contain 'book'",
                "'todo X' - ToDos are tasks without specific deadlines",
                "   (X is a string) e.g. 'todo buy shampoo' adds the task 'buy shampoo' to the list",
                "'deadline X /by DD/MM/YYYY TIME' - Deadlines are tasks that need to" +
                " be done before a specific date and time in 24-hour format",
                "   e.g. 'deadline math homework /by 2/3/2022 1400' adds a " +
                        "task with deadline Mar 2 2022 1400 to the list",
                "'event X /at DD/MM/YYYY TIME /to DD/MM/YYYY TIME' - Events are tasks that start" + 
                        " at a specific date and time and ends at a specific date and time",
                "   e.g. 'event project meeting /at 3/3/2022 0900' /to 4/3/2022 1800 " +
                        "adds a task with a time range");
    }

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