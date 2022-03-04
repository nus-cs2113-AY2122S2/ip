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
        super(CommandType.DeleteTaskCommand);
        taskIndex = extractTaskIndex(userInput);
    }

    private int extractTaskIndex(String userInput) throws DukeException {
        int taskIndex = 0;
        final int ARRAY_INDEX_OFFSET = 1;
        String taskIndexString = userInput.replace("delete", "");
        taskIndexString = taskIndexString.trim();
        if (taskIndexString.isEmpty()) {
            throw new DukeException(DukeExceptionCause.EmptyTaskIndex);
        }
        try {
            taskIndex = Integer.parseInt(taskIndexString) - ARRAY_INDEX_OFFSET;
        } catch (NumberFormatException ne) {
            throw new DukeException(DukeExceptionCause.InvalidTaskIndex);
        }
        return taskIndex;
    }

    public int getTaskIndex() {
        return taskIndex;
    }

}
