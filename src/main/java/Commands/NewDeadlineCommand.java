package Commands;

import Components.Deadline;
import Exceptions.MaxTaskException;
import Interfaces.UI;
import Managers.TaskManager;

import static Functions.MessageDisp.printWithLine;

public class NewDeadlineCommand extends Command {
    private String description;
    private String dateTime;

    public NewDeadlineCommand(String description, String dateTime) {
        this.description = description;
        this.dateTime = dateTime;
    }

    @Override
    public void execute(TaskManager taskManager, UI ui) throws MaxTaskException {
        try{
            Deadline deadline = new Deadline(description, dateTime);
            taskManager.addTask(deadline);
            printWithLine("Yup yup yup, " + System.lineSeparator()
                    + deadline.toString() + "," + System.lineSeparator()
                    + "annnd there we go, it's been added!" + System.lineSeparator()
                    + "You have " + taskManager.getNumTasks() + " tasks in the list.");
        } catch (Exception e) {
            throw e;
        }
    }
}
