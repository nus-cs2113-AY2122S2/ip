package operations;

<<<<<<< HEAD
=======
import exceptions.DukeException;
>>>>>>> 9dada07e69461ecaf25973bf549e8fd670b8849f
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
<<<<<<< HEAD
    public Operation(String operationNameLocal, String order) {

        this.operationName = operationNameLocal;
        this.order = order;
        this.result = operate();
=======
    public Operation(String operationNameLocal, String order) throws DukeException{
        try {
            this.operationName = operationNameLocal;
            this.order = order;
            this.result = operate();
        } catch (DukeException e) {
            throw e;
        }


>>>>>>> 9dada07e69461ecaf25973bf549e8fd670b8849f

    }


    /**
     * Gets the Order of the operations.Operation
<<<<<<< HEAD
=======
     *
>>>>>>> 9dada07e69461ecaf25973bf549e8fd670b8849f
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
     *  Prints the result of the operation, for the operations.Operation abstract class, just print the operation name
     */
    public void displayResult() {
        ChatBox.printChatBox(operationName);
    }

<<<<<<< HEAD
    public String operate() {
        return operationName;
=======
    public String operate() throws DukeException {
        try {
            return operationName;
        } catch (Exception e) {
            throw e;
        }

>>>>>>> 9dada07e69461ecaf25973bf549e8fd670b8849f
    }

}
