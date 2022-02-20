package duke.data.task;

import java.time.LocalDate;

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

    public boolean isMatchingDate(LocalDate date) {
        if (date == null || savedDate == null) {
            return false;
        }
        return savedDate.equals(date);
    }

    public String formatAsData(String FS) {
        return (isDone ? 1 : 0) + FS + this.taskDescription;
    }

    @Override
    public String toString() {
        if (this.isDone) {
            return INDICATOR_MARKED + " " + this.taskDescription;
        } else {
            return INDICATOR_UNMARKED + " " + this.taskDescription;
        }
    }
}
