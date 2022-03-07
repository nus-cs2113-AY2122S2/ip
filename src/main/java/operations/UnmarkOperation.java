package operations;

import exceptions.DukeException;
import operations.Operation;
import tasks.TaskList;

public class UnmarkOperation extends Operation {

    /**
     * Initializes operation with operationName
     *
     * @param operationNameLocal Name of the operations.Operation
     * @param order  Order given by User
     */
    public UnmarkOperation(String operationNameLocal, String order) throws DukeException {
        super(operationNameLocal, order);
        helpMessage = "     unmark <i>                                         "
                + "--set the ith task as uncompleted (1-based)\n";
    }

    @Override
    public String operate() throws DukeException {
        String[] orderListLocal = order.split(" ");
        int indexLocal = Integer.parseInt(orderListLocal[1]) - 1;
        int listSize = TaskList.getSize();
        try {
            TaskList.getElement(indexLocal).setMark(false);
            return "OK, I've marked this task as not done yet: \n " + TaskList.getElement(indexLocal).getReport();
        } catch (DukeException e) {
            throw e;
        }

    }
}
