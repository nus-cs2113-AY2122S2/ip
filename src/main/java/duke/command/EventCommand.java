package duke.command;

import duke.data.task.Event;

import java.util.ArrayList;

import static duke.common.Strings.MESSAGE_EVENT_ADDED;
import static duke.common.Strings.MESSAGE_IMPOSSIBLE;

public class EventCommand extends Command {
    private final Event newEvent;

    public EventCommand(String taskDescription, String time) {
        super();
        newEvent = new Event(taskDescription, time);
    }

    public ArrayList<String> execute() {
        if (taskList == null) {
            commandFeedback.add(MESSAGE_IMPOSSIBLE);
            commandFeedback.add(null);
            return commandFeedback;
        }
        taskList.addTask(newEvent);
        commandFeedback.add(MESSAGE_EVENT_ADDED);
        commandFeedback.add(newEvent.toString());
        return commandFeedback;
    }
}
