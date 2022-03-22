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

    /** Returns a string containing all tasks in a format for Ui */
    public String getAllTasksUi() {
        String output = "";
        for (int i = 0; i < this.getSize(); i++) {
            output += String.format("%d. %s", i + 1, this.getTask(i));
            /** Add line break */
            if (i != this.getSize() - 1) {
                output += "\n";
            }
        }
        return output;
    }

    /** Returns a string on how many tasks are remaining in the current list */
    public String getRemainingTasksStr() {
        return String.format("Now you have %d tasks in the list.", this.getSize());
    }
}
