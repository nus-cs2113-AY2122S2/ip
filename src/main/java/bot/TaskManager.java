package bot;

import tasks.Task;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * TaskManager is a wrapper around the list of tasks kept by the bot.
 */
public class TaskManager {
    /**
     * The list of tasks stored by the bot.
     */
    private final List<Task> tasks;

    /**
     * Creates a new Sailfish task manager.
     *
     * @throws IOException If error reading database.
     */
    protected TaskManager() throws IOException {
        this.tasks = new ArrayList<>();
        // Load the database.
        StorageManager.loadData(this.tasks);
    }

    /**
     * Save the current task list into a database.
     *
     * @throws IOException If error writing into database.
     */
    protected void saveData() throws IOException {
        StorageManager.saveData(this.tasks);
    }

    /**
     * Helper method to get a task at a particular index from a parsed command.
     *
     * @param commandParser Command object containing parsed information.
     * @return Task object.
     * @throws NumberFormatException For invalid index.
     */
    protected Task getTask(CommandParser commandParser) throws NumberFormatException {
        int index = Integer.parseInt(commandParser.getDesc()) - 1;
        if (index < 0 || index >= this.tasks.size()) {
            throw new NumberFormatException("Please specify a integer for the index and range!");
        }
        return this.getTask(index);
    }

    /**
     * Get a task at a particular index. User must ensure that index is valid. Otherwise,
     * they must be ready to catch a possible IndexOutOfBoundsException.
     *
     * @param index Index of the task in the list.
     * @return Task object.
     * @throws IndexOutOfBoundsException If index is out of bounds.
     */
    protected Task getTask(int index) throws IndexOutOfBoundsException {
        return this.tasks.get(index);
    }

    /**
     * Add a new task into the list.
     *
     * @param task Task object to add.
     */
    protected void addTask(Task task) {
        this.tasks.add(task);
    }

    /**
     * Remove a task from the list.
     *
     * @param task Task object to remove.
     */
    protected void deleteTask(Task task) {
        this.tasks.remove(task);
    }

    /**
     * Find tasks using keywords.
     *
     * @param input Keyword string from user.
     * @return List of tasks that match keywords.
     */
    protected List<Task> findTasks(String input) {
        // Split the keywords by space.
        String[] keywords = input.toLowerCase().split(" ");

        List<Task> matched = new ArrayList<>();
        for (Task task: this.tasks) {
            for (String word: keywords) {
                if (task.taskDescription.toLowerCase().contains(word)) {
                    matched.add(task);
                }
            }
        }
        return matched;
    }

    /**
     * Get the number of current tasks stored by the bot.
     *
     * @return Number of tasks.
     */
    protected int getNumTasks() {
        return this.tasks.size();
    }
}
