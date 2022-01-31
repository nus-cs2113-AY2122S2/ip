import java.util.Scanner;
import java.util.ArrayList;

import static java.lang.Integer.parseInt;

public class Marites {

    private static final Scanner SCANNER = new Scanner(System.in);
    private static final ArrayList<Task> tasks = new ArrayList<>();

    private static void printSeparator() {
        System.out.println("========================================");
    }

    private static void printIntroduction() {
        // Found in https://emojicombos.com/kaomoji
        String logo = "(งツ)ว";

        System.out.println(logo);
        System.out.println("Hi, I'm Marites! I've heard so many things about you!");
        System.out.println("I have a lot of stories to share, but first, how can I help you?");
        printSeparator();
    }

    public static void main(String[] args) {
        printIntroduction();
        while (true) {
            String userInput = SCANNER.nextLine();
            String commandFeedback = processUserCommand(userInput);
            showFeedback(commandFeedback);
        }
    }

    private static String processUserCommand(String userInput) {
        String[] tokens = userInput.split("\\s");
        if (userInput.equals("bye")) {
            return executeExit();
        } else if (userInput.equals("list")) {
            return executeListTasks();
        } else if (tokens[0].equals("mark")) {
            return executeSetTaskStatus(tokens, true);
        } else if (tokens[0].equals("unmark")) {
            return executeSetTaskStatus(tokens, false);
        } else {
            return executeAddTask(userInput);
        }
    }

    /*
        The following methods take in either a
            - String, denoting the full line of user input, or
            - a String[], denoting the tokenized version of the user input.
        They return a String, denoting the feedback after executing the command.
     */

    private static String executeListTasks() {
        StringBuilder taskList = new StringBuilder();
        for (int i = 1; i <= tasks.size(); ++i) {
            String task = String.format("%d. %s%n", i, tasks.get(i-1));
            taskList.append(task);
        }
        return taskList.toString();
    }

    private static String executeAddTask(String userInput) {
        tasks.add(new Task(userInput));
        return String.format("added: %s%n", userInput);
    }

    private static String executeSetTaskStatus(String[] tokens, boolean isDone) {
        int taskIndex = parseInt(tokens[1]);
        Task taskToMark = tasks.get(taskIndex - 1);
        taskToMark.setDone(isDone);
        String message = (isDone ? "Good job on getting this done!" :
                "Okay, I've marked this as not yet done:");
        return String.format(message + "%n    %s%n", taskToMark);
    }

    private static String executeExit() {
        printExitMessage();
        System.exit(0);
        return "";
    }

    private static void printExitMessage() {
        System.out.println("See you next time!");
        printSeparator();
    }

    private static void showFeedback(String feedback) {
        printSeparator();
        System.out.print(feedback);
        printSeparator();
    }
}
