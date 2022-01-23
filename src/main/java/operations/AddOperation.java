package operations;

import tasks.Task;
import tasks.TaskList;

public class AddOperation extends Operation {

    /**
     * Initialize operation with operationName
     *
     * @param operationNameLocal Name of the Operation
     * @param order Order given by User
     */
    public AddOperation(String operationNameLocal, String order) {
        super(operationNameLocal, order);
    }

    @Override
    public String operate() {
        Task newTask = new Task(order);
        try {
            TaskList.addTask(newTask);
            return "added: " + newTask.getTaskDescription();
        } catch (Exception e) {
            return "failed to add: " + newTask.getTaskDescription();
        }
    }

}
