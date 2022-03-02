package jarvis.commands;

import jarvis.display.DisplayMessages;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;


public class Task {
    protected String description;
    protected boolean isDone;
    private static final DateTimeFormatter STORE_FORMAT = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
    private static final DateTimeFormatter PRINT_FORMAT = DateTimeFormatter.ofPattern("dd MMM yyyy HHmm");
    private static final String TASK_ICON = "T";

    /**
     * Constructor for Task. Marks task as not done by default.
     * @param description Description of the task to be stored.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Getter function for retrieving the task's status icon. Marked by an X if it is done. A blank space otherwise.
     * @return
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Getter function for retrieving task's description
     * @return A String of the task's description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Getter function for retrieving an icon for the task's type.
     *
     * @return An icon in String data type to represent Task.
     */
    public String getTypeIcon() {
        return TASK_ICON;
    }

    /**
     * Function to mark a task as done, if it is not already marked. Calls default message for when task is already
     * marked.
     *
     * @return True if task is successfully marked as done. False otherwise.
     */
    public boolean markAsDone() {
        boolean isTaskDone = this.isDone == true;
        if (isTaskDone) {
            DisplayMessages.unmarkError();
            DisplayMessages.horizontalLine();
            return false;
        }
        this.isDone = true;
        return true;
    }

    /**
     * Function to unmark a task, if it is not already unmarked. Calls default message for when task is already
     * unmarked.
     *
     * @return True if task is successfully unmarked. False otherwise.
     */
    public boolean markAsUndone() {
        boolean isTaskUndone = this.isDone == false;
        if (isTaskUndone) {
            DisplayMessages.markError();
            return false;
        }
        this.isDone = false;
        return true;
    }

    /**
     * Function to retrieve Task's icon, status and description in a single String. Used for printing the entire task.
     * e.g. [T][ ] Read book
     *
     * @return A String of icon, status and description formatted.
     */
    public String getFullTask() {
        return "[" + getTypeIcon() + "]" + "[" + getStatusIcon() + "] " + getDescription();
    }

    /**
     * Function to export data of the Task. This is called when saving data when Jarvis is shutting down.
     *
     * @return String of task's icon, status and description to be placed inside data file for next boot's usage.
     */
    public String exportData() {
        String status = isDone ? "YES" : "NO";
        String temp = getTypeIcon() + " " + status + " " + getDescription();
        return temp;
    }

    public static LocalDateTime parseDate(String date, String time) {
        LocalDateTime taskDate = null;
        try {
            taskDate = LocalDateTime.parse(date + " " + time, STORE_FORMAT);
        } catch (DateTimeParseException e) {
            DisplayMessages.invalidDateTime();
        }
        return taskDate;
    }

    public static String dateToString(LocalDateTime date) {
        return date.format(PRINT_FORMAT);
    }
}
