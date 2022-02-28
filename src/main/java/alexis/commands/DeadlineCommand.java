package alexis.commands;

import alexis.exceptions.MissingDeadlineTimingException;
import alexis.storage.Storage;
import alexis.taskList.TaskList;
import alexis.task.Deadline;

import java.time.DateTimeException;
import java.time.LocalDate;

import static alexis.parser.Parser.parseDate;
import static alexis.parser.Parser.parseTiming;

/**
 * Creates a new deadline task
 */
public class DeadlineCommand extends Command{

    protected String description;
    protected String timing;
    protected LocalDate date;

    /**
     * Sets up the Deadline Command, parsing the user's input to extract the description, timing and date of the task.
     *
     * @param fullDescription fullDescription of user's input excluding the "deadline" part
     * @throws MissingDeadlineTimingException If deadline timing is missing
     * @throws DateTimeException If date is not instance of LocalDate
     */
    public DeadlineCommand(String fullDescription) throws MissingDeadlineTimingException, DateTimeException {
        if (!fullDescription.contains(" /by ")) {
            throw new MissingDeadlineTimingException();
        } else {
            String[] deadlineDescriptionSplitArr = fullDescription.split(" /by ");
            description = deadlineDescriptionSplitArr[0];
            date = parseDate(deadlineDescriptionSplitArr[1]);
            timing = parseTiming(date);
        }
    }

    /**
     * Adds deadline task to Alexis.tasks
     *
     * @param taskList Alexis.tasks
     * @param storage Alexis.ui
     */
    @Override
    public void execute(TaskList taskList, Storage storage) {
        taskList.add(new Deadline(description, timing));
        int listSize = taskList.getListSize();
        int taskIndex = listSize - 1;
        taskList.getTask(taskIndex).addDate(date);
        taskList.getTask(taskIndex).addNewTaskMessage();
        storage.save(listSize, taskList.taskArrayList);
    }

}
