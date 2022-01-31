package operations;

<<<<<<< HEAD
=======
import exceptions.DukeException;
import exceptions.TodoDukeException;
>>>>>>> 9dada07e69461ecaf25973bf549e8fd670b8849f
import tasks.Task;
import tasks.TaskFactory;

public class ToDoAddOperation extends AddOperation{

    /**
<<<<<<< HEAD
     * Initializes operation with operationName
     *  @param operationNameLocal Name of the Operation
     * @param order Order given by User
     */
    public ToDoAddOperation(String operationNameLocal, String order) {
=======
     * Initializes operation with operationName.
     *
     *  @param operationNameLocal Name of the Operation
     * @param order Order given by User
     */
    public ToDoAddOperation(String operationNameLocal, String order) throws DukeException {
>>>>>>> 9dada07e69461ecaf25973bf549e8fd670b8849f
        super(operationNameLocal, order);
    }

    /**
<<<<<<< HEAD
     *  Makes a new ToDoTask object
=======
     *  Makes a new ToDoTask object.
     *
>>>>>>> 9dada07e69461ecaf25973bf549e8fd670b8849f
     * @param taskDescription The description of the task
     * @return A new ToDoTask object
     */
    @Override
<<<<<<< HEAD
    protected Task makeTask(String taskDescription) {
        // the "todo" is not included
        String taskType = taskDescription.split(" ",2)[0];
        taskDescription = taskDescription.split(" ",2)[1];
        System.out.println(String.format("ToDoAddOperation is called%s%s",taskDescription, taskType));
        TaskFactory taskFactory = new TaskFactory(taskType, taskDescription);
        Task newTask = taskFactory.makeTask();
        return newTask;
    }


=======
    protected Task makeTask(String taskDescription) throws DukeException {
        // the "todo" is not included
        String taskType;
        try{
            taskType = taskDescription.split(" " , 2)[0];
            taskDescription = taskDescription.split(" " ,2)[1];

        } catch (ArrayIndexOutOfBoundsException e) {
            throw new TodoDukeException();
        }
        TaskFactory taskFactory = new TaskFactory(taskType , taskDescription);
        try{
            Task newTask = taskFactory.makeTask();
            return newTask;
        } catch (DukeException e){
            throw e;
        }

    }
>>>>>>> 9dada07e69461ecaf25973bf549e8fd670b8849f


}
