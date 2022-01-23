public class AddOperation extends Operation{

    /**
     * Initialize operation with operationName
     *
     * @param _operationName Name of the Operation
     * @param order Order given by User
     */
    public AddOperation(String _operationName, String order) {
        super(_operationName, order);
    }

    @Override
    public String operate(){
        Task _newTask = new Task(order);
        Boolean addBool = TaskList.addTask(_newTask);
        if (addBool)
        {
            return "added: " + _newTask.getTaskDescription();
        }
        else
        {
            return "failed to add: " + _newTask.getTaskDescription();
        }

    }





}
