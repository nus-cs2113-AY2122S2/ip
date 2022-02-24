package vera.command;

import vera.Storage;
import vera.TaskList;
import vera.Ui;

public class UnmarkCommand extends Command{
    public static final String COMMAND_WORD = "unmark";
    public static final String MESSAGE_USAGE = "Unmark: Marks a task as undone."
            + "\nTo unmark a specific task, enter 'unmark <list_index>'.\n\n Here, "
            + "'list_index' denotes the index of a task \n based on the task list under the command 'list'.\n"
            + "\nE.g., 'unmark 3' unmarks the third task in the task list.\n\n"
            + "Note: You can only unmark one task per command input.";

    private int markIndex;

    public UnmarkCommand(int markIndex, TaskList taskList) {
        if (taskList.isTaskExist(markIndex)) {
            this.markIndex = markIndex;
        }
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        if (!taskList.isTaskDone(markIndex)) {
            System.out.println("This task was already unmarked!");
            return;
        }
        taskList.unmarkTask(markIndex);
        storage.rewriteSavedState(taskList);
    }
}