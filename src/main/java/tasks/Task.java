package tasks;

public class Task {
    private String taskDescription;
    private Boolean marked=false;

    public Task(String _taskDescription){
        this.taskDescription =  _taskDescription;
    }

    /**
     * @return Get the description of the task
     */
    public String getTaskDescription(){
        return taskDescription;
    }

    /**
     * @return Get the report of the task
     */
    public String getReport(){
        if (marked != true) {
            return "[ ] " + taskDescription;
        } else {
            return "[X] " + taskDescription;
        }
    }


    /**
     * Set the mark of a task
     * @param isMark Boolean that is to be set
     */
    public void setMark(Boolean isMark) {
       marked = isMark;
    }

}
