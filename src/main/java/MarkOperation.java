public class MarkOperation extends Operation{

    /**
     * Initialize operation with operationName
     *
     * @param _operationName Name of the Operation
     * @param order          Order given by User
     */
    public MarkOperation(String _operationName, String order) {
        super(_operationName, order);
    }

    @Override public String operate(){
        String [] _orderList = order.split(" ");
        int _index = Integer.parseInt(_orderList[1]);
        int _listSize =  TaskList.size();
        if (_index >= _listSize){
            return "Woops! fail to mark this task: \n   Task index out of range";
        }
        else{
            TaskList.get(_index).markTask();
            return "Nice! I've marked this task as done: \n " + TaskList.get(_index).getTaskDescription();
        }

    }


}
