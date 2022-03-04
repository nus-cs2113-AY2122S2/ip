package command;

import controller.Storage;
import controller.TaskList;
import controller.UI;
import task.Task;
import task.Todo;

public class AddCommand extends Command {
    String input;
    String action;

    public AddCommand(String action, String input) {
        super();
        this.input = input;
        this.action =action;
    }

    /**
     * Add specific task into task list according to the input
     * Show task count
     * Save the task list data into file
     * @param tasks task list
     * @param ui user interface
     * @param storage storage.
     */

    public void execute(TaskList tasks, UI ui, Storage storage){
        String t = tasks.addTask(action,input);
        if(t == null){
            ui.showErrorTask();
            return;
        }
        ui.showAdd();
        ui.showTask(t);
        ui.showTaskCount(tasks.getCount());
        storage.save(tasks.getTaskList());
    }

    public boolean isExit(){
        return false;
    }

}
