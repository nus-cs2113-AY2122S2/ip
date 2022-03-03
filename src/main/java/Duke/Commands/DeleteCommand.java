package Duke.Commands;

import Duke.*;
import Duke.Storage.Storage;
import Duke.Tasks.TaskList;
import Duke.Ui.Ui;

public class DeleteCommand extends Command {
    private int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void executeCommand(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        System.out.println(Ui.DISPLAY_LINE + System.lineSeparator()
                    + "Okay! I have removed this task from the list!");
        System.out.println(tasks.get(index));
        tasks.delete(index);
        System.out.println("You have " + tasks.size() + " items left in the list:)");
        System.out.println(Ui.DISPLAY_LINE);
    }

}
