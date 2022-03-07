package operations;

import exceptions.DukeException;
import exceptions.TodoDukeException;
import tasks.Task;
import tasks.TaskFactory;

public class ToDoAddOperation extends AddOperation {

    /**
     * Initializes operation with operationName.
     *
     * @param operationNameLocal Name of the Operation
     * @param order Order given by User
     */
    public ToDoAddOperation(String operationNameLocal, String order) throws DukeException {
        super(operationNameLocal, order);
        helpMessage = "     todo <task description>                            --add a todo type task\n";
    }

    /**
     * Makes a new ToDoTask object.
     *
     * @param taskDescription The description of the task
     * @return A new ToDoTask object
     */
    @Override
    protected Task makeTask(String taskDescription) throws DukeException {
        String taskType;
        try {
            taskType = taskDescription.split(" ", 2)[0];
            taskDescription = taskDescription.split(" ", 2)[1];

        } catch (ArrayIndexOutOfBoundsException e) {
            throw new TodoDukeException();
        }
        TaskFactory taskFactory = new TaskFactory(taskType, taskDescription);
        try {
            Task newTask = taskFactory.makeTask();
            return newTask;
        } catch (DukeException e) {
            throw e;
        }

    }


}
