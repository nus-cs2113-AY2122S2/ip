public class Task {
    private String taskDescription;
    private boolean isCompleted = false;

    public Task(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public boolean getCompletionStatus() {
        return isCompleted;
    }

    public void setCompleted() {
        isCompleted = true;
    }

    public void revertCompleted() {
        isCompleted = false;
    }

    public String toString() {
        return "[" + (isCompleted ? "X" : " ") + "] " + getTaskDescription();
    }
}
