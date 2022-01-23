import java.util.Scanner;

public class Duke {
    public static void echoInput() {
        String line;
        Scanner in = new Scanner(System.in);
        do {
            System.out.println("Echoing Input, type bye to exit");
            line = in.nextLine();
            if (line.equalsIgnoreCase("bye")) {
                break;
            }
            System.out.println(line);
        } while (true);
    }

    public static void main(String[] args) {
        System.out.println("____________________________________________________________\n"
                + "Hello, nice to meet you. I'm Yae! (*^▽^*)\n"
                + "What can I do for you?\n"
                + "____________________________________________________________");
        echoInput();
        System.out.println("Goodbye, see you next time!");
    }
}
