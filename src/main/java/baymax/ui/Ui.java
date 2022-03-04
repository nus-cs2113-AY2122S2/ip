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
                "  What can I do for you? \n";
        System.out.println(greeting);
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
            return;
        }
        System.out.println("Here are the tasks in your list:");
        for (Task task : tasks) {
            System.out.println(String.format("%d ", tasks.indexOf((task)) + 1) + task);
        }
        System.out.println(horiLine);
    }

}
