package operations;

<<<<<<< HEAD
=======
import exceptions.DukeException;
>>>>>>> 9dada07e69461ecaf25973bf549e8fd670b8849f
import operations.Operation;

public class UndefinedOperation extends Operation {

    /**
     * Initializes operation with operationName
     *
     * @param operationNameLocal Name of the operations.Operation
     * @param order Order given by user
     */
<<<<<<< HEAD
    public UndefinedOperation(String operationNameLocal, String order) {
        super(operationNameLocal, order);
=======
    public UndefinedOperation(String operationNameLocal , String order) throws DukeException {
        super(operationNameLocal , order);
>>>>>>> 9dada07e69461ecaf25973bf549e8fd670b8849f
        result = "Oops, I'm not sure what you mean.";
    }

}
