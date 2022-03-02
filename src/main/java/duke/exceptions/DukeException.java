package duke.exceptions;
/**
 * Signals an error caused by command processing.
 */
public class DukeException extends Exception {
    public DukeException(String message) {
        super(message);
    }
}
