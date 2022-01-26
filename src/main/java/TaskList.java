import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> taskList;

    public TaskList(){
        this.taskList = new ArrayList<Task>();
    }

    public void addTask(String s){
        Task newTask = new Task(s);
        taskList.add(newTask);
        System.out.println("Added:" + System.lineSeparator() + newTask);
    }

    public void displayTasks(){
        System.out.println("Here are your tasks to be completed!");
        for (int i = 0; i < taskList.size(); i++){
            System.out.println((i+1)+". "+taskList.get(i));
        }
    }

    public void setTaskStatus(int i, boolean status){
        Task task = taskList.get(i);
        task.setDone(status);
        if (status){
            System.out.println("Ok, task done!");
        } else {
            System.out.println("Ok, you didn't actually do the task!");
        }
        System.out.println("  "+task);
    }

}
