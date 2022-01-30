package tasks;

public class EventTask extends Task{
    private String dateTime;
    public EventTask(String taskDescription, String taskType) {
        super(taskDescription, taskType);
    }

    /**
     * Initializes task with task description, taskType, dateTime.
     *
     * @param taskDescription The description of the task
     * @param taskType The type of the task
     * @param dateTime The time of the task
     */
    public EventTask(String taskDescription, String taskType,String dateTime){
        super();
        this.taskDescription = taskDescription;
        this.taskType = taskType;
        this.dateTime = dateTime;
    }


    /**
     * Sets dateTime.
     *
     * @param dateTime The time of the task
     */
    public void setDateTime(String dateTime){
        this.dateTime = dateTime;
    }


    /**
     * Gets the report of the event task.
     *
     * @return The report of the event task
     */
    @Override
    public String getReport(){
        return String.format("[%s][%s] %s (at: %s)", taskType, markedSign(), taskDescription, dateTime);
    }

}
