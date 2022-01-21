public class ByeOperation extends Operation{

    /**
     * Initialize operation with operationName
     *
     * @param _operationName Name of the Operation
     * @param order
     */
    public ByeOperation(String _operationName, String order) {
        super(_operationName, order);
    }

    @Override
    public String operate(){
        return "Bye. Hope to see you again soon!";
    }
}
