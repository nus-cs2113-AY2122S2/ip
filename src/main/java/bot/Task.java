package bot;

public class Task {
    /**
     * The actual task description.
     */
    public final String task;

    /**
     * Creates a Task object.
     *
     * @param task      Task description.
     */
    public Task(String task) {
        this.task = task;
    }

    @Override
    public String toString() {
        return this.task;
    }
}
