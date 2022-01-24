import java.util.Scanner;

public class Duke {
    public static final String PURPLE_BOLD_BRIGHT = "\033[1;95m";
    public static final String RESET = "\033[0m";

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(PURPLE_BOLD_BRIGHT + logo + RESET);

        String command;
        Scanner in = new Scanner(System.in);
        System.out.println("Hello! I'm Duke" + System.lineSeparator() + "What can I do for you?\n");
        command = in.nextLine();

        while (!command.equals("bye")) {
            System.out.println(command + "\n");
            command = in.nextLine();
        }
        System.out.println("Bye! Hope to see you again soon!");
    }
}
