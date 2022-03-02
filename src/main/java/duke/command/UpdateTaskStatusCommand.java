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
        super(CommandType.UPDATETASKSTATUS);
        this.taskIndex = extractTaskIndex(userInput, isTaskDone);
        this.isTaskDone = isTaskDone;
    }

    private int extractTaskIndex(String userInput, boolean isTaskDone) throws DukeException {
        String taskIndexStringVersion;
        int taskIndexStartPosition = 0;
        int taskIndex = 0;
        if (isTaskDone == true) {
            taskIndexStringVersion = userInput.replace("mark", "");
        } else {
            taskIndexStringVersion = userInput.replace("unmark", "");
        }
        taskIndexStringVersion = taskIndexStringVersion.trim();
        if (taskIndexStringVersion.isEmpty()) {
            throw new DukeException(DukeExceptionCause.EMPTYTASKINDEX);
        }
        try {
            taskIndex = Integer.parseInt(taskIndexStringVersion) - 1;
        } catch (NumberFormatException ne) {
            throw new DukeException(DukeExceptionCause.INVALIDTASKINDEX);
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
