import duke.*;

import java.util.Scanner;

public class Duke {
    private static Scanner SCANNER = new Scanner(System.in);
    private static Parser parser;
    private static TaskManager taskManager;
    private static Storage storage;
    private static String input;
    private static String output;

    private static final String PATH = "./data/";
    private static final String FILENAME = "duke.txt";
    private static final String LIST_COMMAND = "list";
    private static final String MARK_COMMAND = "mark";
    private static final String UNMARK_COMMAND = "unmark";
    private static final String TODO_COMMAND = "todo";
    private static final String DEADLINE_COMMAND = "deadline";
    private static final String EVENT_COMMAND = "event";
    private static final String DELETE_COMMAND = "delete";
    private static final String FIND_COMMAND = "find";

    public static void main(String[] args) {
        initDuke();
        showWelcomeMessage();

        while (true) {
            input = getInput();
            parseUserInput(input);
            if (parser.isExiting()) {
                break;
            }
            try {
                output = executeCommand();
                showOutput(output);
            } catch (DukeException e) {
                showError(e);
            }
        }

        showExitMessage();
        terminateDuke();
    }

    /**
     * Initiates Duke.
     * Reads data from the local file.
     * If the local file exists, creates a TaskManager
     * with a non-empty TaskList with the local data,
     * else creates a TaskManager with empty TaskList.
     */
    private static void initDuke() {
        storage = new Storage(PATH, FILENAME);
        parser = new Parser();
        if (storage.isFileExists()) {
            taskManager = new TaskManager(storage.readFile());
        } else {
            taskManager = new TaskManager();
        }
    }

    /**
     * Terminates Duke.
     * Saves data to a local file.
     */
    private static void terminateDuke() {
        storage.writeFile(taskManager.getList());
    }

    private static void showOutput(String string) {
        Ui.printText(string);
    }

    private static void showError(Exception e) {
        Ui.printError(e);
    }

    private static void showWelcomeMessage() {
        showOutput(Ui.welcomeMessage());
    }

    private static void showExitMessage() {
        showOutput(Ui.exitMessage());
    }

    private static String getInput() {
        return SCANNER.nextLine().trim();
    }

    private static void parseUserInput(String input) {
        parser.parseString(input);
    }

    /**
     * Executes user command.
     *
     * @return UI output message of the command executed.
     * @throws DukeException if the user input is invalid or empty.
     */
    private static String executeCommand() throws DukeException {
        String feedback;
        String command = parser.getCommand();
        switch (command) {
        case LIST_COMMAND:
            feedback = listTask();
            break;
        case MARK_COMMAND:
            feedback = markTask(true);
            break;
        case UNMARK_COMMAND:
            feedback = markTask(false);
            break;
        case TODO_COMMAND:
            feedback = addTodo();
            break;
        case DEADLINE_COMMAND:
            feedback = addDeadline();
            break;
        case EVENT_COMMAND:
            feedback = addEvent();
            break;
        case DELETE_COMMAND:
            feedback = delEvent();
            break;
        case FIND_COMMAND:
            feedback = findTask();
            break;
        default:
            throw new DukeException(Ui.invalidCommandError());
        }
        return feedback;
    }

    private static String listTask() {
        return taskManager.listTask();
    }

    private static String addTodo() throws DukeException {
        return taskManager.createTodo(parser.getTaskDescription());
    }

    private static String addDeadline() throws DukeException {
        return taskManager.createDeadline(parser.getTaskDescription());
    }

    private static String addEvent() throws DukeException {
        return taskManager.createEvent(parser.getTaskDescription());
    }

    private static String markTask(boolean isDone) throws DukeException {
        return taskManager.markTask(parser.getTaskId(), isDone);
    }

    private static String delEvent() throws DukeException {
        return taskManager.delTask(parser.getTaskId());
    }

    private static String findTask() throws DukeException {
        return taskManager.findTask(parser.getDescription());
    }
}
