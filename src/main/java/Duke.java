import java.util.Scanner;
import java.util.Arrays;

public class Duke {
    public static void main(String[] args) {
        welcomeMessage();
        Task[] userLists = new Task[] {};
        Scanner input = new Scanner(System.in);
        String userInput = input.nextLine();

        while(!userInput.equals("bye")) {
            Tokenise userInputTokens = new Tokenise(userInput);
            if (userInputTokens.getIsKeyword()) {
                switch (userInputTokens.getTokens()[0]){
                case "list":
                    String allTasks = listTask(userLists);
                    allTasks = wrapMessage(allTasks);
                    System.out.println(allTasks);
                    break;
                case "mark":
                    userLists[Integer.parseInt(userInputTokens.getTokens()[1])].setMark();
                    System.out.println(
                            wrapMessage("Nice! I've marked this task as done:\n" +
                                    userLists[Integer.parseInt(userInputTokens.getTokens()[1])].taskStatus()
                            ));
                    break;
                case "unmark":
                    userLists[Integer.parseInt(userInputTokens.getTokens()[1])].unMark();
                    System.out.println(
                            wrapMessage("OK, I've marked this task as not done yet:\n" +
                                    userLists[Integer.parseInt(userInputTokens.getTokens()[1])].taskStatus()
                            ));
                    break;
                default:
                }
            } else {
                userLists = addTask(userInput, userLists);
            }

            userInput = input.nextLine();
        }

        //end of program
        byeMessage();
    }

    /**
     * add a new task to the existing
     * array of tasks
     *
     * @param userInput
     * @param userLists
     * @return Array of Tasks
     */
    public static Task[] addTask (String userInput, Task[] userLists) {
        Task newTask = new Task(userInput);
        userLists = Arrays.copyOf(userLists, userLists.length + 1);
        userLists[userLists.length - 1] = newTask;
        userInput = wrapMessage("added: " + userInput + " \n");
        System.out.println(userInput);
        return userLists;
    }

    /**
     * Change tasks into a printable list
     * of current tasks the user have
     *
     * @param tasks
     * @return String of all the tasks
     */
    public static String listTask(Task[] tasks) {
        String allTasks = "";
        for (int i = 1; i <= tasks.length; i++) {
            allTasks = allTasks + " " + i + "." + tasks[i-1].taskStatus();
        }
        return allTasks;
    }

    public static String wrapMessage(String message) {
        message = "____________________________________________________________\n"+
                message +
                "____________________________________________________________\n";

        return message;
    }

    public static void welcomeMessage() {
        String welcome= "____________________________________________________________\n" +
                " Hello! I'm Duke\n" +
                " What can I do for you?\n" +
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