package baymax.ui;

import baymax.data.Task;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * class for mange UI (output of baymax)
 * give response and guide to the user
 */
public class Ui {
    String input;
    private final Scanner in;
    public static final String horiLine = "_____________________________________________";
    boolean isBye;

    public Ui() {
        in = new Scanner(System.in);
        isBye = false;
    }

    public String getUserCommand() {
        input = in.nextLine();
        return input;
    }

    public void showLoadingError(String message) {
        System.out.println(" You have error in loading data. Error message:");
        System.out.println("" + message);
    }

    public void displayWelcomeMessage() {
        System.out.println(horiLine);
        String greeting = "  Hello, I'm Baymax.\n"+
                "  Your personal task managing companion. \n" +
                "  What can I do for you? ";
        System.out.println(greeting);
        this.helpMessage();
    }

    public void helpMessage(){
        System.out.println(horiLine);
        String help = "  Add three types of tasks: event, deadline, task \n"+
                "  \tin the form of <task_type> <description> \n" +
                "  \tto add deadline time: append \"'/by MM dd yyyy\" \n" +
                "  \tto add event time: append \"'/at MM dd yyyy\" \n" +
                "  Use list method to see all the tasks \n" +
                "  Use mark and unmark methods with task index to set or unset the tasks as done \n" +
                "  Use find method with description to search specific tasks \n" +
                "  Use delete method with task index to delete certain task \n" +
                "  Let's start!!! ";
        System.out.println(help);
        System.out.println(horiLine);
    }

    public void showByeMessage() {
        System.out.println(horiLine);
        System.out.println("Bye, Hope to see you again soon!");
        System.out.println(horiLine);
    }

    public void displayErrorMessage() {
        System.out.println(horiLine);
        System.out.println("Error occur: cannot find this command.\n"+
                "Please re-enter your command.");
        System.out.println(horiLine);
    }

    public void printTaskList(ArrayList<Task> tasks) {
        System.out.println(horiLine);
        if (tasks.size() == 0) {
            System.out.println("There's no tasks found! Let's add more tasks");
            System.out.println(horiLine);
            return;
        }
        System.out.println("Here are the tasks in your list:");
        for (Task task : tasks) {
            System.out.println(String.format("%d ", tasks.indexOf((task)) + 1) + task);
        }
        System.out.println(horiLine);
    }

    public void DateExceptionMessage() {
        System.out.println(horiLine);
        System.out.println(" ☹ OOPS!!! Please re-enter the date in format: MM dd yyyy");
        System.out.println(horiLine);
    }

    public void DescripEmptyExceptionMessage() {
        System.out.println(horiLine);
        System.out.println(" ☹ OOPS!!! The description or timestamp of the task cannot be empty.");
        System.out.println(horiLine);
    }

    public void ArrayIndexOutOfBoundsExceptionMessage() {
        System.out.println(horiLine);
        System.out.println("☹ OOPS!!!Please input an integer for task index.");
        System.out.println(horiLine);
    }

    public void NumberFormatExceptionMessage() {
        System.out.println(horiLine);
        System.out.println("☹ OOPS!!!Please put in an integer value");
        System.out.println(horiLine);
    }

    public void IndexOutOfBoundsExceptionMessage() {
        System.out.println("☹ OOPS!!!Entered index is out of bounds. Please redo it.");
        System.out.println(horiLine);
    }

}
