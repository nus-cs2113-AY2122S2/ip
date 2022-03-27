package exception;

public class NoDescriptionException extends DukeException{
    private static final String ERROR_MESSAGE = "Oops! No Description Found!";

    public String getErrorMessage() {
        return ERROR_MESSAGE;
    }
}
