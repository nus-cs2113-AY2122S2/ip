package alexis.commands;

import alexis.exceptions.EmptyListException;
import alexis.main.Alexis;

/**
 * Displays the full task list to the user
 */
public class DisplayListCommand extends Command{

    public static final String DISPLAY_TASK_MESSAGE = "Here are the tasks in your list:";
    public static final String EMPTY_LIST_MESSAGE = "Your list is empty. You have no tasks now.";

    /**
     * Assesses the Alexis.tasks taskList and prints out all the tasks in it.
     */
    public DisplayListCommand() {
        try {
            int numOfTasks = Alexis.tasks.getListSizeForDisplayList();
            System.out.println(DISPLAY_TASK_MESSAGE);
            for (int i = 0; i < numOfTasks; i++) {
                System.out.println((i + 1) + "." + Alexis.tasks.getTask(i).toString());
            }
        } catch (NullPointerException | EmptyListException e) {
            System.out.println(EMPTY_LIST_MESSAGE);
        }
    }

}
