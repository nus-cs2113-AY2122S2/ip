package vera.command;

import vera.Storage;
import vera.TaskList;
import vera.Ui;
import vera.exception.InputEmptyException;

import static vera.constant.Indexes.DELETE_INDEX;

public class DeleteCommand extends Command {
    private int deleteIndex;

    public static final String COMMAND_WORD = "delete";
    public static final String MESSAGE_USAGE = "Delete: Deletes a task in the task list.\n"
            + "To delete a specific task, enter 'delete <list_index>'.\n\n Here, "
            + "'list_index' denotes the index of a task \n based on the task list under the command 'list'.\n"
            + "\nE.g., 'delete 2' deletes the second task in the task list.\n\n"
            + "Note: You can only delete one task per command input.";

    public DeleteCommand(String[] parsedInput, TaskList taskList) throws InputEmptyException {
        if (parsedInput[DELETE_INDEX].isBlank()) {
            throw new InputEmptyException();
        }
        if (taskList.isTaskExist(Integer.parseInt(parsedInput[DELETE_INDEX]) - 1)) {
            deleteIndex = Integer.parseInt(parsedInput[DELETE_INDEX]) - 1;
        }
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.removeTask(deleteIndex);
        storage.rewriteSavedState(taskList);
    }
}
