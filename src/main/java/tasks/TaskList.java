package tasks;

import java.util.ArrayList;

import exceptions.DukeException;
import exceptions.TaskListDukeException;
import storage.TaskFileStorage;



/**
 * In charge of the global task list.
 */
public class TaskList {
    private static final String TASK_DAO_BASE_PATH = "data";
    private static final String TASK_DAO_FILE_NAME = "tasks.txt";
    private static ArrayList<Task> taskArrayList;
    private static TaskFileStorage taskFileDao;


    /**
     * Constructor of TaskList
     *
     * @throws DukeException Duke Exception
     */
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
     * @throws DukeException Duke Exception
     */
    public static void addTask(Task newTaskLocal) throws DukeException {
        try {
            TaskList.taskArrayList.add(newTaskLocal);
        } catch (Exception e) {
            throw new TaskListDukeException();
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
     *
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
