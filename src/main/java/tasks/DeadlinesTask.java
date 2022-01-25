package tasks;

public class DeadlinesTask extends Task{
    private String dateTime;
    public DeadlinesTask(String taskDescription, String taskType) {
        super(taskDescription, taskType);
    }

    /**
     * Initializes deadline task with task description and task type and dateTime
     * @param taskDescription
     * @param taskType
     * @param dateTime
     */
    public DeadlinesTask(String taskDescription, String taskType,String dateTime){
        super();
        this.taskDescription = taskDescription;
        this.taskType = taskType;
        this.dateTime = dateTime;
    }

    /**
     * Sets the dateTime
     * @param dateTime
     */
    public void setDateTime(String dateTime){
        this.dateTime = dateTime;
    }

    /**
     * Gets the report of the deadline task
     * @return The report of the task
     */
    @Override
    public String getReport(){
        return String.format("[%s][%s] %s (by: %s)", taskType, markedSign(), taskDescription, dateTime);
    }
}
