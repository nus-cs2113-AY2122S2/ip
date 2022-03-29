package Duke;

import java.util.ArrayList;

public class TaskList {
    /**
     * Add a task to the list of userLists
     *
     * @param task Task class to be added
     * @param userLists ArrayList of user userLists
     */
    public static void addTask (Task task, ArrayList<Task> userLists) {
        userLists.add(task);
        String userInput = Wrapper.wrapMessage(
                String.format("Got it. I've added this task:\n" +
                                "  %s" +
                                " Now you have %d tasks in the list\n",
                        task.toString(), userLists.size()));
        System.out.println(userInput);
    }

    /**
     * Deletes a task from the list of userLists
     *
     * @param userLists ArrayList of user userLists
     * @param index Index in the ArrayList to be deleted
     * @throws DukeExceptionMarkBounds when index is out of the ArrayList
     */
    public static void deleteTask (ArrayList<Task> userLists, int index) throws
            DukeExceptionMarkBounds {
        try {
            Task removedTask = userLists.remove(index);
            String userInput = Wrapper.wrapMessage(
                    String.format("Noted. I've removed this task:\n" +
                                    "  %s" +
                                    " Now you have %d userLists in the list\n",
                            removedTask, userLists.size()));
            System.out.println(userInput);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeExceptionMarkBounds();
        }
    }

    /**
     * Change userLists into a printable list
     * of current userLists the user have
     *
     * @param userLists ArrayList of user tasks
     * @return String of all the userLists
     */
    public static String listTask(ArrayList<Task> userLists) {
        String allTasks = "";
        for (int i = 1; i <= userLists.size(); i++) {
            allTasks = allTasks + " " + i + "." + userLists.get(i - 1).toString();
        }
        return allTasks;
    }

    /**
     * Find tasks related to the search keyword
     *
     * Adds all these tasks into a string with all
     * tasks that fit the search keyword
     *
     * @param userLists ArrayList of user userLists
     * @param searchStr Keyword to search
     * @return String of the tasks which are found
     */
    public static String findTask(ArrayList<Task> userLists, String searchStr) {
        String foundTask = "";
        int i = 0;
        for (Task task : userLists) {
            if (task.toString().contains(searchStr)) {
                i++;
                foundTask = foundTask + " " + i + "." + task.toString();
            }
        }
        return foundTask;
    }
}
