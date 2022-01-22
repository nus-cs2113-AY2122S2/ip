package util;

public class Task {
    private String description;
    private boolean isDone;
    private static int count = 0;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
        count++;
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

    public void setDone(boolean done) {
        isDone = done;
    }

    public String getStatusSymbol() {
        return (isDone() ? "X" : " ");
    }

    public static int getCount() {
        return count;
    }
}
