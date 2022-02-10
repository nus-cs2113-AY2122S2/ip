package Managers;

import java.util.Locale;
import java.util.Scanner;

import Components.Task;
import Exceptions.*;

import static Constants.BaoConstants.LOGO;
import static Constants.BaoConstants.LINE_BREAK;

public class Bao {
    private static Scanner in = new Scanner(System.in);

    private static void greet() {
        System.out.print(LINE_BREAK);
        System.out.println("You have" + System.lineSeparator()
                          + LOGO + System.lineSeparator()
                          + "\t\t\t\t\tat your service");
        System.out.println("Hello there! Bao here!" + System.lineSeparator()
                           + "How can I help?");
        System.out.print(LINE_BREAK);
    }

    private static void serveUser(){
        String userInput = in.nextLine();

        while (!userInput.equals("bye")) {
            try {
                if (userInput.equalsIgnoreCase("list")) {
                    listTasks();
                } else if (userInput.toLowerCase().startsWith ("mark")) {
                    markTask(userInput);
                } else if (userInput.toLowerCase().startsWith("unmark")) {
                    unmarkTask(userInput);
                } else if (userInput.toLowerCase().startsWith("todo")) {
                    addToDo(userInput);
                } else if (userInput.toLowerCase().startsWith("deadline")) {
                    addDeadline(userInput);
                } else if (userInput.toLowerCase().startsWith("event")) {
                    addEvent(userInput);
                } else {
                    throw new BaoUnknownCommand(userInput + " is an unknown command.");
                }
            } catch (BaoUnknownCommand e) {
                System.out.print(LINE_BREAK);
                System.out.println("OOPS!! I do not understand that.");
                System.out.print(LINE_BREAK);
            }
            userInput = in.nextLine();
        }
    }

    private static void farewell() {
        System.out.print(LINE_BREAK);
        System.out.println("Alright, goodbye. See you later alligator!");
        System.out.print(LINE_BREAK);
    }

    private static void addedTaskMessage(Task task) {
        System.out.print(LINE_BREAK);
        System.out.println("Yup yup yup, " + System.lineSeparator()
                          + task.toString() + "," + System.lineSeparator()
                          + "annnd there we go, it's been added!" + System.lineSeparator()
                          + "You have " + TaskManager.getNumTasks() + " tasks in the list.");
        System.out.print(LINE_BREAK);
    }

    private static void addToDo(String msg) {
        try{
            TaskManager.addToDo(msg);
            addedTaskMessage(TaskManager.getLastTask());
        } catch (NoTaskDescriptionException e) {
            System.out.println("You ain't fooling me with empty tasks!");
        } catch (MaxTaskException e) {
            System.out.println("Hey! Calm down, Charlie Brown. You've too many on your plate right now.");
        }
    }

    private static void addDeadline(String msg) {
        try {
            TaskManager.addDeadline(msg);
            addedTaskMessage(TaskManager.getLastTask());
        } catch (BadDateTimeFormatException e) {
            System.out.print(LINE_BREAK);
            System.out.println("Include a deadline with \"/by\".");
            System.out.print(LINE_BREAK);
        } catch (NoTaskDescriptionException e) {
            System.out.print(LINE_BREAK);
            System.out.println("You ain't fooling me with an empty task!");
            System.out.print(LINE_BREAK);
        } catch (NoDateTimeException e) {
            System.out.print(LINE_BREAK);
            System.out.println("Deadlines work better when there's a deadline!");
            System.out.print(LINE_BREAK);
        } catch (MaxTaskException e) {
            System.out.print(LINE_BREAK);
            System.out.println("Calm down, Charlie Brown! You have too much on your plate at the moment.");
            System.out.print(LINE_BREAK);
        }
    }

    private static void addEvent(String msg) {
        try {
            TaskManager.addEvent(msg);
            addedTaskMessage(TaskManager.getLastTask());
        } catch (BadDateTimeFormatException e) {
            System.out.print(LINE_BREAK);
            System.out.println("Include a date with \"/at\".");
            System.out.print(LINE_BREAK);
        } catch (NoTaskDescriptionException e) {
            System.out.print(LINE_BREAK);
            System.out.println("Need more details on this event, Boss!");
            System.out.print(LINE_BREAK);
        } catch (NoDateTimeException e) {
            System.out.print(LINE_BREAK);
            System.out.println("When's it happening?");
            System.out.print(LINE_BREAK);
        } catch (MaxTaskException e) {
            System.out.print(LINE_BREAK);
            System.out.println("Calm down, Charlie Brown! You have too much on your plate at the moment.");
            System.out.print(LINE_BREAK);
        }
    }

    private static void markTask(String msg) {
        try {
            System.out.print(LINE_BREAK);
            TaskManager.markTask(msg);
            System.out.println("Boom! Another task done already???");
            System.out.print(LINE_BREAK);
        } catch (NumberFormatException e) {
            System.out.println("So close! You just need to provide me the task number to mark.");
            System.out.print(LINE_BREAK);
        } catch (BadIndexException e) {
            System.out.println("I've checked and double checked. There is no such task.");
            listTasks();
        }
    }

    private static void unmarkTask(String msg) {
        try {
            System.out.print(LINE_BREAK);
            TaskManager.unmarkTask(msg);
            System.out.println("Oh oops, overlooked that one did we?");
            System.out.print(LINE_BREAK);
        } catch (NumberFormatException e) {
            System.out.println("Don't be embarrassed... What's the task number to unmark.");
            System.out.print(LINE_BREAK);
        } catch (BadIndexException e) {
            System.out.println("I've checked and double checked. There is no such task.");
            listTasks();
        }
    }

    private static void listTasks() {
        System.out.print(LINE_BREAK);
        System.out.println("Here are your tasks:");
        TaskManager.listTasks();
        System.out.print(LINE_BREAK);
    }

    public static void initiateBao() {
        greet();
        serveUser();
        farewell();
    }
}
