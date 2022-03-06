package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;


public class FindCommand extends Command {
    private String searchTerm;

    public FindCommand(String searchTerm) {
        super();
        this.searchTerm = searchTerm;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printMatchingTasks(tasks, searchTerm);
    }
}