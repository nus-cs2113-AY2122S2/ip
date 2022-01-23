public class ListOperation extends Operation{


    /**
     * Initialize operation with operationName
     *
     * @param _operationName Name of the Operation
     * @param order Order given by User
     */
    public ListOperation(String _operationName, String order) {
        super(_operationName, order);
        result = operate();
    }

    @Override
    public String operate(){
        int _sizeArray = TaskList.size();
        String _result = "";
        for (int i = 0; i< _sizeArray; i++){
            if (i>0){
                _result += "\n";
            }
            Task _task = TaskList.get(i);
            _result += (String.valueOf(i+1)+". "+_task.getTaskDescription());
        }
        return _result;
    }


}
