package command;

import duke.Task;
import duke.TaskList;

import java.util.ArrayList;

public class DeleteCommand extends Command{
    private TaskList taskList;
    private int taskIndex;

    public DeleteCommand(TaskList taskList, int taskIndex){
        this.taskList = taskList;
        this.taskIndex = taskIndex;
    }

    public void execute(){
        String message;
        ArrayList<Task> tasks = taskList.getTasks();
        try {
            message = tasks.get(taskIndex-1).printTask();
            tasks.remove(taskIndex-1);
            System.out.println("Noted. I've removed this task:");
            System.out.println(message);
            System.out.println("Now you have " + taskList.getTaskCount() + " tasks in the list.");
            taskList.decrementTasks();
        } catch (IndexOutOfBoundsException e) {
            System.out.println("This task does not exist!");
        } catch (NumberFormatException e){
            System.out.println("Please use numbers only to specify task!");
        }
    }

}
