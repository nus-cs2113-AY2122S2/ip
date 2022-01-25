package operations;

import tasks.Task;
import tasks.TaskFactory;

public class ToDoAddOperation extends AddOperation{

    /**
     * Initializes operation with operationName
     *  @param operationNameLocal Name of the Operation
     * @param order Order given by User
     */
    public ToDoAddOperation(String operationNameLocal, String order) {
        super(operationNameLocal, order);
    }

    /**
     *  Makes a new ToDoTask object
     * @param taskDescription The description of the task
     * @return A new ToDoTask object
     */
    @Override
    protected Task makeTask(String taskDescription) {
        // the "todo" is not included
        String taskType = taskDescription.split(" ",2)[0];
        taskDescription = taskDescription.split(" ",2)[1];
        System.out.println(String.format("ToDoAddOperation is called%s%s",taskDescription, taskType));
        TaskFactory taskFactory = new TaskFactory(taskType, taskDescription);
        Task newTask = taskFactory.makeTask();
        return newTask;
    }




}
