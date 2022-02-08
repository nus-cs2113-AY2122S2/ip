package duke.task;

public class Task {
    protected final String taskDescription;
    protected boolean isDone;
    protected static int numOfTasks = 0;

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

    @Override
    public String toString() {
        return getStatusIcon() + taskDescription;
    }
}
