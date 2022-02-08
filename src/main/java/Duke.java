import java.util.Scanner;

public class Duke {
    private static Scanner SCANNER = new Scanner(System.in);
    private static Parser parser;
    private static TaskManager taskManager;
    private static String userInput;
    private static String output;

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

    private static void showWelcomeMessage() {
        Ui.print("Hello! I'm Duke\n" + "What can I do for you?");
    }

    private static void showExitMessage() {
        Ui.print("Bye. Hope to see you again soon!");
    }

    private static String getUserInput() {
        return SCANNER.nextLine().trim();
    }

    private static void parseUserInput(String input) {
        parser.parseString(input);
    }

    private static void showOutput(String string) {
        Ui.print(string);
    }

    private static String executeCommand() {
        String feedback = "";
        if (parser.isListingTasks()) {
            feedback = listTask();
        } else if (parser.isMarkingTask()) {
            feedback = markTask();
        } else if (parser.isAddingTask()) {
            feedback = addTask();
        }
        return feedback;
    }

    private static String listTask() {
        return taskManager.listTask();
    }

    private static String addTask() {
        return taskManager.addTask(parser.getCommand(), parser.getAddedTask());
    }

    private static String markTask() {
        return taskManager.markTask(parser.getMarkedTask()[0],
                                parser.getMarkedTask()[1]);//modify
    }
}
