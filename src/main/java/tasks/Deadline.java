package tasks;

import sora.SoraUI;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Task of type Deadline. Deadline tasks contain a description of the task
 * and a date and time that represents the due date of the task.
 */
public class Deadline extends Task {
    private static final String DEADLINE_CHECKBOX = "[D]";

    private LocalDateTime dueDate;

    /**
     * Constructs a Deadline task with the specified description and due date.
     *
     * @param deadlineDescription The description of the Deadline task.
     * @param dateAndTime The due date of the deadline task.
     */
    public Deadline(String deadlineDescription, LocalDateTime dateAndTime) {
        super(deadlineDescription);
        this.dueDate = dateAndTime;
    }

    /**
     * Returns a string representation of the due date in a format that is meant for displaying
     * to the user.
     *
     * @return The due date of the instance in a format for displaying to the user.
     */
    public String getDueDate() {
        DateTimeFormatter dateTimeFormat= DateTimeFormatter.ofPattern(SoraUI.DATE_TIME_OUTPUT_FORMAT);
        return dueDate.format(dateTimeFormat);
    }

    /**
     * Returns a string representation of the due date in a format that is meant for exporting
     * to the file storage.
     *
     * @return The due date of the instance in a format for exporting to the file storage.
     */
    public String getDueDateForFileStorage() {
        DateTimeFormatter dateTimeFormat= DateTimeFormatter.ofPattern(SoraUI.DATE_TIME_INPUT_FORMAT);
        return dueDate.format(dateTimeFormat);
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
