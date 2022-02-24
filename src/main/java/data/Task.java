package data;

public class Task {
    private String description;
    private boolean isDone;

    public Task(String description) {
        this.description = description;
        isDone = false;
    }

    public Task(String description, boolean isDone){
        this.description = description;
        this.isDone = isDone;
    }

    public String getDescription() {

        return description;
    }

    public boolean isDone() {

        return isDone;
    }

    public void setDescription(String description) {

        this.description = description;
    }

    public void markAsDone() {

        isDone = true;
    }

    public void unmark() {

        isDone = false;
    }

    public String getStatusIcon() {

        return (isDone ? "X" : " ");
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

    public String getInfo() {
        return "U / " + isDone + " / " + description;
    }
}
