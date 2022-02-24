package em.exception;

public class InvalidUserInputException extends Exception {
    /**
     * List of pre-defined error messages according to user input
     */
    public static final String INVALID_INPUT = "☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
    public static final String NO_DESCRIPTION = "☹ OOPS!!! The task description cannot be empty.";
    public static final String NO_TIME_OR_DESCRIPTION = "☹ OOPS!!! Did not include time or description.";
    public static final String INVALID_TASK = "☹ OOPS!!! The task number entered is not valid.";
    public static final String CORRUPTED_TASK_NUM = "☹ OOPS!!! The task number entered is not a number.";

    /**
     * Constructs an InvalidUserInputException with a specified error message.
     *
     * @param errorMsg The error message written by the throwing method.
     */
    public InvalidUserInputException(String errorMsg) {
        super(errorMsg);
    }
}
