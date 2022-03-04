package duke;

import duke.task.Task;

public class unknownCommand extends Command {

    @Override
    public void execute(TaskList task, Ui ui, Storage storage) {
        System.out.println("I don't understand");
    }
}
