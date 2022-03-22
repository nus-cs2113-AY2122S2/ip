package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Represents a Find Command to show items that
 * contain the search term a user entered
 */
public class FindCommand extends Command {
    public static final String COMMAND_WORD = "find";
    public static final String MESSAGE_SUCCESS = "Here are the matching tasks in your list:";
    private final String keyword;

    public FindCommand(String keyword) {
        super();
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showToUser(
                MESSAGE_SUCCESS,
                tasks.getAllTasksUi());
    }
}
