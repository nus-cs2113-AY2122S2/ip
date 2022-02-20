package duke.common;

/**
 * Utility class for string constants.
 */
public class Strings {

    // Misc text elements
    public static final String HORIZONTAL_SEPARATOR = "------------------------------------------------------------";
    public static final String INPUT_PROMPT = "> ";
    public static final String LS = System.lineSeparator();
    public static final String FS = "`";

    // Messages
    public static final String MESSAGE_DIRECTORY_FOUND = "Data directory found!";
    public static final String MESSAGE_DIRECTORY_CREATED = "Data directory created successfully!";
    public static final String MESSAGE_DIRECTORY_ERROR = "Data directory could not be created...";
    public static final String MESSAGE_DATA_FILE_FOUND = "Data file found!";
    public static final String MESSAGE_DATA_FILE_CREATED = "Data file created successfully!";
    public static final String MESSAGE_DATA_FILE_ERROR = "Data file could not be created...";
    public static final String MESSAGE_MALFORMED_TASK = "Skipped malformed task data at line %d: %s";
    public static final String MESSAGE_DATA_SAVE_ERROR = "Error saving to file. Your changes (if any) were NOT saved!";
    public static final String MESSAGE_DATA_LOADED = "Successfully loaded %d tasks from file.";
    public static final String MESSAGE_CLOSE_TO_FIX = "Close the program NOW if you wish to fix this manually.";

    public static final String MESSAGE_WELCOME = "Hi, I'm Robit! What would you like me to do?";
    public static final String MESSAGE_FILTERABLE_EXPLANATION = "Filterable tasks are marked with a ^.";
    public static final String MESSAGE_SHOW_DATE_FILTER = "Showing only tasks on %s...";
    public static final String MESSAGE_SHOW_TASKS = "Here are your tasks:";
    public static final String MESSAGE_NO_TASKS = "You don't have any tasks!";
    public static final String MESSAGE_INCORRECT_COMMAND_FORMAT = "Incorrect command format for %s.";
    public static final String MESSAGE_COMMAND_USAGE = "Usage: \"%s\"";
    public static final String MESSAGE_COMMAND_USAGE_ALT = "Usage: \"%s\" or \"%s\"";
    public static final String MESSAGE_DATE_SUPPORT = "Filtering is supported with dates! Try \"d/M/YYYY [hh:mm]\"!";
    public static final String MESSAGE_ITEMIZED_TASK = "%d) %s";
    public static final String MESSAGE_TODO_ADDED = "Todo successfully added:";
    public static final String MESSAGE_DEADLINE_ADDED = "Deadline successfully added:";
    public static final String MESSAGE_EVENT_ADDED = "Event successfully added:";
    public static final String MESSAGE_TASK_DELETED = "Task successfully deleted:";
    public static final String MESSAGE_UNKNOWN_COMMAND = "I don't understand that command...";
    public static final String MESSAGE_NO_SUCH_INDEX = "There's no task with that index...";
    public static final String MESSAGE_TASK_MARKED = "I've marked this task as done. Yay!";
    public static final String MESSAGE_TASK_UNMARKED = "I've unmarked this task.";
    public static final String MESSAGE_TASK_DISPLAY_FORMAT = "%d) %s";
    public static final String MESSAGE_TASK_ALREADY_MARKED = "That task is already marked.";
    public static final String MESSAGE_TASK_ALREADY_UNMARKED = "That task is already unmarked.";
    public static final String MESSAGE_GOODBYE = "Goodbye!";

    public static final String MESSAGE_IMPOSSIBLE = "Something has gone horribly wrong... program terminating.";

    // Date/Time formats
    public static final String FORMAT_DATE = "d/M/yyyy";
    public static final String FORMAT_DATETIME = "d/M/yyyy HH:mm";
    public static final String FORMAT_DATE_DISPLAY = "d MMM yyyy";

    // Commands
    public static final String COMMAND_BYE = "bye";
    public static final String COMMAND_LIST = "list";
    public static final String COMMAND_TODO = "todo";
    public static final String COMMAND_DEADLINE = "deadline";
    public static final String COMMAND_EVENT = "event";
    public static final String COMMAND_MARK = "mark";
    public static final String COMMAND_UNMARK = "unmark";
    public static final String COMMAND_DELETE = "delete";

    // Argument separators
    public static final String DEADLINE_SEPARATOR = " /by ";
    public static final String EVENT_SEPARATOR = " /at ";

    // Usage examples
    public static final String USAGE_BYE = COMMAND_BYE;
    public static final String USAGE_LIST = COMMAND_LIST;
    public static final String USAGE_LIST_DATE = COMMAND_LIST + " " + FORMAT_DATE;
    public static final String USAGE_TODO = COMMAND_TODO + " <your task>";
    public static final String USAGE_DEADLINE = COMMAND_DEADLINE + " <your task>" + DEADLINE_SEPARATOR
            + "<task deadline>";
    public static final String USAGE_EVENT = COMMAND_EVENT + " <your event>" + EVENT_SEPARATOR + "<time>";
    public static final String USAGE_MARK = COMMAND_MARK + " <task index>";
    public static final String USAGE_UNMARK = COMMAND_UNMARK + " <task index>";
    public static final String USAGE_DELETE = COMMAND_DELETE + " <task index>";
}
