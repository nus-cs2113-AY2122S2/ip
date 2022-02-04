package operations;

import exceptions.DukeException;
import operations.Operation;

public class UndefinedOperation extends Operation {

    /**
     * Initializes operation with operationName
     *
     * @param operationNameLocal Name of the operations.Operation
     * @param order Order given by user
     */
    public UndefinedOperation(String operationNameLocal, String order) throws DukeException {
        super(operationNameLocal, order);
        result = "Oops, I'm not sure what you mean.";
    }

}
