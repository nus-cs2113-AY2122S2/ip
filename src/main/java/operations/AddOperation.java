package operations;

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
    public AddOperation(String operationNameLocal, String order) {
        super(operationNameLocal, order);
    }

    /**
     * Makes a new Task for add operation
     * @param taskDescription
     * @return The new task
     */
    protected Task makeTask(String taskDescription) {
        TaskFactory taskFactory = new TaskFactory("",taskDescription);
        Task newTask = taskFactory.makeTask();
        return newTask;
    }

    /**
     * Adds a new task to tasklist
     * @return The report of the operation result
     */
    @Override
    public String operate() {
        Task newTask = makeTask(order);
        if (newTask == null){
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
