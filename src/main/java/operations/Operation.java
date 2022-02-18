package operations;

import exceptions.DukeException;
import ui.ChatBox;

/**
 * operations.Operation of duke
 */
public abstract class Operation {

    protected String operationName = "";
    protected String order = "";
    protected String result = "";


    /**
     * Initializes operation with operationName
     * @param operationNameLocal Name of the operations.Operation
     * @param order Order given by User
     */
    public Operation(String operationNameLocal, String order) throws DukeException {
        try {
            this.operationName = operationNameLocal;
            this.order = order;
            this.result = operate();
        } catch (DukeException e) {
            throw e;
        }


    }


    /**
     * Gets the Order of the operations.Operation
     *
     * @return operations.Operation with Parameters
     */
    public String getOperationName() {
        return operationName;
    }


    /**
     * @return Result of the operation
     */
    public String getResult() {
        return result;
    }

    /**
     * Sets the result to certain value
     * @param result
     */
    public void setResult(String result) {
        this.result = result;
    }


    /**
     *  Prints the result of the operation, for the operations.Operation abstract class, just print the operation name
     */
    public void displayResult() {
        ChatBox.printChatBox(operationName);
    }

    public String operate() throws DukeException {
        try {
            return operationName;
        } catch (Exception e) {
            throw e;
        }

    }

}
