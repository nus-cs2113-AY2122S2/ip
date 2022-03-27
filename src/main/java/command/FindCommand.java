package command;

import controller.Storage;
import controller.TaskList;
import controller.UI;
import task.Task;

import javax.naming.directory.InitialDirContext;
import java.util.ArrayList;

public class FindCommand extends Command{
    private static final String INDENT = "    ";
    private String text;
    public FindCommand(String fullCommand) {
        int textIdx = 1 + fullCommand.indexOf(" ");
        text = fullCommand.substring(textIdx);
    }
    public void execute(TaskList tasks, UI ui, Storage storage){
        ArrayList<Task> targetList = new ArrayList<>();
        for(Task task : tasks.getTaskList()){
            if(task.toString().contains(text)){
                targetList.add(task);
            }
        }

        if(targetList.size() ==0){
            ui.showNotFound();
            return;
        }
        ui.showFound();
        int i = 1;
        for(Task task : targetList){
            System.out.println(INDENT + (i++)+ "." + task.toString());
        }
    }

    public boolean isExit(){
        return false;
    }

}
