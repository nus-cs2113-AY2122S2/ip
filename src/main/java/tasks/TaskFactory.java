package tasks;

public class TaskFactory {
    private String taskDescription;
    private Boolean marked = false;
    private String taskType = "";

    /**
     * Initializes the task factory
     * @param taskType
     * @param taskDecription
     */
    public TaskFactory(String taskType, String taskDecription){
        this.taskDescription = taskDecription;
        this.taskType = taskType;
    }

    public TaskFactory(){
    }

    /**
     * Makes certain task according to the taskType
     * @return Certain task
     */
    public Task makeTask() {
        switch (taskType) {
        case "todo":
            return new ToDoTask(taskDescription,"T");
        case "deadline":
            String[] taskInfomation = taskDescription.split("/by");
            taskDescription = taskInfomation[0].trim();
            String dateTime = taskInfomation[1].trim();
            return new DeadlinesTask(taskDescription, "D", dateTime);
        case "event":
            taskInfomation = taskDescription.split("/at");
            taskDescription = taskInfomation[0].trim();
            dateTime = taskInfomation[1].trim();
            return new EventTask(taskDescription, "E", dateTime);
        default:
            return new Task(taskDescription);
        }
    }

    /**
     * Sets the taskDescription of TaskFactory
     * @param taskDescription
     */
    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    /**
     * Sets the taskType of TaskFactory
     * @param taskType The task type
     */
    public void setTaskType(String taskType){
        this.taskType = taskType;
    }
}
