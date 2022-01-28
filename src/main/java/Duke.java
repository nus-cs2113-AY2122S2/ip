import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        final String horizontalLine = "____________________________________________________________\n";
        final String OPENING_MSG = horizontalLine
                + "Hello! I'm Duke\n"
                + "What can I do for you?\n"
                + horizontalLine;
        final String CLOSING_MSG = " Bye. Hope to see you again soon!\n"
                + horizontalLine;
        System.out.println(OPENING_MSG);

        while (true) {
            String userCommand = in.next();
            if (userCommand.equals("bye")) {
                break;
            }
            System.out.println(horizontalLine + userCommand + System.lineSeparator() + horizontalLine);
        }
        System.out.println(CLOSING_MSG);
    }
}
