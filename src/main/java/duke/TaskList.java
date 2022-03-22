package duke;

import duke.task.Task;

import java.util.ArrayList;

/**
 * Represents a container containing the different Tasks that
 * have been added by the user.
 */
public class TaskList {
    public static final String EXCEPTION_INVALID_KEYWORD = "No item containing this term is found. " +
            "Please enter another search term.";
    ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> taskList) {
        this.tasks = taskList;
    }

    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    public Task getTask(int index) {
        return this.tasks.get(index);
    }

    public int getSize() {
        return this.tasks.size();
    }

    public void addTask(Task task) {
        this.tasks.add(task);
    }

    public Task removeTask(int taskIndex) {
        return this.tasks.remove(taskIndex);
    }

    /** Returns a string containing all tasks in a format for Ui */
    public String getAllTasksUi() {
        String output = "";
        for (int i = 0; i < this.getSize(); i++) {
            output += String.format("%d. %s", i + 1, this.getTask(i));
            output += "\n";
        }
        /** Remove last line break */
        output = output.substring(0, output.length() - 1);
        return output;
    }

    /** Returns a string of tasks that have the search term in it in a format for Ui */
    public String getFindTasksUi(String keyword) throws DukeException {
        String output = "";
        int containsCounter = 0;
        for (int i = 0; i < this.getSize(); i++) {
            if (this.getTask(i).contains(keyword)) {
                containsCounter += 1;
                output += String.format("%d. %s", containsCounter, this.getTask(i));
                output += "\n";
            }
        }

        /** Throw Exception if search term not found in any task in the tasklist */
        if (output.length() == 0) {
            throw new DukeException(EXCEPTION_INVALID_KEYWORD);
        }

        /** Remove last line break */
        output = output.substring(0, output.length() - 1);
        return output;
    }

    /** Returns a string on how many tasks are remaining in the current list */
    public String getNumRemainingTasksUi() {
        return String.format("Now you have %d tasks in the list.", this.getSize());
    }
}
