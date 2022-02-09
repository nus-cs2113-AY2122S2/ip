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

    public void printDescription() {
        if (isDone) {
            System.out.println("[X] " + description);
        } else {
            System.out.println("[ ] " + description);
        }
    }

    public void markAsDone() {
        isDone = true;
        System.out.println("Nice, I've marked this task as done:");
    }

    public void unmarkAsDone() {
        isDone = false;
        System.out.println("OK, I've marked this task as not done yet:");
    }
}
