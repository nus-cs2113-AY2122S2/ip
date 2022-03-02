package util.task;

/**
 * Represents a type of task the user needs to complete during a certain period of time
 */
public class Event extends Todo{
    protected String at;

    /**
     * Initialize an event object
     *
     * @param task The task user adds
     * @param at The duration of this task
     */
    public Event(String task, String at) {
        super(task);
        super.typeOfTask = "[E]";
        this.at = at;
    }

    public String getTime() {
        return at;
    }

    @Override
    public String toString() {
        return super.toString() + " (at: " + at + ")";
    }
}
