package Misc;

import Tasks.Task;

import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    private Scanner myObj;

    public Ui() {
        myObj = new Scanner(System.in);
    }

    /**
     * Prints a line.
     */
    public static void showLine() {
        System.out.println("____________________________________________________________");
    }

    /**
     * Prints a message.
     * @param message The message.
     */
    public static void showMsg(String message) {
        showLine();
        System.out.println(message);
        showLine();
    }

    /**
     * Prints welcome message.
     */
    public void showWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);
        showMsg(" Hello! I'm Duke\n What can I do for you?");
    }

    /**
     * Prints bye message.
     */
    public void showBye() {
        showMsg(" Bye. Hope to see you again soon!");
    }

    /**
     * Prints loading error.
     */
    public void showLoadingError() {
        showMsg(" An error occurred when creating/loading save file");
    }

    /**
     * Prints error.
     * @param e Error message.
     */
    public void showError(String e) {
        showMsg(e);
    }

    /**
     * Prints task list.
     * @param array Tasks list.
     */
    public void showTaskList(ArrayList<Task> array) {
        StringBuilder msg = new StringBuilder(" Here are the tasks in your list:");
        for (int i = 0; i < array.size(); i++) {
            msg.append('\n').append(i + 1).append(".").append(array.get(i).toString());
        }
        showMsg(msg.toString());
    }

    /**
     * Prints add task notification.
     * @param array Tasks list.
     */
    public void showAddTaskNotification(ArrayList<Task> array) {
        String msg = " Got it. I've added this task:\n"
                + array.get(array.size() - 1).toString() + "\n"
                + " Now you have " + array.size() + " tasks in the list.";
        showMsg(msg);
    }

    /**
     * Prints delete task notification.
     * @param array Tasks list.
     * @param index Index to be deleted.
     */
    public void showDeleteTaskNotification(ArrayList<Task> array, int index) {
        String msg = " Noted. I've removed this task:\n"
                + array.get(index).toString() + "\n"
                + " Now you have " + (array.size() - 1) + " tasks in the list.";
        showMsg(msg);
    }

    /**
     * Prints mark task notification.
     * @param task The task.
     */
    public void showMarkTaskNotification(String task) {
        String msg = " Nice! I've marked this task as done:\n"
                + task;
        showMsg(msg);
    }

    /**
     * Prints unmark task notification.
     * @param task The task.
     */
    public void showUnmarkTaskNotification(String task) {
        String msg = " OK, I've marked this task as not done yet:\n"
                + task;
        showMsg(msg);
    }

    /**
     * Prints find task notification.
     * @param task The task.
     */
    public void showFindTaskNotification(ArrayList<Task> array, String task) {
        StringBuilder msg = new StringBuilder(" Here are the matching tasks in your list:");
        for (int i = 0; i < array.size(); i++) {
            if (array.get(i).toString().contains(task)) {
                msg.append('\n').append(i + 1).append(".").append(array.get(i).toString());
            }
        }
        showMsg(msg.toString());
    }


    /**
     * Reads the input.
     * @return Input as string.
     */
    public String readCommand() {
        return myObj.nextLine();
    }
}