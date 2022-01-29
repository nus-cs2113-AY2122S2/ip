import java.util.Arrays;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String[] commandHistory = new String[100];
        int numCommands = 0;
        boolean isLoop = true;

        System.out.println("   ____________________________________________________________");
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");

        while (isLoop) {
            Scanner sc = new Scanner(System.in);
            String command = sc.nextLine();

            switch (command) {
                case "list":
                    System.out.println("____________________________________________________________");
                    for (int i = 0; i < numCommands; i++) {
                        System.out.println(String.format("%d. %s", i + 1, commandHistory[i]));
                    }
                    System.out.println("____________________________________________________________");
                    break;
                case "bye":
                    System.out.println("____________________________________________________________");
                    System.out.println("Bye. Hope to see you again soon!");
                    System.out.println("____________________________________________________________");
                    isLoop = false;
                    break;
                default:
                    commandHistory[numCommands] = command;
                    numCommands += 1;

                    System.out.println("____________________________________________________________");
                    System.out.println(String.format("added: %s", command));
                    System.out.println("____________________________________________________________");
            }
        }
    }
}
