public class EmptyCommandException extends Exception{
    private static final String EMPTY_COMMAND_MESSAGE =
            "____________________________________________________________"
            + "\n☹ OOPS!!! I'm sorry, but command cannot be empty!"
            + "\n____________________________________________________________";

    public EmptyCommandException() {
        super(EMPTY_COMMAND_MESSAGE);
    }
}
