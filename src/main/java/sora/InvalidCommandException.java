package sora;

public class InvalidCommandException extends Exception {
    // Used for when exception was not thrown with specified expected and received values
    private static final String EMPTY_VALUE_PLACEHOLDER = "N.A.";

    /**
     * List of pre-defined error messages
     */
    public static final String NO_SUCH_COMMAND_MSG = "no such command";

    public static final String TODO_NO_DESCRIPTION = "no description for todo command was found";

    public static final String EVENT_NO_DESCRIPTION = "no description for event command was found";
    public static final String EVENT_MISSING_FLAG = "required flag for event command was not found";
    public static final String EVENT_NO_PERIOD = "no period for event command was found";
    public static final String EVENT_INVALID_FLAGS = "usage of flags in this command was invalid";

    public static final String DEADLINE_NO_DESCRIPTION = "no description for deadline command was found";
    public static final String DEADLINE_MISSING_FLAG = "required flag for deadline command was not found";
    public static final String DEADLINE_NO_DUE_DATE = "no due date for deadline command was found";

    private String expectedValue;
    private String receivedValue;

    public InvalidCommandException(String errorMsg) {
        super(errorMsg);
        this.expectedValue = EMPTY_VALUE_PLACEHOLDER;
        this.receivedValue = EMPTY_VALUE_PLACEHOLDER;
    }

    public InvalidCommandException(String errorMsg, String expectedValue, String receivedValue) {
        super(errorMsg);
        this.expectedValue = expectedValue;
        this.receivedValue = receivedValue;
    }



    /**
     * Accessor methods
     */
    public String getExpectedValue() {
        return this.expectedValue;
    }

    public String getReceivedValue() {
        return this.receivedValue;
    }
}
