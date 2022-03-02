package bim.command;

import bim.Storage;
import bim.Ui;
import bim.task.TaskList;

/**
 * Lists all tasks whose description contain a keyword.
 */
public class FindCommand extends Command {
    private String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Search the task list and print the results.
     *
     * @param tasks   The task list to be searched
     * @param ui      Ui object to print messages
     * @param storage Storage object for saving to disk.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printSearchResult(tasks.findTask(keyword));
    }
}
