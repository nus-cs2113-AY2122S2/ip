package vera.command;

import vera.Storage;
import vera.TaskList;
import vera.Ui;

public class ListCommand extends Command {
    public static final String COMMAND_WORD = "list";
    public static final String MESSAGE_USAGE = "List: Displays a list of tasks "
            + "added and shows \nwhether or not certain tasks are marked.";

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.printAllTasks(ui);
    }

}
