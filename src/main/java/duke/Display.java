package duke;

import java.sql.SQLOutput;

public class Display {
    // Types of errors
    public enum ErrorType {
        MISSING_TASK_NO, INVALID_TASK_NO, EMPTY_INPUT, EMPTY_TASK_NAME,
        MISSING_EVENT_DELIMITER, MISSING_DEADLINE_DELIMITER,
        EMPTY_TASK_LIST, COMMAND_NOT_RECOGNISED, CSV_DELIMITER_IN_TASK,
        FILE_CREATION_FAILED, SAVE_LOAD_FAILED, SAVE_WRITE_FAILED
    }

    // Error Messages
    public static final String ERROR_MISSING_TASK_NO =
            "Please specify a task number to mark/unmark.";
    public static final String ERROR_INVALID_TASK_NO =
            "The input task number you have entered is invalid.";
    public static final String ERROR_EMPTY_INPUT =
            "Please enter either a task to do, mark/unmark <task number> or list";
    public static final String ERROR_EMPTY_TASK_NAME =
            "Please enter a name for your task.\n"
                    + "SYNTAX: todo <todo name>\n"
                    + "        event <event name> /at <time range>\n"
                    + "        deadline <deadline name> /by <due date>";
    public static final String ERROR_MISSING_EVENT_DELIMITER =
            "Please specify a time range for the event with the delimiter \"/at\"\n"
                    + "SYNTAX: event <event name> /at <time range>";
    public static final String ERROR_MISSING_DEADLINE_DELIMITER =
            "Please specify a deadline for the task with the delimiter \"/by\"\n"
                    + "SYNTAX: deadline <deadline name> /by <due date>";
    public static final String ERROR_EMPTY_TASK_LIST =
            "The task list is empty!";
    public static final String ERROR_COMMAND_NOT_RECOGNISED =
            "The input that you have entered is not recognised, enter 'help' to see available commands.";
    public static final String ERROR_CSV_DELIMITER_IN_TASK =
            "The delimiter '" + Duke.CSV_DELIMITER + "' is not allowed to be included in any part of the Task.\n"
                    + "Please try again after omitting it!";
    public static final String ERROR_FILE_CREATION_FAILED = 
            "The save system was unable to create the save file for this program. Any tasks created will not be saved.";
    public static final String ERROR_SAVE_LOAD_FAILED = 
            "The save system attempted to look for a save file, but encountered errors in the process of doing so.\n"
                    + "The program will proceed with running as if there was no save files.";
    public static final String ERROR_SAVE_WRITE_FAILED =
            "The save system attempted to write to a save file, but encountered errors in the process of doing so.\n"
                    + "The program will proceed with running without saving.";

    // Text Constants
    public static final String RABBIT_ASCII =
            "                      /|      __\n"
                    + "*             +      / |   ,-~ /             +\n"
                    + "     .              Y :|  //  /                .         *\n"
                    + "         .          | jj /( .^     *\n"
                    + "               *    >-\"~\"-v\"              .        *        .\n"
                    + "*                  /       Y\n"
                    + "   .     .        jo  o    |     .            +\n"
                    + "                 ( ~T~     j                     +     .\n"
                    + "      +           >._-' _./         +\n"
                    + "               /| ;-\"~ _  l\n"
                    + "  .           / l/ ,-\"~    \\     +\n"
                    + "              \\//\\/      .- \\\n"
                    + "       +       Y        /    Y\n"
                    + "               l       I     !\n"
                    + "               ]\\      _\\    /\"\\\n"
                    + "              (\" ~----( ~   Y.  )\n"
                    + "          ~~~~~~~~~~~~~~~~~~~~~~~~~~\n";
    public static final String WARREN_ASCII =
            ",--.   ,--.\n"
                    + "|  |   |  | ,--,--.,--.--.,--.--. ,---. ,--,--,\n"
                    + "|  |.'.|  |' ,-.  ||  .--'|  .--'| .-. :|      \\\n"
                    + "|   ,'.   |\\ '-'  ||  |   |  |   \\   --.|  ||  |\n"
                    + "'--'   '--' `--`--'`--'   `--'    `----'`--''--'\n";
    public static final String GREETING_MESSAGE =
            "Hello,\n" + WARREN_ASCII
                    + "What can I do for you? (enter 'hello' for available commands!)";
    public static final String FAREWELL_MESSAGE = "Bye. Hope to see you again soon!";
    public static final String HELP_MESSAGE =
            "| AVAILABLE COMMANDS |\n"
                    + "  1. list\n"
                    + "        Displays the full list of tasks that have been saved.\n"
                    + "  2. todo <task name>\n"
                    + "        Creates a new todo list entry.\n"
                    + "  3. event <event name> /at <time range>\n"
                    + "        Creates a new event entry with its accompanying time of occurrence.\n"
                    + "  4. deadline <deadline name> /by <due date>\n"
                    + "        Creates a new deadline entry with its accompanying due date.\n"
                    + "  5. mark <task entry number>\n"
                    + "        Marks the entry as done, see list for entry numbers.\n"
                    + "  6. unmark <task entry number>\n"
                    + "        Marks the entry as incomplete, see list for entry numbers.\n"
                    + "  7. help / ?\n"
                    + "        Displays this help menu.\n"
                    + "  8. bye / exit / quit\n"
                    + "        Displays the farewell message and closes the application.";

    /**
     * Simply prints out a dividing line to standard output
     */
    public static void printDivider() {
        System.out.println("____________________________________________________________");
    }

    /**
     * Prints out the greeting message to standard output
     */
    public static void printGreetingMessage() {
        printDivider();
        System.out.println(RABBIT_ASCII);
        printDivider();
        System.out.println(GREETING_MESSAGE);
        printDivider();
    }

    /**
     * Prints out the farewell message to standard output
     */
    public static void printFarewellMessage() {
        System.out.println(FAREWELL_MESSAGE);
        printDivider();
    }

    public static void printHelpMessage() {
        System.out.println(HELP_MESSAGE);
    }

    /**
     * Prints out error message based on what type of error has been encountered by the program
     *
     * @param error The error type encountered by the program
     */
    public static void printError(ErrorType error) {
        switch (error) {
        case MISSING_TASK_NO:
            System.out.println(ERROR_MISSING_TASK_NO);
            break;
        case INVALID_TASK_NO:
            System.out.println(ERROR_INVALID_TASK_NO);
            break;
        case EMPTY_INPUT:
            System.out.println(ERROR_EMPTY_INPUT);
            break;
        case EMPTY_TASK_NAME:
            System.out.println(ERROR_EMPTY_TASK_NAME);
            break;
        case MISSING_EVENT_DELIMITER:
            System.out.println(ERROR_MISSING_EVENT_DELIMITER);
            break;
        case MISSING_DEADLINE_DELIMITER:
            System.out.println(ERROR_MISSING_DEADLINE_DELIMITER);
            break;
        case EMPTY_TASK_LIST:
            System.out.println(ERROR_EMPTY_TASK_LIST);
            break;
        case COMMAND_NOT_RECOGNISED:
            System.out.println(ERROR_COMMAND_NOT_RECOGNISED);
            break;
        case CSV_DELIMITER_IN_TASK:
            System.out.println(ERROR_CSV_DELIMITER_IN_TASK);
            break;
        case FILE_CREATION_FAILED:
            System.out.println(ERROR_FILE_CREATION_FAILED);
            break;
        case SAVE_LOAD_FAILED:
            System.out.println(ERROR_SAVE_LOAD_FAILED);
            break;
        case SAVE_WRITE_FAILED:
            System.out.println(ERROR_SAVE_WRITE_FAILED);
            break;
        }
    }
}