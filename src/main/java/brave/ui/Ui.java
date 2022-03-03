package brave.ui;

import brave.data.Task;
import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    String input;
    private final Scanner in;
    public static final String LINE_SPLIT = "____________________________________________________________";
    boolean isExit;

    public Ui() {
        in = new Scanner(System.in);
        isExit = false;
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
        System.out.println(LINE_SPLIT);
        System.out.println("Hello I'm Brave");
        System.out.println("What can I do for you?");
        System.out.println(LINE_SPLIT);
    }

    public void showFarewellMessage() {
        System.out.println(LINE_SPLIT);
        System.out.println("Bye, Hope to see you again soon!");
        System.out.println(LINE_SPLIT);
    }

    public void printTaskList(ArrayList<Task> tasks) {
        if (tasks.size() == 0) {
            System.out.println("You currently have no task!");
            return;
        }
        System.out.println(LINE_SPLIT);
        for (Task task : tasks) {
            System.out.println(String.format("%d ", tasks.indexOf((task)) + 1) + task);
        }
        System.out.println(LINE_SPLIT);
    }
}
