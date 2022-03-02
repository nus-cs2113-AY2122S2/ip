package Duke.Commands;

import Duke.Storage.Storage;
import Duke.Tasks.TaskList;
import Duke.Ui.Ui;

/**
 * Represents the command to unmark a task.
 */
public class UnmarkCommand extends Command {
    private int index;

    public UnmarkCommand(int index) {
        this.index = index;
    }

    /**
     * Indicates whether the program should exit.
     *
     * @return Indication on whether the program should exit.
     */
    public boolean isExit() {
        return false;
    }

    /**
     * Executes the command.
     *
     * @param tasks Tasklist containing of  the tasks.
     * @param ui    User interface of Duke.
     * @param storage   Storage of Duke.
     */
    public void executeCommand(TaskList tasks, Ui ui, Storage storage) {
        tasks.unMarkItem(index);
        System.out.println(Ui.DISPLAY_LINE + System.lineSeparator() + "OK, I've marked this task as not done yet:");
        System.out.println(tasks.get(index));
        System.out.println(Ui.DISPLAY_LINE);
    }

}
