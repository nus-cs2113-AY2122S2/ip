package duke.command;

import duke.TaskList;
import duke.Ui;
import duke.exception.AdditionalException;
import duke.storage.Storage;
import duke.task.Task;

import java.time.LocalDate;
import java.util.ArrayList;

public class DateCommand extends Command {

    private String fullCommand;

    public DateCommand(String fullCommand) {
        this.fullCommand = fullCommand;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws AdditionalException {
        String[] words = fullCommand.split(" ");
        if (words.length != 2) {
            throw new AdditionalException("Please input the date in the correct format and only the date");
        }
        LocalDate date = LocalDate.parse(words[1]);
        ArrayList<Task> listOfTasks = tasks.getListOfSameDates(date);
        ui.showList(listOfTasks);
    }

    @Override
    public boolean isBye() {
        return false;
    }

    @Override
    public void executeFromFile(ArrayList<Task> listOfTasks) throws AdditionalException {
    }
}
