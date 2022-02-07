import java.util.ArrayList;

public class TaskManager {

    /** The limit on how many tasks can be in the list */
    private static final int TASK_LIMIT = 100;

    /** Keeps track on how many tasks are in the list*/
    private static int taskCount = 0;
    /** The list of tasks. Limited to 100 */
    private static Task[] taskList = new Task[100];

    public static ArrayList<String> run(Command operation, String[] arguments) {
        switch (operation) {
        case LIST:
            return getAllTasks();
        case MARK:
            return markTask(true, arguments[0], operation);
        case UNMARK:
            return markTask(false, arguments[0], operation);
        case TODO:
            return addTask(new Todo(arguments[0]));
        case DEADLINE:
            return addTask(new Deadline(arguments[0], arguments[1]));
        case EVENT:
            return addTask(new Event(arguments[0], arguments[1]));
        case HELP:
            return getHelpOptions();
        case NONE:
        default:
            return giveInvalidCommand(operation);
        }
    }

    /**
     * @return Return the current list of tasks.
     */
    private static ArrayList<String> getAllTasks() {
        ArrayList<String> responses = new ArrayList<>();
        if (taskCount == 0){
            responses.add("The list empty!");
        }
        for (int i = 0; i < taskCount; i++) {
            responses.add(i + 1 + ". " + taskList[i]);
        }
        return responses;
    }

    /**
     * Marks a task as complete or incomplete.
     * @param isDone Whether task is completed
     * @param taskIndex Index of the task we want to mark
     * @param operation The current command being used
     * @return Response for confirmation of marking task
     */
    private static ArrayList<String> markTask(boolean isDone, String taskIndex, Command operation) {
        // The task list is 1 base indexing while the array itself is 0 base indexing
        int index = Integer.parseInt(taskIndex) - 1;
        if (index < 0 || index >= taskCount) {
            // Marking outside the range is not allowed
            return giveInvalidCommand(operation);
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
        return responses;
    }

    /**
     * Add a task to the list of tasks.
     * Does not add a task if we are at the limit.
     * @param newTask The new task to be added to the list
     * @return Response for adding a task
     */
    private static ArrayList<String> addTask(Task newTask) {
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
        return responses;
    }

    /**
     * Print out all the commands the bot will respond to.
     * Activates when user enters <code>help</code>
     * @return All available help options
     */
    private static ArrayList<String> getHelpOptions() {
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
        return responses;
    }

    /**
     * Activates when input is outside the expected behavior.
     * Prints that something wrong occurred. Sometimes based on the given command.
     * @param operation The current command being used
     * @return Response for invalid command
     */
    protected static ArrayList<String> giveInvalidCommand(Command operation) {
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
        return responses;
    }

}
