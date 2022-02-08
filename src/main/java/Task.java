public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String getDescription() {
        return description;
    }

    public boolean getIsDone() {
        return isDone;
    }

    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void markTaskAsDone() {
        this.setDone(true);
        System.out.println("____________________________________________________________");
        System.out.println("Nice! I've marked this task as done:");
    }

    public void markTaskAsUndone() {
        this.setDone(false);
        System.out.println("____________________________________________________________");
        System.out.println("OK, I've marked this task as not done yet:");
    }

    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

}
