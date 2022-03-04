package Commands;

import Components.Deadline;

import Exceptions.MaxTaskException;

import Interfaces.UI;

import Managers.TaskManager;

/**
 * Command for Bao to create a task with deadline to add to task list.
 */
public class NewDeadlineCommand extends Command {
    private String description;
    private String dateTime;

    /**
     * Creates new-deadline command with specified task description and date and time of the deadline.
     *
     * @param description Description of task to be completed.
     * @param dateTime Deadline of the task. String input.
     */
    public NewDeadlineCommand(String description, String dateTime) {
        this.description = description;
        this.dateTime = dateTime;
    }

    /**
     * Creates and adds the new deadline to the task list.
     *
     * @param taskManager TaskManager to execute command on.
     * @param ui The interface that provides interaction with the user.
     */
    @Override
    public void execute(TaskManager taskManager, UI ui) throws MaxTaskException {
        try{
            Deadline deadline = new Deadline(description, dateTime);
            taskManager.addTask(deadline);
            ui.newTaskMessage(deadline);
        } catch (Exception e) {
            throw e;
        }
    }
}
