public abstract class Task {
    private String taskDescription;
    private boolean isCompleted;

    public Task(String taskDescription) {
        setTaskDescription(taskDescription);
        setCompleted(false);
    }

    public Task(String taskDescription, boolean isCompleted) {
        setTaskDescription(taskDescription);
        setCompleted(isCompleted);
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    public boolean getCompletionStatus() {
        return isCompleted;
    }

    public void setCompleted(boolean isCompleted) {
        this.isCompleted = isCompleted;
    }

    public String toString() {
        return "[" + (isCompleted ? "X" : " ") + "] " + getTaskDescription();
    }

    public abstract String getTaskCode();

    public abstract String getExtraInfo();
}
