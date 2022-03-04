/**
 * container for user visible messages.
 */

public class Ui {

    public static final String DIVIDER = "____________________________________________________________";

    public static final String WELCOME_MESSAGE = "Hello from\n"
            + " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n"
            + DIVIDER
            + "\n"
            + " Hello! I'm Duke\n"
            + " What can I do for you?\n"
            + DIVIDER;

    public static final String GOODBYE_MESSAGE = DIVIDER
            + "\n"
            + " Bye. Hope to see you again soon!\n"
            + DIVIDER;

    public static final String INIT_FAILED_MESSAGE = " Failed to initialise Duke application. Exiting...";

    public static final String EMPTY_INPUT_MESSAGE = " Your input cannot be empty, please key in a valid command!";

    public static final String ILLEGAL_INDEX_MESSAGE = " Please enter a valid index!";

    public static final String EMPTY_DATE_MESSAGE = " Please enter a date to the task!";

    public static final String EMPTY_DESCRIPTION_MESSAGE = " ☹ OOPS!!! The description of a task cannot be empty.";

    public static final String Mark_Task_MESSAGE = " Nice! I've marked this task as done:";

    public static final String Mark_Failed_MESSAGE = " This task has already been marked as done:";

    public static final String Unmark_Task_MESSAGE = " OK, I've marked this task as not done yet:";

    public static final String Unmark_Failed_MESSAGE = " This task has not been marked as done:";

    public static final String LIST_TASK_MESSAGE = " Here are the tasks in your list:";

    public static final String DELETE_TASK_MESSAGE = " Noted. I've removed this task:";

    public static final String SAVE_DATA_MESSAGE = " Data saved successfully";

    public static final String SAVE_FAILED_MESSAGE = " Data failed to be saved";

    public static final String LOAD_FAILED_MESSAGE = " Data is corrupted! Please delete the existing data file.";

    public static final String ILLEGAL_COMMAND_MESSAGE = " ☹ OOPS!!! I'm sorry, but I don't know what that means :-(";

    public static final String WRONG_INPUT_FORMAT_MESSAGE = " Please make sure the input follows the following format: \n"
            + " todo [description] \n"
            + " deadline [description] /by [date] \n"
            + " event [description] /at [date]";


    public static void showWelcomeMessage() {
        System.out.println(WELCOME_MESSAGE);
    }

    public static void showGoodByeMessage() {
        System.out.println(GOODBYE_MESSAGE);
    }

    public static void showInitFailedMessage() {
        System.out.println(INIT_FAILED_MESSAGE);
    }

    public static void showAddTaskMessage() {
        System.out.println(Ui.DIVIDER);
        System.out.println(" Got it. I've added this task: ");
        System.out.println("   " + TaskManager.tasks.get(TaskManager.tasksCount));
        System.out.println(" Now you have " + TaskManager.tasks.size() + " tasks in the list");
        System.out.println(Ui.DIVIDER);
    }

}
