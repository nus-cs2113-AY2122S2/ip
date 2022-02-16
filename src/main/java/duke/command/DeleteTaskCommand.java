package duke.command;

import duke.Duke;
import duke.exception.DukeException;
import duke.exception.DukeExceptionCause;

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
