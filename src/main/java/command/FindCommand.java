package command;

import duke.Task;
import duke.TaskList;

import java.util.ArrayList;

public class FindCommand extends Command {
    private TaskList taskList;
    private String keyword;

    public FindCommand(TaskList taskList, String keyword){
        this.taskList = taskList;
        this.keyword = keyword;
    }

    public void execute(){
        ArrayList<Task> tasks = taskList.getTasks();
        int matchedIndex = 1;
        separator();
        System.out.println("Here are the matching tasks in your list:");
        for(int j=0; j < taskList.getTaskCount(); j++){
            if(tasks.get(j).getDescription().contains(keyword)){
                System.out.println(matchedIndex + "." + tasks.get(j).printTask());
                matchedIndex++;
            }
        }
        separator();
    }
}
