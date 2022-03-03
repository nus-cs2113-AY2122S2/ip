package Duke.Commands;

import Duke.Storage.Storage;
import Duke.Tasks.TaskList;
import Duke.Tasks.Deadline;
import Duke.Ui.Ui;

public class DeadlineCommand extends Command {
    private Deadline deadline;

    public DeadlineCommand(String description, boolean isDone, String date) {
        deadline = new Deadline(description, isDone, date);
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void executeCommand(TaskList tasks, Ui ui, Storage storage) {
        tasks.add(deadline);
        System.out.println(Ui.DISPLAY_LINE  + System.lineSeparator() + "Okay! I've added this task:");
        System.out.println(deadline);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.\n" + Ui.DISPLAY_LINE);
    }

}
