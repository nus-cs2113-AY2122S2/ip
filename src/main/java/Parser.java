import java.util.Scanner;

public class Parser {
    private static final String COMMAND_BYE = "bye";
    private static final String COMMAND_LIST = "list";
    private static final String COMMAND_MARK = "mark";
    private static final String COMMAND_UNMARK = "unmark";
    private static boolean isInteger(String toCheck) {
        try {
            int intValue = Integer.parseInt(toCheck);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    public static boolean isOnline(String command, TaskManager taskManager) {
        switch(command) {
        case COMMAND_BYE:
            System.out.println("Bye and see you again soon!");
            return false;
        case COMMAND_LIST:
            taskManager.listTasks();
            return true;
        default:
            break;
        }

        String[] arrOfCommand = command.split(" ");

        if ((arrOfCommand[0].equals(COMMAND_MARK) && arrOfCommand.length > 1) && isInteger(arrOfCommand[1])) {
            taskManager.markTasks(true, Integer.parseInt(arrOfCommand[1]));
        } else if ((arrOfCommand[0].equals(COMMAND_UNMARK) && arrOfCommand.length > 1) && isInteger(arrOfCommand[1])) {
            taskManager.markTasks(false, Integer.parseInt(arrOfCommand[1]));
        } else {
            taskManager.addNewTask(command);
        }
        return true;
    }
}