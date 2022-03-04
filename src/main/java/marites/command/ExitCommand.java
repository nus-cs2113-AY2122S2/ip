package marites.command;

import marites.Storage;
import marites.TaskList;
import marites.Ui;

/**
 * Class for representing exit commands.
 */
public class ExitCommand extends Command {
    /**
     * Returns whether this Command is an ExitCommand.
     * @return Exit or not
     */
    @Override
    public boolean isExit() {
        return true;
    }

    /**
     * Executes the ExitCommand.
     * @param storage A Storage instance for handling storage
     * @param ui A Ui instance for providing output
     * @param taskList A TaskList instance for managing tasks
     */
    @Override
    public void execute(Storage storage, Ui ui, TaskList taskList) {
        ui.showExitMessage();
    }
}
