package duke.command;

import duke.TaskList;
import duke.Ui;
import duke.exception.AdditionalException;
import duke.storage.Storage;
import duke.task.Task;

import java.io.IOException;
import java.util.ArrayList;

public class UnmarkCommand extends Command {

    private String fullCommand;

    public UnmarkCommand(String fullCommand) {
        this.fullCommand = fullCommand;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException, AdditionalException {
        String[] words = fullCommand.split(" ");
        if (words.length != 2) {
            throw new AdditionalException("Please input the index and only the index");
        }
        int indexToUnmark = Integer.parseInt(words[1]) - 1;
        Task taskToUnmark = tasks.getTask(indexToUnmark);
        taskToUnmark.markAsUndone();
        ui.showUnmarkDone(taskToUnmark);
        storage.saveAll(tasks);
    }

    @Override
    public boolean isBye() {
        return false;
    }

    @Override
    public void executeFromFile(ArrayList<Task> listOfTasks) {

    }
}
