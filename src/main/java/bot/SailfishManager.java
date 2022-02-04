package bot;

import tasks.Task;

import java.util.ArrayList;
import java.util.List;

public class SailfishManager {
    /**
     * The list of tasks stored by the bot.
     */
    private final List<Task> tasks;

    protected SailfishManager() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Helper method to get a task at a particular index from a parsed command.
     *
     * @param command Command object containing parsed information.
     * @return Task object.
     * @throws NumberFormatException For invalid index.
     */
    protected Task getTask(Command command) throws NumberFormatException {
        int index = Integer.parseInt(command.getDesc()) - 1;
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
     * Get the number of current tasks stored by the bot.
     *
     * @return Number of tasks.
     */
    protected int getNumTasks() {
        return this.tasks.size();
    }
}
