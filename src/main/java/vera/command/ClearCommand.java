package vera.command;

import vera.Storage;
import vera.TaskList;
import vera.Ui;

public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_USAGE = "Clear: Deletes all tasks in the list.\n"
            + "To execute the command, enter 'clear'.";

    @Override
    public void execute (TaskList taskList, Ui ui, Storage storage) {
        ui.showToUser("Are you sure you want to delete all tasks?\n"
                + "You will not be able to recover them after deleting. [Y/N]");
        ui.showLine();
        while (true) {
            String input = ui.readCommand();
            ui.showLine();
            if (input.trim().equalsIgnoreCase("Y")
                    || input.trim().equalsIgnoreCase("Yes")) {
                ui.showToUser("Understood. Proceeding to delete"
                        + "\nall current tasks in the list..........");
                taskList.deleteAllTasks(ui);
                break;
            }
            if (input.trim().equalsIgnoreCase("N")
                    || input.trim().equalsIgnoreCase("No")) {
                ui.showToUser("Okay, we'll keep it as it is.");
                break;
            }
            ui.showToUser("Please confirm your choice with either Y (Yes) or N (No).");
            ui.showLine();
        }
        storage.rewriteSavedState(taskList);
    }
}
