package boba.command;

import boba.data.Storage;
import boba.response.Ui;
import boba.task.TaskList;

/**
 * Class for find command
 */
public class FindCommand extends Command{

    /** keyword tasks must contain */
    private String keyword;

    /**
     * Constructor for FindCommand
     * @param keyword
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Finds the tasks that contains the keyword
     * @param tasks List of all tasks
     * @param ui UI of Boba
     * @param storage Save file of list
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        TaskList validTasks = tasks.findTask(keyword);
        ui.printFindTask(validTasks);
    }

    /**
     * @return Not ExitCommand return false
     */
    @Override
    public boolean isExit() {
        return false;
    }

}
