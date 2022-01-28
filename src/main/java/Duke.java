import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class Duke {
    private static final Scanner in = new Scanner(System.in);
    private static final ArrayList<Task> taskStore = new ArrayList<Task>();

    private static final String EMPTY_TASKLIST = "404 Not Found";
    private static final String ERROR_INDEX = "Invalid index!";
    private static final String ERROR_COMMAND = "Error in command!";

    private static final String MESSAGE_GREETING_1 = "Hi! I'm Bim!";
    private static final String MESSAGE_GREETING_2 = "What can I do for you?";
    private static final String MESSAGE_GOODBYE = "See you soon!";
    private static final String MESSAGE_MARK_TASK = "Okie Dokie!";
    private static final String MESSAGE_UNMARK_TASK = "Oh no! Anyways..";

    private static final String DELIMITER_EVENT = "/at ";
    private static final String DELIMITER_DEADLINE = "/by ";

    private static final String OP_MARK = "mark";
    private static final String OP_UNMARK = "unmark";
    private static final String OP_ADD_DEADLINE = "deadline";
    private static final String OP_ADD_TODO = "todo";
    private static final String OP_ADD_EVENT = "event";
    private static final String OP_ADD_TASK = "task";
    private static final String OP_EXIT_PROGRAM = "bye";
    private static final String OP_LIST_TASK = "list";

    public static final String LINE_SEPARATOR = "----------------------------------";

    public static void main(String[] args) {
        printWelcomeMessage();
        readInput();
    }

    public static void readInput() {
        while (in.hasNextLine()) {
            String input = in.nextLine();
            String[] words = input.split(" ", 2);
            String command = words[0].toLowerCase();
            String commandArg = "";
            if (words.length > 1) {
                commandArg = words[1];
            }
            handleCommand(command, commandArg);
            System.out.println(LINE_SEPARATOR);
        }
    }

    private static void handleCommand(String command, String commandArg) {
        switch (command) {
        case OP_EXIT_PROGRAM:
            exitProgram();
            break;
        case OP_LIST_TASK:
            listTask();
            break;
        case OP_MARK:
            modifyTask(OP_MARK, commandArg);
            break;
        case OP_UNMARK:
            modifyTask(OP_UNMARK, commandArg);
            break;
        case OP_ADD_TODO:
            addToDo(commandArg);
            break;
        case OP_ADD_DEADLINE:
            addDeadline(commandArg);
            break;
        case OP_ADD_EVENT:
            addEvent(commandArg);
            break;
        default:
            addTask(command);
            break;
        }
    }

    private static void exitProgram() {
        System.out.println(MESSAGE_GOODBYE);
        System.out.println(LINE_SEPARATOR);
        System.exit(0);
    }

    private static void listTask() {
        if (taskStore.isEmpty()) {
            System.out.println(EMPTY_TASKLIST);
        }
        int i = 1;
        for (Task t : taskStore) {
            System.out.println(i + "." + t);
            ++i;
        }
    }

    private static void modifyTask(String mode, String commandArg) {
        int index = Integer.parseInt(commandArg) - 1;
        if (!isValidIndex(index)) {
            System.out.println(ERROR_INDEX);
            return;
        }
        Task currentTask = taskStore.get(index);
        if (mode.equals(OP_MARK)) {
            currentTask.setAsDone();
            System.out.println(MESSAGE_MARK_TASK);
        } else {
            currentTask.setAsNotDone();
            System.out.println(MESSAGE_UNMARK_TASK);
        }
        System.out.println("\t" + currentTask);
    }

    private static void addToDo(String commandArg) {
        ToDo newToDo = new ToDo(commandArg);
        taskStore.add(newToDo);
        printAddMessage(OP_ADD_TODO);
    }

    private static void addDeadline(String commandArg) {
        if (!isValidArgument(DELIMITER_DEADLINE, commandArg)) {
            System.out.println(ERROR_COMMAND);
            return;
        }
        String[] parsedTokens = parseArgument(DELIMITER_DEADLINE, commandArg);
        String deadlineDescription = parsedTokens[0];
        String deadlineDate = parsedTokens[1];
        Deadline newDeadline = new Deadline(deadlineDescription, deadlineDate);
        taskStore.add(newDeadline);
        printAddMessage(OP_ADD_DEADLINE);
    }

    private static void addEvent(String commandArg) {
        if (!isValidArgument(DELIMITER_EVENT, commandArg)) {
            System.out.println(ERROR_COMMAND);
            return;
        }
        String[] parsedTokens = parseArgument(DELIMITER_EVENT, commandArg);
        String eventDescription = parsedTokens[0];
        String eventDate = parsedTokens[1];
        Event newEvent = new Event(eventDescription, eventDate);
        taskStore.add(newEvent);
        printAddMessage(OP_ADD_EVENT);
    }

    private static void addTask(String commandArg) {
        Task newTask = new Task(commandArg);
        taskStore.add(newTask);
        printAddMessage(OP_ADD_TASK);
    }

    private static boolean isValidArgument(String delimiter, String commandArg) {
        return commandArg.contains(delimiter);
    }

    private static String[] parseArgument(String delimiter, String commandArg) {
        return commandArg.split(delimiter);
    }

    private static boolean isValidIndex(int index) {
        return index < taskStore.size();
    }

    private static void printWelcomeMessage() {
        System.out.println(LINE_SEPARATOR);
        System.out.println(MESSAGE_GREETING_1);
        System.out.println(MESSAGE_GREETING_2);
        System.out.println(LINE_SEPARATOR);
    }

    private static void printAddMessage(String type) {
        System.out.println("New " + type + " added!");
        System.out.println("\t" + taskStore.get(taskStore.size() - 1));
        System.out.println("There are " + taskStore.size() + " task(s) in the list");
    }
}
