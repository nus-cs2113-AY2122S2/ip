/**
 * Deals with communicating to the user
 * Prints error messages, welcome and goodbye messages
 */
public class UI {

    public static final String LOGO = " ____        _        \n"
                                + "|  _ \\ _   _| | _____ \n"
                                + "| | | | | | | |/ / _ \\\n"
                                + "| |_| | |_| |   <  __/\n"
                                + "|____/ \\__,_|_|\\_\\___|\n";

    public static final String MESSAGE_GREET = "Hello! I'm Duke! :)\n" + "What can I do for you?\n";
    public static final String MESSAGE_BYE = "Bye. Hope to see you again soon! :)";
    public static final String COMMAND_BYE = "bye";
    public static final String COMMAND_LIST = "list";
    public static final String COMMAND_MARK = "mark";
    public static final String COMMAND_TODO = "todo";
    public static final String COMMAND_DEADLINE = "deadline";
    public static final String COMMAND_EVENT = "event";
    public static final String COMMAND_DELETE = "delete";
    public static final String COMMAND_FIND = "find";
    public static final String ERROR_NO_TASK_NUMBER = "☹ OOPS! You have not entered the task number!";
    public static final String ERROR_NO_KEYWORD = "☹ OOPS! You have not entered what you want to find! (The keyword!)";
    public static final String ERROR_NO_TASK = "☹ OOPS! You have not entered the task!";
    public static final String ERROR_NO_EVENT = "☹ OOPS! You have not entered the event!";
    public static final String ERROR_NO_EVENT_DATE = "Hey! You have not entered the event date! hint: use '/at'";
    public static final String ERROR_NO_DUE_DATE = "Hey! You have not entered the due date! hint: use '/by'";
    public static final String ERROR_GENERAL = "☹ OOPS! I'm sorry, but I don't know what that means.";
    public static final String ERROR_FAILED_TO_CREATE_FILE = "Failed to create file to save tasks.";

}
