package duke.command;

import duke.TaskList;
import duke.Ui;
import duke.exception.AdditionalException;
import duke.storage.Storage;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Represents the date command which is to be executed.
 */
public class DateCommand extends Command {

    private static final int CORRECT_NUMBER_OF_WORDS = 2;
    private String fullCommand;

    public DateCommand(String fullCommand) {
        this.fullCommand = fullCommand;
    }

    /**
     * Runs when there is a date command by searching through the list of tasks and print the tasks
     * that match the date which the user inputted.
     *
     * @param tasks The TaskList object that contains the list of tasks.
     * @param ui The user interface object that allows for printing of the confirmation message.
     * @param storage The storage object which allows for the saving of the tasks to the file.
     * @throws AdditionalException If the date is in an incorrect format.
     * @see AdditionalException
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws AdditionalException {
        LocalDate date = getDate();
        ArrayList<Task> listOfTasks = getListOfSameDates(tasks, date);
        ui.showList(listOfTasks);
    }

    @Override
    public void executeFromFile(ArrayList<Task> listOfTasks) throws AdditionalException {
    }

    /**
     * Rets the date the user is requesting to search for.
     *
     * @return The date that to be searched for.
     * @throws AdditionalException If the date is in an incorrect format.
     * @see AdditionalException
     */
    private LocalDate getDate() throws AdditionalException {
        String[] words = fullCommand.split(" ");
        if (words.length != CORRECT_NUMBER_OF_WORDS) {
            throw new AdditionalException("Please input the date in the correct format and only the date");
        }
        LocalDate date = LocalDate.parse(words[1]);
        return date;
    }

    /**
     * Returns a list of tasks with the same date amongst the tasks in the list of tasks.
     *
     * @param date The date that is to be compared with the date of tasks.
     * @return The list of tasks with the same date.
     */
    private ArrayList<Task> getListOfSameDates(TaskList tasks, LocalDate date) {
        ArrayList<Task> tasksWithSameDate = new ArrayList<>();
        for (int i = 0; i < tasks.getSize(); i++) {
            addToList(tasksWithSameDate, date, tasks.getTask(i));
        }
        return tasksWithSameDate;
    }

    /**
     * Determines if the task is a deadline task or an event task and adds the task to the list of tasks accordingly.
     *
     * @param tasksWithSameDate The list of tasks with the same date.
     * @param date The date that is to be compared with the date of tasks.
     * @param task The task to be compared with.
     */
    private void addToList(ArrayList<Task> tasksWithSameDate, LocalDate date, Task task) {
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
     * Adds the task to the list of tasks with the same date when the date matches.
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
