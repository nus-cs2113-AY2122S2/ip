package operations;

<<<<<<< HEAD
=======
import exceptions.DukeException;
>>>>>>> 9dada07e69461ecaf25973bf549e8fd670b8849f
import tasks.Task;
import tasks.TaskFactory;

public class EventAddOperation extends AddOperation{
<<<<<<< HEAD
=======

>>>>>>> 9dada07e69461ecaf25973bf549e8fd670b8849f
    /**
     * Initializes operation with operationName
     *  @param operationNameLocal Name of the Operation
     * @param order Order given by User
     */
<<<<<<< HEAD
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
=======
    public EventAddOperation(String operationNameLocal, String order) throws DukeException {
        super(operationNameLocal, order);
    }

    /**
     * Makes a new event Task for add operation.
     *
     * @param taskDescription
     * @return The new event task
     */
    @Override
    protected Task makeTask(String taskDescription) throws DukeException{
        // the "todo" is not included
        String taskType = taskDescription.split(" ", 2)[0];
        taskDescription = taskDescription.split(" ", 2)[1];
        try {
            TaskFactory taskFactory = new TaskFactory(taskType, taskDescription);
            Task newTask = taskFactory.makeTask();
            return newTask;
        } catch(DukeException e)
        {
            throw e;
        }
>>>>>>> 9dada07e69461ecaf25973bf549e8fd670b8849f
    }


}
