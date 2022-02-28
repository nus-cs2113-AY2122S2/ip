package command;

import duke.Task;
import duke.TaskList;

import java.util.ArrayList;

public class UnmarkCommand extends Command{
    private TaskList taskList;
    private int taskIndex;

    public UnmarkCommand(TaskList taskList, int taskIndex){
        this.taskList = taskList;
        this.taskIndex = taskIndex;
    }

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
