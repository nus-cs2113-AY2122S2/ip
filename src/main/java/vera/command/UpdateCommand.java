package vera.command;

import vera.Storage;
import vera.TaskList;
import vera.Ui;

import static vera.constant.Indexes.TASK_DATE_INDEX;
import static vera.constant.Messages.ERROR_SYSTEM_FAULT_MESSAGE;

public class UpdateCommand extends Command {
    String[] toAdd;

    public UpdateCommand(String[] filteredTaskContent) {
        toAdd = filteredTaskContent;
    }

    public boolean isTaskBeingReplaced(Ui ui) {
        boolean isOldTaskReplaced = false;
        ui.showToUser("Oops! It seems that you've already added this task.\n"
                + "Would you like to override the\nexisting time and/or date "
                + "with the new input? (Y/N)");
        ui.showLine();
        while (true) {
            ui.showLine();
            String input = ui.readCommand();
            if (input.trim().equalsIgnoreCase("Y")
                    || input.trim().equalsIgnoreCase("Yes")) {
                isOldTaskReplaced = true;
                System.out.println("Understood. Proceeding to change"
                        + "\nthe old task with the new one..........");
                break;
            }
            if (input.trim().equalsIgnoreCase("N")
                    || input.trim().equalsIgnoreCase("No")) {
                System.out.println("Okay, we'll keep it as it is.");
                break;
            }
            ui.showToUser("Please confirm your choice with either Y (Yes) or N (No).");
            ui.showLine();
        }
        return isOldTaskReplaced;
    }

    public void execute(TaskList taskList, Ui ui, Storage storage) {
        if (isTaskBeingReplaced(ui)) {
            int taskIndexToReplace = taskList.findIndexToReplace(toAdd);
            if (taskIndexToReplace == -1) {
                System.out.println(ERROR_SYSTEM_FAULT_MESSAGE);
                return;
            }
            taskList.replaceTaskDate(taskIndexToReplace, toAdd[TASK_DATE_INDEX].trim(), ui);
            storage.rewriteSavedState(taskList);
        }
    }
}
