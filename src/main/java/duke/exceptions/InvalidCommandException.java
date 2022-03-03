package duke.exceptions;

public class InvalidCommandException extends DukeException {
    private static final String INVALID_COMMAND_MESSAGE =
            "☹ OOPS!!! I'm sorry, but I don't know what '%s' means :-("
            + "\nType 'help' to get a list of commands for Duke!";

    public InvalidCommandException(String errorCommand) {
        super(String.format(INVALID_COMMAND_MESSAGE, errorCommand));
    }
}
