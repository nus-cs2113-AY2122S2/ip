package exception;

public class InvalidCommandException extends DukeException{
    private static final String ERROR_MESSAGE = "Oops! I don't understand this command";

    public String getErrorMessage() {
        return ERROR_MESSAGE;
    }
}
