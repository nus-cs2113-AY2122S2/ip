public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * @param description
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void changeStatus() {
        this.isDone = !this.isDone;
    }


    public String getStatusIcon() {
        return ("[" + (isDone ? "X" : " ") + "] "); // mark done task with X
    }

    @Override
    public String toString() {
        return getStatusIcon() + description;
    }
}