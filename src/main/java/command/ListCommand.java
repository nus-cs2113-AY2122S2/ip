package command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class ListCommand extends Command {

    public ListCommand() {
        ;
    }
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        return ui.generateResponse(taskList.toString());
    }
}
