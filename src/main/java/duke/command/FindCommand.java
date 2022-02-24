package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * FindCommand documentation
 */
public class FindCommand extends Command {
    private final String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void execute(Ui ui, TaskList tasks, Storage storage) {
        tasks.findTask(keyword);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
