/**
 * Operation of duke
 */
abstract class Operation {

    protected String operationName="";
    protected String order="";
    protected String result="";


    /**
     * Initialize operation with operationName
     * @param _operationName Name of the Operation
     */
    public Operation(String _operationName, String order){

        this.operationName = _operationName;
        this.order = order;
        this.result = operate();

    }


    /**
     * get the Order of the Operation
     * @return Operation with Parameters
     */
    public String getOperationName(){
        return operationName;
    }


    /**
     * @return Result of the operation
     */
    public String getResult(){
        return result;
    }


    /**
     *  Print the result of the operation, for the Operation abstract class, just print the operation name
     */
    public void displayResult(){

        ChatBox.chatBoxPrinter(operationName);

    }

    public String operate(){
        return operationName;
    }

}
