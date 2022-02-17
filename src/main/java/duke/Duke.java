package duke;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import exceptions.UnknownCommandException;

public class Duke {
    private static ArrayList<Task> tasks = new ArrayList<>();
    private static TaskFileManager taskFileManager = new TaskFileManager();

    public static void main(String[] args) {
        loadTaskFile();
        showWelcomeMessage();
        greet();
        converse();
        exit();
    }

    public static void printLine() {
        System.out.println("____________________________________________________________");
    }

    public static void showWelcomeMessage() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        System.out.println("Hello from\n" + logo);
    }

    public static void greet() {
        printLine();
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        printLine();
    }

    public static void exit() {
        printLine();
        System.out.println("Bye. Hope to see you again soon!");
        printLine();
    }


    public static void converse() {
        Scanner sc = new Scanner(System.in);
        String response = sc.nextLine();

        boolean isNotBye = !response.equals("bye");

        while (isNotBye) {
            try {
                runCommand(response);
            } catch (UnknownCommandException e) {
                System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            } catch (NullPointerException e) {
                System.out.println("☹ OOPS!!! Description cannot be empty!");
            }
            response = sc.nextLine();
            isNotBye = !response.equals("bye");
        }
    }

    //@@author quitejasper-reused
    //Reused from https://github.com/FaliciaOng/ip/blob/master/src/main/java/Duke.java
    //with minor modifications
    private static void updateToFile() {
        try {
            taskFileManager.saveTaskList("duke.txt", tasks);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void loadTaskFile() {
        try {
            taskFileManager.loadTaskList("duke.txt", tasks);
        } catch (IOException e) {
            System.out.println("--not valid file--");
        }
    }
    //@@author

    private static void runCommand(String response) throws UnknownCommandException {
        String[] words = response.split(" ", 2);
        String command = words[0];
        String detail = (words.length > 1) ? words[1] : null;

        boolean hasAddedTask = false;
        boolean hasUpdate = false;

        switch (command) {
        case "list":
            listTasks();
            break;
        case "mark":
            hasUpdate = markTask(response);
            break;
        case "unmark":
            unmarkTask(response);
            break;
        case "todo":
            addTask(new Todo(detail));
            hasAddedTask = true;
            break;
        case "deadline":
            int byIndex = detail.indexOf("/by");
            boolean isByPresent = byIndex != -1;
            if (isByPresent) {
                String by = detail.substring(byIndex + 3).trim();
                String deadlineDesc = detail.substring(0, byIndex).trim();
                System.out.println("deadlineDesc:__" + deadlineDesc + "__");
                addTask(new Deadline(deadlineDesc, by));
                hasAddedTask = true;
            } else {
                System.out.println("Sorry, missing /by argument...");
            }
            break;
        case "event":
            int atIndex = detail.indexOf("/at");
            boolean isAtPresent = atIndex != -1;
            if (isAtPresent) {
                String at = detail.substring(atIndex + 3).trim();
                String eventDesc = detail.substring(0, atIndex).trim();
                addTask(new Event(eventDesc, at));
                hasAddedTask = true;
            } else {
                System.out.println("Sorry, missing /at argument...");
            }
            break;
        default:
            throw new UnknownCommandException();
        }

        if (hasAddedTask || hasUpdate) {
            updateToFile();
        }
    }

    public static void listTasks() {
        printLine();

        for (int i = 0; i < tasks.size(); i++) {
            System.out.print(i + 1);
            System.out.println("." + tasks.get(i));
        }

        printLine();
    }

    public static void addTask(Task task) {
        printLine();

        tasks.add(task);
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        System.out.println("Now you have " + tasks.size() + " task(s) in the list.");

        printLine();
    }

    public static boolean markTask(String response) {
        printLine();

        try {
            String[] words = response.split(" ");
            int taskIndex = Integer.parseInt(words[1]);
            boolean isNotIndex = taskIndex > tasks.size() || taskIndex == 0 || taskIndex < 0;

            if (isNotIndex) {
                System.out.println("Sorry, seems like there's no such task with that index.");
                printLine();
                return false;
            } else {
                Task t = tasks.get(taskIndex - 1);
                boolean isNotDone = !t.isDone;

                if (isNotDone) {
                    t.markAsDone();
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println(t);
                } else {
                    System.out.println("Hmm, you've already marked this task.");
                }
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("☹ OOPS!!! You forgot to indicate an index!");
        }

        printLine();
        return true;
    }

    public static boolean unmarkTask(String response) {
        printLine();

        try {
            String[] words = response.split(" ");
            int taskIndex = Integer.parseInt(words[1]);
            boolean isNotIndex = taskIndex > tasks.size() || taskIndex == 0;

            if (isNotIndex) {
                System.out.println("Sorry, seems like there's no such task with that index.");
                printLine();
                return false;
            } else {
                Task t = tasks.get(taskIndex - 1);
                boolean isDone = t.isDone;

                if (isDone) {
                    t.markAsNotDone();
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println(t);
                } else {
                    System.out.println("Hmm, this task is already unmarked.");
                }
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("☹ OOPS!!! You forgot to indicate an index!");
        }

        printLine();
        return true;
    }
}
