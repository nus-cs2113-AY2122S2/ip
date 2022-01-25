package tasks;

import java.util.ArrayList;

/**
 * In charge of the global task list
 */
public class TaskList {

    private static ArrayList<Task> taskArrayList =  new ArrayList<>();

    /**
     * Adds tasks
     * @param newTaskLocal The new task to be appended
     * @return The indication of the result of the addTask operation
     */
    public static void addTask (Task newTaskLocal) {
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
     * @param indexLocal The index of element/task that the get method wants to get
     * @return The index-the element(0-based)
     */
    public static Task getElement(int indexLocal){
        return taskArrayList.get(indexLocal);
    }





}
