package command;

import controller.Storage;
import controller.TaskList;
import controller.UI;

public class DeleteCommand extends Command{
    int idx;
    public DeleteCommand(int i) {
        super();
        idx = i;
    }

    /**
     * Remove task from task list according to the input
     * Show task count
     * Save the task list data into file
     * @param tasks task list
     * @param ui user interface
     * @param storage storage.
     */

    public void execute(TaskList tasks, UI ui, Storage storage){
        ui.showDelete();
        tasks.removeTaskByIdx(idx);
        ui.showList();
        ui.showTaskCount(tasks.getCount());
        storage.save(tasks.getTaskList());
    }

    public boolean isExit(){
        return false;
    }
}
