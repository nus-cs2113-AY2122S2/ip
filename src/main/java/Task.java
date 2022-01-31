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

    public boolean markAsDone() {
        boolean isTaskDone = this.isDone == true;
        if (isTaskDone) {
            System.out.println("This task is already marked as done! Did to mean to unmark it?");
            return false;
        }

        this.isDone = true;
        return true;
    }

    public boolean markAsUndone() {
        boolean isTaskUndone = this.isDone == false;
        if (isTaskUndone) {
            System.out.println("This task is already marked as not done! Did you mean to mark it?");
            return false;
        }
        this.isDone = false;
        return true;
    }
}
