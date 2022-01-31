package tasks;

public class EventTask extends Task{
    private String dateTime;
    public EventTask(String taskDescription, String taskType) {
        super(taskDescription, taskType);
    }

    /**
<<<<<<< HEAD
     * Initializes task with task description, taskType, dateTime
=======
     * Initializes task with task description, taskType, dateTime.
     *
>>>>>>> 9dada07e69461ecaf25973bf549e8fd670b8849f
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

<<<<<<< HEAD
    /**
     * Sets dateTime
=======

    /**
     * Sets dateTime.
     *
>>>>>>> 9dada07e69461ecaf25973bf549e8fd670b8849f
     * @param dateTime The time of the task
     */
    public void setDateTime(String dateTime){
        this.dateTime = dateTime;
    }

<<<<<<< HEAD
    /**
     * Gets the report of the event task
=======

    /**
     * Gets the report of the event task.
     *
>>>>>>> 9dada07e69461ecaf25973bf549e8fd670b8849f
     * @return The report of the event task
     */
    @Override
    public String getReport(){
        return String.format("[%s][%s] %s (at: %s)", taskType, markedSign(), taskDescription, dateTime);
    }

}
