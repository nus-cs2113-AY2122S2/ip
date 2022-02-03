import java.util.Scanner;
import java.util.Arrays;

public class Duke {

    private static final String HORIZONTAL_LINE = "____________________________________________________________";
    private static final String EXIT_COMMAND = "bye";
    private static final String LIST_COMMAND = "list";
    private static final String MARK_COMMAND = "mark";
    private static final String UNMARK_COMMAND = "unmark";

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
        TaskManager t = new TaskManager();

        String firstWord = "";
        String index;

        do {
            userInput = in.nextLine();
            if (userInput.contains(" ")) {
                firstWord = userInput.substring(0, userInput.indexOf(" "));
            }

            if (userInput.equalsIgnoreCase(LIST_COMMAND)) {
                System.out.println(HORIZONTAL_LINE);
                t.listTasks();
                System.out.println(HORIZONTAL_LINE);
            }
            else if (firstWord.equalsIgnoreCase(MARK_COMMAND)) {
                index = userInput.substring(userInput.indexOf(" ") + 1);
                t.markAsDone(Integer.parseInt(index) - 1);
                System.out.println(HORIZONTAL_LINE);
            }
            else if (firstWord.equalsIgnoreCase(UNMARK_COMMAND)) {
                index = userInput.substring(userInput.indexOf(" ") + 1);
                t.unmarkAsNotDone(Integer.parseInt(index) - 1);
                System.out.println(HORIZONTAL_LINE);
            }
            else if (userInput.equalsIgnoreCase(EXIT_COMMAND) == false) {
                System.out.println(HORIZONTAL_LINE);
                t.addTask(firstWord, userInput);
                System.out.println(HORIZONTAL_LINE);
            }
        } while (!userInput.equalsIgnoreCase(EXIT_COMMAND));

        System.out.println(HORIZONTAL_LINE);
        System.out.println(" Bye. Hope to see you again soon!");
        System.out.println(HORIZONTAL_LINE);
    }
}
