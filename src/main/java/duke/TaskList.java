package duke;

import duke.task.Task;

import java.util.ArrayList;

/**
 * Represents a container containing the different Tasks that
 * have been added by the user.
 */
public class TaskList {
    ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> taskList) {
        this.tasks = taskList;
    }

    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    public Task getTask(int index) {
        return this.tasks.get(index);
    }

    public int getSize() {
        return this.tasks.size();
    }

    public void addTask(Task task) {
        this.tasks.add(task);
    }

    public Task removeTask(int taskIndex) {
        return this.tasks.remove(taskIndex);
    }
}
