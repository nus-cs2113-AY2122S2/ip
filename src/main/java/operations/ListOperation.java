package operations;
import tasks.TaskList;
import tasks.Task;
public class ListOperation extends Operation {


    /**
     * Initialize operation with operationName
     *
     * @param operationNameLocal Name of the Operation
     * @param order Order given by User
     */
    public ListOperation(String operationNameLocal, String order) {
        super(operationNameLocal, order);
        result = operate();
    }

    @Override
    public String operate(){
        int sizeArray = TaskList.getSize();
        String resultLocal = "";
        for (int i = 0; i< sizeArray; i++){
            if (i>0){
                resultLocal += "\n";
            }
            Task taskLocal = TaskList.getElement(i);
            resultLocal += (String.valueOf(i+1)+". "+taskLocal.getReport());
        }
        return resultLocal;
    }


}
