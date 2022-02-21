package vera.command;

import vera.Storage;
import vera.TaskList;
import vera.Ui;
import vera.exception.InputEmptyException;
import vera.exception.InputRepeatedException;

import static vera.constant.Indexes.TASK_DATE_INDEX;
import static vera.constant.Indexes.TASK_DESCRIPTION_INDEX;

public class DeadlineCommand extends Command {
    String[] toAdd;

    public static final String COMMAND_WORD = "deadline";
    public static final String MESSAGE_USAGE = "Deadline: Adds a 'deadline' task "
            + "into the task list. \nA 'deadline' contains both a task description \nand a date "
            + "to finish the task by.\n\nTo execute the command,\nenter 'deadline <task_description> "
            + "/by <task_date>'.\nE.g. deadline return book /by Sunday.";

    public DeadlineCommand(String[] filteredTaskContent, TaskList tasklist)
            throws InputEmptyException, InputRepeatedException {
        if (filteredTaskContent[TASK_DESCRIPTION_INDEX].isBlank() ||
                filteredTaskContent[TASK_DATE_INDEX].isBlank()) {
            throw new InputEmptyException();
        }
        if (tasklist.isTaskAlreadyAdded(filteredTaskContent[TASK_DESCRIPTION_INDEX].trim())) {
            throw new InputRepeatedException();
        }
        toAdd = filteredTaskContent;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.addTask(toAdd[TASK_DESCRIPTION_INDEX].trim(),
                toAdd[TASK_DATE_INDEX].trim(), COMMAND_WORD);
        storage.appendToFile(toAdd[TASK_DESCRIPTION_INDEX].trim(),
                toAdd[TASK_DATE_INDEX].trim(), "0", "D");
    }
}
