package operations;

import exceptions.DukeException;
import ui.ChatBox;

/**
 * operations.Operation of duke
 */
public abstract class Operation {

    private static final String NULL_RESULT = "not executed yet";
    protected String operationName = "";
    protected String order = "";
    protected String result = "";
    protected String helpMessage = "Duke operation\n";



    /**
     * Initializes operation with operationName
     *
     * @param operationNameLocal Name of the operations.Operation
     * @param order Order given by User
     */
    public Operation(String operationNameLocal, String order) throws DukeException {
        this.operationName = operationNameLocal;
        this.helpMessage = "     " + operationNameLocal + "                                             "
                + "--help message not defined\n";
        if (order.equals("")) {

            this.result = operationNameLocal + " " + NULL_RESULT;
        } else {
            try {
                this.order = order;
                this.result = operate();
            } catch (DukeException e) {
                throw e;
            }
        }
    }

    /**
     * A lazy constructor that will creates
     *
     * @param operationNameLocal
     */
    public Operation(String operationNameLocal) {
        this.operationName = operationNameLocal;
    }


    public void executeOperation() throws DukeException {
        try {
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
     * Sets the order of the operation
     *
     * @param newOrder the new order
     */
    public void setOrder(String newOrder) {
        order = newOrder;
    }


    /**
     * @return Result of the operation
     */
    public String getResult() {
        return result;
    }

    /**
     * Sets the result to certain value
     *
     * @param result
     */
    public void setResult(String result) {
        this.result = result;
    }


    /**
     * Prints the result of the operation, for the operations.Operation abstract class, just print the operation name
     */
    public void displayResult() {
        ChatBox.printChatBox(operationName);
    }


    /**
     * Gets the help message of the operation
     *
     * @return help message of the operation
     */
    public String getHelpMessage() {
        return helpMessage;
    }

    /**
     * Execute operation
     * @return Return the result
     * @throws DukeException The exception of duke
     */
    public String operate() throws DukeException {
        try {
            return operationName;
        } catch (Exception e) {
            throw e;
        }
    }

}
