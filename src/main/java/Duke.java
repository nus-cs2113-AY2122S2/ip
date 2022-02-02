import java.util.Scanner;
import java.util.Arrays;

public class Duke {
    public static void main(String[] args) {
        welcomeMessage();
        Task[] userLists = new Task[] {};
        Scanner input = new Scanner(System.in);
        String userInput = input.nextLine();

        //main handler for receiving input
        while(!userInput.equals("bye")) {
            //change user input into an array of tokens
            Tokenise userInputTokens = new Tokenise(userInput);
            String newUserInput = userInputTokens.removeKeyword();
            if (userInputTokens.getIsKeyword()) {
                switch (userInputTokens.getTokens()[0]){
                case "list":
                    String allTasks = listTask(userLists);
                    allTasks = wrapMessage(allTasks);
                    System.out.println(allTasks);
                    break;
                case "todo":
                    Todo newTodo = new Todo(newUserInput);
                    userLists = addTask(newTodo, userLists);
                    break;
                case "deadline":
                    //split string into the description and time
                    String deadlineTime = newUserInput.substring(newUserInput.indexOf("/")+4);
                    newUserInput = newUserInput.substring(0, newUserInput.indexOf("/"));
                    Deadline newDeadline = new Deadline(newUserInput.trim(), deadlineTime);
                    userLists = addTask(newDeadline, userLists);
                    break;
                case "event":
                    //split string into the description and time
                    String eventTime = newUserInput.substring(newUserInput.indexOf("/")+4);
                    newUserInput = newUserInput.substring(0, newUserInput.indexOf("/"));
                    Event newEvent = new Event(newUserInput, eventTime);
                    userLists = addTask(newEvent, userLists);
                    break;
                case "mark":
                    userLists[Integer.parseInt(userInputTokens.getTokens()[1])].setMark();
                    System.out.println(
                            wrapMessage("Nice! I've marked this task as done:\n" +
                                    userLists[Integer.parseInt(
                                            userInputTokens.getTokens()[1])].toString()
                            ));
                    break;
                case "unmark":
                    userLists[Integer.parseInt(userInputTokens.getTokens()[1])].unMark();
                    System.out.println(
                            wrapMessage("OK, I've marked this task as not done yet:\n" +
                                    userLists[Integer.parseInt(
                                            userInputTokens.getTokens()[1])].toString()
                            ));
                    break;
                default:
                }
            } else {
                Task newTask = new Task(userInput);
                userLists = addTask(newTask, userLists);
                //in the case where non-keyword inputs are disallowed
                //System.out.println("Please use a keyword for inputs");
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
     * @param task
     * @param userLists
     * @return Array of Tasks
     */
    public static Task[] addTask (Task task, Task[] userLists) {
        userLists = Arrays.copyOf(userLists, userLists.length + 1);
        userLists[userLists.length - 1] = task;
        String userInput = wrapMessage("Got it. I've added this task:\n " +
                task.toString() + " \nNow you have " + userLists.length +
                " tasks in the list.\n");
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
            allTasks = allTasks + " " + i + "." + tasks[i-1].toString();
        }

        return "Here are the tasks in your list:\n" + allTasks;
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
