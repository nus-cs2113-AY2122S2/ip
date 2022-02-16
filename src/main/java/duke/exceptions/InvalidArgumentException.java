package duke.exceptions;

public class InvalidArgumentException extends Exception {

    private static final String INVALID_COMMAND_MESSAGE =
            "____________________________________________________________"
            + "\n☹ OOPS!!! The command entered for %s ran into an error. The error: \n%s"
            + "\n____________________________________________________________";

    /**
     * Exception to represent any parsing or run-time errors such as indexoutofbounds.
     * @param inputCommand representing the user's typed command
     * @param errorMessage representing the error message to show
     */
    public InvalidArgumentException(String inputCommand, String errorMessage) {
        super(String.format(INVALID_COMMAND_MESSAGE, inputCommand, errorMessage));
    }
}
