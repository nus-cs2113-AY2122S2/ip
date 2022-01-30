import java.util.Scanner;

public class Duke {
    private static Scanner SCANNER = new Scanner(System.in);
    private static Parser parser;
    private static TaskList taskList;
    private static Ui UI = new Ui();
    private static String userInput;

    public static void main(String[] args) {
        initDuke();
        showWelcomeMessage();

        while (!parser.isExiting()) {
            if (parser.isListingTasks()) {
                UI.print(taskList.toString());
            } else if (parser.isMarkingTask()) {
                UI.print(taskList.markTask(parser.getMarkedTask()[0],
                                Integer.valueOf(parser.getMarkedTask()[1]))
                );
            } else if (parser.isAddingTask()) {
                UI.print(taskList.addTask(parser.getAddedTask()));
            }
            userInput = SCANNER.nextLine().trim();
            parser.parseString(userInput);
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
}
