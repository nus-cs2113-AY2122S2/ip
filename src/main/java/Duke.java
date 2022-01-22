import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        // We can assume <= 100 tasks
        String[] savedTasks = new String[100];
        int numSavedTasks = 0;

        System.out.println("Hi, I'm Robit!");
        System.out.println("What would you like me to do?");
        System.out.println("------------------------------------------------------------");

        while (true) {
            System.out.print("> ");
            String userInput = in.nextLine();
            System.out.println("------------------------------------------------------------");
            switch (userInput) {
            case "bye":
                System.out.println("Goodbye!");
                System.out.println("------------------------------------------------------------");
                return;
            case "list":
                for (int i = 0; i < numSavedTasks; i++) {
                    System.out.println((i+1) + ") " + savedTasks[i]);
                }
                break;
            default:
                savedTasks[numSavedTasks] = userInput;
                numSavedTasks++;
                System.out.println("Task added: " + userInput);
            }
            System.out.println("------------------------------------------------------------");
        }
    }
}
