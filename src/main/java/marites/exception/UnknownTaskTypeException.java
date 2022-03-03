package marites.exception;

public class UnknownTaskTypeException extends MaritesException {
    private final String taskType;

    public UnknownTaskTypeException(String taskType) {
        super();
        this.taskType = taskType;
    }

    private static final String ERROR_MESSAGE =
            "I don't know this command: '%s'\n";

    public String getErrorMessage() {
        return String.format(ERROR_MESSAGE, taskType);
    }
}
