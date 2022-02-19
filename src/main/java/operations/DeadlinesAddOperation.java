package operations;

import exceptions.DukeException;
import tasks.Task;
import tasks.TaskFactory;

/**
 * Handles adding deadline specificly
 */
public class DeadlinesAddOperation extends AddOperation {
    /**
     * Initializes operation with operationName
     *  @param operationNameLocal Name of the Operation
     * @param order Order given by User
     */
    public DeadlinesAddOperation(String operationNameLocal, String order) throws DukeException {
        super(operationNameLocal, order);
        helpMessage = "     deadline <task description> /<YYYY-MM-DD> [HH:MM]  --add a deadline type task (HH:MM is optional) \n";
    }

    /**
     *  Makes a new deadline objectg.
     *
     * @param taskDescription
     * @return A new DeadlineTask
     */
    @Override
    protected Task makeTask(String taskDescription) throws DukeException {
        // the "todo" is not included
        String taskType = taskDescription.split(" ", 2)[0];
        taskDescription = taskDescription.split(" ", 2)[1];
        TaskFactory taskFactory = new TaskFactory(taskType, taskDescription);
        Task newTask = taskFactory.makeTask();
        return newTask;

    }
}
