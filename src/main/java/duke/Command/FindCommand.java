package duke.Command;

import duke.TaskList;
import duke.UI;
import duke.save;

public class FindCommand extends Command {
    private String searchTerm;
    public FindCommand(String searchTerm) {
        super();
        this.searchTerm = searchTerm;
    }
    public void execute(TaskList tasks, UI ui, save save) {
        ui.printMatchingTasks(tasks, searchTerm);
    }
}