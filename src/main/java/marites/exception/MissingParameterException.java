package marites.exception;

/**
 * Exception raised when an input command is missing a parameter.
 */
public class MissingParameterException extends MaritesException {
    private final String missingParameterTag;
    private static final String ERROR_MESSAGE =
            "You're missing a parameter: %s\n";

    public MissingParameterException(String missingParameterTag) {
        super();
        this.missingParameterTag = missingParameterTag;
    }

    /**
     * Returns an error message for user consumption.
     * @return Error message
     */
    @Override
    public String getErrorMessage() {
        return String.format(ERROR_MESSAGE, missingParameterTag);
    }
}
