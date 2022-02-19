package duke.task;

public class Task {

    private String description;
    private boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public Task(String description, Boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public String getDescription() {
        return description;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public boolean getStatus() {
        return isDone;
    }

    public String getTaskType() {
        return "task";
    }

    public String getDateFormattedString() {
        return "-";
    }

    public String getDateForStorage() {
        return "-";
    }

    @Override
    public String toString(){
        return "["+getStatusIcon()+"] "+description;
    }
}
