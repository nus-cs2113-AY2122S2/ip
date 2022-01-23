package operations;

public class ByeOperation extends Operation {

    /**
     * Initialize operation with operationName
     *
     * @param operationName Name of the Operation
     * @param  order Order given by User
     */
    public ByeOperation(String operationName, String order) {

        super(operationName, order);
    }

    @Override
    public String operate() {
        return "Bye. Hope to see you again soon!";
    }
}
