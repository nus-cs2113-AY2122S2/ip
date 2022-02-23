package taskitems.task;

import java.util.ArrayList;

public class TaskList {
    public ArrayList<Task> taskList = new ArrayList<>();
    public int size = 0;

    public void add (Task task) {
        taskList.add(task);
        size++;
        System.out.println("added: " + taskList.get(size-1));
        System.out.println("You now have " + size + " tasks in the list.");
    }

    public void add (Task task, boolean Backend) {
        taskList.add(task);
        size++;
    }

    public void delete (int num) {
        taskList.remove(num - 1);
        size--;
    }

    public Task getTask(int num) {
        return taskList.get(num - 1);
    }

    public void mark (int num) {
        taskList.get(num - 1).setMarked(true);
    }

    public void unmark (int num) {
        taskList.get(num - 1).setMarked(false);
    }

}
