import java.util.ArrayList;

/**
 * Manages an ArrayList of Tasks.
 */
public class TaskList {

    private ArrayList<Task> taskList;

    public TaskList(){
        this.taskList = new ArrayList<Task>();
    }

    /**
     * Adds a new string as Task to the ArrayList and prints the Task added.
     * @param taskName
     */
    public void addTask(String taskName){
        Task newTask = new Task(taskName);
        taskList.add(newTask);
        System.out.println("Added:" + System.lineSeparator() + newTask);
    }

    /**
     * Prints out the list of Tasks.
     */
    public void displayTasks(){
        System.out.println("Here are your tasks to be completed!");
        for (int i = 0; i < taskList.size(); i++){
            System.out.println((i+1)+". "+taskList.get(i));
        }
    }

    /**
     * Sets the completion status of a Task using its index (as displayed to the user).
     * @param index
     * @param status
     */
    public void setTaskStatus(int index, boolean status){
        Task task = taskList.get(index);
        task.setDone(status);
        if (status){
            System.out.println("Ok, task done!");
        } else {
            System.out.println("Ok, you didn't actually do the task!");
        }
        System.out.println("  "+task);
    }

}
