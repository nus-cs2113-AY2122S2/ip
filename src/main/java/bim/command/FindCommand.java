package bim.command;

import bim.Storage;
import bim.Ui;
import bim.task.TaskList;

public class FindCommand extends Command {
    private String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printSearchResult(tasks.findTask(keyword));
    }
}
