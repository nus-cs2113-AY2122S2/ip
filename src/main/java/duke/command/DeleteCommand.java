package duke.command;

import duke.TaskList;
import duke.Ui;
import duke.storage.Storage;
import duke.task.Task;

import java.io.IOException;
import java.util.ArrayList;

public class DeleteCommand extends Command {

    private String fullCommand;

    public DeleteCommand(String fullCommand) {
        this.fullCommand = fullCommand;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        String[] words = fullCommand.split(" ");
        int indexToDelete = Integer.parseInt(words[1]) - 1;
        Task taskToDelete = tasks.getTask(indexToDelete);
        tasks.deleteTask(indexToDelete);
        int numberOfTasks = tasks.getSize();
        ui.showDeleteDone(taskToDelete, numberOfTasks);
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
