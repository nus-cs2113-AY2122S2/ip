package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class MarkCommand extends Command {
    private String taskToMark;

    public MarkCommand(String taskToMark) {
        this.taskToMark = taskToMark;
    }
    @Override
    public void execute(Ui ui, TaskList tasks, Storage storage) {
        tasks.doTask(taskToMark);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
