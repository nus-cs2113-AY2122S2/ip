package operations;
import exceptions.DukeException;
import tasks.TaskList;
public class MarkOperation extends Operation {

    /**
     * Initializes operation with operationName.
     *
     * @param operationNameLocal Name of the Operation
     * @param order          Order given by User
     */
    public MarkOperation(String operationNameLocal, String order) throws DukeException {

        super(operationNameLocal, order);
    }

    @Override public String operate() {
        String [] orderList = order.split(" ");
        int indexLocal = Integer.parseInt(orderList[1]) - 1;
        int listSizeLocal =  TaskList.getSize();
        if (indexLocal >= listSizeLocal) {
            return "Woops! fail to mark this task: \n   tasks.Task index out of range";
        } else {
            TaskList.getElement(indexLocal).setMark(true);
            return "Nice! I've marked this task as done: \n " + TaskList.getElement(indexLocal).getReport();
        }

    }

}
