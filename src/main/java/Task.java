public class Task {
    private final String taskDescription;
    private static int numOfTasks = 0;

    public Task(String taskDescription) {
        this.taskDescription = taskDescription;
        numOfTasks += 1;
    }

    public static int getNumOfTasks() {
        return numOfTasks;
    }

    public String getTaskDescription() {
        return taskDescription;
    }
}
