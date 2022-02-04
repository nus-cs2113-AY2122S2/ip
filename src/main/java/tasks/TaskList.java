package tasks;

import dao.TaskFileDAO;
import exceptions.DukeException;

import java.util.ArrayList;

/**
 * In charge of the global task list.
 */
public class TaskList {
    private static ArrayList<Task> taskArrayList;
    private static final String TASK_DAO_BASE_PATH = "data";
    private static final String TASK_DAO_FILE_NAME = "tasks.txt";
    private static TaskFileDAO taskFileDao;
    public TaskList() throws DukeException {
        try {
            taskFileDao = new TaskFileDAO(TASK_DAO_BASE_PATH,TASK_DAO_FILE_NAME);
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
    public static Task getElement(int indexLocal) {
        return taskArrayList.get(indexLocal);
    }

    public static void save() throws DukeException{
        taskFileDao.writeTasks(taskArrayList);
    }

}
