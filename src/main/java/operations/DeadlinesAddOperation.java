package operations;

import exceptions.DukeException;
import tasks.Task;
import tasks.TaskFactory;

public class DeadlinesAddOperation extends AddOperation {
    /**
     * Initializes operation with operationName
     *  @param operationNameLocal Name of the Operation
     * @param order Order given by User
     */
    public DeadlinesAddOperation(String operationNameLocal, String order) throws DukeException {
        super(operationNameLocal, order);
    }

    /**
     *  Makes a new deadline objectg.
     *
     * @param taskDescription
     * @return A new DeadlineTask
     */
    @Override
    protected Task makeTask(String taskDescription) throws DukeException{
        // the "todo" is not included
        String taskType = taskDescription.split(" ", 2)[0];
        taskDescription = taskDescription.split(" ", 2)[1];
        TaskFactory taskFactory = new TaskFactory(taskType, taskDescription);
        Task newTask = taskFactory.makeTask();
        return newTask;

    }
}
