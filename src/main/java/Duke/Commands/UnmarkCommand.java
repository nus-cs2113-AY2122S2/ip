package Duke.Commands;

import Duke.Storage.Storage;
import Duke.Tasks.TaskList;
import Duke.Ui.Ui;

public class UnmarkCommand extends Command {
    private int index;

    public UnmarkCommand(int index) {
        this.index = index;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void executeCommand(TaskList tasks, Ui ui, Storage storage) {
        tasks.unMarkItem(index);
        System.out.println(Ui.DISPLAY_LINE + System.lineSeparator() + "OK, I've marked this task as not done yet:");
        System.out.println(tasks.get(index));
        System.out.println(Ui.DISPLAY_LINE);
    }

}
