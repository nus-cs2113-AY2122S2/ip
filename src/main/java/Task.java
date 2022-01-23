public class Task {
    private final String taskDescription;
    private boolean isDone;
    private static int numOfTasks = 0;

    public Task(String taskDescription) {
        this.taskDescription = taskDescription;
        this.isDone = false;
        numOfTasks += 1;
    }

    public static int getNumOfTasks() {
        return numOfTasks;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public void setDone() {
        this.isDone = true;
    }

    public void setUndone() {
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "[X] " : "[ ] ");
    }
}
