package vera;

import vera.exception.CommandMissingException;
import vera.exception.InputEmptyException;
import vera.exception.InputRepeatedException;
import vera.exception.MaxTaskException;
import vera.task.Deadline;
import vera.task.Event;
import vera.task.Task;
import vera.task.Todo;

import java.util.Scanner;

public class Vera {
    private static Task[] tasks;
    private static int taskCount;
    private static Scanner SCANNER;
    private static final String PARTITION_LINE = "______________________________"
            + "______________________________";

    private static final String HELP_MESSAGE = "For more information, please enter the 'help' command.";
    private static final String HELP_MESSAGE_SPECIFIC_COMMAND = "\n\nFor more information on "
            + "the command you wish to execute,\nenter 'help <command>' e.g. help todo";
    private static final String HELP_MESSAGE_LIST_COMMAND = "List: Displays a list of tasks "
            +"added and shows \nwhether or not certain tasks are marked.";
    private static final String HELP_MESSAGE_MARK_COMMAND = "Mark: Marks a task as done. "
            + "\nTo mark a specific task, please enter 'mark <list_index>'.\n\n Here, "
            + "'list_index' denotes the index of a task \n based on the task list under the command 'list'\n"
            + "\nE.g., 'mark 1' marks the first task in the task list as done";
    private static final String HELP_MESSAGE_UNMARK_COMMAND = "Unmark: Marks a task as undone."
            + "\nTo unmark a specific task, please enter 'unmark <list_index>'.\n\n Here, "
            + "'list_index' denotes the index of a task \n based on the task list under the command 'list'\n"
            + "\nE.g., 'unmark 3' unmarks the third task in the task list";
    private static final String HELP_MESSAGE_TODO_COMMAND = "Todo: Adds a 'todo' task into the task list."
            + "\nA 'todo' contains only a task description. \n\nTo add other features to your task, "
            + "such as date and time, \nuse either 'deadline' or 'event'\n\nTo execute the command, \n"
            + "enter 'todo <task_description>', e.g. todo read book";
    private static final String HELP_MESSAGE_DEADLINE_COMMAND = "Deadline: Adds a 'deadline' task "
            + "into the task list. \nA 'deadline' contains both a task description \nand a date "
            + "to finish the task by.\n\nTo execute the command,\nenter 'deadline <task_description> "
            + "/by <task_date>'.\nE.g. deadline return book /by Sunday";
    private static final String HELP_MESSAGE_EVENT_COMMAND = "Event: Adds an 'event' task into the task list.\n"
            + "An 'event' contains both a task description \nand a date "
            + "of when the event will happen. \n\nTo execute the command,\n"
            + "enter 'event <task_description> /at <task_date>'.\n"
            + "E.g. event project meeting /at 6th Aug 2-4pm";
    private static final String HELP_MESSAGE_QUICK_START_COMMAND = "Command quick start guide:\n1) List: list\n"
            + "2) Mark: mark <list_index>\n"
            + "3) Unmark: unmark <list_index>\n"
            + "4) Todo: todo <task_description>\n"
            + "5) Deadline: deadline <task_description> /by <task_date>\n"
            + "6) Event: event <task_description> /at <task_date>";

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


    private static final int OPTIONS_INDEX = 0;
    private static final int MARK_INDEX = 1;
    private static final int TASK_CONTENT_INDEX = 1;
    private static final int TASK_DESCRIPTION_INDEX = 0;
    private static final int TASK_DESCRIPTION_INDEX_TODO = 1;
    private static final int TASK_DATE_INDEX = 1;
    private static final int HELP_OPTIONS_INDEX = 1;

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

    public static String showHelpList() {
        System.out.println(PARTITION_LINE + "\nHere is a list of commands available:");
        String[] helpCommands = {"list", "mark", "unmark", "todo", "deadline", "event"};
        for (String helpCommand : helpCommands) {
            System.out.println(PARTITION_LINE + System.lineSeparator()
                    + showSpecificHelpCommand(helpCommand));
        }
        return "For a quick summary of what commands to execute, \n"
                + "enter 'help quick start'";
    }

    public static String showSpecificHelpCommand(String parsedInput) {
        switch(parsedInput.toLowerCase()) {
        case "list":
            return HELP_MESSAGE_LIST_COMMAND;
        case "mark":
            return HELP_MESSAGE_MARK_COMMAND;
        case "unmark":
            return HELP_MESSAGE_UNMARK_COMMAND;
        case "todo":
            return HELP_MESSAGE_TODO_COMMAND;
        case "deadline":
            return HELP_MESSAGE_DEADLINE_COMMAND;
        case "event":
            return HELP_MESSAGE_EVENT_COMMAND;
        case "quick start":
            return HELP_MESSAGE_QUICK_START_COMMAND + HELP_MESSAGE_SPECIFIC_COMMAND;
        default:
            return showHelpList();
        }
    }

    public static String filterHelpCommand(String userInput) {
        try {
            String[] parsedInput = parseData(userInput, " ", 2);
            return showSpecificHelpCommand(parsedInput[HELP_OPTIONS_INDEX]);
        } catch (IndexOutOfBoundsException e) {
            return showHelpList();
        }
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
            return filterHelpCommand(userInput);
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
