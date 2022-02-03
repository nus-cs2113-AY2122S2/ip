import java.util.Scanner;

public class Vera {
    private static Task[] tasks;
    private static int taskCount;
    private static Scanner SCANNER;
    private static final String PARTITION_LINE = "______________________________"
            + "______________________________";

    private static final String ERROR_INVALID_INPUT_MESSAGE = "Please key in an appropriate command";
    private static final String ERROR_MAX_TASK_MESSAGE = "Sorry! You've reached the maximum "
            + "amount of tasks allowed on your task list";
    private static final String ERROR_EVENT_MISSING_INPUT_MESSAGE = "Oops! You forgot to "
            + "add a '/at' to your 'event' command";
    private static final String ERROR_DEADLINE_MISSING_INPUT_MESSAGE = "Oops! You forgot to"
            + " add a '/by' to your 'deadline' command";
    private static final String ERROR_TODO_REPEATED_INPUT_MESSAGE = "Oops! It seems that you've "
            + "already added this task.";

    private static final int OPTIONS_INDEX = 0;
    private static final int MARK_INDEX = 1;
    private static final int TASK_CONTENT_INDEX = 1;
    private static final int TASK_DESCRIPTION_INDEX = 0;
    private static final int TASK_DESCRIPTION_INDEX_TODO = 1;
    private static final int TASK_DATE_INDEX = 1;

    public enum TaskType {
        TODO, DEADLINE, EVENT
    }

    public static void initTaskManager() {
        tasks = new Task[100];
        taskCount = 0;
        SCANNER = new Scanner(System.in);
    }

    public static void printWithPartition(String message) {
        System.out.println(PARTITION_LINE + System.lineSeparator()
                + message + System.lineSeparator() + PARTITION_LINE);
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

    public static String list() {
        int printIndex = 1;
        System.out.println(PARTITION_LINE +
                "\nHere are the tasks in your list:");
        for (int i = 0; i < taskCount; i++) {
            System.out.println(printIndex + ". " + tasks[i]);
            printIndex++;
        }
        return "A total of " + (printIndex - 1) + " item(s) have been found!";
    }

    public static boolean isTaskAlreadyAdded(String inputTaskDescription) {
        for (int i = 0; i < taskCount; i++) {
            if (tasks[i].getDescription().equalsIgnoreCase(inputTaskDescription)) {
                return true;
            }
        }
        return false;
    }

    public static String addToTaskList(TaskType type, String[] filteredTaskContent) {
        if (taskCount < 100) {
            switch (type) {
            case EVENT:
                tasks[taskCount] = new Event(filteredTaskContent[TASK_DESCRIPTION_INDEX],
                        filteredTaskContent[TASK_DATE_INDEX]);
                break;
            case DEADLINE:
                tasks[taskCount] = new Deadline(filteredTaskContent[TASK_DESCRIPTION_INDEX],
                        filteredTaskContent[TASK_DATE_INDEX]);
                break;
            default:
                tasks[taskCount] = new Todo(filteredTaskContent[TASK_DESCRIPTION_INDEX_TODO]);
            }
            taskCount++;
            return "Got it. I've added this task:\n" + "  " + tasks[taskCount - 1]
                    + "\nNow you have " + taskCount + " task(s) in the list";
        }
        return ERROR_MAX_TASK_MESSAGE;
    }

    public static String filterTodoBeforeAddingToTaskList(String[] parsedInput) {
        if (isTaskAlreadyAdded(parsedInput[TASK_DESCRIPTION_INDEX_TODO])) {
            return ERROR_TODO_REPEATED_INPUT_MESSAGE;
        }
        return addToTaskList(TaskType.TODO, parsedInput);
    }

    public static String[] parseData(String data, String command) {
        return data.split(command);
    }

    public static boolean isTaskBeingReplaced() {
        boolean isOldTaskReplaced = false;
        printWithPartition("Oops! It seems that you've already added this task.\n"
                + "Would you like to override the\nexisting time and/or date "
                + "with the new input? [Y/N]");
        while (true) {
            String input = SCANNER.nextLine();
            if (input.equalsIgnoreCase("Y")) {
                isOldTaskReplaced = true;
                System.out.println(PARTITION_LINE + "\nUnderstood. Proceeding to change"
                        + "\nthe old task with the new one..........");
                break;
            } else if (input.equalsIgnoreCase("N")) {
                break;
            } else {
                printWithPartition("Please confirm your choice with either Y or N");
            }
        }
        return isOldTaskReplaced;
    }

    public static String handleRepeatedInputs(String[] filteredTaskContent) {
        if (isTaskBeingReplaced()) {
            int taskIndexToReplace = 0;
            for (int i = 0; i < taskCount; i++) {
                if (tasks[i].getDescription().equalsIgnoreCase(filteredTaskContent[TASK_DESCRIPTION_INDEX])) {
                    taskIndexToReplace = i;
                    break;
                }
            }
            tasks[taskIndexToReplace].resetInput(filteredTaskContent[TASK_DATE_INDEX]);
            return "Done! I've updated this task:\n" + "  " + tasks[taskIndexToReplace];
        }
        return "Okay, we'll keep it as it is.";
    }

    public static String filterTaskBeforeAddingToTaskList(String[] parsedInput, String command, TaskType type) {
        if (!parsedInput[TASK_CONTENT_INDEX].contains(command)) {
            if (type.equals(TaskType.EVENT)) {
                return ERROR_EVENT_MISSING_INPUT_MESSAGE;
            }
            return ERROR_DEADLINE_MISSING_INPUT_MESSAGE;
        }

        String[] filteredTaskContent = parseData(parsedInput[TASK_CONTENT_INDEX], command);
        if (isTaskAlreadyAdded(filteredTaskContent[TASK_DESCRIPTION_INDEX])) {
            return handleRepeatedInputs(filteredTaskContent);
        }

        return addToTaskList(type, filteredTaskContent);
    }

    public static boolean isInvalidInput(String[] parsedInput) {
        boolean isMarkIndexMissing = parsedInput.length < 2;
        if (isMarkIndexMissing) {
            return true;
        }
        int inputMarkIndex = Integer.parseInt(parsedInput[MARK_INDEX]);
        return inputMarkIndex <= 0 || inputMarkIndex > taskCount;
    }

    public static String markTask(String[] parsedInput) {
        if (isInvalidInput(parsedInput)) {
            return "Bzzt!\n Please"
                    + " key in a valid task number "
                    + "to mark your task.";
        }

        int taskIndexToMark = Integer.parseInt(parsedInput[MARK_INDEX]) - 1;
        if (tasks[taskIndexToMark].isDone()) {
            return "This task has already been marked!";
        }
        tasks[taskIndexToMark].markAsDone();
        return "Nice! I've marked this task as done:\n  " + tasks[taskIndexToMark];
    }

    public static String unmarkTask(String[] parsedInput) {
        if (isInvalidInput(parsedInput)) {
            return "Bzzt!\n Please"
                    + " key in a valid task number "
                    + "to unmark your task.";
        }

        int taskIndexToUnmark = Integer.parseInt(parsedInput[MARK_INDEX]) - 1;
        if (!tasks[taskIndexToUnmark].isDone()) {
            return "This task was already unmarked!";
        }
        tasks[taskIndexToUnmark].markAsUndone();
        return "Ok, I've marked this task as" +
                " not done yet:\n  " + tasks[taskIndexToUnmark];
    }

    public static String executeInput(String userInput) {
        String[] parsedInput = userInput.split(" ", 2);
        switch (parsedInput[OPTIONS_INDEX].toLowerCase()) {
        case "list":
            return list();
        case "mark":
            return markTask(parsedInput);
        case "unmark":
            return unmarkTask(parsedInput);
        case "todo":
            return filterTodoBeforeAddingToTaskList(parsedInput);
        case "event":
            return filterTaskBeforeAddingToTaskList(parsedInput, "/at", TaskType.EVENT);
        case "deadline":
            return filterTaskBeforeAddingToTaskList(parsedInput, "/by", TaskType.DEADLINE);
        default:
            return ERROR_INVALID_INPUT_MESSAGE;
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
