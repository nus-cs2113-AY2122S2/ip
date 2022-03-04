package marites.exception;

/**
 * Exception raised when a date or time given by the user is invalid.
 */
public class InvalidDateTimeException extends MaritesException {
    String dateTime;
    private static final String ERROR_MESSAGE = "This isn't a valid date and time: %s\n" +
        "(Note that the format is yyyy-MM-dd HH:mm)\n";
    public InvalidDateTimeException(String dateTime) {
        this.dateTime = dateTime;
    }
    /**
     * Returns an error message for user consumption.
     * @return Error message
     */
    @Override
    public String getErrorMessage() {
        return String.format(ERROR_MESSAGE, dateTime);
    }
}
