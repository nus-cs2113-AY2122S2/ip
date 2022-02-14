package sora;

import tasks.TasksManager;
import util.Helper;

import java.util.Random;

public class SoraUI {
    /**
     * Banner logo design
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

    /**
     * Default parameters for printing formatting lines
     */
    protected static final int DEFAULT_LINE_LENGTH = 60;
    protected static final String DEFAULT_LINE_CHAR = "-";

    /**
     * List of command keywords
     */
    public static final String EXIT_COMMAND_KEYWORD = "bye";

    public static final String LIST_COMMAND_KEYWORD = "list";

    public static final String ADD_TODO_COMMAND_KEYWORD = "todo";
    public static final String ADD_EVENT_COMMAND_KEYWORD = "event";
    public static final String ADD_EVENT_FLAG_KEYWORD = "/at";
    public static final String ADD_DEADLINE_COMMAND_KEYWORD = "deadline";
    public static final String ADD_DEADLINE_FLAG_KEYWORD = "/by";
    public static final String MARK_TASK_AS_DONE_COMMAND_KEYWORD = "mark";
    public static final String UNMARK_TASK_AS_DONE_COMMAND_KEYWORD = "unmark";

    /**
     * List of flag keywords (for command validation)
     */
    public static final String[] LIST_OF_FLAG_KEYWORDS = {
            ADD_EVENT_FLAG_KEYWORD,
            ADD_DEADLINE_FLAG_KEYWORD
    };

    /**
     * List of response messages
     */
    protected static final String LIST_PRE_EXECUTION_RESPONSE =
            "%s, here's a list of tasks that you have given to me:\n";
    protected static final String EMPTY_LIST_RESPONSE =
            "%s, my list is empty at the moment...\n";

    protected static final String ADD_TASK_SUCCESS_RESPONSE =
            "%s, I've added your new task to my list:\n";
    protected static final String ADD_TASK_FAILURE_RESPONSE =
            "%s, somehow I wasn't able to add your task to my list...\nSorry about that! (-ω-、)\n";

    protected static final String MARK_TASK_DONE_SUCCESS_RESPONSE =
            "%s, I've marked this task as done:\n";
    protected static final String MARK_TASK_DONE_FAILURE_RESPONSE =
            "%s, I couldn't mark that task as done.\nSorry about that... (-ω-、)\n";

    protected static final String UNMARK_TASK_DONE_SUCCESS_RESPONSE =
            "%s, I've marked this task as not done:\n";
    protected static final String UNMARK_TASK_DONE_FAILURE_RESPONSE =
            "%s, I couldn't mark that task as not done.\nSorry about that... (-ω-、)\n";

    protected static final String COMMAND_NOT_UNDERSTOOD_RESPONSE =
            "%s, I can't understand what you've just typed...\nCould you try again?\n";

    protected static final String TODO_MISSING_DESCRIPTION_RESPONSE =
            "%s, seems like you didn't give me a description for your\ntodo... Could you try again? (⌒_⌒;)\n";

    protected static final String EVENT_MISSING_FLAG_RESPONSE =
            "%s, I couldn't find the proper flag required for the\nevent command... Could you try again? (⌒_⌒;)\n";
    protected static final String EVENT_NO_DESCRIPTION_RESPONSE =
            "%s, seems like you didn't give me a description for your\nevent... Could you try again? (⌒_⌒;)\n";
    protected static final String EVENT_NO_PERIOD_RESPONSE =
            "%s, seems like you didn't give me a date for your event...\nCould you try again? (⌒_⌒;)\n";
    protected static final String EVENT_INVALID_FLAGS =
            "%s, the flags used in your event command is invalid...\nCould you try again? (⌒_⌒;)\n";

    protected static final String SELF_LOATHING_ERROR_RESPONSE = "" +
            "Ah dang. Internal processing in %s failed.\nNot your fault, user. It's the monkey developer's fault.\n" +
            "If you see the dev, tell him this: %s\n";

    /**
     * Prints a line on the console based on the default parameters defined in this Java class.
     */
    protected void printLine() {
        // Prints a line based on the default parameters
        printLine(DEFAULT_LINE_LENGTH, DEFAULT_LINE_CHAR);
    }

    /**
     * Prints a line on the console based on the specified length and the character/symbol to use
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

        System.out.println("I'm Sora ヽ(・∀・)ﾉ");
        System.out.println("What can I do for you?");
        printLine();
    }

    /**
     * Bids farewell to the user when the "exit" command is entered.
     */
    protected void printGoodbye() {
        if (Helper.getHourOfDay() >= 6 && Helper.getHourOfDay() < 18) {
            System.out.println("Goodbye! Have a great day ahead (⌒▽⌒)☆");
        } else if (Helper.getHourOfDay() >= 18 && Helper.getHourOfDay() < 22) {
            System.out.println("Goodbye! Have a good evening <(￣︶￣)>");
        } else {
            System.out.println("Good night, have a good rest... (－ω－) zzZ");
        }

        System.out.println("See you again soon~");
        printLine();
    }

    /**
     * Prints a prompt on the console. If isFirstPrompt is false, it indicates that this is not the first time
     * this method is called and thus will print out an additional line of text before printing out the prompt
     * symbol.
     *
     * @param isFirstPrompt boolean value to determine if this method is called for the first time
     *                      since this Sora instance was started
     */
    protected void printPrompter(boolean isFirstPrompt) {
        if (isFirstPrompt) {
            System.out.print(PROMPT_SYMBOL + " ");
        } else {
            printLine();
            System.out.println("What's next?");
            printLine();
            System.out.print(PROMPT_SYMBOL + " ");
        }
    }

    /**
     * Randomly chooses one of the positive acknowledgement words from the array POSITIVE_ACKNOWLEDGEMENT_WORDS
     * and returns it.
     *
     * @return A string containing a randomly chosen acknowledgement word
     */
    protected String getRandomPositiveAcknowledgement() {
        /**
         * If Sora.IN_TESTING_MODE is set to true, do not choose a random acknowledgement word
         * and pick the first word in POSITIVE_ACKNOWLEDGEMENT_WORDS
         */
        if (Sora.IN_TESTING_MODE) {
            return POSITIVE_ACKNOWLEDGEMENT_WORDS[0];
        }

        Random rand = new Random();
        int randNum = rand.nextInt(POSITIVE_ACKNOWLEDGEMENT_WORDS.length);

        return POSITIVE_ACKNOWLEDGEMENT_WORDS[randNum];
    }

    protected String getRandomNegativeAcknowledgement() {
        /**
         * If Sora.IN_TESTING_MODE is set to true, do not choose a random acknowledgement word
         * and pick the first word in NEGATIVE_ACKNOWLEDGEMENT_WORDS
         */
        if (Sora.IN_TESTING_MODE) {
            return NEGATIVE_ACKNOWLEDGEMENT_WORDS[0];
        }

        Random rand = new Random();
        int randNum = rand.nextInt(NEGATIVE_ACKNOWLEDGEMENT_WORDS.length);

        return NEGATIVE_ACKNOWLEDGEMENT_WORDS[randNum];
    }

    protected void printMarkTaskResponseMessage(boolean isSuccessful, TasksManager tasksManager, int taskNum) {
        if (isSuccessful) {
            System.out.printf(SoraUI.MARK_TASK_DONE_SUCCESS_RESPONSE, getRandomPositiveAcknowledgement());
            System.out.println();
            tasksManager.displayTask(taskNum);
            System.out.println();
            return;
        }

        // Mark task was unsuccessful
        System.out.printf(SoraUI.MARK_TASK_DONE_FAILURE_RESPONSE, getRandomNegativeAcknowledgement());
    }

    protected void printUnmarkTaskResponseMessage(boolean isSuccessful, TasksManager tasksManager, int taskNum) {
        if (isSuccessful) {
            System.out.printf(SoraUI.UNMARK_TASK_DONE_SUCCESS_RESPONSE, getRandomPositiveAcknowledgement());
            System.out.println();
            tasksManager.displayTask(taskNum);
            System.out.println();
            return;
        }

        // Unmark task was unsuccessful
        System.out.printf(SoraUI.UNMARK_TASK_DONE_FAILURE_RESPONSE, getRandomNegativeAcknowledgement());
    }

    protected void printAddTaskResponseMessage(boolean isSuccessful, TasksManager tasksManager) {
        if (isSuccessful) {
            System.out.printf(SoraUI.ADD_TASK_SUCCESS_RESPONSE, getRandomPositiveAcknowledgement());
            System.out.println();
            tasksManager.displayLastAddedTask();
            System.out.println();
            return;
        }

        // Adding task was unsuccessful
        System.out.printf(SoraUI.ADD_TASK_FAILURE_RESPONSE, getRandomNegativeAcknowledgement());
    }

    protected void displayTaskList(TasksManager tasksManager) {
        // Check if the task list is empty
        if (tasksManager.isEmpty()) {
            System.out.printf(EMPTY_LIST_RESPONSE, getRandomNegativeAcknowledgement());
            return;
        }

        System.out.printf(LIST_PRE_EXECUTION_RESPONSE, getRandomPositiveAcknowledgement());
        System.out.println();
        tasksManager.displayAllTasks();
        System.out.println();
    }

    protected void printCommandNotUnderstood() {
        System.out.printf(SoraUI.COMMAND_NOT_UNDERSTOOD_RESPONSE, getRandomNegativeAcknowledgement());
    }

    protected void printTodoMissingDescription() {
        System.out.printf(SoraUI.TODO_MISSING_DESCRIPTION_RESPONSE, getRandomNegativeAcknowledgement());
    }

    public void printEventMissingFlag() {
        System.out.printf(SoraUI.EVENT_MISSING_FLAG_RESPONSE, getRandomNegativeAcknowledgement());
    }

    public void printEventMissingDescription() {
        System.out.printf(SoraUI.EVENT_NO_DESCRIPTION_RESPONSE, getRandomNegativeAcknowledgement());
    }

    public void printEventMissingPeriod() {
        System.out.printf(SoraUI.EVENT_NO_PERIOD_RESPONSE, getRandomNegativeAcknowledgement());
    }

    public void printEventInvalidFlags() {
        System.out.printf(SoraUI.EVENT_INVALID_FLAGS, getRandomNegativeAcknowledgement());
    }
}
