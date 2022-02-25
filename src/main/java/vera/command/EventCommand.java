package vera.command;

import vera.Storage;
import vera.TaskList;
import vera.Ui;
import vera.exception.InputEmptyException;
import vera.exception.InputRepeatedException;

public class EventCommand extends Command {
    String toAddTaskDescription;
    String toAddTaskDate;

    public static final String COMMAND_WORD = "event";
    public static final String MESSAGE_USAGE = "Event: Adds an 'event' task into the task list.\n"
            + "An 'event' contains both a task description \nand a date "
            + "of when the event will happen. \n\nTo execute the command,\n"
            + "enter 'event <task_description> /at <task_date>'.\n"
            + "E.g. event project meeting /at 2022/08/06 1400.\n"
            + "\nAll event dates must be given in the format:"
            + "\n\t\tyyyy/MM/dd [HH:mm], where\n"
            + "year is in 4 digits, month and day in two digits, and an \noptional time in 24 hour format.";

    public EventCommand(String taskDescription, TaskList tasklist, String dateInput)
            throws InputEmptyException, InputRepeatedException {
        if (taskDescription.isBlank()) {
            throw new InputEmptyException();
        }
        if (tasklist.isTaskAlreadyAdded(taskDescription)) {
            throw new InputRepeatedException();
        }
        toAddTaskDate = dateInput;
        toAddTaskDescription = taskDescription;
    }


    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.addTask(toAddTaskDescription, toAddTaskDate, COMMAND_WORD);
        storage.appendToFile(toAddTaskDescription, toAddTaskDate, "0", "E");
    }
}
