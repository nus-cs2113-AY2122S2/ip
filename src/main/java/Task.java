public class Task {
    private String description;
    private boolean taskDone;
    private int taskId;

    private static int taskRunningCount = 1;
    //constructors
    public Task(String description) {
        this.taskId = taskRunningCount;
        this.description = description;
        this.taskDone = false; //default when a task is created it is not done
        taskRunningCount++;
    }

    //mutator methods
    public void editDescription(String description) {
        this.description = description;
    }
    public void markAsDone() {
        this.taskDone = true;
    }
    public void markAsNotDone() {
        this.taskDone = false;
    }

    //accessor methods
    public String getDescription() {
        return description;
    }
    public String getTaskStatus() {
        return (taskDone ? "X" : " "); //if task done mark it with X
    }
    public int getTaskId() {
        return taskId;
    }
    public static int getTaskRunningCount() {
        return taskRunningCount;
    }
}


