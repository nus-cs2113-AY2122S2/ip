package duke.data.task;

import java.time.LocalDate;

/**
 * Parent class for all tasks.
 */
public class Task {
    protected static final String INDICATOR_MARKED = "[+]";
    protected static final String INDICATOR_UNMARKED = "[ ]";

    protected String taskDescription;
    protected boolean isDone;
    protected LocalDate savedDate;

    public Task(String description) {
        this.taskDescription = description;
        this.isDone = false;
        this.savedDate = null;
    }

    public boolean getIsDone() {
        return this.isDone;
    }

    public void setIsDone(boolean isDone) {
        this.isDone = isDone;
    }

    public String getDescription() {
        return taskDescription;
    }

    /**
     * Checks if the task's date matches the date being queried.
     * @param date the date being queried.
     * @return true, if the task's date and the queried date match, or false otherwise (or if either date is null).
     */
    public boolean isMatchingDate(LocalDate date) {
        if (date == null || savedDate == null) {
            return false;
        }
        return savedDate.equals(date);
    }

    /**
     * Encodes the task into the format in which it is stored in the data file.
     * @param FS the character used as the delimiter in the data file.
     * @return the task, serialised as a string.
     */
    public String formatAsData(String FS) {
        return (isDone ? 1 : 0) + FS + this.taskDescription;
    }

    /**
     * Pretty-prints the task as a string.
     * @return task details, as a human-friendly string.
     */
    @Override
    public String toString() {
        if (this.isDone) {
            return INDICATOR_MARKED + " " + this.taskDescription;
        } else {
            return INDICATOR_UNMARKED + " " + this.taskDescription;
        }
    }
}
