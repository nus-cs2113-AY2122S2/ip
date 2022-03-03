package boba.command;

import boba.data.Storage;
import boba.response.Ui;
import boba.task.TaskList;

public class FindCommand extends Command{

    private String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        TaskList validTasks = tasks.findTask(keyword);
        ui.printFindTask(validTasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }

}
