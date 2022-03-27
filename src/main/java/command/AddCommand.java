package command;

import controller.Storage;
import controller.TaskList;
import controller.UI;
import exception.DukeException;
import exception.InvalidCommandException;
import exception.NoDescriptionException;
import exception.NoTimeException;
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

    public void execute(TaskList tasks, UI ui, Storage storage) throws DukeException {
        String description = "";
        String time = "";
        String[] info = new String[2];
        switch (action){
        case "todo":
            description = input.replace("todo", "").trim();
            if(description == null || input.isEmpty()){
                throw new NoDescriptionException();
            }
            tasks.addNewTodo(description);
            break;
        case "event":
            info = getInfo("event");
            description = info[0];
            time = info[1];
            tasks.addNewEvent(description,time);
            break;
        case "deadline":
            info = getInfo("deadline");
            description = info[0];
            time = info[1];
            tasks.addNewDeadline(description,time);
            break;
        default:
            throw new InvalidCommandException();
        }
        ui.showAdd();
        ui.showTask(tasks.getNewAdd());
        ui.showTaskCount(tasks.getCount());
        storage.save(tasks.getTaskList());
    }

    private String[] getInfo(String action) throws DukeException {
        int descriptionIdx = input.indexOf(" ");
        if(descriptionIdx == -1){
            throw new NoDescriptionException();
        }
        int timeIdx = 0;
        if(action.equals("event")) {
            timeIdx = input.indexOf("/at");
        }else if(action.equals("deadline")){
            timeIdx = input.indexOf("/by");
        }
        if(timeIdx == -1){
            throw new NoTimeException();
        }
        String description = input.substring(descriptionIdx+1, timeIdx).trim();
        String time = input.substring(timeIdx+4).trim();
        String[] res = new String[2];
        res[0] = description;
        res[1] = time;
        return res;
    }
    public boolean isExit(){
        return false;
    }

}
