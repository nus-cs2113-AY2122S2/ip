package boba.command;

import boba.data.Storage;
import boba.exception.BobaException;
import boba.response.Ui;
import boba.task.Deadline;
import boba.task.TaskList;

/**
 * Class for deadline command
 */
public class DeadlineCommand extends Command {

    /** Deadline item to be added to taskList*/
    private Deadline deadline;

    /**
     * Constructor for DeadlineCommand
     * @param description
     * @param by
     */
    public DeadlineCommand(String description, String by) {
        deadline = new Deadline(description, by);
    }

    /**
     * Add a deadline item to taskList
     * @param tasks List of all tasks
     * @param ui UI of Boba
     * @param storage Save file of list
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            tasks.addTask(deadline);
            ui.printAddSuccess(deadline, tasks.size());
            storage.save(tasks);
        } catch (BobaException e) {
            ui.printLimitError();
        }
    }

    /**
     * @return Not ExitCommand return false
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
