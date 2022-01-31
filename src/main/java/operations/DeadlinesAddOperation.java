package operations;

<<<<<<< HEAD
=======
import exceptions.DukeException;
>>>>>>> 9dada07e69461ecaf25973bf549e8fd670b8849f
import tasks.Task;
import tasks.TaskFactory;

public class DeadlinesAddOperation extends AddOperation {
    /**
     * Initializes operation with operationName
     *  @param operationNameLocal Name of the Operation
     * @param order Order given by User
     */
<<<<<<< HEAD
    public DeadlinesAddOperation(String operationNameLocal, String order) {
=======
    public DeadlinesAddOperation(String operationNameLocal, String order) throws DukeException {
>>>>>>> 9dada07e69461ecaf25973bf549e8fd670b8849f
        super(operationNameLocal, order);
    }

    /**
<<<<<<< HEAD
     *  Makes a new deadline objectg
=======
     *  Makes a new deadline objectg.
     *
>>>>>>> 9dada07e69461ecaf25973bf549e8fd670b8849f
     * @param taskDescription
     * @return A new DeadlineTask
     */
    @Override
<<<<<<< HEAD
    protected Task makeTask(String taskDescription) {
=======
    protected Task makeTask(String taskDescription) throws DukeException{
>>>>>>> 9dada07e69461ecaf25973bf549e8fd670b8849f
        // the "todo" is not included
        String taskType = taskDescription.split(" ", 2)[0];
        taskDescription = taskDescription.split(" ", 2)[1];
        TaskFactory taskFactory = new TaskFactory(taskType, taskDescription);
        Task newTask = taskFactory.makeTask();
        return newTask;

    }
}
