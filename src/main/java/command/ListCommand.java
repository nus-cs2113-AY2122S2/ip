package command;


import controller.Storage;
import controller.TaskList;
import controller.UI;

public class ListCommand extends Command {
    private static final String INDENT = "    ";

    /**
     * Print out all task in the task list.
     * @param taskList task list.
     * @param ui user interface.
     * @param storage storage.
     */
    public void execute(TaskList taskList, UI ui, Storage storage){
        if(taskList.getCount() == 0){
            ui.showEmptyList();
            return;
        }
        ui.showList();
        for(int i = 0; i<taskList.getCount(); i++){
            System.out.println(INDENT+(i+1) + "." + taskList.getTaskByIdx(i));
        }
    }
    public  boolean isExit(){
        return false;
    }
}
