import java.util.Scanner;

/**
 * The Parser class receives the user's input and checks that it is valid
 * Based on the user's command, the Parser class will delegate it to the CommandHandler class accordingly
 */
public class Parser {

    public static final String EXIT_MESSAGE = "bye";
    public static final String PRINT_MESSAGE = "list";
    public static final String MARK_MESSAGE = "mark";
    public static final String UNMARK_MESSAGE = "unmark";
    public static final String DELETE_MESSAGE = "delete";
    public static final String TODO_MESSAGE = "todo";
    public static final String DEADLINE_MESSAGE = "deadline";
    public static final String EVENT_MESSAGE = "event";

    /**
     * This method is called when the user wishes to mark / unmark / delete a specific task
     * The method first finds the specified task number in the user input (String)
     * The task number is subsequently converted into the TaskList's task index (integer)
     *
     * @param userInput String containing the task number
     * @return Corresponding task index
     */
    public static int getTaskIndex(String userInput) {
        String taskNumber = userInput.split(" ")[1];
        return Integer.parseInt(taskNumber) - 1;
    }

    /**
     * This method is invoked when the user wishes to add a new deadline or event
     * As these tasks have an additional parameter (time), this method extracts the time from user input
     *
     * @param parsedUserInput String containing the task's description and time
     * @param indicator "/by" for deadline or "/at" for event to split the task's description and time
     * @return Array with 2 elements - task's description and task's time
     */
    public static String[] parseAdditionalParameters (String parsedUserInput, String indicator) {
        String[] additionalParameters = parsedUserInput.split(indicator, 2);
        additionalParameters[0] = additionalParameters[0].trim();
        additionalParameters[1] = additionalParameters[1].trim();
        return additionalParameters;
    }

    /**
     * This method is invoked if a user wishes to add a new Task
     * The method extracts the first word from the user input to determine the type of Task the user wishes to add
     * The CommandHandler class is subsequently invoked to handle the respective tasks
     *
     * @param userInput String containing the task description (and time)
     * @throws DukeException If the first word of user input does not correspond to any known command
     */
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

    /**
     * Takes in user input and execute accordingly with the help of CommandHandler class
     * If user wishes to add a new Task, the parseTask method is called
     * If DukeException is caught, user input does not correspond to any known command
     * If IndexOutOfBoundsException is caught, user input is missing one or more parameters
     *
     * @param userInput String that represents the user's input
     * @param in Scanner to read in next input
     */
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
