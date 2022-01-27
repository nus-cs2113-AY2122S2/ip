/**
 * A basic reminder with status (done or not done yet)
 */
public class Reminder {
    /** The content of a reminder */
    private final String content;
    /** Whether this reminder is marked as done or not */
    private final Boolean isDone;

    /**
     * The only method to create a reminder by others
     *
     * @param content
     */
    public Reminder(String content) {
        this(content, false);
    }

    /**
     * Inner constructor, used when changing the status of instance
     *
     * @param content
     * @param isDone
     */
    private Reminder(String content, boolean isDone) {
        this.content = content;
        this.isDone = isDone;
    }

    /**
     * Get a new Reminder marked as Done with the same content.
     *
     * @return A new Reminder marked as Done with the same content
     */
    public Reminder setDone() {
        return new Reminder(content, true);
    }

    /**
     * Get a new Reminder marked as not Done yet with the same content.
     *
     * @return A new Reminder marked as not Done yet with the same content
     */
    public Reminder clearDone() {
        return new Reminder(content, false);
    }


    /**
     * Check the reminder is marked as done or not;
     *
     * @return The status of reminder
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Get the content of the reminder.
     *
     * @return The content of the reminder
     */
    public String getContent() {
        return content;
    }

    @Override
    public String toString() {
        return "[" + (isDone() ? "X" : " ") + "]\t" + getContent() + "\n";
    }
}
