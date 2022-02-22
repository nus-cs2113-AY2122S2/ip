package tasks;

/**
 * Represents a Task of type Deadline. Deadline tasks contain a description of the task
 * and a date and time that represents the due date of the task.
 */
public class Deadline extends Task {
    // Index values of the task details when reading in from the String array argument in the constructor
    private static final int DEADLINE_DESCRIPTION = 0;
    private static final int DEADLINE_DATE = 1;
    private static final String DEADLINE_CHECKBOX = "[D]";

    private String dueDate;

    /**
     * Constructs a Deadline task with the specified description and due date.
     *
     * @param description The description of the Deadline task.
     * @param dueDate The due date of the deadline task.
     */
    public Deadline(String description, String dueDate) {
        super(description);
        this.dueDate = dueDate;
    }

    /**
     * Constructs a Deadline task with the specified description and due date in the
     * string array parameter.
     *
     * @param descriptionAndDate A string array containing the description and due date.
     */
    public Deadline(String[] descriptionAndDate) {
        super(descriptionAndDate[DEADLINE_DESCRIPTION]);
        this.dueDate = descriptionAndDate[DEADLINE_DATE];
    }

    /**
     * Gets the due date of the instance.
     *
     * @return The due date of the instance.
     */
    public String getDueDate() {
        return this.dueDate;
    }

    /**
     * Sets the due date of the instance.
     *
     * @param dueDate The new due date of the instance.
     */
    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    /**
     * Returns a string representation of the Deadline instance.
     *
     * @return A string representation of the Deadline instance.
     */
    public String toString() {
        String checkbox = getCheckbox();
        return String.format("%s%s %s (due on: %s)", DEADLINE_CHECKBOX, checkbox, getDescription(), getDueDate());
    }
}
