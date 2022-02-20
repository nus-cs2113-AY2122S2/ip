package duke.task;

/**
 * Represents a todo task in Duke.
 */
public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    public Todo(String description, Boolean isDone) {
        super(description, isDone);
    }

    /**
     * Returns the Task's type in String.
     *
     * @return Returns the string "todo".
     */
    @Override
    public String getTaskType() {
        return "todo";
    }

    /**
     * Returns "-" as todo task
     * does not have a date
     *
     * @return Returns the string "-".
     */
    @Override
    public String getDateFormattedString() {
        return "-";
    }

    /**
     * Returns "-" as todo task
     * does not have a date
     *
     * @return Returns the string "-".
     */
    @Override
    public String getDateForStorageFile() {
        return "-";
    }

    /**
     * Returns the whole string
     * including the task icon, task status
     * and task name to be printed.
     *
     * @return The task icon, task status and task name in String.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
