import java.util.Scanner;

public class Duke {
    private static Scanner SCANNER = new Scanner(System.in);
    private static Parser parser;
    private static TaskList taskList;
    private static Ui UI = new Ui();
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
        taskList = new TaskList();
    }

    private static void showWelcomeMessage() {
        UI.print("Hello! I'm Duke\n" + "What can I do for you?");
    }

    private static void showExitMessage() {
        UI.print("Bye. Hope to see you again soon!");
    }

    private static String getUserInput() {
        return SCANNER.nextLine().trim();
    }

    private static void parseUserInput(String input) {
        parser.parseString(input);
    }

    private static void showOutput(String string) {
        UI.print(string);
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
        return taskList.toString();
    }

    private static String addTask() {
        return taskList.addTask(parser.getCommand(), parser.getAddedTask());
    }

    private static String markTask() {
        return taskList.markTask(parser.getMarkedTask()[0],
                                parser.getMarkedTask()[1]);
    }
}
