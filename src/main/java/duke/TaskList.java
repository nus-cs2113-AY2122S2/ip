package duke;

import duke.exception.AdditionalException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Represents a list of tasks and has operations that add and delete tasks to the list.
 */
public class TaskList {

    private ArrayList<Task> listOfTasks;

    public TaskList() {
        listOfTasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> listOfTasks) {
        this.listOfTasks = listOfTasks;
    }

    /**
     * This is the getList method that returns this TaskList's list of tasks.
     * If the list is empty, an AdditionalException will be raised.
     *
     * @return This TaskList's list of tasks.
     * @throws AdditionalException if list is empty.
     * @see AdditionalException
     */
    public ArrayList<Task> getList() throws AdditionalException {
        if (listOfTasks.isEmpty()) {
            throw new AdditionalException("YAY!!! you do not have any tasks at the moment hehe");
        }
        return listOfTasks;
    }

    /**
     * This is the getTask method that returns the task stored a particular index.
     *
     * @param index the index of the task to be returned from the list of tasks.
     * @return The task stored at a particular index.
     */
    public Task getTask(int index) {
        return listOfTasks.get(index);
    }

    /**
     * This is the getSize method that returns the number of tasks stored in the list of tasks.
     *
     * @return The number of tasks stored in the list of tasks.
     */
    public int getSize() {
        return listOfTasks.size();
    }

    /**
     * This is deleteTask method that deletes the task stored at a particular index.
     *
     * @param indexToDelete The index of the task to be deleted from the list of tasks.
     */
    public void deleteTask(int indexToDelete) {
        listOfTasks.remove(indexToDelete);
    }

    /**
     * This is the addTask method that adds a task at the end of the list of tasks.
     *
     * @param task The task to be added to the list of tasks.
     */
    public void addTask(Task task) {
        listOfTasks.add(task);
    }

    /**
     * This is the getListOfSameDates method that returns a list of tasks with the same date amongst the tasks
     * in the list of tasks.
     *
     * @param date The date that is to be compared with the date of tasks.
     * @return The list of tasks with the same date.
     */
    public ArrayList<Task> getListOfSameDates(LocalDate date) {
        int count = 0;
        ArrayList<Task> listOfTasksWithSameDate = new ArrayList<>();
        for (int i = 0; i < listOfTasks.size(); i++) {
            addToList(listOfTasksWithSameDate, date, i);
        }
        return listOfTasksWithSameDate;
    }

    /**
     * This is the addToList method that takes in the list of tasks with same date and passes it to the addIfSameDate
     * method when there is a task, with the same date of the user input, in the list of tasks.
     *
     * @param tasksWithSameDate The list of tasks with the same date.
     * @param date The date that is to be compared with the date of tasks.
     * @param index The index of the task in the list of tasks that is to be compared with.
     */
    private void addToList(ArrayList<Task> tasksWithSameDate, LocalDate date, int index) {
        Task task = listOfTasks.get(index);
        LocalDate dateOfTask;
        if (task instanceof Deadline) {
            Deadline deadline = (Deadline) task;
            dateOfTask = deadline.getDate();
            addIfSameDate(tasksWithSameDate, date, dateOfTask, deadline);
        } else if (task instanceof Event) {
            Event event = (Event) task;
            dateOfTask = event.getDate();
            addIfSameDate(tasksWithSameDate, date, dateOfTask, event);
        } else {
            return;
        }

    }

    /**
     * This is the addIfSameDate method that takes in the list of tasks with same date and adds the task to the
     * list of tasks with same date when the date matches.
     *
     * @param tasksWithSameDate The list of tasks with the same date.
     * @param date The date that is to be compared with the date of the tasks.
     * @param dateOfTask The date of the task that is being compared with.
     * @param task The task that is being compared with, and added if it has the same date.
     */
    private void addIfSameDate(ArrayList<Task> tasksWithSameDate, LocalDate date, LocalDate dateOfTask, Task task) {
        if (date.equals(dateOfTask)) {
            tasksWithSameDate.add(task);
        }
    }
}
