package yae.ui;

public class Ui {

    public static final String WELCOME_MESSAGE = "____________________________________________________________\n"
            + "Hello, nice to meet you. I'm Yae! (*^▽^*)\n"
            + "What can I do for you?\n"
            + "____________________________________________________________";
    public static final String DEFAULT_ERROR = "Sorry, I did not understand that command. Input help to find out more.";
    public static final String DESCRIPTION_MISSING = "Description/Task Number could not be found!";
    public static final String DATE_MISSING = "Date could not be found!";
    public static final String HELP = "Here are the list of commands:\n"
            + "list: Lists current tasks.\n"
            + "mark: Marks task as done. (e.g. mark <task number>)\n"
            + "unmark: Marks task as not done. (e.g. unmark <task number>)\n"
            + "todo: adds a todo. (e.g. todo <description>)\n"
            + "deadline: adds a deadline. (e.g. deadline <description> /by <date>)\n"
            + "event: adds an event. (e.g. event <description> /at <date>)\n"
            + "delete: delete a task. (e.g. delete <task number>)\n";

    public static void printWelcomeMessage() {
        System.out.println(WELCOME_MESSAGE);
    }

    public static void printDefaultErrorMessage() {
        System.out.println(DEFAULT_ERROR);
    }

    public static void printMissingDescriptionErrorMessage() {
        System.out.println(DESCRIPTION_MISSING);
    }

    public static void printMissingDateErrorMessage() {
        System.out.println(DATE_MISSING);
    }

    public static void printHelp() {
        System.out.println(HELP);
    }
}
