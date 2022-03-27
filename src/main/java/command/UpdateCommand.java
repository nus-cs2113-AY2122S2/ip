package command;

import controller.*;
import exception.DukeException;
import exception.InvalidIndexException;
import exception.NoDescriptionException;
import task.Task;

public class UpdateCommand extends Command{
    private String type;
    private String command;
    public UpdateCommand(String type, String fullCommand){
        super();
        this.type = type;
        command = fullCommand;
    }

    /**
     * Update the status of the task according to the input
     * Show the task being modified
     * Save the task list data into file
     * @param tasks task list
     * @param ui user interface
     * @param storage storage.
     */
    public void execute(TaskList tasks, UI ui, Storage storage) throws DukeException {
        String idxInString = command.replace(type,"").trim();
        Helper.checkIndex(idxInString,tasks);
        int idx = Integer.parseInt(idxInString)-1;
        String t = null;
        if(type.equals("mark")){
            t = tasks.markTaskByIdx(idx);
        }else{
            t = tasks.unmarkTaskByIdx(idx);
        }
        ui.showTask(t);
        ui.showList();
        storage.save(tasks.getTaskList());
    }
    public boolean isExit(){
        return false;
    }
}
