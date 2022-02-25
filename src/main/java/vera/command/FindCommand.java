package vera.command;

import vera.Storage;
import vera.TaskList;
import vera.Ui;
import vera.exception.InputEmptyException;

public class FindCommand extends Command {
    private String findTaskByDescription;
    private String findTaskByDate;

    public static final String COMMAND_WORD = "find";
    public static final String MESSAGE_USAGE = "Find: Finds related task descriptions \nbased on the given input.\n"
            + "\nTo search for a task, \nenter 'find <keyword>', e.g. 'find book'";


    public FindCommand(String taskDescription, String taskDate) throws InputEmptyException {
        if (taskDate == null) {
            if (taskDescription.isBlank()) {
                throw new InputEmptyException();
            }
            findTaskByDescription = taskDescription;
        }
        findTaskByDate = taskDate;
    }


    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        if (findTaskByDate == null) {
            taskList.findByTaskDescription(findTaskByDescription, ui);
        } else {
            taskList.findByTaskDate(findTaskByDate, ui);
        }
    }
}
