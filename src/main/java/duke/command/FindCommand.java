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

    /**
     * @param ui user interface to execute to.
     * @param tasks task list to execute on.
     * @param storage storage (save file) to execute on.
     */
    @Override
    public void execute(Ui ui, TaskList tasks, Storage storage) {
        tasks.findTask(keyword);
    }

    /**
     * Determines if the program should terminate after executing this command.
     * @return false as FindCommand should not terminate the program.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
