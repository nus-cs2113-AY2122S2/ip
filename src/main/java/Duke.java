import java.util.Scanner;
import java.util.Arrays;

public class Duke {
    public static void main(String[] args) {
        welcomeMessage();
        Scanner input = new Scanner(System.in);
        String userInput = input.nextLine();
        String userLists[] = new String[] {};

        while(!userInput.equals("bye")) {
            switch (userInput) {
            case "list":
                String allTasks = listTask(userLists);
                allTasks = wrapMessage(allTasks);
                System.out.println(allTasks);
                break;
            default:
                userLists = Arrays.copyOf(userLists, userLists.length+1);
                userLists[userLists.length-1] = userInput;
                userInput = wrapMessage("added: " + userInput + " ");
                System.out.println(userInput);
            }

            userInput = input.nextLine();
        }

        //end of program
        byeMessage();
    }

    public static String listTask(String[] tasks) {
        String allTasks = "";
        for (int i = 1; i <= tasks.length; i++) {
            allTasks = allTasks + " " + i + ". " + tasks[i-1] + "\n";
        }
        return allTasks;
    }

    public static String wrapMessage(String message) {
        message = "____________________________________________________________\n"+
                message +
                "\n____________________________________________________________\n";

        return message;
    }

    public static void welcomeMessage() {
        String welcome="____________________________________________________________\n"+
                " Hello! I'm Duke\n"+
                " What can I do for you?\n"+
                "____________________________________________________________\n";
        System.out.println(welcome);
    }

    public static void byeMessage() {
        String goodBye = "____________________________________________________________\n" +
                " Bye. Hope to see you again soon!\n" +
                "____________________________________________________________\n";
        System.out.println(goodBye);
    }
}