package command;

import duke.Task;
import duke.TaskList;

import java.util.ArrayList;

public class ListCommand extends Command {
    private TaskList taskList;

    public ListCommand(TaskList taskList){
        this.taskList = taskList;
    }

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
