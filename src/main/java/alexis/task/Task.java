package alexis.task;

import java.time.LocalDate;

import static alexis.ui.Ui.ADD_NEW_TASK_MESSAGE;
import static alexis.ui.Ui.MARK_AS_DONE_MESSAGE;
import static alexis.ui.Ui.MARK_AS_UNDONE_MESSAGE;

/**
 * Represents each task
 */
public class Task {

    public static final String BUFFER = "  ";

    protected String description;
    protected String timing;
    protected boolean isDone;
    protected LocalDate date = null;

    public char typeOfTask() {
        return '@';
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String getDescription() {
        return description;
    }

    public String getTiming() {
        return timing;
    }

    public String getFullDescription() {
        return description;
    }

    public LocalDate getDate() {
        return date;
    }

    public void addDate(LocalDate date) {
        this.date = date;
    }

    public String toString() {
        return "[" + typeOfTask() + "][" + getStatusIcon() + "] " + getFullDescription();
    }

    /**
     * Invoked every time a new task is added.
     */
    public void addNewTaskMessage() {
        System.out.println(ADD_NEW_TASK_MESSAGE);
        System.out.println(BUFFER + this);
    }

    /**
     * Sets up Task. Used for todo type tasks.
     *
     * @param description Description of task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Sets up Task. Used for deadline and event type tasks.
     *
     * @param description Description of task
     * @param timing Timing of task
     */
    public Task(String description, String timing) {
        this.description = description;
        this.timing = timing;
        this.isDone = false;
    }

    /**
     * Invoked in MarkCommand to mark task
     */
    public void markAsDone() {
        this.isDone = true;
        System.out.println(MARK_AS_DONE_MESSAGE);
        System.out.println(BUFFER + this);

    }

    /**
     * Invoked in UnmarkCommand to unmark task
     */
    public void markAsUndone() {
        this.isDone = false;
        System.out.println(MARK_AS_UNDONE_MESSAGE);
        System.out.println(BUFFER + this);
    }

    public void setIsDone() {
        this.isDone = true;
    }
}
