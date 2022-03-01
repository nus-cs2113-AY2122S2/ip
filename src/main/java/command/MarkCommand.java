package command;

import duke.Task;
import duke.TaskList;

import java.util.ArrayList;

/**
 * Subclass of Command to handle marking of tasks as done
 */
public class MarkCommand extends Command{
    private TaskList taskList;
    private int taskIndex;

    /**
     * Initialises a MarkCommand with the TaskList and index of Task to be marked
     * @param taskList list of tasks to be marked from
     * @param taskIndex index of Task to be marked as done
     */
    public MarkCommand(TaskList taskList, int taskIndex){
        this.taskList = taskList;
        this.taskIndex = taskIndex;
    }

    /**
     * Mark the specified Task as done
     */
    public void execute(){
        ArrayList<Task> tasks = taskList.getTasks();
        try {
            tasks.get(taskIndex-1).markAsDone();
            System.out.println(tasks.get(taskIndex-1).printTask());
        } catch (IndexOutOfBoundsException e) {
            System.out.println("This task does not exist!");
        } catch (NumberFormatException e){
            System.out.println("Please use numbers only to specify task!");
        }
    }
}
