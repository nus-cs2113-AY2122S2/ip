import java.util.Scanner;

public class Duke {

    private static final String HORIZONTAL_LINE = "____________________________________________________________";
    private static final String EXIT_COMMAND = "bye";

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

        do {
            userInput = in.nextLine();
            System.out.println(HORIZONTAL_LINE);
            System.out.println(" " + userInput);
            System.out.println(HORIZONTAL_LINE);
        } while (!userInput.equalsIgnoreCase(EXIT_COMMAND));

        System.out.println(" Bye. Hope to see you again soon!");
        System.out.println(HORIZONTAL_LINE);
    }
}
