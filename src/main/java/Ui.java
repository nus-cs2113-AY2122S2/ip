import java.util.Scanner;

/**
 * The Ui class handles anything related to user interaction such as printing to CLI or reading user input
 */
public class Ui {

    public static final String WELCOME_MESSAGE = "##    ##  #######  ##    ##  ######      ##     ## #### ##    ##  ######\n"
                                               + " ##  ##  ##     ## ###   ## ##    ##     ###   ###  ##  ###   ## ##    ##\n"
                                               + "  ####   ##     ## ####  ## ##           #### ####  ##  ####  ## ##\n"
                                               + "   ##    ##     ## ## ## ## ##   ####    ## ### ##  ##  ## ## ## ##   ####\n"
                                               + "   ##    ##     ## ##  #### ##    ##     ##     ##  ##  ##  #### ##    ##\n"
                                               + "   ##    ##     ## ##   ### ##    ##     ##     ##  ##  ##   ### ##    ##\n"
                                               + "   ##     #######  ##    ##  ######      ##     ## #### ##    ##  ######\n"
                                               + "Hello! I'm Yong Ming\n"
                                               + "What can I do for you?";
    public static final String WRONG_FORMAT_MESSAGE = "OOPS!!! One or more parameters are missing or invalid. The correct format is:\n"
                                                    + "todo [description]\n"
                                                    + "deadline [description] /by [deadline]\n"
                                                    + "event [description] /at [time]\n"
                                                    + "mark [Task#]\n"
                                                    + "unmark [Task#]\n"
                                                    + "delete [Task#]\n";
    public static final String WRONG_INPUT_MESSAGE = "OOPS!!! I'm sorry, but I don't know what that means :-(\n"
                                                   + "The accepted inputs are:\n"
                                                   + "> todo [description]\n"
                                                   + "> deadline [description] /by [deadline]\n"
                                                   + "> event [description] /at [time]\n"
                                                   + "> list\n"
                                                   + "> mark [Task#]\n"
                                                   + "> unmark [Task#]\n"
                                                   + "> delete [Task#]\n"
                                                   + "> bye\n";
    public static final String END_MESSAGE = "Bye! Hope to see you again soon :)";

    public static void printWelcome() {
        System.out.println(WELCOME_MESSAGE);
    }

    public static void printWrongFormat() {
        System.out.println(WRONG_FORMAT_MESSAGE);
    }

    public static void printWrongInput() {
        System.out.println(WRONG_INPUT_MESSAGE);
    }

    public static void printBye() {
        System.out.println(END_MESSAGE);
    }

    /**
     * Prints all the tasks in user's TaskList to give user an overview of his tasks
     */
    public static void printList() {
        if (TaskList.size() == 0) {
            System.out.println("There are no tasks yet!\n");
        } else {
            for (int i = 0; i < TaskList.size(); i++) {
                int listIndex = i + 1;
                System.out.println(listIndex + "." + TaskList.get(i));
            }
            System.out.println();
        }
    }

    /**
     * Prints the number of tasks in user's TaskList
     * Method is invoked when user adds or deletes a task from TaskList to update user on the number of remaining tasks
     */
    public static void printListSize() {
        if (TaskList.size() == 0) {
            System.out.println("Now you have no tasks in the list\n");
        } else if (TaskList.size() == 1) {
            System.out.println("Now you have 1 task in the list\n");
        } else {
            System.out.println("Now you have " + TaskList.size() + " tasks in the list.\n");
        }
    }

    /**
     * Informs user that task has been added to TaskList
     */
    public static void printAddToList() {
        int newTaskIndex = TaskList.size() - 1;
        Task addedTask = TaskList.get(newTaskIndex);
        System.out.println("Got it. I've added this task:" + System.lineSeparator() + addedTask);
        printListSize();
    }

    /**
     * Informs user which task is going to be deleted
     *
     * @param taskIndex Index of the task that is to be deleted
     */
    public static void printDeleteFromList(int taskIndex) {
        Task targetTask = TaskList.get(taskIndex);
        System.out.println("Got it. Removing this task:" + System.lineSeparator() + targetTask);
    }

    public static String readCommand(Scanner in) {
        return in.nextLine();
    }

}
