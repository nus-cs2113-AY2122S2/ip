public class InvalidCommandException extends Exception {
    private static final String INVALID_COMMAND_MESSAGE =
            "____________________________________________________________"
            + "\n☹ OOPS!!! I'm sorry, but I don't know what '%s' means :-("
            + "\n____________________________________________________________";

    public InvalidCommandException(String errorCommand) {
        super(String.format(INVALID_COMMAND_MESSAGE, errorCommand));
    }
}
