package tasklist;

import task.Task;

import java.util.ArrayList;

public class TaskList {
    public ArrayList<Task> taskArrayList;

    /**
     * Creates a TaskList object based on the ArrayList of Tasks provided.
     * This ArrayList should be created with the data stored in the text document
     * used for storage.
     * @param tasks
     */
    public TaskList(ArrayList<Task> tasks) {
        this.taskArrayList = tasks;
    }

    /**
     * This constructor is used should there be no data available/
     * the file indicated is not found. A new empty ArrayList storing Tasks will be created.
     */
    public TaskList() {
        this.taskArrayList = new ArrayList<>();
    }

    /**
     * @return the size of the taskArrayList attribute of this taskList.
     */
    public int getSize() {
        return taskArrayList.size();
    }

    /**
     * @param index the index of the task in the taskArrayList.
     * @return the Task object that is of that index in the taskArrayList.
     */
    public Task getTaskViaIndex(int index) {
        return taskArrayList.get(index);
    }

    /**
     * This method adds a specific task to the taskArrayList.
     * @param task the Task to be added.
     */
    public void addTaskToList(Task task) {
        taskArrayList.add(task);
    }

    /**
     * This method removes a specific task from the taskArrayList.
     * @param task the Task to be removed.
     */
    public void removeTaskFromList(Task task) {
        taskArrayList.remove((task));
    }

    /**
     * This method is used to filter tasks based on a search term.
     * @param detail The search term used to compare with all tasks.
     * @return An ArrayList of tasks that contain the search term.
     */
    public ArrayList<Task> findFromList(String detail) {
        ArrayList<Task> filteredTasks = new ArrayList<>();
        for (Task task:taskArrayList) {
            if (task.getDescription().contains(detail)) {
                filteredTasks.add(task);
            }
        }
        return filteredTasks;
    }
}
