package duke.command;

import duke.TaskList;
import duke.Ui;
import duke.exception.AdditionalException;
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
     * This is the execute method that runs when there is a find command.
     * The method will search through the list of tasks that is stored in the TaskList tasks and print the tasks
     * that match the keyword which the user input.
     *
     * @param tasks The TaskList object that contains the list of tasks.
     * @param ui The user interface object that allows for printing of the list of tasks.
     * @param storage The storage object which allows for the saving of the tasks to the file.
     * @throws AdditionalException If there are no tasks.
     * @see AdditionalException
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws AdditionalException {
        String keyword = getKeyword();
        ArrayList<Task> listOfTasks = getTasks(tasks, ui, keyword);
        ui.showList(listOfTasks);
    }

    /**
     * This is the getTasks method that searches through the tasks in the TaskList.
     * It creates a new list of tasks and adds the tasks that contain the keyword.
     * After searching and adding the tasks, it then returns the list of tasks.
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
     * This is the getKeyword function that gets the keyword to be searched for from the user input.
     *
     * @return The keyword to be search for.
     * @throws AdditionalException If there are no keywords to search for.
     * @see AdditionalException
     */
    private String getKeyword() throws AdditionalException {
        int startingIndex = "find".length();
        int endingIndex = fullCommand.length();
        String keyword = fullCommand.substring(startingIndex, endingIndex).trim();
        if (keyword.length() == 0) {
            throw new AdditionalException("You want me to find nothing or everything...?");
        }
        return keyword;
    }

    /**
     * This is the addTaskIfContains method that checks if the description of the task contains the keyword.
     * If it contains, the method will add the task to the list of tasks.
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

    /**
     * This is the isBye method that returns whether the command is "bye".
     *
     * @return False because the command is "find".
     */
    @Override
    public boolean isBye() {
        return false;
    }

    @Override
    public void executeFromFile(ArrayList<Task> listOfTasks) throws AdditionalException {

    }
}
