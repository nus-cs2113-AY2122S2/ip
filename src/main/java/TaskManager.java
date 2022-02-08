/**
 * Class that manages the Commands inputted and does
 * the proper instructions based on the input.
 * Changes anything relating to Tasks.
 */
public class TaskManager {

    /** The limit on how many tasks can be in the list */
    private static final int TASK_LIMIT = 100;

    /** Keeps track on how many tasks are in the list*/
    private static int taskCount = 0;
    /** The list of tasks. Limited to 100 */
    private static Task[] taskList = new Task[100];

    /**
     * Runs the given command and calls the right method
     * to print the proper message.
     * @param operation The Command the user entered
     * @param arguments The arguments that follows the command
     */
    public static void run(Command operation, String[] arguments) {
        try {
            switch (operation) {
            case LIST:
                printAllTasks();
                break;
            case MARK:
                markTask(true, arguments[0]);
                break;
            case UNMARK:
                markTask(false, arguments[0]);
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
                printHelpOptions();
                break;
            case NONE:
            default:
                throw new BobaException(Command.NONE);
            }
        } catch (BobaException e) {
            ErrorHandler.printErrorMessage(e.getOperation());
        }
    }

    /**
     * Prints the current list of tasks.
     */
    private static void printAllTasks() {
        if (taskCount == 0){
            BobaResponse.addResponse("The list empty!");
        }
        for (int i = 0; i < taskCount; i++) {
            BobaResponse.addResponse(i + 1 + ". " + taskList[i]);
        }
        BobaResponse.printResponse();
    }

    /**
     * Marks a task as complete or incomplete.
     * @param isDone Whether task is completed
     * @param taskIndex Index of the task we want to mark
     */
    private static void markTask(boolean isDone, String taskIndex) throws BobaException {
        // The task list is 1 base indexing while the array itself is 0 base indexing
        int index = Integer.parseInt(taskIndex) - 1;
        if (index < 0 || index >= taskCount) {
            // Marking outside the range is not allowed
            throw new BobaException(Command.MARK);
        }
        Task selectedTask = taskList[index];
        if (isDone) {
            selectedTask.markAsDone();
            BobaResponse.addResponse("Beep boop! I've marked this task as done:");
        } else {
            selectedTask.markAsNotDone();
            BobaResponse.addResponse("Boop beep! I've marked this task as not done:");
        }
        BobaResponse.addResponse(selectedTask.toString());
        BobaResponse.printResponse();
    }

    /**
     * Add a task to the list of tasks.
     * Does not add a task if we are at the limit.
     * @param newTask The new task to be added to the list
     */
    private static void addTask(Task newTask) {
        if (taskCount == TASK_LIMIT) {
            BobaResponse.addResponse("The list is full");
            BobaResponse.addResponse("Task could not be added");
        } else {
            taskList[taskCount] = newTask;
            taskCount++;
            BobaResponse.addResponse("Got it. I've added this task:");
            BobaResponse.addResponse("\t" + newTask.toString());
            BobaResponse.addResponse("Now you have " + taskCount + " tasks in your list.");
        }
        BobaResponse.printResponse();
    }

    /**
     * Print out all the commands the bot will respond to.
     * Activates when user enters <code>help</code>
     */
    private static void printHelpOptions() {
        BobaResponse.addResponse("Here are all the possible commands:");
        BobaResponse.addResponse("\t1. bye");
        BobaResponse.addResponse("\t2. list");
        BobaResponse.addResponse("\t3. todo <description>");
        BobaResponse.addResponse("\t4. deadline <description> /by <time>");
        BobaResponse.addResponse("\t5. event <description> /at <time>");
        BobaResponse.addResponse("\t6. mark <number>");
        BobaResponse.addResponse("\t7. unmark <number>");
        BobaResponse.addResponse("\t8. help");
        BobaResponse.printResponse();
    }
}
