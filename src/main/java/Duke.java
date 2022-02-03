import java.util.Scanner;

public class Duke {

    private static Scanner sc = new Scanner(System.in);
    private static String[] list = new String[100];
    private static int stringCounter = 0;
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
            String input = sc.nextLine();
            if (input.equals("bye")) {
                break;
            } else if (input.equals("list")) {
                System.out.println(HORIZONTAL_LINE);
                for (int i = 0; i < list.length; i++) {
                    if (list[i] != null) {
                        System.out.println(String.valueOf(i + 1) + ". " + list[i]);
                    } else {
                        System.out.println(HORIZONTAL_LINE);
                        break;
                    }
                }
            } else {
                list[stringCounter] = input;
                stringCounter++;
                System.out.println(HORIZONTAL_LINE + "\n"
                        + "added: " + input + "\n"
                        + HORIZONTAL_LINE);
            }
        }

        System.out.println(HORIZONTAL_LINE + "\n"
                + "Bye. Hope to see you again soon!\n"
                + HORIZONTAL_LINE);
    }
}