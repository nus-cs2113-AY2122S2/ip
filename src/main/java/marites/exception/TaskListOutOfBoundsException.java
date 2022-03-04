package marites.exception;

/**
 * Exception raised when an index into the TaskList is out of bounds.
 */
public class TaskListOutOfBoundsException extends MaritesException {
    private int index;

    public TaskListOutOfBoundsException() {
        super();
    }
    /**
     * Returns an error message for user consumption.
     * @return Error message
     */
    @Override
    public String getErrorMessage() {
        return "That index is out of bounds.";
    }
}
