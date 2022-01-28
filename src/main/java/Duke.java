import java.util.Scanner;
import java.util.Arrays;

public class Duke {

    private static final String HORIZONTAL_LINE = "____________________________________________________________";
    private static final String EXIT_COMMAND = "bye";
    private static final String LIST_COMMAND = "list";
    private static final String MARK_COMMAND = "mark";
    private static final String UNMARK_COMMAND = "unmark";
    private static final int MAX_NUM_OF_TASKS = 100;

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        System.out.println("Hello from\n" + logo);
        System.out.println(HORIZONTAL_LINE);
        System.out.println(" Hello! I'm Duke");
        System.out.println(" What can I do for you?");
        System.out.println(HORIZONTAL_LINE);

        Scanner in = new Scanner(System.in);
        String userInput;
        String[] tasks = new String[MAX_NUM_OF_TASKS];
        int taskCount = 0;
        String firstWord = "";
        String index;
        String currentTask;

        do {
            userInput = in.nextLine();
            if (userInput.contains(" ")) {
                firstWord = userInput.substring(0, userInput.indexOf(" "));
            }

            if (userInput.equalsIgnoreCase(LIST_COMMAND)) {
                System.out.println(HORIZONTAL_LINE);
                System.out.println(" Here are the tasks in your list:");
                for (int i = 0; i < taskCount; i++) {
                    System.out.println(" " + (i + 1) + ". " + tasks[i]);
                }
                System.out.println(HORIZONTAL_LINE);
            }
            else if (firstWord.equalsIgnoreCase(MARK_COMMAND)) {
                index = userInput.substring(userInput.indexOf(" ") + 1);
                currentTask = tasks[Integer.parseInt(index) - 1].substring(4);
                tasks[Integer.parseInt(index) - 1] = "[X] " + currentTask;
                System.out.println(" Nice! I've marked this task as done:");
                System.out.println("   " + tasks[Integer.parseInt(index) - 1]);
                System.out.println(HORIZONTAL_LINE);
            }
            else if (firstWord.equalsIgnoreCase(UNMARK_COMMAND)) {
                index = userInput.substring(userInput.indexOf(" ") + 1);
                currentTask = tasks[Integer.parseInt(index) - 1].substring(4);
                tasks[Integer.parseInt(index) - 1] = "[ ] " + currentTask;
                System.out.println(" OK, I've marked this task as not done yet:");
                System.out.println("   " + tasks[Integer.parseInt(index) - 1]);
                System.out.println(HORIZONTAL_LINE);
            }
            else if (userInput.equalsIgnoreCase(EXIT_COMMAND) == false) {
                tasks[taskCount] = "[ ] " + userInput;
                System.out.println(HORIZONTAL_LINE);
                System.out.println(" added: " + userInput);
                System.out.println(HORIZONTAL_LINE);
                taskCount++;
            }
        } while (!userInput.equalsIgnoreCase(EXIT_COMMAND));

        System.out.println(HORIZONTAL_LINE);
        System.out.println(" Bye. Hope to see you again soon!");
        System.out.println(HORIZONTAL_LINE);
    }
}
