import java.util.Scanner;
import java.util.ArrayList;


public class Duke {
    public static void main(String[] args) {
        printWelcomeMessage();
        ArrayList<Task> taskList = new ArrayList<Task>();

        Scanner sc = new Scanner(System.in);
        String userInput = sc.nextLine();

        while (!userInput.equals("bye")) {
            System.out.println("-----------------------------");
            if (userInput.equals("list")) {
                displayTaskListMenu();
                displayAllTasks(taskList);
            } else if (userInput.startsWith("unmark")) {
                Task task = getTask(userInput,taskList);
                task.markAsNotDone();
                System.out.println(task);
            } else if (userInput.startsWith("mark")) {
                Task task = getTask(userInput,taskList);
                task.markAsDone();
                System.out.println(task);
            } else if (userInput.startsWith("deadline")) {
                Deadline deadline = getDeadline(userInput);
                taskList.add(deadline);
                System.out.println(deadline);
                printTotalNumberOfTasks(taskList);
            } else if (userInput.startsWith("event")) {
                Event event = getEvent(userInput);
                taskList.add(event);
                System.out.println(event);
                printTotalNumberOfTasks(taskList);
            } else {
                Todo todo = getTodo(userInput);
                taskList.add(todo);
                System.out.println(todo);
                printTotalNumberOfTasks(taskList);
            }
            userInput = sc.nextLine();
        }
        printGoodbyeMessage();
    }

    private static void printTotalNumberOfTasks(ArrayList<Task> taskList) {
        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
    }

    private static Todo getTodo(String userInput) {
        String description = userInput.split("todo")[1].trim();
        Todo todo = new Todo(description);
        return todo;
    }

    private static Event getEvent(String userInput) {
        String[] tokenArray = stringToToken(userInput,"/at");
        String duration = tokenArray[1].trim();
        String description = tokenArray[0].split("event")[1].trim();
        Event event = new Event(description,duration);
        return event;
    }

    private static Deadline getDeadline(String userInput) {
        String[] tokenArray = stringToToken(userInput,"/by");
        String by = tokenArray[1].trim();
        String description = tokenArray[0].split("deadline")[1].trim();
        Deadline deadline = new Deadline(description,by);
        return deadline;
    }

    private static Task getTask(String userInput,ArrayList<Task> taskList) {
        int taskIndex = getTaskIndex(userInput);
        return taskList.get(taskIndex);
    }

    private static int getTaskIndex(String userInput) {
        String[] tokenArray = stringToToken(userInput," ");
        return Integer.valueOf(tokenArray[1]) - 1; //index is raw value - 1
    }

    private static String[] stringToToken(String userInput,String delimiter) {
        return userInput.split(delimiter);
    }

    private static void displayAllTasks(ArrayList<Task> taskList) {
        for (Task task : taskList) {
            System.out.print(task.getTaskId() + ".");
            System.out.println(task);
        }
        System.out.println("-----------------------------");
    }

    private static void displayTaskListMenu() {
        System.out.println("Olivia presents you a list of tasks for you to do:");
    }

    private static void printGoodbyeMessage() {
        System.out.println("-----------------------------");
        System.out.println("Bye. Hope to see you again soon! With Love, Olivia");
        System.out.println("-----------------------------");
    }

    private static void printWelcomeMessage() {
        String botLogo = "  ___                             __  \n"
                       + "|  _  |  |   |  \\        / |     /  \\ \n"
                       + "| | | |  |   |   \\      /  |    / _  \\ \n"
                       + "| | | |  |   |    \\    /   |   / /_\\  \\ \n"
                       + "| |_| |  |   |     \\  /    |  /   _    \\ \n"
                       + "| ___ |  |__ |      \\/     | /___/ \\____\\ \n";
        System.out.println("Hello from\n" + botLogo);
        System.out.println("-----------------------------");
        System.out.println("Greetings! I'm Olivia, your lovely personal assistant.");
        System.out.println("What can Olivia do for you my love?");
        System.out.println("-----------------------------");
    }
}
