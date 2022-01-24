import java.util.Locale;
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
        String[] trackIsDoneList = new String[100];
        int taskCount = 0;

        String border = "_____________________________________________________";
        String userInput;
        Scanner in = new Scanner(System.in);
        userInput = in.nextLine();
        while (!userInput.equalsIgnoreCase("bye")) {
            System.out.println(border);

            if (userInput.equalsIgnoreCase("list")) {
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < taskCount; i += 1) {
                    System.out.println((i + 1) + ".[" + trackIsDoneList[i] +"] " + taskList[i]);
                }
            }
            else if (userInput.toLowerCase().startsWith("unmark")) {
                int taskIndex = Integer.parseInt(userInput.split(" ")[1]);
                if (taskIndex > taskCount || taskIndex <= 0) {
                    System.out.println("Task does not exist!");
                }
                else {
                    trackIsDoneList[taskIndex - 1] = " ";
                    System.out.println("Uh oh! This task is undone:");
                    System.out.println("[" + trackIsDoneList[taskIndex - 1] + "] " + taskList[taskIndex - 1]);
                }
            }
            else if (userInput.toLowerCase().startsWith("mark")){
                int taskIndex = Integer.parseInt(userInput.split(" ")[1]);
                if (taskIndex > taskCount || taskIndex <= 0) {
                    System.out.println("Task does not exist!");
                }
                else {
                    trackIsDoneList[taskIndex - 1] = "X";
                    System.out.println("Fantastic! This task is done:");
                    System.out.println("[" + trackIsDoneList[taskIndex - 1] + "] " + taskList[taskIndex - 1]);
                }
            }
            else{
                taskList[taskCount] = userInput;
                trackIsDoneList[taskCount] = " ";
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
