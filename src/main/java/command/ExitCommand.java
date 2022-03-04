package command;

import controller.Storage;
import controller.TaskList;
import controller.UI;

public class ExitCommand extends Command{
    public  void execute(TaskList tasks, UI ui, Storage storage){
        ui.showBye();
        storage.save(tasks.getTaskList());
    }
    public boolean isExit(){
        return true;
    }
}
