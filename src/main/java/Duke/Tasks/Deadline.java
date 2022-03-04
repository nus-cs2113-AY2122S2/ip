package Duke.Tasks;

/**
 * Represents a deadline task.
 */
public class Deadline extends Task {
    protected String date;

    /**
     * Constructs a deadline object.
     *
     * @param description The description of deadline to be done.
     * @param isDone Whether the task has been done.
     * @param date deadline of the task to be done.
     */
    public Deadline(String description,boolean isDone, String date) {
        super(description,isDone);
        this.date = date;
    }

    /**
     * Returns deadline as a readable string.
     *
     * @return deadline as a readable string.
     */
    @Override
    public String toString () {
        return "[D]" + super.toString() + " (by: " + this.date + ")";
    }

    /**
     * Saves the tasks in the list as a certain format.
     *
     * @return String to be saved.
     */
    @Override
    public String saveTasks() {
        return "D " + super.saveTasks() + " | " + this.date + System.lineSeparator();
    }
}
