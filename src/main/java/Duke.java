import java.util.Locale;
import java.util.Scanner;

public class Duke {

    protected static final int MAX_TASKS = 100;
    protected static final String BORDER_DECORATION = "_____________________________________________________";
    private static Task[] taskLists = new Task[MAX_TASKS];

    public static void displayGreeting() {
        String logo = " _______     __   __  \n"
                + "|   _   |[x]| | / / [x]  \n"
                + "|  | |  || ||  / /  | |\n"
                + "|  | |  || ||  <    | |\n"
                + "|__/ |__||_||_| |_| |_|\n";
        System.out.println("Hello from\n" + logo +"\n" + BORDER_DECORATION);
        System.out.println("Hello! I'm Niki\nWhat can I do for you?\n" + BORDER_DECORATION);
    }

    public static void printList() {
        for (int i = 0; i < Task.getNumberOfTasks(); i++) {
            System.out.println((i + 1) + "."+ taskLists[i].toString());
        }
    }

    public static String removeCommandTypeFromUserInput(String userInput) {
        String[] arrayOfTaskStrings = userInput.split(" ");
        String getStringAfterCommandType = "";
        for (int i = 1; i < arrayOfTaskStrings.length; i++) {
            getStringAfterCommandType += arrayOfTaskStrings[i] + " ";
        }
        return getStringAfterCommandType;
    }

    public static void addTask(String userInput) {
        String extractedTaskDescription = removeCommandTypeFromUserInput(userInput);
        Task newTask = new Todo(extractedTaskDescription);
        taskLists[Task.getNumberOfTasks() - 1] = newTask;
        System.out.println("Task added:\n\t" + newTask.toString());
        System.out.println("Now you have " + Task.getNumberOfTasks() + " tasks in your list!");
    }

    public static void addTaskWithTime(String userInput, String stringSeparator) {
        String extractedStringsWithoutCommand = removeCommandTypeFromUserInput(userInput);
        String extractTaskDescription = extractedStringsWithoutCommand.split(stringSeparator)[0];
        String extractTaskDeadlineTime = extractedStringsWithoutCommand.split(stringSeparator)[1];

        Task newTask;
        if (stringSeparator.equals("/by ")) {
            newTask = new Deadline(extractTaskDescription, extractTaskDeadlineTime);
        } else {
            newTask = new Event(extractTaskDescription, extractTaskDeadlineTime);
        }

        taskLists[Task.getNumberOfTasks() - 1] = newTask;
        System.out.println("Task added:\n\t" + newTask.toString());
        System.out.println("Now you have " + Task.getNumberOfTasks() + " tasks in your list!");
    }

    public static boolean isWithinTaskRange(int taskNumber) {
        if (taskNumber > Task.getNumberOfTasks() || taskNumber <= 0) {
            System.out.println("Task does not exist!");
            return false;
        }
        return true;
    }

    public static void markTask(boolean isMarked, String userInput) {
        int taskNumber = Integer.parseInt(userInput.split(" ")[1]);
        if (isWithinTaskRange(taskNumber)) {
            if (isMarked) {
                taskLists[taskNumber - 1].markAsDone();
                System.out.println("Fantastic! This task is done:");
            } else {
                taskLists[taskNumber - 1].markAsUndone();
                System.out.println("Uh oh! This task is undone:");
            }
            System.out.println(taskLists[taskNumber - 1].toString());
        }
    }

    private static void printExit() {
        System.out.println(BORDER_DECORATION);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(BORDER_DECORATION);
    }

    public static void executeCommand() {
        String userInput;
        Scanner in = new Scanner(System.in);
        userInput = in.nextLine();

        while (!userInput.equalsIgnoreCase("bye")) {
            System.out.println(BORDER_DECORATION);
            String command = userInput.split(" ")[0];

            switch (command) {
            case "list":
                printList();
                break;
            case "todo":
                addTask(userInput);
                break;
            case "deadline":
                addTaskWithTime(userInput, "/by ");
                break;
            case "event":
                addTaskWithTime(userInput,"/at ");
                break;
            case "unmark":
                markTask(false, userInput);
                break;
            case "mark":
                markTask(true, userInput);
                break;
            default:
                System.out.println("Invalid command.");
            }
            System.out.println(BORDER_DECORATION);
            userInput = in.nextLine();
        }
    }

    public static void main(String[] args) {
        displayGreeting();
        executeCommand();
        printExit();
    }
}
