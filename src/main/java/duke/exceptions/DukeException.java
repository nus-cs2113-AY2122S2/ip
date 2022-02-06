package duke.exceptions;


public class DukeException extends Exception {
    private final String description;

    public DukeException(String errorMessage, String description) {
        super(errorMessage);
        this.description = description;
    }

    @Override
    public String toString() {
        return description;
    }
}