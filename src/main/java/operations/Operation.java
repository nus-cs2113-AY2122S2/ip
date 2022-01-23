package operations;

import ui.ChatBox;

/**
 * operations.Operation of duke
 */
public abstract class Operation {

    protected String operationName = "";
    protected String order = "";
    protected String result = "";


    /**
     * Initialize operation with operationName
     * @param operationNameLocal Name of the operations.Operation
     * @param order Order given by User
     */
    public Operation(String operationNameLocal, String order) {

        this.operationName = operationNameLocal;
        this.order = order;
        this.result = operate();

    }


    /**
     * get the Order of the operations.Operation
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
     *  Print the result of the operation, for the operations.Operation abstract class, just print the operation name
     */
    public void displayResult() {
        ChatBox.printChatBox(operationName);
    }

    public String operate() {
        return operationName;
    }

}
