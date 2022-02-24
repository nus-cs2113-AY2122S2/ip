package operations;

import exceptions.DukeException;

public class ErrorHandlerOperation extends Operation {
    public ErrorHandlerOperation(String operationNameLocal, String order) throws DukeException {
        super(operationNameLocal, order);
    }


    public void appendErrorMessage(String newErrorMessage) {
        result = result + newErrorMessage;
    }
}
