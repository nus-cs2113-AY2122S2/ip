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
     * Different acknowledgement words for Sora's neutral replies
     */
    protected static final String[] ACKNOWLEDGEMENT_WORDS = {
            "Okay",
            "Alright",
            "Gotcha",
            "Got it"
    };

    /**
     * Default parameters for printing formatting lines
     */
    protected static final int DEFAULT_LINE_LENGTH = 60;
    protected static final String DEFAULT_LINE_CHAR = "-";

    /**
     * List of command keywords
     */
    protected static final String EXIT_COMMAND_KEYWORD = "bye";

    protected static final String LIST_COMMAND_KEYWORD = "list";

    protected static final String ADD_TODO_COMMAND_KEYWORD = "todo";
    protected static final String ADD_EVENT_COMMAND_KEYWORD = "event";
    protected static final String ADD_EVENT_OPTION_KEYWORD = "/at";
    protected static final String ADD_DEADLINE_COMMAND_KEYWORD = "deadline";
    protected static final String ADD_DEADLINE_OPTION_KEYWORD = "/by";
    protected static final String MARK_TASK_AS_DONE_COMMAND_KEYWORD = "mark";
    protected static final String UNMARK_TASK_AS_DONE_COMMAND_KEYWORD = "unmark";

    /**
     * Prints a line on the console based on the default parameters defined in this Java class.
     */
    protected void printLine() {
        // Prints a line based on the default parameters
        printLine(DEFAULT_LINE_LENGTH, DEFAULT_LINE_CHAR);
    }

    /**
     * Prints a line on the console based on the arguments given to this method.
     */
    protected void printLine(int lineLen, String character) {
        // Prints a line based on the specified length and the character/symbol to use
        System.out.println(String.format("%" + lineLen + "s", " ").replaceAll(" ", character));
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
     * Randomly chooses one of the acknowledgement words from the array acknowledgementWords and returns it.
     * @return A string containing a randomly chosen acknowledgement word
     */
    protected String getRandomAcknowledgement() {
        /**
         * If Sora.IN_TESTING_MODE is set to true, do not choose a random acknowledgement word
         * and pick the first word in ACKNOWLEDGEMENT_WORDS
         */
        if (Sora.IN_TESTING_MODE) {
            return ACKNOWLEDGEMENT_WORDS[0];
        }

        Random rand = new Random();
        int randNum = rand.nextInt(ACKNOWLEDGEMENT_WORDS.length);

        return ACKNOWLEDGEMENT_WORDS[randNum];
    }

    protected void displayTaskList(TasksManager tasksManager) {
        // Check if the task list is empty
        if (tasksManager.isEmpty()) {
            System.out.println("Hmm, my list is empty at the moment...");
            return;
        }

        System.out.println(getRandomAcknowledgement()
                + ", here's a list of tasks that you have given to me: ");
        System.out.println();
        tasksManager.displayAllTasks();
    }
}
