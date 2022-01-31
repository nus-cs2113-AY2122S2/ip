package operations;
<<<<<<< HEAD
=======
import exceptions.DukeException;
>>>>>>> 9dada07e69461ecaf25973bf549e8fd670b8849f
import tasks.TaskList;
public class MarkOperation extends Operation {

    /**
<<<<<<< HEAD
     * Initializes operation with operationName
=======
     * Initializes operation with operationName.
>>>>>>> 9dada07e69461ecaf25973bf549e8fd670b8849f
     *
     * @param operationNameLocal Name of the Operation
     * @param order          Order given by User
     */
<<<<<<< HEAD
    public MarkOperation(String operationNameLocal, String order) {
=======
    public MarkOperation(String operationNameLocal, String order) throws DukeException {
>>>>>>> 9dada07e69461ecaf25973bf549e8fd670b8849f

        super(operationNameLocal, order);
    }

<<<<<<< HEAD
    @Override public String operate(){
        String [] orderList = order.split(" ");
        int indexLocal = Integer.parseInt(orderList[1])-1;
        int listSizeLocal =  TaskList.getSize();
        if (indexLocal >= listSizeLocal){
            return "Woops! fail to mark this task: \n   tasks.Task index out of range";
        }
        else{
=======
    @Override public String operate() {
        String [] orderList = order.split(" ");
        int indexLocal = Integer.parseInt(orderList[1]) - 1;
        int listSizeLocal =  TaskList.getSize();
        if (indexLocal >= listSizeLocal) {
            return "Woops! fail to mark this task: \n   tasks.Task index out of range";
        } else {
>>>>>>> 9dada07e69461ecaf25973bf549e8fd670b8849f
            TaskList.getElement(indexLocal).setMark(true);
            return "Nice! I've marked this task as done: \n " + TaskList.getElement(indexLocal).getReport();
        }

    }

}
