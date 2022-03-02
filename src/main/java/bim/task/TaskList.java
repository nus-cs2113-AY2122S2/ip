package bim.task;

import java.util.ArrayList;

/**
 * Contains all the tasks the user have created.
 */
public class TaskList {
    private final ArrayList<Task> tasks;
    private static final String LIST_DOT = ".";
    private static final String NEWLINE = "\n";
    private static final String LINE_INDENT = "\t";

    private static final String EMPTY_LIST = "404 Not Found";

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void addTask(Task newTask) {
        tasks.add(newTask);
    }

    public void deleteTask(int index) {
        tasks.remove(index);
    }

    public void markTask(int index) {
        tasks.get(index).setAsDone();
    }

    public void unmarkTask(int index) {
        tasks.get(index).setAsNotDone();
    }

    public Task getTask(int index) {
        return tasks.get(index);
    }

    public int getSize() {
        return tasks.size();
    }

    /**
     * Returns a string with the search result.
     * If no tasks were found, an empty string will be returned.
     * Else, a formatted string listing all tasks will be returned.
     *
     * @param keyword The string to be searched for, case-insensitive.
     * @return string containing search result.
     */
    public String findTask(String keyword) {
        int count = 1;
        String result = "";
        for(int i = 0; i < tasks.size(); i++) {
            Task currentTask = tasks.get(i);
            String currentTaskDescription = currentTask.getDescription().toLowerCase();
            if (currentTaskDescription.contains(keyword)) {
                result += LINE_INDENT + count + LIST_DOT + currentTask;
                if (i != tasks.size() - 1) {
                    result += NEWLINE;
                }
                count++;
            }
        }
        return result;
    }

    /**
     * Returns a string that lists all tasks in the arraylist <br>
     * Format: INDEX + task.toString()
     *
     * @return A String representation of all tasks in the TaskList
     */
    @Override
    public String toString() {
        if (tasks.isEmpty()) {
            return EMPTY_LIST;
        }

        String output = "";
        for (int i = 0; i < tasks.size(); i++) {
            output += LINE_INDENT + (i + 1) + LIST_DOT + tasks.get(i);
            if (i != tasks.size() - 1) {
                output += NEWLINE;
            }
        }
        return output;
    }
}
