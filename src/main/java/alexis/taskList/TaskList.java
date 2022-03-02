package alexis.taskList;

import alexis.exceptions.EmptyListException;
import alexis.task.Task;

import java.util.ArrayList;

/**
 * Represents the list of tasks
 */
public class TaskList {

    public ArrayList<Task> taskArrayList;
    protected static int listSize = 0;

    /**
     * Creates an ArrayList of tasks. Used when ./data/tasks.list contains valid tasks.
     *
     * @param taskArrayList Arraylist of tasks parsed from saved file
     */
    public TaskList(ArrayList<Task> taskArrayList) {
        this.taskArrayList = taskArrayList;
        listSize = taskArrayList.size();
    }

    /**
     * Creates an empty ArrayList of tassk. Used when ./data/tasks.txt is corrupted/empty.
     */
    public TaskList() {
        this.taskArrayList = new ArrayList<>(100);
        listSize = 0;
    }

    public int getListSize() {
        return listSize;
    }

    /**
     * Same functionality as getListSize() except it throws an exception
     *
     * @return listSize
     * @throws EmptyListException If task list is empty
     */
    public int getListSizeForDisplayList() throws EmptyListException{
        if (listSize == 0) {
            throw new EmptyListException();
        } else {
            return listSize;
        }
    }

    public Task getTask(int taskNumber) {
        return taskArrayList.get(taskNumber);
    }

    public void add(Task newTask) {
        taskArrayList.add(newTask);
        listSize++;
    }

    public void remove(int taskNumber) {
        taskArrayList.remove(taskNumber);
        listSize--;
    }

}
