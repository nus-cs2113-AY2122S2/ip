package duke.command;

import duke.storage.Storage;
import duke.task.TaskManager;
import duke.ui.Ui;

public class FindCommand extends Command {

    private String keyword;


    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskManager taskManager, Ui ui, Storage storage) {
        TaskManager relatedTaskManager = taskManager.findTask(this.keyword);
        if (relatedTaskManager.getNumOfTasks() == 0) {
             Ui.printWithDivider("Sorry, task(s) containing the word " + this.keyword +" not found.");
        }
        else {
            Ui.printWithDivider( "Here are the matching tasks in your list: \n"
                    + relatedTaskManager);
        }

    }
}
