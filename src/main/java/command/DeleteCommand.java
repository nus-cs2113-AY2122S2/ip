package command;

import duke.Task;
import duke.TaskList;

import java.util.ArrayList;

/**
 * Subclass of Command to handle deleting of tasks
 */
public class DeleteCommand extends Command{
    private TaskList taskList;
    private int taskIndex;

    /**
     * Initialises a DeleteCommand with the TaskList to be deleted from and index of Task to be deleted
     * @param taskList list of tasks to be deleted from
     * @param taskIndex index of Task to be deleted
     */
    public DeleteCommand(TaskList taskList, int taskIndex){
        this.taskList = taskList;
        this.taskIndex = taskIndex;
    }

    /**
     * Delete the specified Task and output the result to user
     */
    public void execute(){
        String message;
        ArrayList<Task> tasks = taskList.getTasks();
        try {
            message = tasks.get(taskIndex-1).printTask();
            taskList.deleteTask(taskIndex-1);
            separator();
            System.out.println("Noted. I've removed this task:");
            System.out.println(message);
            System.out.println("Now you have " + taskList.getTaskCount() + " tasks in the list.");
            separator();
        } catch (IndexOutOfBoundsException e) {
            System.out.println("This task does not exist!");
        } catch (NumberFormatException e){
            System.out.println("Please use numbers only to specify task!");
        }
    }

}
