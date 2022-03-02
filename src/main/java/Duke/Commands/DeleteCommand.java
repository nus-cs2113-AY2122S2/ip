package Duke.Commands;

import Duke.*;
import Duke.Storage.Storage;
import Duke.Tasks.TaskList;
import Duke.Ui.Ui;

/**
 * Represents the command to delete tasks.
 */
public class DeleteCommand extends Command {
    private int index;

    public DeleteCommand(int index) {
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
    public void executeCommand(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        System.out.println(Ui.DISPLAY_LINE + System.lineSeparator()
                    + "Okay! I have removed this task from the list!");
        System.out.println(tasks.get(index));
        tasks.delete(index);
        System.out.println("You have " + tasks.size() + " items left in the list:)");
        System.out.println(Ui.DISPLAY_LINE);
    }

}
