package Duke.Commands;

import Duke.Storage.Storage;
import Duke.Tasks.TaskList;
import Duke.Ui.Ui;

public class MarkCommand extends Command {
    private int index;

    public MarkCommand(int index) {
        this.index = index;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void executeCommand(TaskList tasks, Ui ui, Storage storage) {
        tasks.markItem(index);
        System.out.println(Ui.DISPLAY_LINE + System.lineSeparator() + "Nice! I've marked this as done:");
        System.out.println(tasks.get(index));
        System.out.println(Ui.DISPLAY_LINE);
    }

}
