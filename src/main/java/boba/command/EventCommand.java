package boba.command;

import boba.data.Storage;
import boba.exception.BobaException;
import boba.response.Ui;
import boba.task.Event;
import boba.task.TaskList;

/**
 * Class for event command
 */
public class EventCommand extends Command{

    /** Event item to be added to taskList */
    private Event event;

    /**
     * Constructor for EventCommand
     * @param description
     * @param at
     */
    public EventCommand(String description, String at) {
        event = new Event(description, at);
    }

    /**
     * Add an event item to taskList
     * @param tasks List of all tasks
     * @param ui UI of Boba
     * @param storage Save file of list
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            tasks.addTask(event);
            ui.printAddSuccess(event, tasks.size());
            storage.save(tasks);
        } catch (BobaException e) {
            ui.printLimitError();
        }
    }

    /**
     * @return Not ExitCommand return false
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
