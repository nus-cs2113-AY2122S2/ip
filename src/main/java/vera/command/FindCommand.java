package vera.command;

import vera.Storage;
import vera.TaskList;
import vera.Ui;

public class FindCommand extends Command {
    public static final String COMMAND_WORD = "find";
    public static final String MESSAGE_USAGE = "Find: Finds related task descriptions \nbased on the given input.\n"
            + "\nTo search for a task, \nenter 'find <keyword>', e.g. 'find book'";
    private String taskDescriptionKeyword;

    public FindCommand(String taskDescriptionKeyword) {
        this.taskDescriptionKeyword = taskDescriptionKeyword.trim();
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.findTasks(taskDescriptionKeyword, ui);
    }
}
