package command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.DukeException;

public class ExitCommand extends Command {

    public ExitCommand() {
        ;
    }
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        storage.saveTaskList(taskList);
        return ui.generateResponse("Bye. Hope to see you again soon!\n");
    }
}
