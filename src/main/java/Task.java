/**
 * Represents a task with task information stored as a string and a boolean for whether it is completed.
 */
public class Task {
    private String taskInfo;
    private boolean isDone;

    /**
     * Constructor for a new Task, assumes that the Task is not completed.
     * @param taskInfo
     */
    public Task(String taskInfo){
        this.taskInfo = taskInfo;
        this.isDone = false;
    }

    public String getTaskInfo() {
        return taskInfo;
    }

    public void setTaskInfo(String taskInfo) {
        this.taskInfo = taskInfo;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    @Override
    public String toString() {
        String completion = " ";
        if (isDone){
            completion = "X";
        }
        return "["+completion+"] "+taskInfo;
    }
}
