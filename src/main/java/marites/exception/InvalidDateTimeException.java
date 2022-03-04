package marites.exception;

public class InvalidDateTimeException extends MaritesException {
    String dateTime;
    private static final String ERROR_MESSAGE = "This isn't a valid date and time: %s\n" +
        "(Note that the format is yyyy-MM-dd HH:mm)\n";
    public InvalidDateTimeException(String dateTime) {
        this.dateTime = dateTime;
    }
    @Override
    public String getErrorMessage() {
        return String.format(ERROR_MESSAGE, dateTime);
    }
}
