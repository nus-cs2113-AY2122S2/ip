public class Task {
    protected String description;
    protected boolean isTaskDone;

    public Task(String description) {
        this.description = description;
        this.isTaskDone = false;
    }

    public String getStatusIcon() {
        return (isTaskDone ? "[X]" : "[ ]");
    }

    public void markAsDone() {
        this.isTaskDone = true;
    }

    public void markAsUndone() {this.isTaskDone = false; }

    public String getDescription(){
        return description;
    }
}
