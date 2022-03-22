package Commands;

import Components.Event;

import Exceptions.MaxTaskException;

import Interfaces.UI;

import Managers.TaskManager;

/**
 * Command for Bao to create a new event to add to task list.
 */
public class NewEventCommand extends Command {
    private String description;
    private String dateTime;

    /**
     * Creates new-event command with specified task description and date and time of the event.
     *
     * @param description Description of event happening.
     * @param dateTime Date and time of the event. String input.
     */
    public NewEventCommand(String description, String dateTime) {
        this.description = description;
        this.dateTime = dateTime;
    }

    /**
     * Creates and adds the new event to the task list.
     *
     * @param taskManager TaskManager to execute command on.
     * @param ui The interface that provides interaction with the user.
     */
    @Override
    public void execute(TaskManager taskManager, UI ui) throws MaxTaskException {
        try{
            Event event = new Event(description, dateTime);
            taskManager.addTask(event);
            ui.newTaskMessage(event);
        } catch (Exception e) {
            throw e;
        }
    }
}
