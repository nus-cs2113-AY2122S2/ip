import java.util.Scanner;

public class Command {
    public static final String EXIT_COMMAND = "bye";
    public static final String LIST_COMMAND = "list";
    public static final String MARK_COMMAND = "mark";
    public static final String UNMARK_COMMAND = "unmark";
    public static final String DELETE_COMMAND = "delete";
    public static final String FIND_COMMAND = "find";

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
