public class Task {
    protected String description;
    protected boolean isDone;
    protected static int numTask = 0;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
        numTask++;
    }

    public boolean getStatus() {
        return isDone;
    }

    public String getDescription() {
        return description;
    }

    public static int getNumTask() {
        return numTask;
    }

    public void markAsDone() {
        isDone = true;
    }

    public void unmarkAsDone() {
        isDone = false;
    }
}
