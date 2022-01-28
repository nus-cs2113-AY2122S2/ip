import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println("____________________________________________________________"
                + "\nHello! I'm Dook!"
                + "\nWhat can I do for you?"
                + "\n____________________________________________________________");
        Scanner scannerInput = new Scanner(System.in);
        while (true) {
            String userInput = scannerInput.nextLine();
            if (userInput.equals("bye")) {
                break;
            } else {
                System.out.println(userInput);
            }
        }
        scannerInput.close();

        System.out.println("Bye. Hope to see you again soon!"
                + "\n____________________________________________________________");
    }
}
