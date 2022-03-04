package marites.command;

import marites.Storage;
import marites.TaskList;
import marites.Ui;
import marites.exception.MaritesException;
import marites.task.Task;

import java.util.ArrayList;

/**
 * Class for representing find commands.
 */
public class FindCommand extends Command {

    private final String query;

    public FindCommand(String query) {
        this.query = query;
    }

    /**
     * Executes the FindCommand.
     * @param storage A Storage instance for handling storage
     * @param ui A Ui instance for providing output
     * @param taskList A TaskList instance for managing tasks
     */
    @Override
    public void execute(Storage storage, Ui ui, TaskList taskList) {
        ArrayList<Task> matchingTasks = taskList.findTasks(query);
        ui.showFindResultMessage(taskList, matchingTasks);
    }
}
