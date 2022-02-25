package alexis.commands;

import alexis.exceptions.MissingDeadlineTimingException;
import alexis.storage.Storage;
import alexis.taskList.TaskList;
import alexis.task.Deadline;

public class DeadlineCommand extends Command{

    protected String description;
    protected String timing;

    public DeadlineCommand(String fullDescription) throws MissingDeadlineTimingException {
        if (!fullDescription.contains(" /by ")) {
            throw new MissingDeadlineTimingException();
        }
        String[] deadlineDescriptionSplitArr = fullDescription.split(" /by ");
        description = deadlineDescriptionSplitArr[0];
        timing = deadlineDescriptionSplitArr[1];

    }

    @Override
    public void execute(TaskList taskList, Storage storage) {
        taskList.add(new Deadline(description, timing));
        int listSize = taskList.getListSize();
        int taskIndex = listSize - 1;
        taskList.getTask(taskIndex).addNewTaskMessage();
        storage.save(listSize, taskList.taskArrayList);
    }

}
