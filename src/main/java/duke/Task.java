package duke;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) throws NullPointerException {
        if (description.isBlank()) {
            throw new NullPointerException();
        }
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsNotDone() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        return " [" + this.getStatusIcon() + "] " + this.description;
    }

    //@@author quitejasper-reused
    //Reused from https://github.com/FaliciaOng/ip/blob/master/src/main/java/TaskFileManager.java
    //with minor modifications
    public String toFileString() {
        return "| " + getStatusNumber() + " | " + this.description;
    }

    private int getStatusNumber() {
        return (isDone ? 1 : 0);
    }
    //@@author
}
