/**
 * Represents exceptions that are specifically related to the Duke application
 * Also allows support for specifying the precise error message
 */
public class DukeException extends Exception {
    public DukeException(String errorMessage) {
        super(errorMessage);
    }
}
