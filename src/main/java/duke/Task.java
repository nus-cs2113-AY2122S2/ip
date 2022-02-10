package duke;

public class Task {
    private String description;
    private boolean isDone;

    public Task(String description) {
        this.description = description;
        isDone = false;
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
}
