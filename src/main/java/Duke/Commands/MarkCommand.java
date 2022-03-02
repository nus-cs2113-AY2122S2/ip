package Duke.Commands;

import Duke.Storage.Storage;
import Duke.Tasks.TaskList;
import Duke.Ui.Ui;

/**
 * Represents the command to mark a task.
 */
public class MarkCommand extends Command {
    private int index;

    public MarkCommand(int index) {
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
        tasks.markItem(index);
        System.out.println(Ui.DISPLAY_LINE + System.lineSeparator() + "Nice! I've marked this as done:");
        System.out.println(tasks.get(index));
        System.out.println(Ui.DISPLAY_LINE);
    }

}
