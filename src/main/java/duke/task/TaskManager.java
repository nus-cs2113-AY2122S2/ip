package duke.task;

import duke.exception.DukeException;
import duke.ui.Ui;

import java.util.ArrayList;

public class TaskManager {
    private ArrayList<Task> tasks = new ArrayList<>();
    private int numOfTasks = 0;

    /**
     * Class to manage the tasks
     */
    public TaskManager(){
    }

    /**
     *
     * @return Number of tasks
     */
    public int getNumOfTasks() {
        return this.numOfTasks;
    }

    /**
     * Adds a task into the task list.
     *
     * @param type Type of command (todo, event or deadline)
     * @param taskName Task name
     * @param addInfo Any additional info (date or time)
     */
    public void addTask(String type, String taskName, String addInfo) {
        Task toBeAdded;

        switch (type) {
        case "todo":
            toBeAdded = new ToDo(taskName);
            break;
        case "deadline":
            toBeAdded = new Deadline(taskName, addInfo);
            break;
        case "event":
            toBeAdded = new Event(taskName, addInfo);
            break;
        default:
            toBeAdded = new Task(taskName);
            break;
        }

        this.numOfTasks++;
        this.tasks.add(toBeAdded);

        Ui.printWithDivider("Got it. I've added this task:\n\t" + toBeAdded.toString()
                + String.format("\nNow you have %d task%s in the list.", this.numOfTasks,
                this.numOfTasks > 1 ? "s" : ""));

    }


    /**
     * Deletes a task from the task list.
     *
     * @param taskNumber Task number of the task to be deleted (as seen from list)
     * @throws DukeException Throws exception if task number invalid
     */
    public void deleteTask(int taskNumber) throws DukeException {
        try {
            Task task = tasks.get(taskNumber - 1);
            tasks.remove(task);
            this.numOfTasks--;

            Ui.printWithDivider("Noted. I've removed this task:\n\t " + task.toString()
                    + String.format("\nNow you have %d task%s in the list.", this.numOfTasks,
                    this.numOfTasks > 1 ? "s" : ""));
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Index out of bounds!");
        }
    }

    /**
     * Replace existing task list with a new task list.
     *
     * @param tasks Replacing task list.
     */
    public void setTasks(ArrayList<Task> tasks) {
        this.tasks = tasks;
        this.numOfTasks = tasks.size();
    }

    /**
     * Marks task as complete.
     *
     * @param taskNumber Task number of the task to be deleted (as seen from list)
     * @throws DukeException Throws exception if task number invalid
     */
    public void markCompleted  (int taskNumber) throws DukeException {
        try {
            Task task = tasks.get(taskNumber - 1);
            task.setCompleted(true);
            Ui.printWithDivider(task.toString());
        }
        catch (IndexOutOfBoundsException e) {
            throw new DukeException("Index out of bounds!");
        }
    }

    /**
     * Marks task as incomplete.
     *
     * @param taskNumber Task number of the task to be deleted (as seen from list)
     * @throws DukeException Throws exception if task number invalid
     */
    public void unmarkCompleted (int taskNumber) throws DukeException {
        try {
            Task task = tasks.get(taskNumber - 1);
            task.setCompleted(false);
            Ui.printWithDivider(task.toString());
        }
        catch (IndexOutOfBoundsException e) {
            throw new DukeException("Index out of bounds!");
        }
    }

    /**
     * Find tasks containing keyword in its name
     * @param keyword Keyword in task name
     * @return TaskManager containing all the related tasks
     */
    public TaskManager findTask (String keyword) {
        TaskManager relatedTaskManager = new TaskManager();
        ArrayList<Task> relatedTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getName().contains(keyword)) {
                relatedTasks.add(task);
            }
        }
        relatedTaskManager.setTasks(relatedTasks);
        return relatedTaskManager;
    }

    /**
     * Return the task list (number listed)
     * @return Task list
     */
    @Override
    public String toString() {
        String output = "";
        int number = 1 ;
        for (Task item : this.tasks) {
            output += String.format("%d. %s", number, item.toString());
            if (number != numOfTasks) {
                output += "\n";
            }
            number ++;
        }
        return output;
    }
}
