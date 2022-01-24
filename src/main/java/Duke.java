import java.util.Scanner;

public class Duke {
    public static void displayGreeting() {
        String border = "_____________________________________________________";
        String logo = " _______     __   __  \n"
                + "|   _   |[x]| | / / [x]  \n"
                + "|  | |  || ||  / /  | |\n"
                + "|  | |  || ||  <    | |\n"
                + "|__/ |__||_||_| |_| |_|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println(border);
        System.out.println("Hello! I'm Niki");
        System.out.println("What can I do for you?");
        System.out.println(border);
    }

    public static void main(String[] args) {
        displayGreeting();
        String[] taskList = new String[100];
        int taskCount = 0;

        String border = "_____________________________________________________";
        String userInput;
        Scanner in = new Scanner(System.in);
        userInput = in.nextLine();
        while (!userInput.equalsIgnoreCase("bye")) {
            System.out.println(border);

            if (userInput.equalsIgnoreCase("list")) {
                for (int i = 0; i < taskCount; i += 1) {
                    System.out.println(i + 1 + ". " + taskList[i]);
                }
            }
            else{
                taskList[taskCount] = userInput;
                System.out.println("added: "+ userInput);
                taskCount += 1;
            }

            System.out.println(border);
            userInput = in.nextLine();
        }

        System.out.println(border);
        System.out.println("Bye!!! Hope to see you again soon! :)");
        System.out.println(border);
    }
}
