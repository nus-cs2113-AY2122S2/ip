package duke.command;

import duke.data.task.Deadline;

import java.util.ArrayList;

import static duke.common.Strings.*;

/**
 * Adds a new deadline task.
 */
public class DeadlineCommand extends Command {
    private final Deadline newDeadline;

    public DeadlineCommand(String taskDescription, String dueBy) {
        super();
        newDeadline = new Deadline(taskDescription, dueBy);
    }

    @Override
    public ArrayList<String> execute() {
        if (taskList == null) {
            commandFeedback.add(MESSAGE_IMPOSSIBLE);
            commandFeedback.add(null);
            return commandFeedback;
        }
        taskList.addTask(newDeadline);
        commandFeedback.add(MESSAGE_DEADLINE_ADDED);
        commandFeedback.add(newDeadline.toString());
        return commandFeedback;
    }
}
