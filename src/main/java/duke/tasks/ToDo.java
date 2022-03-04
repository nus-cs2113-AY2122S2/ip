package duke.tasks;
import java.util.ArrayList;

public class ToDo {
    /** Description of the user's task */
    protected String description;
    /** Whether or not the task is currently marked as done */
    protected boolean isDone;

    /**
     * Constructor for Todo (task) object
     *
     * @param description Description of the todo
     * @return The todo object
     */
    public ToDo(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Gets the Task Description
     *
     * @return The task description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the task status
     *
     * @param status The status (true/false) of whether the task is done
     */
    public void setDone(boolean status) {
        isDone = status;
    }

    /**
     * Gets the Task Status Icon (X if done, "" if not)
     *
     * @return The status icon
     */
    public String getStatusIcon() {
        String status = (isDone ? "X" : " ");
        String finalString = "[T][" + status + "]";
        return finalString;
    }
}
