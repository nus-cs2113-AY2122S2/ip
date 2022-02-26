package alexis.commands;

import alexis.exceptions.MissingEventTimingException;
import alexis.storage.Storage;
import alexis.taskList.TaskList;
import alexis.task.Event;

import java.time.DateTimeException;
import java.time.LocalDate;

import static alexis.parser.Parser.parseDate;
import static alexis.parser.Parser.parseTiming;

public class EventCommand extends Command{

    protected String description;
    protected String timing;
    protected LocalDate date;

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
