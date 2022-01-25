package operations;

import tasks.Task;
import tasks.TaskFactory;

public class EventAddOperation extends AddOperation{
    /**
     * Initializes operation with operationName
     *  @param operationNameLocal Name of the Operation
     * @param order Order given by User
     */
    public EventAddOperation(String operationNameLocal, String order) {
        super(operationNameLocal, order);
    }

    @Override
    protected Task makeTask(String taskDescription) {
        // the "todo" is not included
        String taskType = taskDescription.split(" ", 2)[0];
        taskDescription = taskDescription.split(" ", 2)[1];
        TaskFactory taskFactory = new TaskFactory(taskType, taskDescription);
        Task newTask = taskFactory.makeTask();
        return newTask;
    }


}
