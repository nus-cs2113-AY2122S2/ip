public class DukeUI {

    protected static final String logo
            = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    protected static final String prompt_symbol = ">";

    protected static final int line_length = 60;
    protected static final String line_char = "-";

    /**
     * List of command keywords
     */
    protected static final String exit_command = "bye";

    protected static final String list_command = "list";

    protected static final String todo_command = "todo";
    protected static final String event_command = "event";
    protected static final String event_option_command = "/at";
    protected static final String deadline_command = "deadline";
    protected static final String deadline_option_command = "/by";
    protected static final String mark_command = "mark";
    protected static final String unmark_command = "unmark";

    public static void displayTaskList(TasksManager tasksManager) {
        // Check if the task list is empty
        if (tasksManager.isEmpty()) {
            System.out.println("The list is empty at the moment...");
            return;
        }
        System.out.println("Here's a list of tasks that you have given to me: ");
        System.out.println();
        tasksManager.displayAllTasks();
    }

    protected void printLine() {
        // Prints a line based on the default parameters
        printLine(line_length, line_char);
    }

    /**
     * Prints a line on the console based on the arguments given to this method.
     */
    protected void printLine(int lineLen, String character) {
        // Prints a line based on the specified length and the character/symbol to use
        System.out.println(String.format("%" + lineLen + "s", " ").replaceAll(" ", character));
    }

    protected void printGreetings() {
        // Print banner
        System.out.println(logo);
        String fillerLine = "____________________________________________________________";

        System.out.println("Hello! I'm Bob");
        System.out.println("What can I do for you?");
        System.out.println(fillerLine);
    }
    protected void printGoodbye() {
        System.out.println("Bye!See you again soon");
        printLine();
    }

    protected void printPrompter(boolean isFirstPrompt) {
        if (isFirstPrompt) {
            System.out.print(prompt_symbol + " ");
        } else {
            printLine();
            System.out.println("What's next?");
            printLine();
            System.out.print(prompt_symbol + " ");
        }
    }
}
