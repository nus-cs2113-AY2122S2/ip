package bim.command;

import bim.BimException;
import bim.Storage;
import bim.Ui;
import bim.task.Event;
import bim.task.TaskList;

import java.time.LocalDate;

/**
 * Adds an event task to the task list when executed.
 */
public class EventCommand extends Command {
    private String description;
    private LocalDate date;

    public EventCommand(String description, LocalDate date) {
        this.description = description;
        this.date = date;
    }

    /**
     * Writes the new task to the data file. Only if the writing is successful, add the new task
     * to the task list and print the newly added task.
     *
     * @param tasks   The task list to add the new event into
     * @param ui      Ui object to print messages
     * @param storage Storage object for saving to disk.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Event newEvent = new Event(description, date);
        try {
            storage.writeData(newEvent);
            tasks.addTask(newEvent);
            ui.printAddTaskMessage(newEvent, tasks.getSize());
        } catch (BimException exception) {
            ui.printErrorMessage(exception.getMessage());
        }
    }
}
