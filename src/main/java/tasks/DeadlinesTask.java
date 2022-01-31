package tasks;

public class DeadlinesTask extends Task{
    private String dateTime;
<<<<<<< HEAD
    public DeadlinesTask(String taskDescription, String taskType) {
        super(taskDescription, taskType);
=======
    public DeadlinesTask(String taskDescription , String taskType) {
        super(taskDescription , taskType);
>>>>>>> 9dada07e69461ecaf25973bf549e8fd670b8849f
    }

    /**
     * Initializes deadline task with task description and task type and dateTime
<<<<<<< HEAD
=======
     *
>>>>>>> 9dada07e69461ecaf25973bf549e8fd670b8849f
     * @param taskDescription
     * @param taskType
     * @param dateTime
     */
<<<<<<< HEAD
    public DeadlinesTask(String taskDescription, String taskType,String dateTime){
=======
    public DeadlinesTask(String taskDescription , String taskType,String dateTime){
>>>>>>> 9dada07e69461ecaf25973bf549e8fd670b8849f
        super();
        this.taskDescription = taskDescription;
        this.taskType = taskType;
        this.dateTime = dateTime;
    }

    /**
<<<<<<< HEAD
     * Sets the dateTime
=======
     * Sets the dateTime.
     *
>>>>>>> 9dada07e69461ecaf25973bf549e8fd670b8849f
     * @param dateTime
     */
    public void setDateTime(String dateTime){
        this.dateTime = dateTime;
    }

    /**
<<<<<<< HEAD
     * Gets the report of the deadline task
=======
     * Gets the report of the deadline task.
     *
>>>>>>> 9dada07e69461ecaf25973bf549e8fd670b8849f
     * @return The report of the task
     */
    @Override
    public String getReport(){
<<<<<<< HEAD
        return String.format("[%s][%s] %s (by: %s)", taskType, markedSign(), taskDescription, dateTime);
=======
        return String.format("[%s][%s] %s (by: %s)" , taskType , markedSign() , taskDescription , dateTime);
>>>>>>> 9dada07e69461ecaf25973bf549e8fd670b8849f
    }
}
