import java.util.Scanner;

public class Duke {
    public static void printLine() {
        System.out.println("_______________"
                + "_______________"
                + "_______________"
                + "_______________");
    }

    public static void main(String[] args) {
        String input;
        Scanner in = new Scanner(System.in);
        printLine();
        System.out.print("Hello! I'm Bob\n" +
                "What can I do for you?\n");
        printLine();
        input = in.nextLine();
        while (!input.equals("bye")) {
            printLine();
            System.out.println(input);
            printLine();
            input = in.nextLine();
        }
        printLine();
        System.out.println("Bye. Hope to see you again soon!");
        printLine();
    }
}
