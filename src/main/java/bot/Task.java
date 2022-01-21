package bot;

public class Task {
    /**
     * The actual task description.
     */
    public final String task;

    /**
     * Checks whether the item has been marked.
     */
    public boolean marked;

    /**
     * Creates a Task object.
     *
     * @param task Task description.
     */
    public Task(String task) {
        this.task = task;
    }

    /**
     * Sets whether the task is marked as done or not.
     *
     * @param marked New marked status.
     */
    public void setMarked(boolean marked) {
        this.marked = marked;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s ", this.marked ? "X" : " ", this.task);
    }
}
