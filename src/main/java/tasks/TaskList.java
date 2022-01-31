package tasks;

import java.util.ArrayList;

/**
<<<<<<< HEAD
 * In charge of the global task list
 */
public class TaskList {

    private static ArrayList<Task> taskArrayList =  new ArrayList<>();

    /**
     * Adds tasks
=======
 * In charge of the global task list.
 */
public class TaskList {
    private static ArrayList<Task> taskArrayList =  new ArrayList<>();

    /**
     * Adds tasks.
     *
>>>>>>> 9dada07e69461ecaf25973bf549e8fd670b8849f
     * @param newTaskLocal The new task to be appended
     * @return The indication of the result of the addTask operation
     */
    public static void addTask (Task newTaskLocal) {
        try {
            TaskList.taskArrayList.add(newTaskLocal);
        } catch (Exception e) {
            System.out.println(e);
        }
<<<<<<< HEAD

=======
>>>>>>> 9dada07e69461ecaf25973bf549e8fd670b8849f
    }

    /**
     * @return The number of tasks in the list
     */
    public static int getSize() {
        return taskArrayList.size();
    }

    /**
<<<<<<< HEAD
=======
     * Gets the element lf the array list with certain index.
     *
>>>>>>> 9dada07e69461ecaf25973bf549e8fd670b8849f
     * @param indexLocal The index of element/task that the get method wants to get
     * @return The index-the element(0-based)
     */
    public static Task getElement(int indexLocal){
        return taskArrayList.get(indexLocal);
    }

<<<<<<< HEAD




=======
>>>>>>> 9dada07e69461ecaf25973bf549e8fd670b8849f
}
