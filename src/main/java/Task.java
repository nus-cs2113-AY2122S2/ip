public class Task {
    protected String taskDescription;
    protected boolean isDone;

    private static int numberOfTasks = 0;

    public Task(String taskDescription) {
        this.taskDescription = taskDescription;
        this.isDone = false;
        numberOfTasks += 1;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public void setTaskName(String newTaskDescription) {
        taskDescription = newTaskDescription;
    }

    public static int getNumberOfTasks() {
        return numberOfTasks;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public void markAsDone() {
        isDone = true;
    }

    public void markAsUndone() {
        isDone = false;
    }
}
