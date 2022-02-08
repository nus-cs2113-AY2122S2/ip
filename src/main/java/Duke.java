import java.util.Scanner;

public class Duke {
    private static Scanner SCANNER = new Scanner(System.in);
    private static Parser parser;
    private static TaskManager taskManager;
    private static String userInput;
    private static String output;

    private static final String LIST_COMMAND = "list";
    private static final String MARK_COMMAND = "mark";
    private static final String UNMARK_COMMAND = "unmark";
    private static final String TODO_COMMAND = "todo";
    private static final String DEADLINE_COMMAND = "deadline";
    private static final String EVENT_COMMAND = "event";

    public static void main(String[] args) {
        initDuke();
        showWelcomeMessage();

        while (true) {
            userInput = getUserInput();
            parseUserInput(userInput);
            if (parser.isExiting()) {
                break;
            }
            output = executeCommand();
            showOutput(output);
        }

        showExitMessage();
    }

    private static void initDuke() {
        parser = new Parser();
        taskManager = new TaskManager();
    }

    private static void showOutput(String string) {
        Ui.print(string);
    }

    private static void showWelcomeMessage() {
        showOutput(Ui.welcomeMessage());
    }

    private static void showExitMessage() {
        showOutput(Ui.exitMessage());
    }

    private static String getUserInput() {
        return SCANNER.nextLine().trim();
    }

    private static void parseUserInput(String input) {
        parser.parseString(input);
    }

    private static String executeCommand() {
        String feedback = "";
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
        default:
            //invalid command
        }
        return feedback;
    }

    private static String listTask() {
        return taskManager.listTask();
    }

    private static String addTodo() {
        return taskManager.addTodo(parser.getTaskDescription());
        // no task description
        // duplicate
    }

    private static String addDeadline() {
        return taskManager.addDeadline(parser.getTaskDescription());
        // incomplete input
        // no /by no date no task
    }

    private static String addEvent() {
        return taskManager.addEvent(parser.getTaskDescription());
        // incomplete input
        // no /by no date no task
    }

    private static String markTask(boolean isDone) {
        try {
            return taskManager.markTask(parser.getTaskId(), isDone);
        } catch (DukeException e) {
            return e.toString();
        }
    }
}
