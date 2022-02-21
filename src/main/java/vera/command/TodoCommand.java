package vera.command;

import vera.Storage;
import vera.TaskList;
import vera.Ui;
import vera.exception.InputEmptyException;
import vera.exception.InputRepeatedException;

import static vera.constant.Indexes.TASK_DESCRIPTION_INDEX_TODO;

public class TodoCommand extends Command {
    String[] toAdd;
    public static final String COMMAND_WORD = "todo";
    public static final String MESSAGE_USAGE = "Todo: Adds a 'todo' task into the task list."
            + "\nA 'todo' contains only a task description. \n\nTo add other features to your task, "
            + "such as date and time, \nuse either 'deadline' or 'event'.\n\nTo execute the command, \n"
            + "enter 'todo <task_description>', e.g. todo read book.";

    public TodoCommand(String[] toAdd, TaskList taskList)
            throws InputEmptyException, InputRepeatedException {
        if (toAdd[TASK_DESCRIPTION_INDEX_TODO].isBlank()) {
            throw new InputEmptyException();
        }
        if (taskList.isTaskAlreadyAdded(toAdd[TASK_DESCRIPTION_INDEX_TODO].trim())) {
            throw new InputRepeatedException();
        }
        this.toAdd = toAdd;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.addTask(toAdd[TASK_DESCRIPTION_INDEX_TODO].trim(), " ", COMMAND_WORD);
        storage.appendToFile(toAdd[TASK_DESCRIPTION_INDEX_TODO].trim(),
                " ", "0", "T");
    }
}
