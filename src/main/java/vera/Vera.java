package vera;

import vera.exception.CommandMissingException;
import vera.exception.InputEmptyException;
import vera.exception.InputRepeatedException;
import vera.task.Deadline;
import vera.task.Event;
import vera.task.Task;
import vera.task.Todo;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;


public class Vera {
    private static ArrayList<Task> tasks;
    private static Scanner SCANNER;
    private static final String SAVE_FILE_PATH = "data/vera.txt";
    private static final String PARTITION_LINE = "______________________________"
            + "______________________________";

    private static final String HELP_MESSAGE = "For more information, please enter the 'help' command.";
    private static final String HELP_MESSAGE_SPECIFIC_COMMAND = "\n\nFor more information on "
            + "the command you wish to execute,\nenter 'help <command>' e.g. help todo";
    private static final String HELP_MESSAGE_LIST_COMMAND = "List: Displays a list of tasks "
            + "added and shows \nwhether or not certain tasks are marked.";
    private static final String HELP_MESSAGE_MARK_COMMAND = "Mark: Marks a task as done. "
            + "\nTo mark a specific task, enter 'mark <list_index>'.\n\n Here, "
            + "'list_index' denotes the index of a task \n based on the task list under the command 'list'.\n"
            + "\nE.g., 'mark 1' marks the first task in the task list as done.\n\n"
            + "Note: You can only mark one task per command input.";
    private static final String HELP_MESSAGE_UNMARK_COMMAND = "Unmark: Marks a task as undone."
            + "\nTo unmark a specific task, enter 'unmark <list_index>'.\n\n Here, "
            + "'list_index' denotes the index of a task \n based on the task list under the command 'list'.\n"
            + "\nE.g., 'unmark 3' unmarks the third task in the task list.\n\n"
            + "Note: You can only unmark one task per command input.";
    private static final String HELP_MESSAGE_TODO_COMMAND = "Todo: Adds a 'todo' task into the task list."
            + "\nA 'todo' contains only a task description. \n\nTo add other features to your task, "
            + "such as date and time, \nuse either 'deadline' or 'event'.\n\nTo execute the command, \n"
            + "enter 'todo <task_description>', e.g. todo read book.";
    private static final String HELP_MESSAGE_DEADLINE_COMMAND = "Deadline: Adds a 'deadline' task "
            + "into the task list. \nA 'deadline' contains both a task description \nand a date "
            + "to finish the task by.\n\nTo execute the command,\nenter 'deadline <task_description> "
            + "/by <task_date>'.\nE.g. deadline return book /by Sunday.";
    private static final String HELP_MESSAGE_EVENT_COMMAND = "Event: Adds an 'event' task into the task list.\n"
            + "An 'event' contains both a task description \nand a date "
            + "of when the event will happen. \n\nTo execute the command,\n"
            + "enter 'event <task_description> /at <task_date>'.\n"
            + "E.g. event project meeting /at 6th Aug 2-4pm.";
    private static final String HELP_MESSAGE_DELETE_COMMAND = "Delete: Deletes a task in the task list.\n"
            + "To delete a specific task, enter 'delete <list_index>'.\n\n Here, "
            + "'list_index' denotes the index of a task \n based on the task list under the command 'list'.\n"
            + "\nE.g., 'delete 2' deletes the second task in the task list.\n\n"
            + "Note: You can only delete one task per command input.";
    private static final String HELP_MESSAGE_BYE_COMMAND = "Bye: Exits the program.";
    private static final String HELP_MESSAGE_QUICK_START_COMMAND = "Command input quick start guide:\n"
            + "1) List: list\n"
            + "2) Mark: mark <list_index>\n"
            + "3) Unmark: unmark <list_index>\n"
            + "4) Todo: todo <task_description>\n"
            + "5) Deadline: deadline <task_description> /by <task_date>\n"
            + "6) Event: event <task_description> /at <task_date>\n"
            + "7) Delete: delete <list_index>\n"
            + "8) Bye: bye";

    private static final String ERROR_INVALID_INPUT_MESSAGE = "Please key in an appropriate command.\n"
            + HELP_MESSAGE;
    private static final String ERROR_EVENT_MISSING_COMMAND_MESSAGE = "Oops! You forgot to "
            + "add a '/at' to your 'event' command.";
    private static final String ERROR_DEADLINE_MISSING_COMMAND_MESSAGE = "Oops! You forgot to"
            + " add a '/by' to your 'deadline' command.";
    private static final String ERROR_TODO_REPEATED_INPUT_MESSAGE = "Oops! It seems that you've "
            + "already added this task.";
    private static final String ERROR_INVALID_DELETE_INDEX_MESSAGE = "Oops! It seems that you've given\n"
            + "an invalid index to delete the task.";
    private static final String ERROR_IO_FAILURE_MESSAGE = "Oh no! We've encountered an error \nwhile "
            + "trying to processing the system.\n"
            + "Please reboot and execute the application again.";
    private static final String ERROR_SYSTEM_FAULT_MESSAGE = "Oops! There seems to be some problem"
            + "with the code.\nPlease contact the developers for help.";

    private static final int OPTIONS_INDEX = 0;
    private static final int MARK_INDEX = 1;
    private static final int TASK_CONTENT_INDEX = 1;
    private static final int TASK_DESCRIPTION_INDEX = 0;
    private static final int TASK_DESCRIPTION_INDEX_TODO = 1;
    private static final int TASK_DATE_INDEX = 1;
    private static final int HELP_OPTIONS_INDEX = 1;
    private static final int DELETE_INDEX = 1;
    private static final int SAVE_TASK_INDEX = 0;
    private static final int SAVE_TASK_MARK_STATUS = 1;
    private static final int SAVE_TASK_DESCRIPTION_INDEX = 2;
    private static final int SAVE_TASK_DATE_INDEX = 3;


    public enum TaskType {
        TODO, DEADLINE, EVENT
    }

    public enum MarkType {
        MARK, UNMARK
    }

    private static void initTaskManager() {
        tasks = new ArrayList<>();
        SCANNER = new Scanner(System.in);
        System.out.println("Booting up...");
        File saveDirectory = new File("data");
        if (saveDirectory.mkdir()) {
            System.out.println("Creating save directory...");
        }
        try {
            File saveState = new File(SAVE_FILE_PATH);
            if (saveState.createNewFile()) {
                System.out.println("Creating new save state...");
            }
        } catch (IOException e) {
            System.out.println(ERROR_IO_FAILURE_MESSAGE);
            System.exit(1);
        }
    }

    private static Task readSavedTask(String[] rawData) {
        Task parsedData;
        switch (rawData[SAVE_TASK_INDEX].trim()) {
        case "T":
            parsedData = new Todo(rawData[SAVE_TASK_DESCRIPTION_INDEX]);
            break;
        case "D":
            parsedData = new Deadline(rawData[SAVE_TASK_DESCRIPTION_INDEX],
                    rawData[SAVE_TASK_DATE_INDEX]);
            break;
        case "E":
            parsedData = new Event(rawData[SAVE_TASK_DESCRIPTION_INDEX],
                    rawData[SAVE_TASK_DATE_INDEX]);
            break;
        default:
            System.out.println("Oops! It seems that your saved file"
                    + "was corrupted.\nProceeding to start anew...");
            return null;
        }
        if (rawData[SAVE_TASK_MARK_STATUS].equals("1")) {
            parsedData.markAsDone();
        }
        return parsedData;
    }

    private static void wipeSavedData() {
        try {
            // Create new FileWriter to overwrite existing file. But
            // no new data is written to overwrite file content, so content remains empty, i.e. clears file content
            FileWriter fw = new FileWriter(SAVE_FILE_PATH);
            fw.close();
        } catch (IOException e) {
            System.out.println(ERROR_IO_FAILURE_MESSAGE);
            System.exit(1);
        }
    }

    private static void loadSaveState() {
        try {
            File f = new File(SAVE_FILE_PATH);
            Scanner s = new Scanner(f);
            String[] taskRawData;
            Task taskParsedData;
            while (s.hasNext()) {
                taskRawData = s.nextLine().split(" \\| ");
                taskParsedData = readSavedTask(taskRawData);
                if (taskParsedData == null) {
                    wipeSavedData();
                    return;
                }
                tasks.add(taskParsedData);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Sorry! There was an error while loading your"
                    + "saved file. Please restart and try again later.");
            System.exit(1);
        }
    }

    private static void printWithPartition(String message) {
        System.out.println(PARTITION_LINE + System.lineSeparator()
                + message + System.lineSeparator() + PARTITION_LINE);
    }

    private static void printExitMessage() {
        String message = "Bye. Hope to see you again soon! :)";
        printWithPartition(message);
    }

    private static void printWelcomeMessage() {
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

    private static String getUserInput() {
        return SCANNER.nextLine();
    }

    private static void showResultsToUser(String feedback) {
        printWithPartition(feedback);
    }

    private static String list() {
        int printIndex = 1;
        System.out.println(PARTITION_LINE +
                "\nHere are the tasks in your list:");
        for (Task task : tasks) {
            System.out.println(printIndex + ". " + task);
            printIndex++;
        }
        return "A total of " + (printIndex - 1) + " item(s) have been found!";
    }

    private static boolean isTaskAlreadyAdded(String inputTaskDescription) {
        for (Task task : tasks) {
            if (task.getDescription().equalsIgnoreCase(inputTaskDescription)) {
                return true;
            }
        }
        return false;
    }

    private static String printMissingInputMessage(String input, TaskType taskType) {
        return "☹ Oops! The " + input + " of a " + taskType + " cannot be empty."
                + HELP_MESSAGE_SPECIFIC_COMMAND;
    }

    private static String appendToFile(Task newTask, String taskType) {
        try {
            FileWriter fw = new FileWriter(SAVE_FILE_PATH, true);
            String taskStatus = "0";
            if (newTask.isDone()) {
                taskStatus = "1";
            }

            String textToAppend = taskType + " | " + taskStatus + " | "
                    + newTask.getDescription();
            if (!taskType.equals("T")) {
                textToAppend += " | " + newTask.getDate();
            }

            fw.write(textToAppend + System.lineSeparator());
            fw.close();

        } catch (IOException e) {
            return ERROR_IO_FAILURE_MESSAGE;
        }
        return "Got it. I've added this task:\n  " + newTask
                + "\nNow you have " + tasks.size() + " task(s) in the list.";
    }

    private static void rewriteSaveState() {
        wipeSavedData();
        for (Task task : tasks) {
            appendToFile(task, task.getType());
        }
    }

    private static Task addToTaskList(String[] parsedInput, TaskType taskType) {
        Task newTask = null;
        switch (taskType) {
        case TODO:
            newTask = new Todo(parsedInput[TASK_DESCRIPTION_INDEX_TODO]);
            break;
        case DEADLINE:
            newTask = new Deadline(parsedInput[TASK_DESCRIPTION_INDEX],
                    parsedInput[TASK_DATE_INDEX]);
            break;
        case EVENT:
            newTask = new Event(parsedInput[TASK_DESCRIPTION_INDEX],
                    parsedInput[TASK_DATE_INDEX]);
            break;
        default:
            printWithPartition(ERROR_SYSTEM_FAULT_MESSAGE);
            System.exit(1);
        }

        tasks.add(newTask);
        return newTask;
    }

    private static String initNewTodo(String[] parsedInput) throws InputEmptyException,
            InputRepeatedException {

        if (parsedInput[TASK_DESCRIPTION_INDEX_TODO].isBlank()) {
            throw new InputEmptyException();
        }
        if (isTaskAlreadyAdded(parsedInput[TASK_DESCRIPTION_INDEX_TODO])) {
            throw new InputRepeatedException();
        }

        Task newTask = addToTaskList(parsedInput, TaskType.TODO);
        return appendToFile(newTask, "T");
    }

    private static String filterTodoBeforeAddingToTaskList(String[] parsedInput) {
        try {
            return initNewTodo(parsedInput);
        } catch (ArrayIndexOutOfBoundsException | InputEmptyException e) {
            return printMissingInputMessage("description", TaskType.TODO);
        } catch (InputRepeatedException e) {
            return ERROR_TODO_REPEATED_INPUT_MESSAGE;
        }
    }

    private static String checkTaskType(TaskType taskType) {
        if (taskType.equals(TaskType.DEADLINE)) {
            return "D";
        }
        return "E";
    }

    private static String initNewEventOrDeadline(TaskType taskType, String[] parsedInput, String command)
            throws InputEmptyException, InputRepeatedException, CommandMissingException {

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

        Task newTask = addToTaskList(filteredTaskContent, taskType);
        String taskTypeToAppend = checkTaskType(taskType);
        return appendToFile(newTask, taskTypeToAppend);
    }

    private static boolean isTaskBeingReplaced() {
        boolean isOldTaskReplaced = false;
        printWithPartition("Oops! It seems that you've already added this task.\n"
                + "Would you like to override the\nexisting time and/or date "
                + "with the new input? [Y/N]");
        while (true) {
            String input = SCANNER.nextLine();
            if (input.equalsIgnoreCase("Y")
                    || input.equalsIgnoreCase("Yes")) {
                isOldTaskReplaced = true;
                System.out.println(PARTITION_LINE + "\nUnderstood. Proceeding to change"
                        + "\nthe old task with the new one..........");
                break;
            }
            if (input.equalsIgnoreCase("N")
                    || input.equalsIgnoreCase("No")) {
                break;
            }
            printWithPartition("Please confirm your choice with either Y (Yes) or N (No).");
        }
        return isOldTaskReplaced;
    }

    private static int findIndexToReplace(String[] filteredTaskContent) {
        int index = 0;
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).getDescription().equalsIgnoreCase(filteredTaskContent[TASK_DESCRIPTION_INDEX])) {
                index = i;
                break;
            }
        }
        return index;
    }

    private static String handleRepeatedInputs(String[] filteredTaskContent) {
        if (isTaskBeingReplaced()) {
            int taskIndexToReplace = findIndexToReplace(filteredTaskContent);
            tasks.get(taskIndexToReplace).resetInput(filteredTaskContent[TASK_DATE_INDEX]);
            rewriteSaveState();
            return "Done! I've updated this task:\n  " + tasks.get(taskIndexToReplace);
        }
        return "Okay, we'll keep it as it is.";
    }

    private static String[] parseData(String data, String keyword) {
        return data.split(keyword, 2);
    }

    private static String filterTaskBeforeAddingToTaskList(String[] parsedInput, String command, TaskType taskType) {
        try {
            return initNewEventOrDeadline(taskType, parsedInput, command);
        } catch (ArrayIndexOutOfBoundsException | InputEmptyException e) {
            return printMissingInputMessage("description and date\n", taskType);
        } catch (InputRepeatedException e) {
            return handleRepeatedInputs(parseData(parsedInput[TASK_CONTENT_INDEX], command));
        } catch (CommandMissingException e) {
            if (taskType.equals(TaskType.EVENT)) {
                return ERROR_EVENT_MISSING_COMMAND_MESSAGE + HELP_MESSAGE_SPECIFIC_COMMAND;
            }
            return ERROR_DEADLINE_MISSING_COMMAND_MESSAGE + HELP_MESSAGE_SPECIFIC_COMMAND;
        }
    }

    private static String markTask(String[] parsedInput) throws InputEmptyException {
        if (parsedInput[MARK_INDEX].isBlank()) {
            throw new InputEmptyException();
        }
        int taskIndexToMark = Integer.parseInt(parsedInput[MARK_INDEX]) - 1;
        if (tasks.get(taskIndexToMark).isDone()) {
            return "This task has already been marked!";
        }
        tasks.get(taskIndexToMark).markAsDone();
        rewriteSaveState();
        return "Nice! I've marked this task as done:\n  " + tasks.get(taskIndexToMark);
    }

    private static String unmarkTask(String[] parsedInput) throws InputEmptyException {
        if (parsedInput[MARK_INDEX].isBlank()) {
            throw new InputEmptyException();
        }
        int taskIndexToUnmark = Integer.parseInt(parsedInput[MARK_INDEX]) - 1;
        if (!tasks.get(taskIndexToUnmark).isDone()) {
            return "This task was already unmarked!";
        }
        tasks.get(taskIndexToUnmark).markAsUndone();
        rewriteSaveState();
        return "Ok, I've marked this task as"
                + " not done yet:\n  " + tasks.get(taskIndexToUnmark);
    }

    private static String catchInvalidMarkInput(String[] parsedInput, MarkType markType) {
        try {
            if (markType.equals(MarkType.MARK)) {
                return markTask(parsedInput);
            }
            return unmarkTask(parsedInput);
        } catch (IndexOutOfBoundsException | InputEmptyException | NumberFormatException e) {
            return "Bzzt!\nPlease"
                    + " key in a valid task number "
                    + "to mark/unmark your task." + HELP_MESSAGE_SPECIFIC_COMMAND;
        }
    }

    private static String showHelpList() {
        System.out.println(PARTITION_LINE + "\nHere is a list of commands available:");
        String[] helpCommands = {"list", "mark", "unmark", "todo", "deadline", "event", "delete", "bye",};
        for (String helpCommand : helpCommands) {
            System.out.println(PARTITION_LINE + System.lineSeparator()
                    + showSpecificHelpCommand(helpCommand));
        }
        return "For a quick summary of what commands to execute, \n"
                + "enter 'help quick start'.";
    }

    private static String showSpecificHelpCommand(String filteredHelpInput) {
        switch (filteredHelpInput.toLowerCase()) {
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
        case "delete":
            return HELP_MESSAGE_DELETE_COMMAND;
        case "bye":
            return HELP_MESSAGE_BYE_COMMAND;
        case "quick start":
            return HELP_MESSAGE_QUICK_START_COMMAND + HELP_MESSAGE_SPECIFIC_COMMAND;
        default:
            return showHelpList();
        }
    }

    private static String filterHelpCommand(String userInput) {
        try {
            String[] parsedInput = parseData(userInput, " ");
            return showSpecificHelpCommand(parsedInput[HELP_OPTIONS_INDEX]);
        } catch (IndexOutOfBoundsException e) {
            return showHelpList();
        }
    }

    private static String deleteTask(String[] parsedInput) throws InputEmptyException {
        if (parsedInput[DELETE_INDEX].isBlank()) {
            throw new InputEmptyException();
        }
        int index = Integer.parseInt(parsedInput[DELETE_INDEX]) - 1;
        Task taskToBeRemoved = tasks.get(index);
        tasks.remove(index);
        return "Okay. I've removed this task:\n  " + taskToBeRemoved
                + "\nNow you have " + tasks.size() + " task(s) in the list.";
    }

    private static String catchInvalidDeleteInput(String[] parsedInput) {
        try {
            String deleteSuccessfulMessage = deleteTask(parsedInput);
            rewriteSaveState();
            return deleteSuccessfulMessage;
        } catch (IndexOutOfBoundsException | InputEmptyException | NumberFormatException e) {
            return ERROR_INVALID_DELETE_INDEX_MESSAGE + HELP_MESSAGE_SPECIFIC_COMMAND;
        }
    }

    private static String executeInput(String userInput) {
        String[] parsedInput = parseData(userInput, " ");
        switch (parsedInput[OPTIONS_INDEX].toLowerCase()) {
        case "list":
            return list();
        case "mark":
            return catchInvalidMarkInput(parsedInput, MarkType.MARK);
        case "unmark":
            return catchInvalidMarkInput(parsedInput, MarkType.UNMARK);
        case "todo":
            return filterTodoBeforeAddingToTaskList(parsedInput);
        case "event":
            return filterTaskBeforeAddingToTaskList(parsedInput, "/at", TaskType.EVENT);
        case "deadline":
            return filterTaskBeforeAddingToTaskList(parsedInput, "/by", TaskType.DEADLINE);
        case "delete":
            return catchInvalidDeleteInput(parsedInput);
        case "help":
            return filterHelpCommand(userInput);
        default:
            return ERROR_INVALID_INPUT_MESSAGE;
        }
    }

    public static void main(String[] args) {
        initTaskManager();
        loadSaveState();
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
