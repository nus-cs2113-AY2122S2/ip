public class Task {
    protected String description;
    protected boolean isTaskDone;
    public Task(String description) {
        this.description = description;
        this.isTaskDone = false;
    }

    public void markAsDone() {
        this.isTaskDone = true;
    }

    public void markAsUndone() {this.isTaskDone = false; }

    public String getDescription(){
        return description;
    }

    public String toString(){
        return (isTaskDone ? "[X] " : "[ ] ") + getDescription();
    }

}
