import java.util.Scanner;
import java.util.Arrays;

public class Duke {

    private static final String HORIZONTAL_LINE = "____________________________________________________________";
    private static final String EXIT_COMMAND = "bye";
    private static final String LIST_COMMAND = "list";
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

        do {
            userInput = in.nextLine();

            if (userInput.equalsIgnoreCase(LIST_COMMAND)) {
                System.out.println(HORIZONTAL_LINE);
                for (int i = 0; i < taskCount; i++) {
                    System.out.println(" " + (i + 1) + ". " + tasks[i]);
                }
                System.out.println(HORIZONTAL_LINE);
            }
            else if (userInput.equalsIgnoreCase(EXIT_COMMAND) == false) {
                tasks[taskCount] = userInput;
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
