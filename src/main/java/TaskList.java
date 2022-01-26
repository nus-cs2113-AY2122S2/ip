import java.util.ArrayList;

public class TaskList {

    private ArrayList<String> taskList;

    public TaskList(){
        this.taskList = new ArrayList<String>();
    }

    public String addTask(String s){
        taskList.add(s);
        return s;
    }

    public void displayTasks(){
        for (int i = 0; i < taskList.size(); i++){
            System.out.println((i+1)+". "+taskList.get(i));
        }
    }

}
