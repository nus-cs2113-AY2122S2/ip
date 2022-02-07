public class Task {
    protected String taskDescription;
    protected boolean isDone;
    protected static final String MARKED_TASK_SYMBOL = "X";
    protected static final String UNMARKED_TASK_SYMBOL = " ";

    private static int numberOfTasks = 0;

    public Task(String taskDescription) {
        this.taskDescription = taskDescription;
        this.isDone = false;
        numberOfTasks += 1;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    public static int getNumberOfTasks() {
        return numberOfTasks;
    }

    public void markAsDone() {
        isDone = true;
    }

    public void markAsUndone() {
        isDone = false;
    }

    @Override
    public String toString() {
        if (isDone) {
            return "[" +MARKED_TASK_SYMBOL+ "] " + taskDescription;
        } else {
            return "[" +UNMARKED_TASK_SYMBOL+ "] " + taskDescription;
        }
    }
}
