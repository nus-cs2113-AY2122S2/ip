package marites;

import java.util.ArrayList;

import marites.exception.TaskListOutOfBoundsException;
import marites.task.Task;

public class TaskList implements java.io.Serializable {
    private final ArrayList<Task> taskList;

    public ArrayList<Task> getTaskList() {
        return taskList;
    }

    public TaskList() {
        taskList = new ArrayList<>();
    }

    public int getTaskCount() {
        return taskList.size();
    }

    public void addTask(Task task) {
        taskList.add(task);
    }

    public Task getTaskByIndex(int taskIndex) throws TaskListOutOfBoundsException {
        try {
            return taskList.get(taskIndex);
        } catch (IndexOutOfBoundsException e) {
            throw new TaskListOutOfBoundsException();
        }
    }

    public void setTaskStatus(int taskIndex, boolean status) throws TaskListOutOfBoundsException {
        Task task = getTaskByIndex(taskIndex);
        task.setDone(status);
    }

    public void deleteTask(int taskIndex) throws TaskListOutOfBoundsException {
        try {
            taskList.remove(taskIndex);
        } catch (IndexOutOfBoundsException e) {
            throw new TaskListOutOfBoundsException();
        }
    }
}
