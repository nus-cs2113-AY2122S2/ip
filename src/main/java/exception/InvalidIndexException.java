package exception;

public class InvalidIndexException extends DukeException{
    private static final String ERROR_MESSAGE = "Oops! This is invalid index!";

    public String getErrorMessage() {
        return ERROR_MESSAGE;
    }
}
