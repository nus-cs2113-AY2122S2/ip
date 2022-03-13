package duke;

import duke.exception.AdditionalException;
import duke.task.Task;

import java.util.ArrayList;

/**
 * Represents a list of tasks and has operations that add and delete tasks to the list.
 */
public class TaskList {

    private ArrayList<Task> listOfTasks;

    public TaskList() {
        listOfTasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> listOfTasks) {
        this.listOfTasks = listOfTasks;
    }

    /**
     * Returns this TaskList's list of tasks.
     * If the list is empty, an AdditionalException will be raised.
     *
     * @return This TaskList's list of tasks.
     * @throws AdditionalException if list is empty.
     * @see AdditionalException
     */
    public ArrayList<Task> getList() throws AdditionalException {
        if (listOfTasks.isEmpty()) {
            throw new AdditionalException("YAY!!! you do not have any tasks at the moment hehe");
        }
        return listOfTasks;
    }

    /**
     * Returns the task stored a particular index.
     *
     * @param index the index of the task to be returned from the list of tasks.
     * @return The task stored at a particular index.
     */
    public Task getTask(int index) {
        return listOfTasks.get(index);
    }

    /**
     * Returns the number of tasks stored in the list of tasks.
     *
     * @return The number of tasks stored in the list of tasks.
     */
    public int getSize() {
        return listOfTasks.size();
    }

    /**
     * Deletes the task stored at a particular index.
     *
     * @param indexToDelete The index of the task to be deleted from the list of tasks.
     */
    public void deleteTask(int indexToDelete) {
        listOfTasks.remove(indexToDelete);
    }

    /**
     * Adds a task at the end of the list of tasks.
     *
     * @param task The task to be added to the list of tasks.
     */
    public void addTask(Task task) {
        listOfTasks.add(task);
    }

}
