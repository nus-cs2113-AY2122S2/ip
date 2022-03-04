import java.util.Scanner;

/**
 * Represents an executable command.
 */

public class Command {
    public static final String EXIT_COMMAND = "bye";
    public static final String LIST_COMMAND = "list";
    public static final String MARK_COMMAND = "mark";
    public static final String UNMARK_COMMAND = "unmark";
    public static final String DELETE_COMMAND = "delete";
    public static final String FIND_COMMAND = "find";

    /**
     * Executes the command until termination or the exit command is called.
     *
     * @param userInput full user input
     * @param in next user input
     */
    public static void executeCommand(String userInput, Scanner in) {
        while (!userInput.equalsIgnoreCase(EXIT_COMMAND)) {

            String commandType = Parser.parseCommand(userInput);
            int taskIndex;
            String keyword;

            if (commandType.equalsIgnoreCase(LIST_COMMAND)) {
                TaskManager.listTasks();
            }
            else if (commandType.equalsIgnoreCase(MARK_COMMAND)) {
                taskIndex = Parser.parseTaskIndex(userInput);
                TaskManager.markAsDone(taskIndex);
            }
            else if (commandType.equalsIgnoreCase(UNMARK_COMMAND)) {
                taskIndex = Parser.parseTaskIndex(userInput);
                TaskManager.markAsNotDone(taskIndex);
            }
            else if (commandType.equalsIgnoreCase(DELETE_COMMAND)) {
                taskIndex = Parser.parseTaskIndex(userInput);
                TaskManager.deleteTask(taskIndex);
            }
            else if (commandType.equalsIgnoreCase(FIND_COMMAND)) {
                keyword = Parser.parseDescription(null, userInput, commandType);
                TaskManager.findTask(keyword);
            }
            else {
                TaskManager.addTask(commandType, userInput);
            }
            userInput = in.nextLine();
        }
    }
}
