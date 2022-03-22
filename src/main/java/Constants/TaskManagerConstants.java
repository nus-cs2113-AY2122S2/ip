package Constants;

/**
 * Constants used by <code>TaskManager</code>.
 */
public class TaskManagerConstants {
    /**
     * Represents the maximum number of tasks that are stored in the task list.
     */
    public static final int MAX_TASKS = 100;

    /**
     * Length of words used in todo command <code>String</code> parsing.
     */
    public static final int LENGTH_TODO = "todo".length();

    /**
     * Length of words used in deadline command <code>String</code> parsing.
     */
    public static final int LENGTH_DEADLINE = "deadline".length();

    /**
     * Length of words used in event command <code>String</code> parsing.
     */
    public static final int LENGTH_EVENT = "event".length();

    /**
     * Length of words used in deadline and event command <code>String</code> parsing.
     */
    // "/at" and "/by" are of same length
    public static final int LENGTH_DATETIME_DELIMITER = "/at".length();
}
