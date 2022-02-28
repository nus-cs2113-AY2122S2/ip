package sora;

import tasks.EmptyListException;
import tasks.Task;
import tasks.TaskList;
import util.Helper;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Random;

/**
 * This class handles the bulk of UI-related fields and methods that Sora uses. This includes
 * (but is not limited to):
 * <ul>
 *     <li>aesthetics such as banner logo and lines,</li>
 *     <li>normal response messages, and</li>
 *     <li>response messages when an exception is encountered.</li>
 * </ul>
 */
public class SoraUI {
    /**
     * Sora's banner logo design
     */
    protected static final String BANNER = "     _______.  ______   .______          ___      \n"
            + "    /       | /  __  \\  |   _  \\        /   \\     \n"
            + "   |   (----`|  |  |  | |  |_)  |      /  ^  \\    \n"
            + "    \\   \\    |  |  |  | |      /      /  /_\\  \\   \n"
            + ".----)   |   |  `--'  | |  |\\  \\----./  _____  \\  \n"
            + "|_______/     \\______/  | _| `._____/__/     \\__\\";

    /**
     * Character used at the beginning of user input prompts
     */
    protected static final String PROMPT_SYMBOL = ">";

    /**
     * Different positive acknowledgement words for Sora's neutral replies
     */
    protected static final String[] POSITIVE_ACKNOWLEDGEMENT_WORDS = {
            "Okay",
            "Alright",
            "Gotcha",
            "Got it"
    };

    /**
     * Different negative acknowledgement words for Sora's replies on errors
     */
    protected static final String[] NEGATIVE_ACKNOWLEDGEMENT_WORDS = {
            "Hmm",
            "Oh no",
            "Ah",
            "Dang",
            "Oops"
    };


    // Default parameters for printing formatting lines
    protected static final int DEFAULT_LINE_LENGTH = 60;
    protected static final String DEFAULT_LINE_CHAR = "-";

    // List of command and flag keywords
    public static final String EXIT_COMMAND_KEYWORD = "bye";

    public static final String LIST_COMMAND_KEYWORD = "list";

    public static final String FIND_COMMAND_KEYWORD = "find";

    public static final String ADD_TODO_COMMAND_KEYWORD = "todo";
    public static final String ADD_EVENT_COMMAND_KEYWORD = "event";
    public static final String ADD_EVENT_FLAG_KEYWORD = "/at";
    public static final String ADD_DEADLINE_COMMAND_KEYWORD = "deadline";
    public static final String ADD_DEADLINE_FLAG_KEYWORD = "/by";

    public static final String MARK_TASK_AS_DONE_COMMAND_KEYWORD = "mark";
    public static final String UNMARK_TASK_AS_DONE_COMMAND_KEYWORD = "unmark";

    public static final String DELETE_TASK_COMMAND_KEYWORD = "delete";

    /**
     * List of flag keywords (for command validation)
     */
    public static final String[] LIST_OF_FLAG_KEYWORDS = {
            ADD_EVENT_FLAG_KEYWORD,
            ADD_DEADLINE_FLAG_KEYWORD
    };

    // Date and time format (for event and deadline tasks)
    public static final String DATE_TIME_INPUT_FORMAT = "dd/MM/yyyy HHmm";
    public static final String DATE_TIME_OUTPUT_FORMAT = "E, d MMM yyyy, h:mm a";

    // List of response messages
    protected static final String LIST_PRE_EXECUTION_RESPONSE =
            "%s, here's a list of %d tasks that you have given to me:\n";

    protected static final String EMPTY_LIST_RESPONSE =
            "%s, my list is empty at the moment...\n";

    protected static final String TASK_NUMBER_OUT_OF_LIST_RANGE_RESPONSE =
            "%s, the task number you've given me is out of the range\nof the current list of tasks." +
                    " Could you re-enter a\nvalid task number?\n";

    protected static final String STANDARD_SEARCH_RESULT_RESPONSE =
            "%s, I have found %d %s that matches your search\nphrase:\n";
    protected static final String NO_RESULT_FOUND_RESPONSE =
            "%s, there are no tasks that match your search phrase.\n" +
                    "Perhaps you could refine your search parameters?\n";
    protected static final String MISSING_SEARCH_STRING_RESPONSE =
            "%s, please provide me with a search string for me to find.\n";

    protected static final String INVALID_DATE_TIME_INPUT_FORMAT_RESPONSE =
            "%s, the formatting of the date and time you've given me\nis incorrect... " +
                    "It should be DD/MM/YYYY HHmm, where 'HHmm'\nis the time in 24-hour format." +
                    "Please try again...\n";

    protected static final String ADD_TASK_SUCCESS_RESPONSE =
            "%s, I've added your new task to my list:\n";
    protected static final String ADD_TASK_FAILURE_RESPONSE =
            "%s, somehow I wasn't able to add your task to my list...\nSorry about that!\n";

    protected static final String MARK_TASK_DONE_SUCCESS_RESPONSE =
            "%s, I've marked this task as done:\n";
    protected static final String MARK_TASK_DONE_FAILURE_RESPONSE =
            "%s, I couldn't mark that task as done.\nSorry about that...\n";

    protected static final String UNMARK_TASK_DONE_SUCCESS_RESPONSE =
            "%s, I've marked this task as not done:\n";
    protected static final String UNMARK_TASK_DONE_FAILURE_RESPONSE =
            "%s, I couldn't mark that task as not done.\nSorry about that...\n";

    protected static final String INVALID_TASK_NUMBER_RESPONSE =
            "%s, I'm not sure what that task number means.\nCould you try re-entering the task number?\n";

    protected static final String DELETE_TASK_SUCCESS_RESPONSE =
            "%s, I've deleted this task:\n";

    protected static final String COMMAND_NOT_UNDERSTOOD_RESPONSE =
            "%s, I can't understand what you've just typed...\nCould you try again?\n";
    protected static final String ILLEGAL_CHARACTER_RESPONSE =
            "%s, it seems like you've entered an illegal character\nin your input... Could you try again?\n";

    protected static final String TODO_MISSING_DESCRIPTION_RESPONSE =
            "%s, seems like you didn't give me a description for your\ntodo... Could you try again?\n";

    protected static final String EVENT_MISSING_FLAG_RESPONSE =
            "%s, I couldn't find the proper flag required for the\nevent command... Could you try again?\n";
    protected static final String EVENT_NO_DESCRIPTION_RESPONSE =
            "%s, seems like you didn't give me a description for your\nevent... Could you try again?\n";
    protected static final String EVENT_NO_PERIOD_RESPONSE =
            "%s, seems like you didn't give me a date for your event...\nCould you try again?\n";
    protected static final String EVENT_INVALID_FLAGS =
            "%s, the flags used in your event command is invalid...\nCould you try again?\n";

    protected static final String DEADLINE_MISSING_FLAG_RESPONSE =
            "%s, I couldn't find the proper flag required for the\ndeadline command... Could you try again?\n";
    protected static final String DEADLINE_NO_DESCRIPTION_RESPONSE =
            "%s, seems like you didn't give me a description for your\ndeadline... Could you try again?\n";
    protected static final String DEADLINE_NO_DUE_DATE_RESPONSE =
            "%s, seems like you didn't give me a due date for your\ndeadline... Could you try again?\n";
    protected static final String DEADLINE_INVALID_FLAGS =
            "%s, the flags used in your deadline command is invalid...\nCould you try again?\n";

    protected static final String INVALID_TASK_TYPE_RESPONSE =
            "%s, the task type given is invalid.\n";

    protected static final String NO_DIRECTORY_FOUND_RESPONSE =
            "\n%s, I couldn't find the directory that contains my data\nfile. Let me go and create it...\n";
    protected static final String NO_FILE_FOUND_RESPONSE =
            "\n%s, I%s couldn't find my data file. I shall create\nthat%s.\n\n";
    protected static final String FILE_CREATED_RESPONSE =
            "%s, I've created my data file in the following\nlocation:\n\t%s\n";
    protected static final String LOADED_FILE_DATA_RESPONSE =
            "I've loaded your tasks from our previous interactions!\n";
    protected static final String TASK_FILE_LOAD_FAILURE_RESPONSE =
            "%s, I failed to add a task from the saved data file to\n" +
                    "my task list. Here's some details about the error:\n" +
                    "\t%s";

    // Exception messages caused by errors beyond Sora's (and maybe user's) control
    protected static final String IO_EXCEPTION_RETHROW_MESSAGE =
            "[%s class] IOException caught, rethrowing it to %s class to program termination.\n";
    protected static final String UNCATEGORISED_INVALID_COMMAND_EXCEPTION_MESSAGE =
            "[SoraExceptionHandler class] Well this is embarrassing, I was unable to figure out\n" +
                    "what was wrong with your input. Hmmm... this is bad. I'll have to terminate\n" +
                    "myself to be safe. Please restart me again in a moment. Sorry about this!\n";

    /**
     * Prints a line on the console based on the default parameters defined in this Java class.
     */
    protected void printLine() {
        // Prints a line based on the default parameters
        printLine(DEFAULT_LINE_LENGTH, DEFAULT_LINE_CHAR);
    }

    /**
     * Prints a line on the console based on the specified length and the character/symbol to use
     *
     * @param lineLen The length of the line (measured in 'number of characters').
     * @param character The character that should be used to print the line.
     */
    protected void printLine(int lineLen, String character) {
        // Generate a line of whitespaces
        String lineOfWhitespaces = String.format("%" + lineLen + "s", " ");

        // Replace each whitespace with the specified character
        String lineOfChars = lineOfWhitespaces.replaceAll(" ", character);

        // Finally, print out the line of characters
        System.out.println(lineOfChars);
    }

    /**
     * Greets the user with appropriate messages based on the time of the day.
     */
    protected void printGreetings() {
        // Print banner
        printLine(DEFAULT_LINE_LENGTH, "=");
        System.out.println(BANNER);
        printLine();

        // Print greetings
        if (Helper.getHourOfDay() < 12) {
            System.out.print("Good morning! ");
        } else if (Helper.getHourOfDay() < 18) {
            System.out.print("Good afternoon! ");
        } else {
            System.out.print("Good evening. ");
        }

        System.out.println("I'm Sora!");
    }

    /**
     * Bids farewell to the user when the "exit" command is entered.
     */
    protected void printGoodbye() {
        if (Helper.getHourOfDay() >= 6 && Helper.getHourOfDay() < 18) {
            System.out.println("Goodbye! Have a great day ahead!");
        } else if (Helper.getHourOfDay() >= 18 && Helper.getHourOfDay() < 22) {
            System.out.println("Goodbye! Have a good evening.");
        } else {
            System.out.println("Good night, have a good rest...");
        }

        System.out.println("See you again soon~");
        printLine();
    }

    /**
     * Prints a prompt on the terminal. The prompt text will differ depending on whether if this method is called
     * for the first time since this instance of Sora was started.
     *
     * @param isFirstPrompt Boolean value to determine if this method is called for the first time
     *                      since this Sora instance was started.
     */
    protected void printPrompter(boolean isFirstPrompt) {
        if (isFirstPrompt) {
            printLine();
            System.out.println("Now then, what can I do for you?");
            printLine();
            System.out.print(PROMPT_SYMBOL + " ");
        } else {
            printLine();
            System.out.println("What's next?");
            printLine();
            System.out.print(PROMPT_SYMBOL + " ");
        }
    }

    /**
     * Randomly chooses one of the positive acknowledgement words from the string array
     * POSITIVE_ACKNOWLEDGEMENT_WORDS and returns it.
     * <p>
     * If Sora.IN_TESTING_MODE is set to true, the first element in the POSITIVE_ACKNOWLEDGEMENT_WORDS
     * is always chosen. No random choosing will be done in this case.
     *
     * @return A string containing a (randomly) chosen positive acknowledgement word.
     */
    protected String getRandomPositiveAcknowledgement() {
        if (Sora.IN_TESTING_MODE) {
            return POSITIVE_ACKNOWLEDGEMENT_WORDS[0];
        }

        Random rand = new Random();
        int randNum = rand.nextInt(POSITIVE_ACKNOWLEDGEMENT_WORDS.length);

        return POSITIVE_ACKNOWLEDGEMENT_WORDS[randNum];
    }

    /**
     * Randomly chooses one of the negative acknowledgement words from the string array
     * NEGATIVE_ACKNOWLEDGEMENT_WORDS and returns it.
     * <p>
     * If Sora.IN_TESTING_MODE is set to true, the first element in the NEGATIVE_ACKNOWLEDGEMENT_WORDS
     * is always chosen. No random choosing will be done in this case.
     *
     * @return A string containing a (randomly) chosen negative acknowledgement word.
     */
    protected String getRandomNegativeAcknowledgement() {
        if (Sora.IN_TESTING_MODE) {
            return NEGATIVE_ACKNOWLEDGEMENT_WORDS[0];
        }

        Random rand = new Random();
        int randNum = rand.nextInt(NEGATIVE_ACKNOWLEDGEMENT_WORDS.length);

        return NEGATIVE_ACKNOWLEDGEMENT_WORDS[randNum];
    }

    /**
     * Prints a response when an attempt to mark a task as done was made. The response printed depends
     * on the success or failure of the attempt made.
     *
     * @param isSuccessful Indicates if the marking of the task was successful or not.
     * @param taskList An instance of TaskList.
     * @param taskNum The task number in taskList that was attempted to be marked as done.
     */
    protected void printMarkTaskResponseMessage(boolean isSuccessful, TaskList taskList, int taskNum) {
        if (!isSuccessful) {
            System.out.printf(MARK_TASK_DONE_FAILURE_RESPONSE, getRandomNegativeAcknowledgement());
            return;
        }

        // Mark task was successful
        System.out.printf(MARK_TASK_DONE_SUCCESS_RESPONSE, getRandomPositiveAcknowledgement());
        System.out.println();
        taskList.displayTask(taskNum);
        System.out.println();
    }

    /**
     * Prints a response when an attempt to unmark a task as done was made. The response printed depends
     * on the success or failure of the attempt made.
     *
     * @param isSuccessful Indicates if the unmarking of the task was unsuccessful or not.
     * @param taskList An instance of TaskList.
     * @param taskNum The task number in taskList that was attempted to be unmarked as done.
     */
    protected void printUnmarkTaskResponseMessage(boolean isSuccessful, TaskList taskList, int taskNum) {
        if (!isSuccessful) {
            System.out.printf(UNMARK_TASK_DONE_FAILURE_RESPONSE, getRandomNegativeAcknowledgement());
            return;
        }

        // Unmark task was successful
        System.out.printf(UNMARK_TASK_DONE_SUCCESS_RESPONSE, getRandomPositiveAcknowledgement());
        System.out.println();
        taskList.displayTask(taskNum);
        System.out.println();
    }

    /**
     * Prints a response when a task has been successfully removed from an instance of TaskList.
     *
     * @param taskRemoved The Task instance that was removed from taskList.
     * @param taskList The instance of TaskList that the task was removed from.
     */
    protected void printDeleteTaskResponseMessage(Task taskRemoved, TaskList taskList) {
        System.out.printf(DELETE_TASK_SUCCESS_RESPONSE, getRandomPositiveAcknowledgement());
        System.out.println();
        taskList.displayTask(taskRemoved);
        System.out.println();
    }

    /**
     * Prints a response when an attempt to add a task to the task list was made. The response printed
     * depends on the success or failure of the attempt made.
     *
     * @param newTask The Task instance that was added to the task list. If this parameter is null, then
     *                it means that the task was not successfully added.
     */
    protected void printAddTaskResponseMessage(Task newTask) {
        if (newTask == null) {
            System.out.printf(ADD_TASK_FAILURE_RESPONSE, getRandomNegativeAcknowledgement());
            return;
        }

        // Adding task was successful
        System.out.printf(ADD_TASK_SUCCESS_RESPONSE, getRandomPositiveAcknowledgement());
        System.out.println();
        System.out.println("\t" + newTask.toString());
        System.out.println();
    }

    /**
     * Prints a response and displays the entire task list that is stored in the provided instance of TaskList.
     *
     * @param taskList The instance of TaskList that contains the list of tasks to be printed.
     * @throws EmptyListException If the instance of TaskList does not contain any tasks (i.e. an empty list).
     */
    protected void printTaskList(TaskList taskList) throws EmptyListException {
        // Check if the task list is empty
        if (taskList.isEmpty()) {
            throw new EmptyListException(EmptyListException.EMPTY_LIST_MSG);
        }

        System.out.printf(LIST_PRE_EXECUTION_RESPONSE, getRandomPositiveAcknowledgement(),
                taskList.getNumberOfTasks());
        System.out.println();
        taskList.displayAllTasks();
        System.out.println();
    }

    /**
     * Prints a response and displays a list of the tasks in searchResults, which contains the
     * tasks that match a certain search parameter.
     *
     * @param searchResults The list of tasks that are part of the search results.
     */
    public void printSearchResults(ArrayList<String> searchResults) {
        if (searchResults.size() == 0) {
            System.out.printf(NO_RESULT_FOUND_RESPONSE, getRandomNegativeAcknowledgement());
            return;
        }

        String taskOrTasks = (searchResults.size() > 1) ? "tasks" : "task";

        System.out.printf(STANDARD_SEARCH_RESULT_RESPONSE, getRandomPositiveAcknowledgement(),
                searchResults.size(), taskOrTasks);
        System.out.println();
        printSearchResultsArrayList(searchResults);
        System.out.println();
    }

    /**
     * Complements printSearchResults. Printing the tasks in searchResults
     *
     * @param searchResults A list containing the tasks that are part of the search results.
     */
    private void printSearchResultsArrayList(ArrayList<String> searchResults) {
        for (String result : searchResults) {
            System.out.println("\t" + result);
        }
    }

    /**
     * Prints a response when the user has entered a command that Sora does not understand.
     */
    protected void printCommandNotUnderstood() {
        System.out.printf(COMMAND_NOT_UNDERSTOOD_RESPONSE, getRandomNegativeAcknowledgement());
    }

    /**
     * Prints a response when the user enters a todo command without a description of it.
     */
    protected void printTodoMissingDescription() {
        System.out.printf(TODO_MISSING_DESCRIPTION_RESPONSE, getRandomNegativeAcknowledgement());
    }

    /**
     * Prints a response when the user enters an event command without the appropriate flag.
     */
    public void printEventMissingFlag() {
        System.out.printf(EVENT_MISSING_FLAG_RESPONSE, getRandomNegativeAcknowledgement());
    }

    /**
     * Prints a response when the user enters an event command without the description.
     */
    public void printEventMissingDescription() {
        System.out.printf(EVENT_NO_DESCRIPTION_RESPONSE, getRandomNegativeAcknowledgement());
    }

    /**
     * Prints a response when the user enters an event command without a date (and time).
     */
    public void printEventMissingPeriod() {
        System.out.printf(EVENT_NO_PERIOD_RESPONSE, getRandomNegativeAcknowledgement());
    }

    /**
     * Prints a response when the user enters an event command with invalid flags.
     */
    public void printEventInvalidFlags() {
        System.out.printf(EVENT_INVALID_FLAGS, getRandomNegativeAcknowledgement());
    }

    /**
     * Prints a response when the user enters a deadline command without the appropriate flag.
     */
    public void printDeadlineMissingFlag() {
        System.out.printf(DEADLINE_MISSING_FLAG_RESPONSE, getRandomNegativeAcknowledgement());
    }

    /**
     * Prints a response when the user enters a deadline command without the description.
     */
    public void printDeadlineMissingDescription() {
        System.out.printf(DEADLINE_NO_DESCRIPTION_RESPONSE, getRandomNegativeAcknowledgement());
    }

    /**
     * Prints a response when the user enters a deadline command without a date (and time).
     */
    public void printDeadlineNoDueDate() {
        System.out.printf(DEADLINE_NO_DUE_DATE_RESPONSE, getRandomNegativeAcknowledgement());
    }

    /**
     * Prints a response when the user enters a deadline command with invalid flags.
     */
    public void printDeadlineInvalidFlags() {
        System.out.printf(DEADLINE_INVALID_FLAGS, getRandomNegativeAcknowledgement());
    }

    /**
     * Prints a response when the directory that contains the required data file cannot be found.
     */
    public void printDirectoryNotFound() {
        System.out.printf(NO_DIRECTORY_FOUND_RESPONSE, getRandomNegativeAcknowledgement());
    }

    /**
     * Prints a response when the file that contains the required tasks' data cannot be found. The response
     * will differ depending on whether the required directory already exists prior to the current running
     * instance of Sora.
     *
     * @param directoryAlreadyExists Indicates if the required directory already exists prior to the current
     *                               running instance of Sora.
     */
    public void printFileNotFound(boolean directoryAlreadyExists) {
        if (!directoryAlreadyExists) {
            System.out.printf(NO_FILE_FOUND_RESPONSE, getRandomNegativeAcknowledgement(), " also",
                    " too");
        } else {
            System.out.printf(NO_FILE_FOUND_RESPONSE, getRandomNegativeAcknowledgement(), "", "");
        }
    }

    /**
     * Prints a response when an empty file that is required for storing tasks' data has been successfully
     * added.
     *
     * @param filePathName The path to the newly-created file that will store tasks' data.
     */
    public void printFileCreatedResponse(Path filePathName) {
        System.out.printf(FILE_CREATED_RESPONSE, getRandomPositiveAcknowledgement(), filePathName);
    }

    /**
     * Prints a response when the tasks stored in the file have been successfully loaded into the current
     * running instance of Sora.
     */
    public void printLoadedFileDataResponse() {
        System.out.println();
        System.out.printf(LOADED_FILE_DATA_RESPONSE);
    }

    /**
     * Prints a response when the user enters a task number that is not within the quantity range of Sora's
     * task list.
     */
    public void printTaskNumOutOfListRange() {
        System.out.printf(TASK_NUMBER_OUT_OF_LIST_RANGE_RESPONSE, getRandomNegativeAcknowledgement());
    }

    /**
     * Prints a response when the user enters an input that contains at least one character that is deemed
     * as an illegal character.
     */
    public void printIllegalCharacterResponse() {
        System.out.printf(ILLEGAL_CHARACTER_RESPONSE, getRandomNegativeAcknowledgement());
    }

    /**
     * Prints a response when the user tries to interact with an empty list.
     */
    public void printEmptyListExceptionResponse() {
        System.out.printf(EMPTY_LIST_RESPONSE, getRandomNegativeAcknowledgement());
    }

    /**
     * Prints a response when the user enters an uninterpretable task number.
     */
    public void printInvalidTaskNumber() {
        System.out.printf(INVALID_TASK_NUMBER_RESPONSE, getRandomNegativeAcknowledgement());
    }

    /**
     * Prints a response when the user did not input a search parameter when using the 'find' command.
     */
    public void printMissingSearchStringResponse() {
        System.out.printf(MISSING_SEARCH_STRING_RESPONSE, getRandomNegativeAcknowledgement());
    }

    /**
     * Prints a response when the user enters an invalid date-time format while creating a new Event or
     * Deadline task.
     */
    public void printInvalidDateTimeInputFormatResponse() {
        System.out.printf(INVALID_DATE_TIME_INPUT_FORMAT_RESPONSE, getRandomNegativeAcknowledgement());
    }

    /**
     * Prints a message when an IOException is caught, including the class that caught it and the class
     * that it is throwing to.
     *
     * @param catchingClass The name of the class that caught the IOException.
     * @param throweeClass The name of the class that the catching class will rethrow the exception to.
     */
    public void printIOExceptionRethrowMessage(String catchingClass, String throweeClass) {
        System.out.printf(IO_EXCEPTION_RETHROW_MESSAGE, catchingClass, throweeClass);
    }

    /**
     * Prints a message when the handleInvalidCommandException method in SoraExceptionHandler class
     * is unable to categorise the InvalidCommandException that it received.
     */
    public void printUncategorisedInvalidCommandExceptionMessage() {
        System.out.printf(UNCATEGORISED_INVALID_COMMAND_EXCEPTION_MESSAGE);
    }

    /**
     * Prints a message when a task that is stored in the local disk file could not be added to
     * the current session of Sora's task list.
     *
     * @param exceptionMessage The error message that was produced by the unsuccessful task addition.
     */
    public void printTaskFileLoadFailureMessage(String exceptionMessage) {
        System.out.printf(TASK_FILE_LOAD_FAILURE_RESPONSE, getRandomNegativeAcknowledgement(), exceptionMessage);
    }

    /**
     * Prints a message when a task type is not one of the three task types: deadline, event, or todo
     */
    public void printInvalidTaskTypeResponse() {
        System.out.printf(INVALID_TASK_TYPE_RESPONSE, getRandomNegativeAcknowledgement());
    }
}

