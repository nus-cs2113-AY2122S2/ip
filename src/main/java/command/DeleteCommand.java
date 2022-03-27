package command;

import controller.Helper;
import controller.Storage;
import controller.TaskList;
import controller.UI;
import exception.DukeException;

public class DeleteCommand extends Command{
    private static final String INDENT = "    ";
    String command;
    public DeleteCommand(String command) {
        this.command=command;
    }

    /**
     * Remove task from task list according to the input
     * Show task count
     * Save the task list data into file
     * @param tasks task list
     * @param ui user interface
     * @param storage storage.
     */

    public void execute(TaskList tasks, UI ui, Storage storage) throws DukeException {
        String idxInString = command.replace("delete","").trim();
        Helper.checkIndex(idxInString,tasks);
        int idx = Integer.parseInt(idxInString)-1;
        ui.showDelete();
        ui.showTask(tasks.removeTaskByIdx(idx));
        ui.showList();
        for(int i = 0; i<tasks.getCount(); i++){
            System.out.println(INDENT+(i+1) + "." + tasks.getTaskByIdx(i));
        }
        ui.showTaskCount(tasks.getCount());
        storage.save(tasks.getTaskList());
    }

    public boolean isExit(){
        return false;
    }
}
