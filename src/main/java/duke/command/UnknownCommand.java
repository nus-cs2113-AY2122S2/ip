package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class UnknownCommand extends Command {

    @Override
    public void execute(TaskList task, Ui ui, Storage storage) {
        System.out.println("I don't understand");
    }
}
