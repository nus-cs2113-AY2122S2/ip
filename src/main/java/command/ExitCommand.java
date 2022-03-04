package command;

import controller.Storage;
import controller.TaskList;
import controller.UI;

public class ExitCommand extends Command{
    /**
     * Show Bye message.
     * Save the task list data into file.
     * @param tasks task list.
     * @param ui user interface.
     * @param storage storage.
     */
    public  void execute(TaskList tasks, UI ui, Storage storage){
        ui.showBye();
        storage.save(tasks.getTaskList());
    }
    public boolean isExit(){
        return true;
    }
}
