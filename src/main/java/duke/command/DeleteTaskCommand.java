package duke.command;

import duke.exception.DukeException;
import duke.exception.DukeExceptionCause;


/**
 * Converts the users input into a DeleteTaskCommand object.
 * The DeleteTaskCommand object contains the index
 * of the task that the user wants to delete within the task list.
 */

public class DeleteTaskCommand extends Command {

    private int taskIndex;

    public DeleteTaskCommand(String userInput) throws DukeException {
        super(CommandType.DELETETASKS);
        try {
            taskIndex = extractTaskIndex(userInput);
        } catch (DukeException de) {
            throw de;
        }
    }

    private int extractTaskIndex(String userInput) throws DukeException {
        String taskIndexString = userInput.replace("delete", "").trim();
        if (taskIndexString.isEmpty()) {
            throw new DukeException(DukeExceptionCause.EMPTYTASKINDEX);
        }
        int taskIndex = 0;
        try {
            taskIndex = Integer.parseInt(taskIndexString);
        } catch (NumberFormatException ne) {
            throw new DukeException(DukeExceptionCause.INVALIDTASKINDEX);
        }
        return taskIndex;
    }

    public int getTaskIndex() {
        return taskIndex;
    }

}
