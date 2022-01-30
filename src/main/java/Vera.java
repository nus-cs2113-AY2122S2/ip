import java.util.Scanner;

public class Vera {
    private static Task[] tasks;
    private static int taskCount;
    private static Scanner SCANNER;
    private static final String PARTITION_LINE = "______________________________"
            + "______________________________";

    private static final String INVALID_INPUT_MESSAGE = "Please key in an appropriate command";

    private static final int OPTIONS_INDEX = 0;
    private static final int MARK_INDEX = 1;
    private static final int TASK_CONTENT_INDEX = 1;
    private static final int TASK_DESCRIPTION_INDEX = 0;
    private static final int TASK_DESCRIPTION_INDEX_TODO = 1;
    private static final int TASK_DATE_INDEX = 1;

    public static void initTaskManager() {
        tasks = new Task[100];
        taskCount = 0;
        SCANNER = new Scanner(System.in);
    }

    public static void printWithPartition(String message) {
        System.out.println(PARTITION_LINE + System.lineSeparator() +
                message + System.lineSeparator() + PARTITION_LINE);
    }

    public static void printExitMessage() {
        String message = "Bye. Hope to see you again soon! :)";
        printWithPartition(message);
    }

    public static void printWelcomeMessage() {
        String logo = " __     __             \n" +
                " \\ \\   / /__ _ __ __ _ \n" +
                "  \\ \\ / / _ \\ '__/ _` |\n" +
                "   \\ V /  __/ | | (_| |\n" +
                "    \\_/ \\___|_|  \\__,_|\n";

        String message = logo + "\nHello! I'm Vera,\n"
                + "your friendly virtual task manager.\n"
                + "How can I help you today?";

        printWithPartition(message);
    }

    public static String getUserInput() {
        return SCANNER.nextLine();
    }

    public static void showResultsToUser(String feedback) {
        printWithPartition(feedback);
    }

    public static String showAddTaskMessage(int currentTaskIndex) {
        return "Got it. I've added this task:\n" + "  " + tasks[currentTaskIndex]
                + "\nNow you have " + (currentTaskIndex + 1)  + " task(s) in the list";
    }

    public static String list(Task[] tasks) {
        int printIndex = 1;
        System.out.println(PARTITION_LINE +
                "\nHere are the tasks in your list:");
        for (int i = 0; i < taskCount; i++) {
            System.out.println(printIndex + ". " + tasks[i]);
            printIndex++;
        }
        return "A total of " + (printIndex - 1) + " item(s) have been found!";
    }

    public static boolean isTaskAlreadyAdded(String taskDescription) {
        for (int i = 0; i < taskCount; i++) {
            if (tasks[i].getDescription().equals(taskDescription)) {
                return true;
            }
        }
        return false;
    }

    public static String addTodo(String taskDescription) {
        if (isTaskAlreadyAdded(taskDescription)) {
            return "Oops! It seems that you've already added this task.";
        }
        if (taskCount < 100) {
            tasks[taskCount]= new Todo(taskDescription);
            taskCount++;
            return showAddTaskMessage(taskCount - 1);
        }
        return "Sorry! You've reached the maximum amount of tasks"
                + "allowed on your task list";
    }

    public static String addEvent(String[] parsedInput) {
        if (!parsedInput[TASK_CONTENT_INDEX].contains("/at")) {
            return "Oops! You forgot to add a '/at' to your 'event' command";
        }
        String[] taskDataInput = parsedInput[TASK_CONTENT_INDEX].split("/at");
        if (isTaskAlreadyAdded(taskDataInput[TASK_DESCRIPTION_INDEX])) {
            return "Oops! It seems that you've already added this task.";
        }
        if (taskCount < 100) {
            tasks[taskCount] = new Event(taskDataInput[TASK_DESCRIPTION_INDEX],
                    taskDataInput[TASK_DATE_INDEX]);
            taskCount++;
            return showAddTaskMessage(taskCount - 1);
        }
        return "Sorry! You've reached the maximum amount of tasks"
                + "allowed on your task list";
    }

    public static String addDeadline(String[] parsedInput) {
        if (!parsedInput[TASK_CONTENT_INDEX].contains("/by")) {
            return "Oops! You forgot to add a '/by' to your 'deadline' command";
        }
        String[] taskDataInput = parsedInput[TASK_CONTENT_INDEX].split("/by");
        if (isTaskAlreadyAdded(taskDataInput[TASK_DESCRIPTION_INDEX])) {
            return "Oops! It seems that you've already added this task.";
        }
        if (taskCount < 100) {
            tasks[taskCount] = new Deadline(taskDataInput[TASK_DESCRIPTION_INDEX],
                    taskDataInput[TASK_DATE_INDEX]);
            taskCount++;
            return showAddTaskMessage(taskCount - 1);
        }
        return "Sorry! You've reached the maximum amount of tasks"
                + "allowed on your task list";
    }

    public static boolean isInvalidInput(String[] parsedInput) {
        return (parsedInput.length < 2 ||
                Integer.parseInt(parsedInput[MARK_INDEX]) <= 0 ||
                Integer.parseInt(parsedInput[MARK_INDEX]) > taskCount);
    }
    
    public static String markTask(String[] parsedInput) {
        if (isInvalidInput(parsedInput)) {
            return "Bzzt! \n Please"
                    + " key in a valid task number "
                    + "to mark your task.";
        }

        int taskIndexToMark = Integer.parseInt(parsedInput[1]) - 1;
        if (tasks[taskIndexToMark].isDone()) {
            return "This task has already been marked!";
        }
        tasks[taskIndexToMark].markAsDone();
        return "Nice! I've marked this task as done:\n  " + tasks[taskIndexToMark];
    }

    public static String unmarkTask(String[] parsedInput) {
        if (isInvalidInput(parsedInput)) {
            return "Bzzt! \n Please"
                    + " key in a valid task number "
                    + "to unmark your task.";
        }

        int taskIndexToUnmark = Integer.parseInt(parsedInput[1]) - 1;
        if (!tasks[taskIndexToUnmark].isDone()) {
            return "This task was already unmarked!";
        }
        tasks[taskIndexToUnmark].markAsUndone();
        return "Ok, I've marked this task as" +
                " not done yet:\n  " + tasks[taskIndexToUnmark];
    }

    public static String executeInput(String userInput) {
        String[] parsedInput = userInput.split(" ", 2);
        switch(parsedInput[OPTIONS_INDEX]) {
        case "list":
            return list(tasks);
        case "mark":
            return markTask(parsedInput);
        case "unmark":
            return unmarkTask(parsedInput);
        case "todo":
            return addTodo(parsedInput[TASK_DESCRIPTION_INDEX_TODO]);
        case "event":
            return addEvent(parsedInput);
        case "deadline":
            return addDeadline(parsedInput);
        default:
            return INVALID_INPUT_MESSAGE;
        }
    }

    public static void main(String[] args) {
        initTaskManager();
        printWelcomeMessage();
        String userCommand = getUserInput();
        while (!userCommand.equals("bye")) {
            String feedback = executeInput(userCommand);
            showResultsToUser(feedback);
            userCommand = getUserInput();
        }
        printExitMessage();
    }
}
