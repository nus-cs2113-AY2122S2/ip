package Duke.Tasks;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;
    public TaskList(ArrayList<Task> listArray) {
        this.tasks = listArray;
    }

    public TaskList() {
        this(new ArrayList<>());
    }

    public void add(Task task) {
        tasks.add(task);
    }

    public int size() {
        return tasks.size();
    }

    public void markItem(int index) {
        tasks.get(index).markAsDone();
    }

    public void unMarkItem(int index) {
        tasks.get(index).unMark();
    }

    public Task get(int index) {
        return tasks.get(index);
    }

    public void delete(int index) {
        tasks.remove(index);
    }

    public boolean isEmpty() {
        return tasks.isEmpty();
    }
}
