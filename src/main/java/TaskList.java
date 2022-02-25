import java.util.ArrayList;

public class TaskList {

    public static final ArrayList<Task> list = new ArrayList<>();

    public static void add (Task task) {
        list.add(task);
    }

    public static void remove (int taskIndex) {
        list.remove(taskIndex);
    }

    public static int size() {
        return list.size();
    }

    public static Task get(int taskIndex) {
        return list.get(taskIndex);
    }

}
