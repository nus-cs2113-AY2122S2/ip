package vera.command;

import vera.Storage;
import vera.TaskList;
import vera.Ui;

import static vera.constant.Messages.ERROR_SYSTEM_FAULT_MESSAGE;

public class UpdateCommand extends Command {
    String taskDescriptionForSearchingTask;
    String toUpdateTaskDate;

    public UpdateCommand(String taskDescription, String dateInput) {
        taskDescriptionForSearchingTask = taskDescription;
        toUpdateTaskDate = dateInput;
    }

    public boolean isTaskBeingReplaced(Ui ui) {
        boolean isOldTaskReplaced = false;
        ui.showToUser("Oops! It seems that you've already added this task.\n"
                + "Would you like to override the\nexisting time and/or date "
                + "with the new input? [Y/N]");
        ui.showLine();
        while (true) {
            String input = ui.readCommand();
            ui.showLine();
            if (input.trim().equalsIgnoreCase("Y")
                    || input.trim().equalsIgnoreCase("Yes")) {
                isOldTaskReplaced = true;
                ui.showToUser("Understood. Proceeding to change"
                        + "\nthe old task with the new one..........");
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
        return isOldTaskReplaced;
    }

    public void execute(TaskList taskList, Ui ui, Storage storage) {
        if (isTaskBeingReplaced(ui)) {
            int taskIndexToReplace = taskList.findIndexToReplace(taskDescriptionForSearchingTask);
            if (taskIndexToReplace == -1) {
                System.out.println(ERROR_SYSTEM_FAULT_MESSAGE);
                return;
            }
            taskList.replaceTaskDate(taskIndexToReplace, toUpdateTaskDate, ui);
            storage.rewriteSavedState(taskList);
        }
    }
}
