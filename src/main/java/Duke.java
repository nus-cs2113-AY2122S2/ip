import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String line = "-----------------------------";
        Scanner in = new Scanner(System.in);
        String input;

        System.out.println("Hello from\n" + logo);
        System.out.println(line);
        System.out.println("Hello there! I'm Duke :D");
        System.out.println("What can I do for you?");
        System.out.println(line);

        while (true) {
            input = in.nextLine();
            if (input.equalsIgnoreCase("bye")) {
                System.out.println("Bye! Hope to see you soon :D");
                System.out.println(line);
                break;
            } else {
                System.out.println(input + System.lineSeparator() + line);
            }
        }
    }
}
