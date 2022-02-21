package vera.command;

import vera.Storage;
import vera.TaskList;
import vera.Ui;
import vera.exception.InputEmptyException;
import vera.exception.InputRepeatedException;

import static vera.constant.Indexes.TASK_DATE_INDEX;
import static vera.constant.Indexes.TASK_DESCRIPTION_INDEX;

public class EventCommand extends Command {
    String[] toAdd;

    public static final String COMMAND_WORD = "event";
    public static final String MESSAGE_USAGE = "Event: Adds an 'event' task into the task list.\n"
            + "An 'event' contains both a task description \nand a date "
            + "of when the event will happen. \n\nTo execute the command,\n"
            + "enter 'event <task_description> /at <task_date>'.\n"
            + "E.g. event project meeting /at 6th Aug 2-4pm.";

    public EventCommand(String[] filteredTaskContent,TaskList tasklist)
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
                toAdd[TASK_DATE_INDEX].trim(), "0", "E");
    }
}
