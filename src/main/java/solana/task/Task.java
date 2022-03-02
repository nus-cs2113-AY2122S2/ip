package solana.task;

/**
 * Represents a Task. This class cannot be instantiated and serves as a Parent class for specific Task classes
 * to inherit from.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void markAsDone() {
        isDone = true;
    }

    public void unmarkAsDone() {
        isDone = false;
    }
}
