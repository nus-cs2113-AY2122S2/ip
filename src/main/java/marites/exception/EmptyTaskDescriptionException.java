package marites.exception;

/**
 * Exception raised when a task is created without a description.
 */
public class EmptyTaskDescriptionException extends MaritesException {
    private static final String ERROR_MESSAGE =
            "Please add a description to your task.\n";

    public String getErrorMessage() {
        return ERROR_MESSAGE;
    }
}
