package command;

import controller.Storage;
import controller.TaskList;
import controller.UI;

public class UpdateCommand extends Command{
    private String type;
    private int idx;
    public UpdateCommand(String type, int idx){
        super();
        this.type = type;
        this.idx = idx;
    }
    public void execute(TaskList tasks, UI ui, Storage storage){
        String t;
        if(type.equals("mark")){
            t = tasks.markTaskByIdx(idx);
        }else{
            t = tasks.unmarkTaskByIdx(idx);
        }
        ui.showTask(t);
        storage.save(tasks.getTaskList());
    }
    public boolean isExit(){
        return false;
    }
}
