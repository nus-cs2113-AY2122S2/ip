public class Task {
    private String taskDescription;
    private boolean isCompleted = false;

    public Task(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    public String getTaskDescription() {
        return this.taskDescription;
    }

    public boolean getCompletionStatus() {
        return this.isCompleted;
    }

    public void completeTask() {
        this.isCompleted = true;
    }

    public void revertCompletedTask() {
        this.isCompleted = false;
    }

    public String toString() {
        return "[" + (isCompleted ? "X" : " ") + "] " + this.getTaskDescription() + "\n";
    }
}
