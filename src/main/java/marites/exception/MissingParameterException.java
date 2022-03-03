package marites.exception;

public class MissingParameterException extends MaritesException {
    private final String missingParameterTag;
    private static final String ERROR_MESSAGE =
            "You're missing a parameter: %s\n";

    public MissingParameterException(String missingParameterTag) {
        super();
        this.missingParameterTag = missingParameterTag;
    }

    public String getErrorMessage() {
        return String.format(ERROR_MESSAGE, missingParameterTag);
    }
}
