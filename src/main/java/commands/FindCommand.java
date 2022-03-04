package commands;

import exception.DukeException;
import storage.FileEditor;
import taskmanager.TaskManager;
import tasks.Task;
import tasks.Todo;
import ui.Ui;

import java.io.IOException;

public class FindCommand extends Command {
    public static final String COMMAND_WORD = "find";

    protected String searchDescription = "";

    public FindCommand(String userInput) {
        try {
            searchDescription = extractDescription(userInput);
        } catch (DukeException e) {
            System.out.println(Ui.EMPTY_SEARCH_DESCRIPTION_MESSAGE);
            Ui.showLine();
        }
    }

    @Override
    public void execute(TaskManager taskManager, FileEditor fileEditor) {
        boolean isEmptyTaskList = checkTaskListSize();
        if (isEmptyTaskList) {
            System.out.println(Ui.EMPTY_TASK_LIST_MESSAGE);
            Ui.showLine();
            return;
        }
        if (searchDescription.equals("")) {
            return;
        }
        System.out.println(Ui.SEARCH_RESULTS_MESSAGE);
        int numberOfTasks = 0;
        for (int i = 0; i < taskManager.getTaskCount(); i++) {
            Task task = taskManager.getTasks().get(i);
            boolean hasSearchDescription = task.getDescription().contains(searchDescription);
            if (hasSearchDescription) {
                numberOfTasks++;
                System.out.print((i+1) + ".");
                System.out.println(task);
            }
        }
        Ui.showSearchResultsCount(numberOfTasks);
    }
}
