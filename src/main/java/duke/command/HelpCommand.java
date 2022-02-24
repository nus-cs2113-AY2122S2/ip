package duke.command;

import duke.Display;
import duke.Storage;
import duke.task.TaskList;

/**
 * Represents a Command where the user wishes to see a full list of available commands
 */
public class HelpCommand extends Command {
    /**
     * Runs the command and prints a full list of available commands for the user
     *
     * @param ui      A Display object to manage printing of errors and other messages
     * @param tasks   A TaskList object with which the command may process or modify
     * @param storage A Storage object to manage the save file of the specified TaskList object if necessary
     */
    @Override
    public void run(Display ui, TaskList tasks, Storage storage) {
        ui.printHelpMessage();
    }
}
