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
        if (command.equals(LIST_COMMAND)) {
            feedback = listTask();
        }
        if (command.equals(MARK_COMMAND)) {
            feedback = markTask();
        }
        if (command.equals(UNMARK_COMMAND)) {
            feedback = unmarkTask();
        }
        if (command.equals(TODO_COMMAND)) {
            feedback = addTodo();
        }
        if (command.equals(DEADLINE_COMMAND)) {
            feedback = addDeadline();
        }
        if (command.equals(EVENT_COMMAND)) {
            feedback = addEvent();
        }
        return feedback;
    }

    private static String listTask() {
        return taskManager.listTask();
    }

    private static String addTodo() {
        return taskManager.addTodo(parser.getTaskDescription());
    }

    private static String addDeadline() {
        return taskManager.addDeadline(parser.getTaskDescription());
    }

    private static String addEvent() {
        return taskManager.addEvent(parser.getTaskDescription());
    }

    private static String markTask() {
        return taskManager.markTask(parser.getTaskId());
    }

    private static String unmarkTask() {
        return taskManager.unmarkTask(parser.getTaskId());
    }
}
