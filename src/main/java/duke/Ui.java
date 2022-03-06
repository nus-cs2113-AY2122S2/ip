package duke;

import java.util.Scanner;
import duke.task.Task;

public class Ui {

    private Scanner inputScanner;

    public Ui() {
        inputScanner = new Scanner(System.in);
    }

    public void showWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke!\n What can I do for you?");
    }


    public String getUserInput() {
        String userInput = inputScanner.nextLine();
        return userInput;
    }


    public void showLine() {
        System.out.println("____________________________________________________________");
    }


    public void showBye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public void showError(String message) {
        System.out.println(message);
    }


    public void printTask(Task task) {
        System.out.println(task.getTask());
    }


    public void printSize(int size) {
        System.out.println("Now you have " + size + " tasks in the list");
    }


    public void printMarkMessage(boolean isDone) {
        if (isDone) {
            System.out.println("Nice! I've marked this task as done:");
        } else {
            System.out.println("OK, I've marked this task as not done yet:");
        }
    }

    public void printAddMessage(Task task, int size) {
        System.out.println("Got it. I've added this task:");
        printTask(task);
        printSize(size);
    }

    public void printRemoveMessage(Task task, int size) {
        System.out.println("Noted. I've removed this task:");
        printTask(task);
        printSize(size);
    }

    public void printMatchingTasks(TaskList tasks, String searchTerm) {
        System.out.println("Here are the matching tasks in your list:");
        for (int i = 0; i < tasks.getSize(); i++) {
            Task task = tasks.getTask(i);
            if (task.getTask().contains(searchTerm)) {
                System.out.println(i + 1 + ". " + task.getTask());
            }
        }
    }


    public void printTaskList(TaskList tasks) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.getSize(); i++) {
            System.out.println(i + 1 + ". " + tasks.getTask(i).getTask());
        }
    }

}