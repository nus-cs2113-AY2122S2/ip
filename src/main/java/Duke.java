import java.util.ArrayList;
import java.util.Scanner;

/**
 * A chat-bot that manages tasks for the user.
 * Number of tasks are limited to 100.
 */
public class Duke {

    /** Similar to String[] args,
     * this limit is imposed on how many arguments follow the initial command
     */
    private static final int SECTION_LIMIT = 2;
    /** The limit on how many tasks can be in the list */
    private static final int TASK_LIMIT = 100;

    /** Keeps track on how many tasks are in the list*/
    private static int taskCount = 0;
    /** The list of tasks. Limited to 100 */
    private static Task[] taskList = new Task[100];

    /**
     * Main method that runs our chat-bot. Handling commands until user exits.
     */
    public static void main(String[] args) {
        giveIntroduction();

        // Scanner is how commands are inputted
        Scanner scan = new Scanner(System.in);
        String input = scan.nextLine();
        Command operation = getCommand(input);
        String[] arguments = parseInput(operation, input);

        // Keep going until the user enters 'bye'
        while (operation != Command.EXIT) {
            if (checkNull(arguments)) {
                // Valid command was inputted but not correctly
                printInvalidCommand(operation);
            } else {
                switch (operation) {
                case LIST:
                    printTasks();
                    break;
                case MARK:
                    markTask(true, arguments[0], operation);
                    break;
                case UNMARK:
                    markTask(false, arguments[0], operation);
                    break;
                case TODO:
                    addTask(new Todo(arguments[0]));
                    break;
                case DEADLINE:
                    addTask(new Deadline(arguments[0], arguments[1]));
                    break;
                case EVENT:
                    addTask(new Event(arguments[0], arguments[1]));
                    break;
                case HELP:
                    printHelp();
                    break;
                case NONE:
                    printInvalidCommand(operation);
                }
            }
            input = scan.nextLine();
            operation = getCommand(input);
            arguments = parseInput(operation, input);
        }
        sayGoodbye();
    }

    /**
     * The initial response the bot gives starting up
     */
    private static void giveIntroduction() {
        ArrayList<String> responses = new ArrayList<>();
        responses.add("Hello! I'm Boba.");
        responses.add("I am a bot 'tasked' to manage your tasks");
        responses.add("What can I do for you?");
        responses.add("Type 'help' to get the list commands I response to");
        botResponse(responses);
    }

    /**
     * The final response after saying bye
     */
    private static void sayGoodbye() {
        ArrayList<String> responses = new ArrayList<>();
        responses.add("Bye. Hope to see you again soon!");
        botResponse(responses);
    }

    /**
     * Determine what command user is using, and return the enum version.
     * If the command is not valid, the default version is returned.
     * @param input Input by the user given to the bot
     * @return The Command that is being used
     */
    private static Command getCommand(String input) {
        // ternary operation for one word commands
        int index = input.indexOf(" ");
        String command = index == -1 ? input : input.substring(0, index) ;
        switch (command) {
        case ("bye"):
            return Command.EXIT;
        case ("list"):
            return Command.LIST;
        case ("todo"):
            return Command.TODO;
        case ("deadline"):
            return Command.DEADLINE;
        case ("event"):
            return Command.EVENT;
        case ("mark"):
            return Command.MARK;
        case ("unmark"):
            return Command.UNMARK;
        case ("help"):
            return Command.HELP;
        default:
            return Command.NONE;
        }
    }

    /**
     * Parses the remaining input for the arguments following the command.
     * Number of arguments is determined by what command.
     * @param operation The current command being used
     * @param input Input by the user given to the bot
     * @return The arguments
     */
    private static String[] parseInput(Command operation, String input) {
        String[] arguments = new String[SECTION_LIMIT];
        switch (operation) {
        case DEADLINE:
        case EVENT:
            // both DEADLINE and EVENT have two arguments
            // both follow the same convention of using a slash command
            int slashIndex = input.indexOf("/");
            if (slashIndex == -1) {
                // improper use of commands as no slash command is present
                break;
            }
            arguments[1] = input.substring(input.indexOf(" ", slashIndex) + 1);
            input = input.substring(0, slashIndex - 1);
        case TODO:
        case MARK:
        case UNMARK:
            int spaceIndex = input.indexOf(" ");
            if (spaceIndex == -1) {
                // improper use of commands as there needs to an argument following
                break;
            }
            arguments[0] = input.substring(spaceIndex + 1);
        default:
            // Only valid commands reach this point
            // The arguments will have null if they are not valid commands
            arguments[0] = arguments[0] == null ? "valid" : arguments[0];
            arguments[1] = arguments[1] == null ? "valid" : arguments[1];
        }
        return arguments;
    }

    /**
     * Check if any of the arguments are null.
     * If there is, it is not a valid command
     * @param arguments The arguments following the command
     * @return A boolean if there is a null present
     */
    private static boolean checkNull(String[] arguments) {
        for (String argument : arguments) {
            if (argument == null) {
                return true;
            }
        }
        return false;
    }

    /**
     * Activates when input is outside the expected behavior.
     * Prints that something wrong occurred. Sometimes based on the given command.
     * @param operation The current command being used
     */
    private static void printInvalidCommand(Command operation) {
        ArrayList<String> responses = new ArrayList<>();
        responses.add("Invalid Command. Please try again.");
        // Provide a bit more insight on what may have gone wrong based on command entered.
        switch (operation) {
        case TODO:
            responses.add("Description is required");
            break;
        case DEADLINE:
            responses.add("Remember to include the /by command!");
            break;
        case EVENT:
            responses.add("Remember to include the /at command!");
            break;
        case MARK:
        case UNMARK:
            responses.add("Make sure to include a valid number");
        }
        botResponse(responses);
    }

    /**
     * Print out the current list of tasks.
     */
    private static void printTasks() {
        ArrayList<String> responses = new ArrayList<>();
        if (taskCount == 0){
            responses.add("The list empty!");
        }
        for (int i = 0; i < taskCount; i++) {
            responses.add(i + 1 + ". " + taskList[i]);
        }
        botResponse(responses);
    }

    /**
     * Marks a task as complete or incomplete.
     * @param isDone Whether task is completed
     * @param taskIndex Index of the task we want to mark
     * @param operation The current command being used
     */
    private static void markTask(boolean isDone, String taskIndex, Command operation) {
        // The task list is 1 base indexing while the array itself is 0 base indexing
        int index = Integer.parseInt(taskIndex) - 1;
        if (index < 0 || index >= taskCount) {
            // Marking outside the range is not allowed
            printInvalidCommand(operation);
            return;
        }
        ArrayList<String> responses = new ArrayList<>();
        Task selectedTask = taskList[index];
        if (isDone) {
            selectedTask.markAsDone();
            responses.add("Beep boop! I've marked this task as done:");
        } else {
            selectedTask.markAsNotDone();
            responses.add("Boop beep! I've marked this task as not done:");
        }
        responses.add(selectedTask.toString());
        botResponse(responses);
    }

    /**
     * Add a task to the list of tasks.
     * Does not add a task if we are at the limit.
     * @param newTask The new task to be added to the list
     */
    private static void addTask(Task newTask) {
        ArrayList<String> responses = new ArrayList<>();
        if (taskCount == TASK_LIMIT) {
            responses.add("The list if full!");
            responses.add("Task could not be added.");
        } else {
            taskList[taskCount] = newTask;
            taskCount++;
            responses.add("Got it. I've added this task:");
            responses.add("\t" + newTask.toString());
            responses.add("Now you have " + taskCount + " tasks in your list.");
        }
        botResponse(responses);
    }

    /**
     * Print out all the commands the bot will respond to.
     * Activates when user enters <code>help</code>
     */
    private static void printHelp() {
        ArrayList<String> responses = new ArrayList<>();
        responses.add("Here are all the possible commands:");
        responses.add("\t1. bye");
        responses.add("\t2. list");
        responses.add("\t3. todo <description>");
        responses.add("\t4. deadline <description> /by <time>");
        responses.add("\t5. event <description> /at <time>");
        responses.add("\t6. mark <number>");
        responses.add("\t7. unmark <number>");
        responses.add("\t8. help");
        botResponse(responses);
    }

    /**
     * The response the bot gives based on the input by the user.
     * @param responses Collection of all the lines the bot responds with
     */
    private static void botResponse(ArrayList<String> responses) {
        System.out.println("............................................................");
        for (String response : responses) {
            System.out.println("\t" + response);
        }
        System.out.println("............................................................");
    }
}
