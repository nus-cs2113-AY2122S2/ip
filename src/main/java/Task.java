public class Task {
    private String taskDescription;
    private Boolean status;

    public Task(String _taskDescription){
        this.taskDescription =  _taskDescription;
    }

    /**
     * @return Get the report of the task
     */
    public String getTaskDescription(){
        if (status != true) {
            return "[ ] " + taskDescription;
        } else return "[X] " + taskDescription;
    }

    /**
     * @return True if mark successfully
     */
    public Boolean markTask()
    {
        try{
            status =  true;
            return true;
        }catch (Exception e){
            return false;
        }

    }

    /**
     * @return True if unmark the task successfully
     */
    public Boolean unmarkTask()
    {
        try{
            status =  false;
            return true;
        }catch (Exception e){
            return false;
        }

    }

}
