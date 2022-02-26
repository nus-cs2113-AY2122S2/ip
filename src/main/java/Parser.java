import em.exception.InvalidUserInputException;
import task.Task;
import java.util.ArrayList;
import static em.exception.InvalidUserInputException.*;

public class Parser {
    /**
     * Returns the command to be added into the database file.
     * Take in an incomplete command and formulate it to be complete so
     * it can be written into the file storing all the task created.
     *
     * @param taskDescription list to store the task description (task type, task description, task timing)
     * @return formulated input to be added into the database.
     */
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

    /**
     * Check whether the task exist in the list by specifying the number of the task.
     * The number of the task is corresponding to the sequence it is being added into
     * the task list.
     *
     * @param taskNumber numbering of task in the task list.
     * @param taskList list storing all the tasks created.
     * @return true if stated task number exist in the taskList.
     * @return false if stated task number does not exist in the taskList.
     * @throws InvalidUserInputException(INVALID_TASK) If the task number stated does not exist.
     */
    public static boolean isTaskValid(int taskNumber, ArrayList<Task> taskList) throws InvalidUserInputException {
        if (taskNumber > taskList.size() || taskNumber <= 0) {
            throw new InvalidUserInputException(INVALID_TASK);
        }
        return true;
    }

    /**
     * Check the validity of yser input to ensure the input can be processed.
     * Check whether user input contains the necessary contents for the command.
     * If the user input is invalid, a message error will be thrown.
     *
     * @param userInput full command [command type + task description] entered by the user.
     * @throws InvalidUserInputException(INVALID_INPUT) If the command type is not valid.
     * @throws InvalidUserInputException(NO_DESCRIPTION) If task description is not inputted.
     */
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

    /**
     * Returns the validity of command type.
     * Ensure that the command type entered by the user can
     * be processed.
     *
     * @param command command type entered by user
     * @return true if command entered is valid
     * @return false if command entered is invalid
     */
    public static Boolean isCommandValid(String command) {
        if (command.equalsIgnoreCase("todo") || command.equalsIgnoreCase("event")
                || command.equalsIgnoreCase("deadline") || command.equalsIgnoreCase("delete")
                || command.equalsIgnoreCase("find") || command.equalsIgnoreCase("mark")
                || command.equalsIgnoreCase("unmark")) {
            return true;
        }
        return false;
    }
}
