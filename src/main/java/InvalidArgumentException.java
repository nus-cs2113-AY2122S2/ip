public class InvalidArgumentException extends Exception {

    private static final String INVALID_COMMAND_MESSAGE =
            "____________________________________________________________"
            + "\n☹ OOPS!!! The command entered for %s does not match (empty or mistyped). "
            + "\n____________________________________________________________";

    public InvalidArgumentException(String inputCommand) {
        super(String.format(INVALID_COMMAND_MESSAGE,inputCommand));
    }
}
