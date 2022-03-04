package tasks;

/**
 * Thrown to indicate that the TaskList instance that is trying to be interacted with is empty,
 * that is, there are no tasks in the list.
 */
public class EmptyListException extends Exception {
    public static final String EMPTY_LIST_MSG = "List is empty.";

    /**
     * Constructs an EmptyListException with the specified error message.
     *
     * @param errorMsg The error message written by the throwing method.
     */
    public EmptyListException(String errorMsg) {
        super(errorMsg);
    }
}
