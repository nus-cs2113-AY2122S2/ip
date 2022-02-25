package vera.command;

import vera.Storage;
import vera.TaskList;
import vera.Ui;
import vera.exception.InputEmptyException;
import vera.exception.InputRepeatedException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static vera.constant.Indexes.TASK_DATE_INDEX;
import static vera.constant.Indexes.TASK_DESCRIPTION_INDEX;
import static vera.constant.Messages.DATE_FORMAT_WITH_TIME;

public class DeadlineCommand extends Command {
    String toAddTaskContent;
    String toAddTaskDate;

    public static final String COMMAND_WORD = "deadline";
    public static final String MESSAGE_USAGE = "Deadline: Adds a 'deadline' task "
            + "into the task list. \nA 'deadline' contains both a task description \nand a date "
            + "to finish the task by.\n\nTo execute the command,\nenter 'deadline <task_description> "
            + "/by <task_date>'.\nE.g. deadline return book /by 2022/02/03.\n\n"
            + "All deadline dates must be given in the format:\n"
            + "\t\tyyyy/MM/dd [HH:mm], where\n"
            + "year is in 4 digits, month and day in two digits, and an\noptional time in 24 hour format.";

    public DeadlineCommand(String taskDescription, TaskList tasklist, String dateInput)
            throws InputEmptyException, InputRepeatedException {
        if (taskDescription.isBlank()) {
            throw new InputEmptyException();
        }
        if (tasklist.isTaskAlreadyAdded(taskDescription)) {
            throw new InputRepeatedException();
        }
        toAddTaskDate = dateInput;
        toAddTaskContent = taskDescription;
    }


    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.addTask(toAddTaskContent, toAddTaskDate, COMMAND_WORD);
        storage.appendToFile(toAddTaskContent, toAddTaskDate, "0", "D");
    }
}
