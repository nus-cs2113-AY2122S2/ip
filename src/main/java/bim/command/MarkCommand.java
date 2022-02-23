package bim.command;

import bim.Bim;
import bim.BimException;
import bim.Storage;
import bim.Ui;
import bim.task.TaskList;

/**
 * Mark or unmark depending on <code>type</code> the task with the specified index in the task list.
 */
public class MarkCommand extends Command {
    private static final String ERROR_INDEX = "Invalid index!";
    private static final String TYPE_MARK = "mark";

    private String type;
    private int index;

    public MarkCommand(String type, int index) {
        this.index = index;
        this.type = type;
    }

    /**
     * Mark or unmark the task specified by the user.
     *
     * @param tasks   The list of tasks
     * @param ui
     * @param storage
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            storage.modifyData(type, index);

            if (type.equals(TYPE_MARK)) {
                tasks.markTask(index);
                ui.printMarkTaskMessage(tasks.getTask(index));
            }
            else {
                tasks.unmarkTask(index);
                ui.printUnmarkTaskMessage(tasks.getTask(index));
            }
        } catch (BimException exception) {
            ui.printErrorMessage(exception.getMessage());
        } catch (IndexOutOfBoundsException invalidIndex) {
            ui.printErrorMessage(ERROR_INDEX);
        }
    }
}
