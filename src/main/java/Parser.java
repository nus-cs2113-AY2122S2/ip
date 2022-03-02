import java.util.Scanner;

public class Parser {

    public static final String EXIT_MESSAGE = "bye";
    public static final String PRINT_MESSAGE = "list";
    public static final String MARK_MESSAGE = "mark";
    public static final String UNMARK_MESSAGE = "unmark";
    public static final String DELETE_MESSAGE = "delete";
    public static final String TODO_MESSAGE = "todo";
    public static final String DEADLINE_MESSAGE = "deadline";
    public static final String EVENT_MESSAGE = "event";
    public static final String FIND_MESSAGE = "find";

    public static int getTaskIndex(String userInput) {
        String[] commandInputs = userInput.split(" ");
        String taskNumber = commandInputs[1];
        return Integer.parseInt(taskNumber) - 1;
    }

    public static String getTargetDescription(String userInput) {
        String[] commandInputs = userInput.split(" ", 2);
        return commandInputs[1];
    }

    public static String[] parseAdditionalParameters (String parsedUserInput, String indicator) {
        String[] additionalParameters = parsedUserInput.split(indicator, 2);
        additionalParameters[0] = additionalParameters[0].trim();
        additionalParameters[1] = additionalParameters[1].trim();
        return additionalParameters;
    }

    private static void parseTask(String userInput) throws DukeException {
        String[] parsedUserInputs = userInput.split(" ", 2);
        parsedUserInputs[0] = parsedUserInputs[0].toLowerCase();
        switch (parsedUserInputs[0]) {
        case TODO_MESSAGE:
            CommandHandler.addTodo(parsedUserInputs[1]);
            break;
        case DEADLINE_MESSAGE:
            CommandHandler.addDeadline(parsedUserInputs[1]);
            break;
        case EVENT_MESSAGE:
            CommandHandler.addEvent(parsedUserInputs[1]);
            break;
        default:
            throw new DukeException();
        }
        Ui.printAddToList();
    }

    public static void parseInput(String userInput, Scanner in) {
        while(!userInput.equalsIgnoreCase(EXIT_MESSAGE)){
            if (userInput.startsWith(PRINT_MESSAGE)) {
                Ui.printList();
            } else if (userInput.startsWith(MARK_MESSAGE)) {
                CommandHandler.markTask(userInput);
            } else if (userInput.startsWith(UNMARK_MESSAGE)) {
                CommandHandler.unmarkTask(userInput);
            } else if (userInput.startsWith(DELETE_MESSAGE)) {
                CommandHandler.deleteTask(userInput);
            } else if (userInput.startsWith(FIND_MESSAGE)) {
                CommandHandler.findTask(userInput);
            } else {
                try {
                    parseTask(userInput);
                } catch (DukeException e) {
                    Ui.printWrongInput();
                } catch (IndexOutOfBoundsException e) {
                    Ui.printWrongFormat();
                }
            }
            userInput = Ui.readCommand(in);
        }
    }

}
