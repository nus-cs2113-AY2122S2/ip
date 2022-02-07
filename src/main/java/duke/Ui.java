package duke;

import java.util.Scanner;

import duke.task.Task;

public class Ui {

    private Scanner inputScanner;

    public Ui() {
        inputScanner = new Scanner(System.in);
    }

    public void welcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What can I do for you?");
    }

    public String getUserInput() {
        String userInput = inputScanner.nextLine();
        return userInput;
    }

    public void printDivider() {
        System.out.println("____________________________________________________________");
    }

    public void bye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public void printError(String message) {
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

}
