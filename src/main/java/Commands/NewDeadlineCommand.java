package Commands;

import Components.Deadline;
import Exceptions.MaxTaskException;
import Interfaces.UI;
import Managers.TaskManager;

import static Functions.MessageDisp.printWithLine;

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
    public void execute(TaskManager taskManager, UI ui) {
        try{
            Deadline deadline = new Deadline(description, dateTime);
            taskManager.addTask(deadline);
            printWithLine("Yup yup yup, " + System.lineSeparator()
                    + deadline.toString() + "," + System.lineSeparator()
                    + "annnd there we go, it's been added!" + System.lineSeparator()
                    + "You have " + taskManager.getNumTasks() + " tasks in the list.");
        } catch (MaxTaskException e) {
            printWithLine("Hey! Calm down, Charlie Brown. You've too many on your plate right now.");
        }
    }
}
