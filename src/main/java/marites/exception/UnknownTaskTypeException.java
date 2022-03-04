package marites.exception;

/**
 * Exception raised when an unknown task type is encountered.
 */
public class UnknownTaskTypeException extends MaritesException {
    private final String taskType;

    public UnknownTaskTypeException(String taskType) {
        super();
        this.taskType = taskType;
    }

    private static final String ERROR_MESSAGE =
            "I don't know this command: '%s'\n";

    /**
     * Returns an error message for user consumption.
     * @return Error message
     */
    @Override
    public String getErrorMessage() {
        return String.format(ERROR_MESSAGE, taskType);
    }
}
