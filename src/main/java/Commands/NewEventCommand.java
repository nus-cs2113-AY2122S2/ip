package Commands;

import Components.Event;
import Exceptions.MaxTaskException;
import Interfaces.UI;
import Managers.TaskManager;

import static Functions.MessageDisp.printWithLine;

public class NewEventCommand extends Command {
    private String description;
    private String dateTime;

    public NewEventCommand(String description, String dateTime) {
        this.description = description;
        this.dateTime = dateTime;
    }

    @Override
    public void execute(TaskManager taskManager, UI ui) {
        try{
            Event event = new Event(description, dateTime);
            taskManager.addTask(event);
            printWithLine("Yup yup yup, " + System.lineSeparator()
                    + event.toString() + "," + System.lineSeparator()
                    + "annnd there we go, it's been added!" + System.lineSeparator()
                    + "You have " + taskManager.getNumTasks() + " tasks in the list.");
        } catch (MaxTaskException e) {
            printWithLine("Hey! Calm down, Charlie Brown. You've too many on your plate right now.");
        }
    }
}
