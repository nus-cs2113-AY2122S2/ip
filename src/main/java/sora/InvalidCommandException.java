package sora;

public class InvalidCommandException extends Exception {
    // Used for when exception was not thrown with specified expected and received values
    private static final String EMPTY_VALUE_PLACEHOLDER = "N.A.";

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

    public static final String FIND_NO_SEARCH_STRING = "No search phrase provided";

    public InvalidCommandException(String errorMsg) {
        super(errorMsg);
    }
}
