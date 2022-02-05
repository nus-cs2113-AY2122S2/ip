import java.util.Scanner;

public class Vera {
    private static Task[] tasks;
    private static int taskCount;
    private static Scanner SCANNER;
    private static final String PARTITION_LINE = "______________________________"
            + "______________________________";

    private static final String HELP_MESSAGE = "For more information, please enter the 'help' command.";
    private static final String ERROR_INVALID_INPUT_MESSAGE = "Please key in an appropriate command.\n"
            + HELP_MESSAGE;
    private static final String ERROR_MAX_TASK_MESSAGE = "Sorry! You've reached the maximum "
            + "amount of tasks allowed on your task list.";
    private static final String ERROR_EVENT_MISSING_COMMAND_MESSAGE = "Oops! You forgot to "
            + "add a '/at' to your 'event' command.";
    private static final String ERROR_DEADLINE_MISSING_COMMAND_MESSAGE = "Oops! You forgot to"
            + " add a '/by' to your 'deadline' command.";
    private static final String ERROR_TODO_REPEATED_INPUT_MESSAGE = "Oops! It seems that you've "
            + "already added this task.";
    private static final String HELP_MESSAGE_SPECIFIC_COMMAND = "\n\nFor more information on "
            + "the command you wish to execute,\nenter 'help <command>' e.g. help todo";

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
        String logo = " __     __             \n"
                + " \\ \\   / /__ _ __ __ _ \n"
                + "  \\ \\ / / _ \\ '__/ _` |\n"
                + "   \\ V /  __/ | | (_| |\n"
                + "    \\_/ \\___|_|  \\__,_|\n";

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

    public static String printMissingInputMessage(String input, TaskType type) {
        return "☹ Oops! The " + input + " of a " + type + " cannot be empty."
                + HELP_MESSAGE_SPECIFIC_COMMAND;
    }

    public static String addTodo(String[] parsedInput) throws InputEmptyException,
            InputRepeatedException, MaxTaskException {
        if (taskCount >= 100) {
            throw new MaxTaskException();
        }
        if (parsedInput[TASK_DESCRIPTION_INDEX_TODO].isBlank()) {
            throw new InputEmptyException();
        }
        if (isTaskAlreadyAdded(parsedInput[TASK_DESCRIPTION_INDEX_TODO])) {
            throw new InputRepeatedException();
        }
        tasks[taskCount] = new Todo(parsedInput[TASK_DESCRIPTION_INDEX_TODO]);
        taskCount++;
        return "Got it. I've added this task:\n  " + tasks[taskCount - 1]
                + "\nNow you have " + taskCount + " task(s) in the list.";
    }

    public static String filterTodoBeforeAddingToTaskList(String[] parsedInput) {
        try {
            return addTodo(parsedInput);
        } catch (ArrayIndexOutOfBoundsException | InputEmptyException e) {
            return printMissingInputMessage("description", TaskType.TODO);
        } catch (InputRepeatedException e) {
            return ERROR_TODO_REPEATED_INPUT_MESSAGE;
        } catch (MaxTaskException e) {
            return ERROR_MAX_TASK_MESSAGE;
        }
    }

    public static String addEventOrDeadline(TaskType type, String[] parsedInput, String command)
            throws InputEmptyException, InputRepeatedException, MaxTaskException, CommandMissingException {
        if (taskCount >= 100) {
            throw new MaxTaskException();
        }
        if (parsedInput[TASK_CONTENT_INDEX].isBlank()) {
            throw new InputEmptyException();
        }
        if (!parsedInput[TASK_CONTENT_INDEX].contains(command)) {
            throw new CommandMissingException();
        }
        String[] filteredTaskContent = parsedInput[TASK_CONTENT_INDEX].split(command, 2);
        if (filteredTaskContent[TASK_DESCRIPTION_INDEX].isBlank() ||
                filteredTaskContent[TASK_DATE_INDEX].isBlank()) {
            throw new InputEmptyException();
        }
        if (isTaskAlreadyAdded(filteredTaskContent[TASK_DESCRIPTION_INDEX])) {
            throw new InputRepeatedException();
        }
        if (type.equals(TaskType.EVENT)) {
            tasks[taskCount] = new Event(filteredTaskContent[TASK_DESCRIPTION_INDEX],
                    filteredTaskContent[TASK_DATE_INDEX]);
        } else {
            tasks[taskCount] = new Deadline(filteredTaskContent[TASK_DESCRIPTION_INDEX],
                    filteredTaskContent[TASK_DATE_INDEX]);
        }
        taskCount++;
        return "Got it. I've added this task:\n  " + tasks[taskCount - 1]
                + "\nNow you have " + taskCount + " task(s) in the list.";
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
            }
            if (input.equalsIgnoreCase("N")) {
                break;
            }
            printWithPartition("Please confirm your choice with either Y or N.");
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
            return "Done! I've updated this task:\n  " + tasks[taskIndexToReplace];
        }
        return "Okay, we'll keep it as it is.";
    }

    public static String[] parseData(String data, String keyword, int limit) {
        return data.split(keyword, limit);
    }

    public static String filterTaskBeforeAddingToTaskList(String[] parsedInput, String command, TaskType type) {
        try {
            return addEventOrDeadline(type, parsedInput, command);
        } catch (ArrayIndexOutOfBoundsException | InputEmptyException e) {
            return printMissingInputMessage("description and date\n", type);
        } catch (InputRepeatedException e) {
            return handleRepeatedInputs(parseData(parsedInput[TASK_CONTENT_INDEX], command, 2));
        } catch (MaxTaskException e) {
            return ERROR_MAX_TASK_MESSAGE;
        } catch (CommandMissingException e) {
            if (type.equals(TaskType.EVENT)) {
                return ERROR_EVENT_MISSING_COMMAND_MESSAGE + HELP_MESSAGE_SPECIFIC_COMMAND;
            }
            return ERROR_DEADLINE_MISSING_COMMAND_MESSAGE + HELP_MESSAGE_SPECIFIC_COMMAND;
        }
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
            return "Bzzt!\nPlease"
                    + " key in a valid task number "
                    + "to mark your task." + HELP_MESSAGE_SPECIFIC_COMMAND;
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
            return "Bzzt!\nPlease"
                    + " key in a valid task number "
                    + "to unmark your task." + HELP_MESSAGE_SPECIFIC_COMMAND;
        }

        int taskIndexToUnmark = Integer.parseInt(parsedInput[MARK_INDEX]) - 1;
        if (!tasks[taskIndexToUnmark].isDone()) {
            return "This task was already unmarked!";
        }
        tasks[taskIndexToUnmark].markAsUndone();
        return "Ok, I've marked this task as"
                + " not done yet:\n  " + tasks[taskIndexToUnmark];
    }

    public static String filterHelpCommand() {
        return "Will update this list soon! Come back next time!";
    }

    public static String executeInput(String userInput) {
        String[] parsedInput = parseData(userInput, " ", 2);
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
        case "help":
            return filterHelpCommand();
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
