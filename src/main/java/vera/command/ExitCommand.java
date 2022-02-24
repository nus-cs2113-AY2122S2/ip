package vera.command;

import vera.Storage;
import vera.TaskList;
import vera.Ui;

public class ExitCommand extends Command {

    public static final String COMMAND_WORD = "bye";
    public static final String MESSAGE_USAGE = "Bye: Exits the program.";

    public static boolean isExit(Command c) {
        return c instanceof ExitCommand;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.showGoodbyeMessage();
    }
}
