package duke.task;


/**
 * Represents a task that needs to be done but does not have any date/time attached to it.
 * A ToDo object corresponds to a ToDo task and contains the task name and the task's symbol.
 */
public class Todo extends Task {
    private final String TODO_SYMBOL = "[T]";

    public Todo(String taskName) {
        super(taskName);
    }

    public String getTaskInformation() {
        return TODO_SYMBOL + super.getTaskInformation();
    }

    /**
     * Returns an acknowledgement message which informs the user that the task was successfully added.
     *
     * @return An acknowledgement message containing the name, type and status of the task that was added.
     */
    public String addTaskMessage() {
        String message;
        message = super.addTaskMessage() + "\t   " + getTaskInformation();
        return message;
    }

    /**
     * Returns an acknowledgement message which informs the user that the task status was successfully updated.
     * The task's status refers to whether or not a task is done.
     *
     * @return An acknowledgement message containing the updated details of the task that was updated.
     */
    public String getTaskUpdatedMessage() {
        String message;
        message = super.getTaskUpdatedMessage() + "\t   " + getTaskInformation();
        return message;
    }

    /**
     * Returns an acknowledgement message which informs the user that the task was successfully removed.
     *
     * @return An acknowledgement message containing the name, type and status of the task that was removed.
     */
    public String removeTaskMessage() {
        String acknowledgementMessage = super.removeTaskMessage() + "\t   " + getTaskInformation();
        return acknowledgementMessage;
    }

}
