package exception;

public class NoTaskIndexFoundException extends DukeException{
    private static final String ERROR_MESSAGE = "Oops! No Task Index Found!";

    public String getErrorMessage() {
        return ERROR_MESSAGE;
    }
}

