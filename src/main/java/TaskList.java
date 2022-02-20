import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks){
        this.tasks = tasks;
    }

    public void addTask(Task t){
        tasks.add(t);
    }

    public void removeTask(Task t){
        tasks.remove(t);
    }

    public void removeTask(int i){
        tasks.remove(i);
    }

    public int getSize(){
        return tasks.size();
    }

    public Task getTask(int index){
        return tasks.get(index);
    }
}
