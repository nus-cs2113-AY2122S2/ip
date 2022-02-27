package alexis.commands;

import alexis.exceptions.MissingDeadlineTimingException;
import alexis.storage.Storage;
import alexis.taskList.TaskList;
import alexis.task.Deadline;

import java.time.DateTimeException;
import java.time.LocalDate;

import static alexis.parser.Parser.parseDate;
import static alexis.parser.Parser.parseTiming;

public class DeadlineCommand extends Command{

    protected String description;
    protected String timing;
    protected LocalDate date;

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
