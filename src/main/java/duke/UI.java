package duke;

import duke.task.Task;

import java.util.Scanner;

public class UI {
    private Scanner inputScanner;

    public UI() {
        inputScanner = new Scanner(System.in);
    }

    public void welcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello I'm\n" + logo);
        System.out.println("What may I do for you?");
    }
    public String getInput() {
        return inputScanner.nextLine();
    }

    public void printTask(Task task) {
        System.out.println(task.getTask());
    }

    public void printSize(int size) {
        System.out.println("Now you have " + size + " tasks in the list");
    }

    public void printMarkMessage(boolean isDone) {
        if (isDone) {
            System.out.println("Well done! I've marked this task as done:");
        } else {
            System.out.println("OK, I've marked this task as not done yet:");
        }
    }

    public void printAddMessage(Task task, int size) {
        System.out.println("Got it. I've added this task:");
        printTask(task);
        printSize(size);
    }

    public void lineBreak() {
        System.out.println("-----------------------------------------------");
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

    public void printError(String message) {
        System.out.println(message);
    }

    public void printTaskList(TaskList tasks) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.getSize(); i++) {
            System.out.println(i + 1 + ". " + tasks.getTask(i).getTask());
        }
    }

    public void printRemoveMessage(Task task, int size) {
        System.out.println("Task "+task+" has been removed.");
        System.out.println("You have "+size+" item remaining in the list.");
    }

    public void bye() {
        System.out.println("Bye, see you again.");
    }
}
