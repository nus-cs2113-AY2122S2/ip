package duke;

/**
 * Represents a custom Exception class to handle Duke errors
 */
public class DukeException extends Exception {
    public DukeException(String message) {
        super(message);
    }
}
