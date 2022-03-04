package tasklist;

import duke.Task;

import java.util.ArrayList;

public class TaskList {
    public ArrayList<Task> taskArrayList;

    public TaskList(ArrayList<Task> tasks) {
        this.taskArrayList = tasks;
    }

    public TaskList() {
        this.taskArrayList = new ArrayList<>();
    }

    public int getSize() {
        return taskArrayList.size();
    }

    public Task getTaskViaIndex(int index) {
        return taskArrayList.get(index);
    }

    public void addTaskToList(Task task) {
        taskArrayList.add(task);
    }

    public void removeTaskFromList(Task task) {
        taskArrayList.remove((task));
    }

    public ArrayList<Task> findFromList(String detail) {
        ArrayList<Task> filteredTasks = new ArrayList<>();
        for (Task task:taskArrayList) {
            if (task.getDescription().contains(detail)) {
                filteredTasks.add(task);
            }
        }
        return filteredTasks;
    }
}
