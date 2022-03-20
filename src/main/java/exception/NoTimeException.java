package exception;

public class NoTimeException extends DukeException{
    private static final String ERROR_MESSAGE = "Oops! No time information found!";

    public String getErrorMessage() {
        return ERROR_MESSAGE;
    }
}
