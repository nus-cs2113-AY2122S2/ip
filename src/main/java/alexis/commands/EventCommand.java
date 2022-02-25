package alexis.commands;

import alexis.exceptions.MissingEventTimingException;
import alexis.storage.Storage;
import alexis.taskList.TaskList;
import alexis.task.Event;

public class EventCommand extends Command{

    protected String description;
    protected String timing;

    public EventCommand(String fullDescription) throws MissingEventTimingException {
        if (!fullDescription.contains(" /at ")) {
            throw new MissingEventTimingException();
        }
        String[] eventDescriptionSplitArr = fullDescription.split(" /at ");
        description = eventDescriptionSplitArr[0];
        timing = eventDescriptionSplitArr[1];
    }

    @Override
    public void execute(TaskList taskList, Storage storage) {
        taskList.add(new Event(description, timing));
        int listSize = taskList.getListSize();
        int taskIndex = listSize - 1;
        taskList.getTask(taskIndex).addNewTaskMessage();
        storage.save(listSize, taskList.taskArrayList);
    }
}
