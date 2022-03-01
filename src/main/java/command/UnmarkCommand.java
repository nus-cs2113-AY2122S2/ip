package command;

import duke.Task;
import duke.TaskList;

import java.util.ArrayList;

/**
 * Subclass of Command to handle marking of tasks as not done
 */
public class UnmarkCommand extends Command{
    private TaskList taskList;
    private int taskIndex;

    /**
     * Initialises a UnmarkCommand with the TaskList and index of Task to be unmarked
     * @param taskList list of tasks to be unmarked from
     * @param taskIndex index of Task to be marked as not done
     */
    public UnmarkCommand(TaskList taskList, int taskIndex){
        this.taskList = taskList;
        this.taskIndex = taskIndex;
    }

    /**
     * Mark the specified Task as not done
     */
    public void execute(){
        ArrayList<Task> tasks = taskList.getTasks();
        try {
            tasks.get(taskIndex-1).markAsUndone();
            System.out.println(tasks.get(taskIndex-1).printTask());
        } catch (IndexOutOfBoundsException e) {
            System.out.println("This task does not exist!");
        } catch (NumberFormatException e){
            System.out.println("Please use numbers only to specify task!");
        }
    }
}
