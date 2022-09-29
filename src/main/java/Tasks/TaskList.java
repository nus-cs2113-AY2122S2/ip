package Tasks;
import Misc.Ui;
import java.util.ArrayList;

public class TaskList  {
    private static ArrayList<Task> tasks;

    /**
     * Constructor for TaskList, initializes the ArrayList.
     * @param load Loaded array from the save file.
     */
    public TaskList(ArrayList<Task> load) {
        tasks = new ArrayList<>();
        tasks.addAll(load);
    }
    /**
     * Constructor for TaskList without the save file.
     */
    public TaskList() {
        this(null);
    }

    /**
     * Returns tasks instance.
     * @return The tasks array.
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Adds a new task.
     * @param task The new task.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Deletes a task.
     * @param index Index to be deleted.
     */
    public void deleteTask(int index) {
        tasks.remove(index);
    }
}