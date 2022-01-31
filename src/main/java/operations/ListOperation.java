package operations;
<<<<<<< HEAD
=======
import exceptions.DukeException;
>>>>>>> 9dada07e69461ecaf25973bf549e8fd670b8849f
import tasks.TaskList;
import tasks.Task;
public class ListOperation extends Operation {


    /**
     * Initializes operation with operationName
     *
     * @param operationNameLocal Name of the Operation
     * @param order Order given by User
     */
<<<<<<< HEAD
    public ListOperation(String operationNameLocal, String order) {
=======
    public ListOperation(String operationNameLocal, String order) throws DukeException {
>>>>>>> 9dada07e69461ecaf25973bf549e8fd670b8849f
        super(operationNameLocal, order);
        result = operate();
    }

    @Override
<<<<<<< HEAD
    public String operate(){
        int sizeArray = TaskList.getSize();
        String resultLocal = "";
        for (int i = 0; i< sizeArray; i++){
            if (i>0){
                resultLocal += "\n";
            }
            Task taskLocal = TaskList.getElement(i);
            resultLocal += (String.valueOf(i+1)+". "+taskLocal.getReport());
=======
    public String operate() {
        int sizeArray = TaskList.getSize();
        String resultLocal = "";
        for (int i = 0; i < sizeArray; i++) {
            if (i > 0) {
                resultLocal += "\n";
            }
            Task taskLocal = TaskList.getElement(i);
            resultLocal += (String.valueOf(i + 1) + ". " + taskLocal.getReport());
>>>>>>> 9dada07e69461ecaf25973bf549e8fd670b8849f
        }
        return resultLocal;
    }


}
