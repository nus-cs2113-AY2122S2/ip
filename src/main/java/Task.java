/**
 * Represents a task with task information stored as a string and a boolean for whether it is completed.
 */
public class Task {
    private String taskInfo;
    private boolean isDone;

    /**
     * Constructor for a new Task, assumes that the Task is not completed.
     *
     * @param String representing taskInfo
     */
    public Task(String taskInfo) {
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

    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    @Override
    public String toString() {
        // ternary operator adapted from John McMonigle https://github.com/nus-cs2113-AY2122S2/ip/pull/56
        // rewrote return statement based on Clarence Chua Ying How's implementation https://github.com/nus-cs2113-AY2122S2/ip/pull/84
        String completion = (isDone ? "X" : " ");
        return String.format("[%s] %s", completion, taskInfo);
    }
}
