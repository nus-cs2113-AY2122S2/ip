package duke.data;

import duke.data.task.Task;

import java.time.LocalDate;
import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> list;

    public TaskList() {
        list = new ArrayList<>();
    }

    public void addTask(Task t) {
        list.add(t);
    }

    public Task removeTask(int index) {
        Task t = list.get(index);
        list.remove(index);
        return t;
    }

    public Task getTask(int index) {
        return list.get(index);
    }

    public ArrayList<Task> getTaskList() {
        return list;
    }

    public int getNumTasks() {
        return list.size();
    }

    public int getNumMatchingTasks(LocalDate date) {
        if (date == null) {
            return getNumTasks();
        }
        int numTasks = 0;
        for (Task t : list) {
            if (t.isMatchingDate(date)) {
                numTasks++;
            }
        }
        return numTasks;
    }

    public int getNumMatchingTasks(String searchString) {
        int numTasks = 0;
        for (Task t : list) {
            if (t.getDescription().contains(searchString)) {
                numTasks++;
            }
        }
        return numTasks;
    }
}
