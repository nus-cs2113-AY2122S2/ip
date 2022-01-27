import java.util.Scanner;

public class Duke {

    public static void greetings() {
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
    }

    public static void echo(String message) {
        System.out.println(message);
    }

    public static void exits() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public static void main(String[] args) {
        int itemNumber = 0;
        String[] listArray = new String[100];
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);
        greetings();
        boolean isLoop = true;
        while (isLoop) {
            Scanner in = new Scanner(System.in);
            String message = in.nextLine();
            String messageLowerCase = message.toLowerCase();
            if (messageLowerCase.equals("bye")) {
                exits();
                isLoop = false;
            } else {
                echo(message);
            }
        }
    }
}
