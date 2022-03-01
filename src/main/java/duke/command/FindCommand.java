package duke.command;

import duke.TaskList;
import duke.Ui;
import duke.exception.AdditionalException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.Task;

import java.util.ArrayList;

/**
 * Represents the find command which is to be executed.
 */
public class FindCommand extends Command {

    private String fullCommand;

    public FindCommand(String fullCommand) {
        this.fullCommand = fullCommand;
    }

    /**
     * Runs when there is a find command by searching through the list of tasks prints the tasks
     * that match the keyword which the user inputted.
     *
     * @param tasks The TaskList object that contains the list of tasks.
     * @param ui The user interface object that allows for printing of the list of tasks.
     * @param storage The storage object which allows for the saving of the tasks to the file.
     * @throws AdditionalException If there are no tasks.
     * @see AdditionalException
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws AdditionalException {
        String keyword = Parser.getDescription("find", fullCommand);
        if (keyword.length() < 1) {
            throw new AdditionalException("You want me to find nothing or everything...?");
        }
        ArrayList<Task> listOfTasks = getTasks(tasks, ui, keyword);
        ui.showList(listOfTasks);
    }

    /**
     * Searches through the list of tasks and returns the list of tasks that contain the keyword.
     *
     * @param tasks The TaskList object that contains the list of tasks.
     * @param ui The user interface object that allows for printing when no results are found.
     * @param keyword The keyword to be searched for.
     * @return The list of tasks that contain the keyword.
     */
    private ArrayList<Task> getTasks(TaskList tasks, Ui ui, String keyword) {
        ArrayList<Task> listOfTasks = new ArrayList<>();
        for (int i = 0; i < tasks.getSize(); i++) {
            Task task = tasks.getTask(i);
            addTaskIfContains(keyword, listOfTasks, task);
        }
        if (listOfTasks.isEmpty()) {
            ui.showNoResults();
        }
        return listOfTasks;
    }

    /**
     * Checks if the description of the task contains the keyword, then adds the tasks to a list.
     *
     * @param keyword The keyword that the user input.
     * @param listOfTasks The list of tasks with tasks which description matches the keyword.
     * @param task The task which description may contain the keyword.
     */
    private void addTaskIfContains(String keyword, ArrayList<Task> listOfTasks, Task task) {
        if (task.getDescription().contains(keyword)) {
            listOfTasks.add(task);
        }
    }

}
