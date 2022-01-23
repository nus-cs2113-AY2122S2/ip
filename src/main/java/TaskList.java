import java.util.ArrayList;

/**
 * In charge of the global task list
 */
public class TaskList {

    private static ArrayList<Task> taskArrayList =  new ArrayList<>();

    /**
     * @param _newTask The new task to be appended
     * @return The indication of the result of the addTask operation
     */
    public static Boolean addTask (Task _newTask)
    {
        try {
            TaskList.taskArrayList.add(_newTask);
            return true;
        } catch (Exception e) {
            return false;
        }

    }

    /**
     * @return The number of tasks in the list
     */
    public static int size()
    {
        return taskArrayList.size();
    }

    /**
     * @param _index The index of element/task that the get method wants to get
     * @return The index-the element(0-based)
     */
    public static Task get(int _index){
        return taskArrayList.get(_index);
    }





}
