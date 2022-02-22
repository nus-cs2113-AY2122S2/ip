package duke.exceptions;

public class EmptyCommandException extends DukeException {
    private static final String EMPTY_COMMAND_MESSAGE =  "☹ OOPS!!! I'm sorry, but command cannot be empty!";

    public EmptyCommandException() {
        super(EMPTY_COMMAND_MESSAGE);
    }
}
