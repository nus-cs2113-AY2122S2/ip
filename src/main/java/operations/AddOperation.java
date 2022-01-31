package operations;

<<<<<<< HEAD
=======
import exceptions.DukeException;
>>>>>>> 9dada07e69461ecaf25973bf549e8fd670b8849f
import tasks.Task;
import tasks.TaskFactory;
import tasks.TaskList;

public class AddOperation extends Operation {

    /**
     * Initializes operation with operationName
     *
     * @param operationNameLocal Name of the Operation
     * @param order Order given by User
     */
<<<<<<< HEAD
    public AddOperation(String operationNameLocal, String order) {
=======
    public AddOperation(String operationNameLocal, String order) throws DukeException {
>>>>>>> 9dada07e69461ecaf25973bf549e8fd670b8849f
        super(operationNameLocal, order);
    }

    /**
     * Makes a new Task for add operation
     * @param taskDescription
     * @return The new task
     */
<<<<<<< HEAD
    protected Task makeTask(String taskDescription) {
        TaskFactory taskFactory = new TaskFactory("",taskDescription);
        Task newTask = taskFactory.makeTask();
        return newTask;
=======
    protected Task makeTask(String taskDescription) throws DukeException {
        TaskFactory taskFactory = new TaskFactory("", taskDescription);
        try{
            Task newTask = taskFactory.makeTask();
            return newTask;
        } catch (DukeException e) {
            throw e;
        }

>>>>>>> 9dada07e69461ecaf25973bf549e8fd670b8849f
    }

    /**
     * Adds a new task to tasklist
<<<<<<< HEAD
     * @return The report of the operation result
     */
    @Override
    public String operate() {
        Task newTask = makeTask(order);
        if (newTask == null){
=======
     *
     * @return The report of the operation result
     */
    @Override
    public String operate() throws DukeException {
        Task newTask;
        try {
            newTask = makeTask(order);
        } catch (DukeException e) {
            throw e;
        }
        if (newTask == null) {
>>>>>>> 9dada07e69461ecaf25973bf549e8fd670b8849f
            return "failed to add";
        }
        try {
            TaskList.addTask(newTask);
            int numberOfTasks = TaskList.getSize();
            return String.format("Got it. I've added this task:\n %s\nNow you have %d tasks in the list.",
                    newTask.getReport(), numberOfTasks);
        } catch (Exception e) {
            return "failed to add: " + newTask.getTaskDescription();
        }
    }

}
