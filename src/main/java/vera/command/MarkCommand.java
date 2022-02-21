package vera.command;

import vera.Storage;
import vera.TaskList;
import vera.Ui;

public class MarkCommand extends Command{
    public static final String COMMAND_WORD = "mark";
    public static final String MESSAGE_USAGE = "Mark: Marks a task as done. "
            + "\nTo mark a specific task, enter 'mark <list_index>'.\n\n Here, "
            + "'list_index' denotes the index of a task \n based on the task list under the command 'list'.\n"
            + "\nE.g., 'mark 1' marks the first task in the task list as done.\n\n"
            + "Note: You can only mark one task per command input.";

    private int markIndex;

    public MarkCommand(int markIndex, TaskList taskList) {
        if (taskList.isTaskExist(markIndex)) {
            this.markIndex = markIndex;
        }
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        if (taskList.isTaskDone(markIndex)) {
            System.out.println("This task has already been marked!");
            return;
        }
        taskList.markTask(markIndex);
        storage.rewriteSavedState(taskList);
    }
}
