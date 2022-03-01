package command;

import duke.Task;
import duke.TaskList;

import java.util.ArrayList;

/**
 * Subclass of Command to handle listing of all tasks
 */
public class ListCommand extends Command {
    private TaskList taskList;

    /**
     * Initialises a ListCommand with the TaskList to be listed
     * @param taskList list of tasks to be listed
     */
    public ListCommand(TaskList taskList){
        this.taskList = taskList;
    }

    /**
     * List out the tasks in the TaskList
     */
    public void execute(){
        ArrayList<Task> tasks = taskList.getTasks();
        System.out.println("================================================");
        System.out.println("Here are the tasks in your list:");
        for(int j=0; j < taskList.getTaskCount(); j++){
            System.out.println((j+1) + "." + tasks.get(j).printTask());
        }
        System.out.println("================================================");
    }
}
