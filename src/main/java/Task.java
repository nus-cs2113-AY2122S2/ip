/** Used to create task objects whenever a task is keyed in*/
public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false; //set to false as default
    }

    /** Method to get task status */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); //if task is done mark with X
    }

    /** Method to mark a task status as Done */
    public void markAsDone() {
        isDone = true;
    }

    /**Method to mark a task status as not done */
    public void markAsNotDone() {
        isDone = false;
    }
}
