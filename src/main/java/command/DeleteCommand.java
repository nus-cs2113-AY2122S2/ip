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
