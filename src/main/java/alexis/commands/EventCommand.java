package alexis.commands;

import alexis.exceptions.MissingEventTimingException;
import alexis.storage.Storage;
import alexis.taskList.TaskList;
import alexis.task.Event;

import java.time.DateTimeException;
import java.time.LocalDate;

import static alexis.parser.Parser.parseDate;
import static alexis.parser.Parser.parseTiming;

/**
 * Adds a new event command
 */
public class EventCommand extends Command{

    protected String description;
    protected String timing;
    protected LocalDate date;

    /**
     * Sets up the Event Command, parsing the user's input to extract the description, timing and date of the task.
     *
     * @param fullDescription fullDescription of user's input excluding the "event" part
     * @throws MissingEventTimingException If event timing is missing
     * @throws DateTimeException If date is not instance of LocalDate
     */
    public EventCommand(String fullDescription) throws MissingEventTimingException, DateTimeException {
        if (!fullDescription.contains(" /at ")) {
            throw new MissingEventTimingException();
        } else {
            String[] eventDescriptionSplitArr = fullDescription.split(" /at ");
            description = eventDescriptionSplitArr[0];
            date = parseDate(eventDescriptionSplitArr[1]);
            timing = parseTiming(date);
        }
    }

    /**
     * Adds event task to Alexis.tasks
     *
     * @param taskList Alexis.tasks
     * @param storage Alexis.ui
     */
    @Override
    public void execute(TaskList taskList, Storage storage) {
        taskList.add(new Event(description, timing));
        int listSize = taskList.getListSize();
        int taskIndex = listSize - 1;
        taskList.getTask(taskIndex).addDate(date);
        taskList.getTask(taskIndex).addNewTaskMessage();
        storage.save(listSize, taskList.taskArrayList);
    }
}
