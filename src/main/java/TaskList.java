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
     * Adds a new string as ToDo to the ArrayList and prints the Task added.
     * @param taskName
     */
    public void addToDo(String taskName){
        Task newToDo = new ToDo(taskName);
        taskList.add(newToDo);
        System.out.println("Added:" + System.lineSeparator() + "   " + newToDo);
    }

    /**
     * Adds a new string as Deadline to the ArrayList and prints the Task added.
     * @param taskName
     * @param deadline
     */
    public void addDeadline(String taskName, String deadline){
        Task newDeadline = new Deadline(taskName, deadline);
        taskList.add(newDeadline);
        System.out.println("Added:" + System.lineSeparator() + "   " + newDeadline);
    }

    /**
     * Adds a new string as Event to the ArrayList and prints the Task added.
     * @param taskName
     * @param eventTime
     */
    public void addEvent(String taskName, String eventTime){
        Task newEvent = new Event(taskName, eventTime);
        taskList.add(newEvent);
        System.out.println("Added:" + System.lineSeparator() + "   " + newEvent);
    }

    /**
     * Prints out the list of Tasks.
     */
    public void displayTasks(){
        System.out.println("Total "+taskList.size()+ " tasks to be completed:");
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
        System.out.println("   "+task);
    }

}
