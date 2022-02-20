package duke.command;

import duke.TaskList;
import duke.Ui;
import duke.exception.AdditionalException;
import duke.storage.Storage;
import duke.task.Task;

import java.util.ArrayList;

public class FindCommand extends Command {

    private String fullCommand;

    public FindCommand(String fullCommand) {
        this.fullCommand = fullCommand;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws AdditionalException {
        int startingIndex = "find".length();
        int endingIndex = fullCommand.length();
        String stringToFind = fullCommand.substring(startingIndex, endingIndex).trim();
        if (stringToFind.length() == 0) {
            throw new AdditionalException("Please key in exactly one keyword!");
        }
        ArrayList<Task> listOfTasks = new ArrayList<>();
        for (int i = 0; i < tasks.getSize(); i++) {
            Task task = tasks.getTask(i);
            addTaskIfContains(stringToFind, listOfTasks, task);
        }
        if (listOfTasks.isEmpty()) {
            ui.showNoResuts();
        }
        for (int i = 0; i < listOfTasks.size(); i++) {
            System.out.println(listOfTasks.get(i));
        }
    }

    private void addTaskIfContains(String stringToFind, ArrayList<Task> listOfTasks, Task task) {
        if (task.getDescription().contains(stringToFind)) {
            listOfTasks.add(task);
        }
    }

    @Override
    public boolean isBye() {
        return false;
    }

    @Override
    public void executeFromFile(ArrayList<Task> listOfTasks) throws AdditionalException {

    }
}
