package Duke.Tasks;

/**
 * Represents an event task.
 */
public class Event extends Task {
    protected String date;

    /**
     * Constructs a event object.
     *
     * @param description The description of event to be done.
     * @param isDone Whether the task has been done.
     * @param date deadline of the task to be done.
     */
    public Event(String description,boolean isDone, String date) {
        super(description, isDone);
        this.date = date;
    }

    /**
     * Returns event as a readable string.
     *
     * @return event as a readable string.
     */
    @Override
    public String toString () {
        return "[E]" + super.toString() + " (at: " + this.date + ")";
    }

    /**
     * Saves the tasks in the list as a certain format.
     *
     * @return String to be saved.
     */
    @Override
    public String saveTasks() {
        return "E " + super.saveTasks() + " | " + this.date + System.lineSeparator();
    }

}
