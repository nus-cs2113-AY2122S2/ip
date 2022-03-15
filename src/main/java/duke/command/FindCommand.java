package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Represents the command to find a task given a search term
 */
public class FindCommand extends Command {
    private String searchTerm;

    /**
     * Constructs the command
     * 
     * @param searchTerm
     *            String representation of the search term
     */
    public FindCommand(String searchTerm) {
        super();
        this.searchTerm = searchTerm;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printMatchingTasks(tasks, searchTerm);
    }
}
