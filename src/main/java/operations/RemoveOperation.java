package operations;

import exceptions.DukeException;
import exceptions.LossIndexDukeException;
import tasks.TaskList;
import tasks.Task;

public class RemoveOperation extends Operation {

    /**
     * Initializes operation with operationName
     *
     * @param operationNameLocal Name of the operations.Operation
     * @param order Order given by User
     */
    public RemoveOperation(String operationNameLocal, String order) throws DukeException {
        super(operationNameLocal, order);
        helpMessage = "     delete <i>                                         --delete the ith task (1-based)\n";
    }

    @Override
    public String operate() throws DukeException {
        int indexLocal;
        try {
            String[] orderListLocal = order.split(" ");
            indexLocal = Integer.parseInt(orderListLocal[1]) - 1;
        } catch (Exception e) {
            throw new LossIndexDukeException();
        }
        Task targetTask;
        int newSize;
        try {
            targetTask = TaskList.getElement(indexLocal);
            TaskList.removeElement(indexLocal);
            newSize = TaskList.getSize();
        } catch (DukeException e) {
            throw e;
        }
        return String.format("Noted. I've removed this task: \n%s\nNow you have %d tasks in the list.",
                targetTask.getReport(), newSize);
    }

}
