import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks){
        this.tasks = tasks;
    }

    /**
     * Add a task to current task list.
     *
     * @param t: Task to add
     */
    public void addTask(Task t){
        tasks.add(t);
    }

    /**
     * Remove a task from current task list.
     *
     * @param t: Task to remove
     */
    public void removeTask(Task t){
        tasks.remove(t);
    }

    /**
     * Remove a task from current task list.
     *
     * @param i: index of task to remove
     */
    public void removeTask(int i){
        tasks.remove(i);
    }

    /**
     * Get the size of current task list.
     *
     * @return size of task list
     */
    public int getSize(){
        return tasks.size();
    }


    /**
     * Get the size of current task list.
     *
     * @return size of task list
     */
    public Task getTask(int index){
        return tasks.get(index);
    }
}
