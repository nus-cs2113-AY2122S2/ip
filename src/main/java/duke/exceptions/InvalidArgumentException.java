package duke.exceptions;

public class InvalidArgumentException extends Exception {

    private static final String INVALID_COMMAND_MESSAGE =
            "____________________________________________________________"
            + "\n☹ OOPS!!! The command entered for %s ran into an error. The error: \n%s"
            + "\n____________________________________________________________";

    public InvalidArgumentException(String inputCommand, String errorMessage) {
        super(String.format(INVALID_COMMAND_MESSAGE, inputCommand, errorMessage));
    }
}
