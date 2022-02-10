import Components.Task;

import Managers.TaskManager;

import java.util.Scanner;

public class Bao {
    private static Scanner in = new Scanner(System.in);
    private static final String LINE_BREAK = "______________________________________________________________________________________";

    private static void greet() {
        System.out.println(LINE_BREAK);
        System.out.println("Hello there! Bao here!" + System.lineSeparator()
                           + "How can I help?");
        System.out.println(LINE_BREAK);
    }

    private static void addedTaskMessage(Task task) {
        System.out.println(LINE_BREAK);
        System.out.println("Yup yup yup, " + System.lineSeparator()
                          + task.toString() + "," + System.lineSeparator()
                          + "annnd there we go, it's been added!" + System.lineSeparator()
                          + "You have " + TaskManager.getNumTasks() + " tasks in the list.");
        System.out.println(LINE_BREAK);
    }

    private static void addToDo(String msg) {
        addedTaskMessage(TaskManager.addToDo(msg));
    }

    private static void addDeadline(String msg) {
        addedTaskMessage(TaskManager.addDeadline(msg));
    }

    private static void addEvent(String msg) {
        addedTaskMessage(TaskManager.addEvent(msg));
    }

    private static void markTask(String msg) {
        System.out.println(LINE_BREAK);
        TaskManager.markTask(msg);
        System.out.println("Boom! Another task done already???");
        System.out.println(LINE_BREAK);
    }

    private static void unmarkTask(String msg) {
        System.out.println(LINE_BREAK);
        TaskManager.unmarkTask(msg);
        System.out.println("Oh oops, overlooked that one did we?");
        System.out.println(LINE_BREAK);
    }

    private static void listTasks() {
        System.out.println(LINE_BREAK);
        System.out.println("Here you go:");
        TaskManager.listTasks();
        System.out.println(LINE_BREAK);
    }

    private static void farewell() {
        System.out.println(LINE_BREAK);
        System.out.println("Alright, goodbye. See you later alligator!");
        System.out.println(LINE_BREAK);
    }

    public static void main(String[] args) {
        String userInput;
        String logo = "  ____       _       ___  " + System.lineSeparator()
                    + " | __ )     / \\     / _ \\" + System.lineSeparator()
                    + " |  _ \\    / _ \\   | | | |" + System.lineSeparator()
                    + " | |_) |  / ___ \\  | |_| |" + System.lineSeparator()
                    + " |____/  /_/   \\_\\  \\___/";

        System.out.println("Hello from" + System.lineSeparator() + logo);

        greet();

        userInput = in.nextLine();
        while (!userInput.equals("bye")) {
            if (userInput.equals("list")) {
                listTasks();
            } else if (userInput.startsWith("mark")) {
                markTask(userInput);
            } else if (userInput.startsWith("unmark")) {
                unmarkTask(userInput);
            } else if (userInput.startsWith("todo")) {
                addToDo(userInput);
            } else if (userInput.startsWith("deadline")) {
                addDeadline(userInput);
            } else if (userInput.startsWith("event")) {
                addEvent(userInput);
            }
            userInput = in.nextLine();
        }

        farewell();
    }
}
