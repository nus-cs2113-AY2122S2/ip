package duke.command;

import duke.exception.DukeException;
import duke.exception.DukeExceptionCause;

/**
 * Converts the users input into a UpdateTaskStatusCommand object.
 * An UpdateTaskStatusCommand object contains the index
 * of the task within the task list that the user wants to update
 * as well as the new status that the user wants to update the task to.
 */

public class UpdateTaskStatusCommand extends Command {
    private int taskIndex;
    private boolean isTaskDone;

    public UpdateTaskStatusCommand(String userInput, boolean isTaskDone) throws DukeException {
        super(CommandType.UpdateTaskStatusCommand);
        this.taskIndex = extractTaskIndex(userInput, isTaskDone);
        this.isTaskDone = isTaskDone;
    }

    private int extractTaskIndex(String userInput, boolean isTaskDone) throws DukeException {
        String taskIndexStringVersion;
        int taskIndex = 0;
        final int ARRAY_INDEX_OFFSET = 1;
        if (isTaskDone == true) {
            taskIndexStringVersion = userInput.replace("mark", "");
        } else {
            taskIndexStringVersion = userInput.replace("unmark", "");
        }
        taskIndexStringVersion = taskIndexStringVersion.trim();
        if (taskIndexStringVersion.isEmpty()) {
            throw new DukeException(DukeExceptionCause.EmptyTaskIndex);
        }
        try {
            taskIndex = Integer.parseInt(taskIndexStringVersion) - ARRAY_INDEX_OFFSET;
        } catch (NumberFormatException ne) {
            throw new DukeException(DukeExceptionCause.InvalidTaskIndex);
        }

        return taskIndex;
    }

    public int getTaskIndex() {
        return taskIndex;
    }

    public boolean isTaskDone() {
        return isTaskDone;
    }
}
