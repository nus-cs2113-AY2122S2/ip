package util.task;

/**
 * Represents a type of task that user needs to do before a given time
 */
public class Deadline extends Todo {
    protected String by;

    /**
     * Initialize a deadline object
     *
     * @param task The task user adds
     * @param by The deadline of this task
     */
    public Deadline(String task, String by) {
        super(task);
        super.typeOfTask = "[D]";
        this.by = by;
    }

    public String getTime() {
        return by;
    }

    @Override
    public String toString() {
        return super.toString() + " (by: " + by + ")";
    }
}
