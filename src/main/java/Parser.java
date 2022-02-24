import em.exception.InvalidUserInputException;
import task.Task;
import java.util.ArrayList;
import static em.exception.InvalidUserInputException.*;

public class Parser {
    public static String formulateDatabaseInput(String[] taskDescription) {
        String databaseInput = null;
        switch (taskDescription[0]) {
        case "todo":
            databaseInput = "T," + "0," + taskDescription[1];
            break;
        case "deadline":
            databaseInput = "D," + "0," + taskDescription[1] + "," + taskDescription[2];
            break;
        case "event":
            databaseInput = "E," + "0," + taskDescription[1] + "," + taskDescription[2];
            break;
        }
        return databaseInput;
    }

    public static boolean isTaskValid(int taskNumber, ArrayList<Task> taskList) throws InvalidUserInputException {
        if (taskNumber > taskList.size() || taskNumber <= 0) {
            throw new InvalidUserInputException(INVALID_TASK);
        }
        return true;
    }

    public static void checkValidityOfInput(String userInput) throws InvalidUserInputException {
        String[] arrayOfUserInput = userInput.split(" ");
        if (arrayOfUserInput.length == 1 && arrayOfUserInput[0].equalsIgnoreCase("list")) {
            return;
        } else if (arrayOfUserInput.length > 1 && arrayOfUserInput[0].equalsIgnoreCase("list")) {
            throw new InvalidUserInputException(INVALID_INPUT);
        } else if (arrayOfUserInput.length <= 1 && isCommandValid(arrayOfUserInput[0])) { //todo, deadline, event with no parameters
            throw new InvalidUserInputException(NO_DESCRIPTION);
        } else if ((arrayOfUserInput.length <= 1) || !isCommandValid(arrayOfUserInput[0])) {
            throw new InvalidUserInputException(INVALID_INPUT);
        } else {
            return;
        }
    }

    public static Boolean isCommandValid(String command) {
        if (command.equalsIgnoreCase("todo") || command.equalsIgnoreCase("event")
                || command.equalsIgnoreCase("deadline") || command.equalsIgnoreCase("delete")
                || command.equalsIgnoreCase("mark") || command.equalsIgnoreCase("unmark")) {
            return true;
        }
        return false;
    }
}
