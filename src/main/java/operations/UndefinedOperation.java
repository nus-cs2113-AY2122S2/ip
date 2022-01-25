package operations;

import operations.Operation;

public class UndefinedOperation extends Operation {

    /**
     * Initializes operation with operationName
     *
     * @param operationNameLocal Name of the operations.Operation
     * @param order Order given by user
     */
    public UndefinedOperation(String operationNameLocal, String order) {
        super(operationNameLocal, order);
        result = "Oops, I'm not sure what you mean.";
    }

}
