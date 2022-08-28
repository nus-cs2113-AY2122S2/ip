public class Task {
    private String taskName;
    private boolean isDone;

    public Task(String taskName) {
        this.taskName = taskName;
        this.isDone = false;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public void setIsDone() {
        this.isDone = true;
    }

    public void setIsNotDone() {
        this.isDone = false;
    }

    public String getTaskName() {
        return taskName;
    }

    public boolean getIsDone() {
        return isDone;
    }

}