package command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import task.Task;

public class FindCommand extends Command {
    private final String searchKey;

    public FindCommand(String searchKey) {
        this.searchKey = searchKey;
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        String result = new String();
        boolean isExist = false;
        for(int i = 1; i <= taskList.size(); i++) {
            if(taskList.getTask(i).getDescription().contains(searchKey)) {
                result += taskList.getTask(i).toString();
                isExist = true;
            }
        }
        if(isExist) {
            return ui.generateResponse("Here are the matching tasks in your list:\n" + result);
        }
        else {
            return ui.generateResponse("There are no matching tasks in your list.\n");
        }
    }
}
