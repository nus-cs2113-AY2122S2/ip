package command;


import controller.Storage;
import controller.TaskList;
import controller.UI;

public class ListCommand extends Command {
    private static final String INDENT = "    ";

    public void execute(TaskList taskList, UI ui, Storage storage){
        ui.showList();
        for(int i = 0; i<taskList.getCount(); i++){
            System.out.println(INDENT+(i+1) + "." + taskList.getTaskByIdx(i));
        }
    }
    public  boolean isExit(){
        return false;
    }
}
