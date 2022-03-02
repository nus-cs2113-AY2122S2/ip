package common;

/**
 * Contains almost all the relevant messages that will be output through the Ui.
 */
public class Message {
    public static final String HORIZONTAL_LINE = "__________________________________________________";

    public static final String GREET_MESSAGE =
            " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n"
            + "__________________________________________________\n"
            + "Hello! Duke here!\n"
            + "How can I help?\n"
            + "__________________________________________________\n";

    public static final String GOODBYE_MESSAGE = "\t Goodbye. See you next time!";
    public static final String TODO_MESSAGE = "Added to new thing to do for ya!";
    public static final String DEADLINE_MESSAGE = "Added to thing for ya, and ya gotta do it soon!";
    public static final String EVENT_MESSAGE = "Added to thing for ya at some place and time!";
    public static final String LIST_MESSAGE = "Here are the tasks you want!";
    public static final String DELETE_MESSAGE = "Either you're done with that or you gave up. Anyways, it's gone!";
    public static final String MARK_MESSAGE = "Marked this task as done!";
    public static final String UNMARK_MESSAGE = "Guess you messed up huh? Reverted that task!";
    public static final String INVALID_COMMAND_MESSAGE = "Whoopsies! I dont know what you're talking about! Try again!";
    public static final String MISSING_DESCRIPTION_MESSAGE = "I think you forgot some stuff there for that command! Try again!";
    public static final String INVALID_INDEX_MESSAGE = "Don't think that's a valid index bro! Try again!";
}