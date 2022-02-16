package solana.task;

public class Task {
    protected String description;
    protected boolean isDone;
    protected static int taskNum = 0;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
        taskNum++;
    }

    public static int getTaskNum() {
        return taskNum;
    }

    public void markAsDone() {
        isDone = true;
    }

    public void unmarkAsDone() {
        isDone = false;
    }
}
