package brave.ui;

import brave.data.Task;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *  Handles the UI of the application
 *  Handles interaction with the user
 */
public class Ui {
    String input;
    private final Scanner in;
    public static final String LINE_OPENER = "-*|Brave|*------------------------------------";
    public static final String LINE_CLOSER = "----------------------------------------------";
    public static final int DELETE_INDEX_OFFSET = 1;

    public Ui() {
        in = new Scanner(System.in);
    }

    public String getUserCommand() {
        input = in.nextLine();
        return input;
    }

    public void showLoadingError(String message) {
        System.out.println("\t Error: Failed to load data.");
        System.out.println("\t " + message);
    }

    public void showWelcomeMessage() {
        System.out.println(LINE_OPENER);
        System.out.println("Greetings from");
        System.out.println("  ____                        _ \n" +
                " | __ ) _ __ __ ___   _____  | |\n" +
                " |  _ \\| '__/ _` \\ \\ / / _ \\ | |\n" +
                " | |_) | | | (_| |\\ V /  __/ |_|\n" +
                " |____/|_|  \\__,_| \\_/ \\___| (_)\n");
        System.out.println("What can I do for you?");
        System.out.println(LINE_CLOSER);
    }

    public void showFarewellMessage() {
        System.out.println(LINE_OPENER);
        System.out.println("Bye, Hope to see you again soon!");
        System.out.println(LINE_CLOSER);
    }

    public void printTaskList(ArrayList<Task> tasks) {
        if (tasks.size() == 0) {
            System.out.println("You currently have no task!");
            return;
        }
        System.out.println(LINE_OPENER);
        for (Task task : tasks) {
            System.out.println(String.format("%d ", tasks.indexOf((task)) + 1) + task);
        }
        System.out.println(LINE_CLOSER);
    }

    public void showAddTaskMessage(Task task, int size) {
        System.out.println(LINE_OPENER);
        System.out.println("Okay! I have added task below!");
        System.out.println(task);
        System.out.println("You currently have " + size + " task in the list");
        System.out.println(LINE_CLOSER);
    }

    public void showDeleteTaskMessage(Task task, int size) {
        System.out.println(LINE_OPENER);
        System.out.println("I managed to delete task below!");
        System.out.println(task);
        System.out.println("You now have " + (size-DELETE_INDEX_OFFSET) + " task remaining");
        System.out.println(LINE_CLOSER);
    }

    public void showMarkTaskMessage(Task task) {
        System.out.println(LINE_OPENER);
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(task);
        System.out.println(LINE_CLOSER);
    }

    public void showUnmarkTaskMessage(Task task) {
        System.out.println(LINE_OPENER);
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(task);
        System.out.println(LINE_CLOSER);
    }
}
