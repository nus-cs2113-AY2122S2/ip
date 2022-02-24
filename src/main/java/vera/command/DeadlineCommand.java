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

public class DeadlineCommand extends Command {
    String[] toAddTaskContent;
    LocalDateTime toAddTaskDate;

    public static final String COMMAND_WORD = "deadline";
    public static final String MESSAGE_USAGE = "Deadline: Adds a 'deadline' task "
            + "into the task list. \nA 'deadline' contains both a task description \nand a date "
            + "to finish the task by.\n\nTo execute the command,\nenter 'deadline <task_description> "
            + "/by <task_date>'.\nE.g. deadline return book /by Sunday.";

    public DeadlineCommand(String[] filteredTaskContent, TaskList tasklist, LocalDateTime dateInput)
            throws InputEmptyException, InputRepeatedException {
        if (filteredTaskContent[TASK_DESCRIPTION_INDEX].isBlank()) {
            throw new InputEmptyException();
        }
        if (tasklist.isTaskAlreadyAdded(filteredTaskContent[TASK_DESCRIPTION_INDEX].trim())) {
            throw new InputRepeatedException();
        }
        toAddTaskDate = dateInput;
        toAddTaskContent = filteredTaskContent;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        String filteredTaskDate;
        if (toAddTaskDate != null) {
            filteredTaskDate = toAddTaskDate.format(DateTimeFormatter.ofPattern("dd MMM yyyy, EEE hh:mm a"));
        } else {
            filteredTaskDate = toAddTaskContent[TASK_DATE_INDEX].trim();
        }
        taskList.addTask(toAddTaskContent[TASK_DESCRIPTION_INDEX].trim(), filteredTaskDate, COMMAND_WORD);
        storage.appendToFile(toAddTaskContent[TASK_DESCRIPTION_INDEX].trim()
                , filteredTaskDate, "0", "D");
    }
}
