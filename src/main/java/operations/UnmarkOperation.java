package operations;

import operations.Operation;
import tasks.TaskList;

public class UnmarkOperation extends Operation {

    /**
     * Initialize operation with operationName
     *
     * @param operationNameLocal Name of the operations.Operation
     * @param order          Order given by User
     */
    public UnmarkOperation(String operationNameLocal, String order) {
        super(operationNameLocal, order);
    }

    @Override public String operate(){
        String [] orderListLocal = order.split(" ");
        int indexLocal = Integer.parseInt(orderListLocal[1])-1;
        int listSize =  TaskList.getSize();
        if (indexLocal >= listSize){
            return "Woops! fail to unmark this task: \n   tasks.Task index out of range";
        }
        else{
            TaskList.getElement(indexLocal).setMark(false);
            return "OK, I've marked this task as not done yet: \n " + TaskList.getElement(indexLocal).getReport();
        }
    }
}
