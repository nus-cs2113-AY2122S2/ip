public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.echoTask();
    }
    public String getDescription() {
        return description;
    }

    public void echoTask() {
        System.out.println("Successfully added to list: " + description);
    }

    /* For Level-3
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void markAsDone() {
        isDone = true;
    }

    public void markAsNotDone() {
        isDone = false;
    }
    */
}