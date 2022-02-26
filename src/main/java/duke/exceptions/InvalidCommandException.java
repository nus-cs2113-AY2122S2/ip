package duke.exceptions;

public class InvalidCommandException extends DukeException {
    private static final String INVALID_COMMAND_MESSAGE =
            "☹ OOPS!!! I'm sorry, but I don't know what '%s' means :-("
            + "\nList of commands for Duke: "
            + "\nlist"
            + "\nbye"
            + "\nmark <task number>"
            + "\nunmark <task number>"
            + "\ntodo <description>"
            + "\ndeadline <description> /by <date>"
            + "\nevent <description> /at <date>"
            + "\ndelete <task number>";

    public InvalidCommandException(String errorCommand) {
        super(String.format(INVALID_COMMAND_MESSAGE, errorCommand));
    }
}
