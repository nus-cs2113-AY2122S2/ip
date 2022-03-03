package marites.command;

import marites.Storage;
import marites.TaskList;
import marites.Ui;
import marites.exception.MaritesException;
import marites.task.Task;

import java.util.ArrayList;

public class FindCommand extends Command {

    private final String query;

    public FindCommand(String query) {
        this.query = query;
    }

    @Override
    public void execute(Storage storage, Ui ui, TaskList taskList) {
        ArrayList<Task> matchingTasks = taskList.findTasks(query);
        ui.showFindResultMessage(taskList, matchingTasks);
    }
}
