import java.util.Scanner;

public class Duke {

    public static Scanner sc = new Scanner(System.in);
    private static String HORIZONTAL_LINE = "____________________________________________________________";

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo + "\n"
                + HORIZONTAL_LINE + "\n"
                + "Hello! I'm Duke\n"
                + "What can I do for you?\n"
                + HORIZONTAL_LINE);

        while (sc.hasNext()) {
            String input = sc.next();
            if (input.equals("bye")) {
                break;
            } else {
                System.out.println(HORIZONTAL_LINE + "\n"
                        + input + "\n"
                        + HORIZONTAL_LINE);
            }
        }

        System.out.println(HORIZONTAL_LINE + "\n"
                + "Bye. Hope to see you again soon!\n"
                + HORIZONTAL_LINE);
    }
}