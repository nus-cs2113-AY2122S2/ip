package EmProgram.exception;

public class InvalidUserInputException extends Exception {
    /**
     * List of pre-defined error messages according to user input
     */
    public static final String INVALID_INPUT = "☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
    public static final String NO_DESCRIPTION = "☹ OOPS!!! The task description cannot be empty.";
    public static final String NO_TIME_OR_DESCRIPTION = "☹ OOPS!!! Did not include time or description.";
    public static final String INVALID_TASK = "☹ OOPS!!! The task number entered is not valid.";
    public static final String CORRUPTED_TASK_NUM = "☹ OOPS!!! The task number entered is not a number.";
    public static final String INVALID_TIME_OR_DATE = "☹ OOPS!!! The time or date you entered is invalid.\n " +
            "Please ensure that the date format is in YYYY-MM-DD and the time format is in HHmm (24 hour) " +
            "(e.g. /at 2021-02-19 1330)";
    public static final String IO_EXCEPTION = "☹ OOPS!!! Something wrong with the input-output operation.";
    public static final String INVALID_MATCH = "☹ OOPS!!! The command is invalid. Please ensure command is entered as: \n" +
            " deadline [TASK DESCRIPTION] /by [DEADLINE TIMING] \n" + " event [TASK DESCRIPTION] /by [EVENT TIMING]" ;

    /**
     * Constructs an InvalidUserInputException with a specified error message.
     *
     * @param errorMsg The error message written by the throwing method.
     */
    public InvalidUserInputException(String errorMsg) {
        super(errorMsg);
    }
}
