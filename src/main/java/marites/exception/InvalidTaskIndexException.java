package marites.exception;

/**
 * Exception raised when a task index is invalid (i.e. is not an integer.)
 */
public class InvalidTaskIndexException extends MaritesException {

    private final String taskIndex;
    private static final String ERROR_MESSAGE =
            "Invalid task index: %s\n";

    public InvalidTaskIndexException(String taskIndex) {
        super();
        this.taskIndex = taskIndex;
    }
    /**
     * Returns an error message for user consumption.
     * @return Error message
     */
    @Override
    public String getErrorMessage() {
        return String.format(ERROR_MESSAGE, taskIndex);
    }
}
