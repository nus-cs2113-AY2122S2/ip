import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("-----------------------------");
        System.out.println("Hello! I'm Olivia, your lovely personal assistant.");
        System.out.println("What can Olivia do for you my love?");
        System.out.println("-----------------------------");
        String userInput;
        do {
            Scanner sc = new Scanner(System.in);
            userInput = sc.nextLine();
            if (userInput.equals("bye")) {
                break;
            }
            System.out.println("-----------------------------");
            System.out.println(userInput);
            System.out.println("-----------------------------");
        } while(!userInput.equals("bye"));
        System.out.println("Bye. Hope to see you again soon! With Love, Olivia");
    }
}
