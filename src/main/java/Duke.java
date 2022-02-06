import java.util.Scanner;
import java.util.Arrays;

public class Duke {
    public static void userInterface() throws DukeException {
        Task[] userLists = new Task[]{};
        Scanner input = new Scanner(System.in);
        String userInput = input.nextLine();

        //main handler for receiving input
        while (!userInput.equals("bye")) {
            Tokenise userInputTokens = new Tokenise(userInput);
            //change user input into an array of tokens
            switch (userInputTokens.getTokens()[0]) {
            case "list":
                String allTasks = listTask(userLists);
                allTasks = wrapMessage(allTasks);
                System.out.println(allTasks);
                break;
            case "todo":
                Todo newTodo = new Todo(userInputTokens.getDescription());
                userLists = addTask(newTodo, userLists);
                break;
            case "deadline":
                Deadline newDeadline = new Deadline(
                        userInputTokens.getDescription(),
                        userInputTokens.getTime());
                userLists = addTask(newDeadline, userLists);
                break;
            case "event":
                //find index in user input tokens which contains the time separator
                Event newEvent = new Event(
                        userInputTokens.getDescription(),
                        userInputTokens.getTime());
                userLists = addTask(newEvent, userLists);
                break;
            case "mark":
                userLists[userInputTokens.getMarkIndex()].setMark();
                System.out.println(
                        wrapMessage("Nice! I've marked this task as done:\n" +
                                    userLists[userInputTokens.getMarkIndex()].toString()
                        ));
                break;
            case "unmark":
                userLists[userInputTokens.getMarkIndex()].unMark();
                System.out.println(
                        wrapMessage("OK, I've marked this task as not done yet:\n" +
                                    userLists[userInputTokens.getMarkIndex()].toString()
                        ));
                break;
            default:
            }
            userInput = input.nextLine();
        }
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
        String userInput = wrapMessage(
                String.format("Got it. I've added this task:\n" +
                              " %s \n" +
                              "Now you have %d tasks in the list",
                task.toString(), userLists.length));
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
        String divider = "____________________________________________________________\n";
        message = String.format("%s %s%s", divider, message, divider);
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

    public static void main(String[] args) throws DukeException {
        welcomeMessage();
        userInterface();
        byeMessage();
    }
}
