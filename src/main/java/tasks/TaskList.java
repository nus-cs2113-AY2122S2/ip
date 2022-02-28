package tasks;

import exceptions.DukeException;
import exceptions.TaskListDukeException;

import storage.TaskFileStorage;

import java.util.ArrayList;

/**
 * In charge of the global task list.
 */
public class TaskList {
    private static ArrayList<Task> taskArrayList;
    private static final String TASK_DAO_BASE_PATH = "data";
    private static final String TASK_DAO_FILE_NAME = "tasks.txt";
    private static TaskFileStorage taskFileDao;

    public TaskList() throws DukeException {
        try {
            taskFileDao = new TaskFileStorage(TASK_DAO_BASE_PATH, TASK_DAO_FILE_NAME);
            taskArrayList = taskFileDao.readTasks();
        } catch (DukeException e) {
            taskArrayList = new ArrayList<>();
        }
    }

    /**
     * Adds tasks.
     *
     * @param newTaskLocal The new task to be appended
     * @return The indication of the result of the addTask operation
     */
    public static void addTask(Task newTaskLocal) {
        try {
            TaskList.taskArrayList.add(newTaskLocal);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * @return The number of tasks in the list
     */
    public static int getSize() {
        return taskArrayList.size();
    }

    /**
     * Gets the element lf the array list with certain index.
     *
     * @param indexLocal The index of element/task that the get method wants to get
     * @return The index-the element(0-based)
     */
    public static Task getElement(int indexLocal) throws DukeException {
        try {
            Task targetTask = taskArrayList.get(indexLocal);
            return targetTask;
        } catch (Exception e) {
            throw new TaskListDukeException();
        }
    }

    /**
     * Remove indexLocal'th element of the tasklist.
     * @param indexLocal The index of task to be removed
     * @throws DukeException Indicate the out of range exception
     */
    public static void removeElement(int indexLocal) throws DukeException {
        try {
            taskArrayList.remove(indexLocal);
        } catch (Exception e) {
            throw new TaskListDukeException();
        }
    }

    public static void save() throws DukeException {
        taskFileDao.writeTasks(taskArrayList);
    }

}
