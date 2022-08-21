import java.util.Scanner;

public class Duke {
    public static boolean processCommand(String command) {
        if (command.equals("bye")) {
            System.out.println("Bye and see you again soon!");
            return false;
        }
        System.out.println("Echoing..." + command + "\n____________________");
        return true;
    }

    public static void main(String[] args) {
        String command;
        Boolean online = true;
        Scanner in = new Scanner(System.in);
        String InitialGreeting = "Hello! I'm Duke\n" +
                "What can I do for you?\n" +
                "____________________";
        System.out.println(InitialGreeting);
        while (online) {
            command= in.nextLine();
            online = processCommand(command);
        }
        in.close();
    }
}
