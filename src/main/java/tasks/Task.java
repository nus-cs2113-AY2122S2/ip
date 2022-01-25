package tasks;

public class Task {
    protected  String taskDescription;
    protected  Boolean marked = false;
    protected String taskType = " ";

    /**
     * Initializes task with task description
     * @param taskDescription The description of the task
     */
    public Task(String taskDescription){
        this.taskDescription =  taskDescription;
    }

    /**
     * Initializes task with task description and task type
     * @param taskDescription The description of the task
     * @param taskType The type of the task
     */
    public Task(String taskDescription, String taskType){
        this.taskDescription = taskDescription;
        this.taskType =  taskType;
    }

    public Task() {

    }

    /**
     * Gets the description of the task
     * @return The description of the task
     */
    public String getTaskDescription(){
        return taskDescription;
    }

    protected String markedSign(){
        if(marked!=true){
            return " ";
        }
        else{
            return "X";
        }
    }

    /**
     * Gets the report of the task
     * @return The report of the task
     */
    public String getReport(){
        return String.format("[%s][%s] %s", taskType, markedSign(), taskDescription);
    }


    /**
     * Sets the mark of a task
     * @param isMark Boolean that is to be set
     */
    public void setMark(Boolean isMark) {
       marked = isMark;
    }

}
