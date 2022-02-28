package duke.helper;

/**
 * Represents an object to handle communication to the user
 */
public class Ui {
    public static final String LINEBREAK = "____________________________________________________________";

    /**
     * Prints to standard output the error message received
     * @param message message of the error faced
     */
    public void printExceptionMessage(String message) {
        System.out.println(LINEBREAK);
        System.out.println(" Error: " + message);
        System.out.println(LINEBREAK);
    }

    /**
     * Prints to standard output the message when a task is marked/unmarked
     * @param mark boolean to show if the task is marked or not
     * @param taskName Task name that was marked/unmarked
     */
    public void printMarkedMessage(boolean mark, String taskName) {
        System.out.println(LINEBREAK);
        if (mark) {
            System.out.println(" Nice! I've marked this task as done:");
            System.out.println("  [X] " + taskName);
        }else {
            System.out.println(" OK, I've marked this task as not done yet:");
            System.out.println("  [ ] " + taskName);
        }
        System.out.println(LINEBREAK);
    }

    /**
     * Prints to standard output the message when a task is added
     * @param task task object added to the list
     * @param taskCount number of tasks in the list currently
     */
    public void printAddTaskMessage(String task, int taskCount) {
        System.out.println(LINEBREAK);
        System.out.println(" added: " + task);
        System.out.println(" Total number of tasks now: " + taskCount);
        System.out.println(LINEBREAK);
    }

    /**
     * Prints to standard output the start up message
     * @param isLoaded boolean to check if the save file was loaded or not
     */
    public void printStartUpMessage(boolean isLoaded) {
        System.out.println(" Hello! I'm Duke\n What can I do for you?");
        System.out.println(LINEBREAK);
        if (isLoaded) {
            System.out.println(" Loaded Save File.");
            System.out.println(LINEBREAK);
        }
    }

    public void printExitMessage() {
        System.out.println(LINEBREAK);
        System.out.println(" Bye. Hope to see you again soon!");
        System.out.println(LINEBREAK);
    }

    /**
     * Print to standard output the message when a task is deleted
     * @param task the task object that was deleted
     * @param taskCount number of tasks in the list currently
     */
    public void printDeleteMessage(String task, int taskCount) {
        System.out.println(LINEBREAK);
        System.out.println(" I have removed this task:");
        System.out.println(task);

        System.out.println(" Total number of tasks now: " + taskCount);
        System.out.println(LINEBREAK);
    }
}
