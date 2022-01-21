public class UndefinedOperation extends Operation{

    /**
     * Initialize operation with operationName
     *
     * @param _operationName Name of the Operation
     * @param order Order given by user
     */
    public UndefinedOperation(String _operationName, String order) {
        super(_operationName, order);
        result = "Oops, I'm not sure what you mean.";
    }

}
