package sora;

/**
 * Thrown to indicate that a command entered by the user has certain issues. In particular, this exception
 * is thrown when the user...
 * <ul>
 *     <li>enters a command that Sora does not understand, or</li>
 *     <li>enters an invalid command when adding a task (todo/event/deadline)</li>
 * </ul>
 */
public class InvalidCommandException extends Exception {
    /**
     * List of pre-defined error messages
     */
    public static final String NO_SUCH_COMMAND_MSG = "No such command";

    public static final String TODO_NO_DESCRIPTION = "No description for todo command was found";

    public static final String EVENT_NO_DESCRIPTION = "No description for event command was found";
    public static final String EVENT_MISSING_FLAG = "Required flag for event command was not found";
    public static final String EVENT_NO_PERIOD = "Ro period for event command was found";
    public static final String EVENT_INVALID_FLAGS = "Usage of flags in event command was invalid";

    public static final String DEADLINE_NO_DESCRIPTION = "No description for deadline command was found";
    public static final String DEADLINE_MISSING_FLAG = "Required flag for deadline command was not found";
    public static final String DEADLINE_NO_DUE_DATE = "No due date for deadline command was found";
    public static final String DEADLINE_INVALID_FLAGS = "Usage of flags in deadline command was invalid";

    /**
     * Constructs an InvalidCommandException with a specified error message.
     *
     * @param errorMsg The error message written by the throwing method.
     */
    public InvalidCommandException(String errorMsg) {
        super(errorMsg);
    }
}
