package boba.command;

import boba.data.Storage;
import boba.exception.BobaException;
import boba.response.Ui;
import boba.task.Deadline;
import boba.task.TaskList;

public class DeadlineCommand extends Command {

    private Deadline deadline;

    public DeadlineCommand(String description, String by) {
        deadline = new Deadline(description, by);
    }

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

    @Override
    public boolean isExit() {
        return false;
    }
}
