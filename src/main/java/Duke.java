import duke.*;

import java.util.Scanner;

public class Duke {
    private static Scanner SCANNER = new Scanner(System.in);
    private static Parser parser;
    private static TaskManager taskManager;
    private static String input;
    private static String output;

    private static final String LIST_COMMAND = "list";
    private static final String MARK_COMMAND = "mark";
    private static final String UNMARK_COMMAND = "unmark";
    private static final String TODO_COMMAND = "todo";
    private static final String DEADLINE_COMMAND = "deadline";
    private static final String EVENT_COMMAND = "event";
    private static final String DELETE_COMMAND = "delete";

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

    private static void initDuke() {
        Reader reader = new Reader();
        taskManager = new TaskManager();
        if (reader.isFileExists()) {
            taskManager = reader.readFile(taskManager);
        }
        parser = new Parser();
    }

    private static void terminateDuke() {
        Writer writer = new Writer();
        writer.writeFile(taskManager.getList());
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
        default:
            throw new DukeException(Ui.invalidCommand());
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
}
