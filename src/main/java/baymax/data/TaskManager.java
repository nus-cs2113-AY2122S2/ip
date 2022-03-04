package baymax.data;

import java.util.ArrayList;

import baymax.storage.Storage;
import baymax.ui.Ui;

/**
 * taskmanager class is used for manage the arrarylist of tasks
 */
public class TaskManager {

    String horiLine = "____________________________________________________________\n";
    private final ArrayList<Task> tasks;
    private Ui ui;

    /**
     * class constructor,
     * no parameter passed,create a new arrarylist
     */
    public TaskManager() {
        tasks = new ArrayList<>();
    }

    /**
     * class constructor
     * initialise with user input of tasks
     * @param tasks
     */
    public TaskManager(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * add new task into arrylist of tasks
     * @param newT the new task
     */
    public void addTask(Task newT) {
        tasks.add(newT);
        System.out.println(horiLine);
        System.out.println(" Got it. I've added this task: \n"+ newT.toString()+"\n"+
                            "Now you have " +  + tasks.size() + " tasks in the list.");
        System.out.println(horiLine);
    }

    /**
     * mark the certain task as done
     * @param taskIndex
     */
    public void markTask (int taskIndex) {
        System.out.println(horiLine);
        Task temp = tasks.get(taskIndex);
        temp.markTaskDone();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(temp.getStatusIcon() + " " +
                           temp.getDescription());
        System.out.println(horiLine);
    }

    /**
     * unmark the certain task as not done yet
     * @param taskIndex
     */
    public void unmarkTask (int taskIndex) {
        System.out.println(horiLine);
        Task temp = tasks.get(taskIndex);
        temp.unmarkTaskDone();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(temp.getStatusIcon() + " " +
                           temp.getDescription());
        System.out.println(horiLine);
    }

    /**
     * delete the task
     * @param taskIndex index of the task to be deleted
     */
    public void deleteTask (int taskIndex) {
        System.out.println(horiLine);
        Task deleted = tasks.get(taskIndex);
        System.out.println("Noted. I've removed this task:");
        String text = String.format("%s %s", deleted.getStatusIcon(), deleted.getDescription());
        System.out.println(text);
        tasks.remove(taskIndex);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        System.out.println(horiLine);
    }

}

