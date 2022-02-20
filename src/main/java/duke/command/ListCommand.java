package duke.command;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import static duke.common.Strings.*;
import static duke.common.Strings.MESSAGE_ITEMIZED_TASK;

/**
 * Lists tasks in the task list.
 */
public class ListCommand extends Command {
    protected LocalDate queryDate;

    public ListCommand() {
        this(null);
    }

    public ListCommand(LocalDate queryDate) {
        super();
        this.queryDate = queryDate;
    }

    @Override
    public ArrayList<String> execute() {
        if (taskList == null) {
            commandFeedback.add(MESSAGE_IMPOSSIBLE);
            commandFeedback.add(null);
            return commandFeedback;
        }
        int numTasks = taskList.getNumMatchingTasks(queryDate);
        if (queryDate == null) {
            commandFeedback.add(MESSAGE_FILTERABLE_EXPLANATION);
        } else {
            commandFeedback.add(String.format(MESSAGE_SHOW_DATE_FILTER,
                    queryDate.format(DateTimeFormatter.ofPattern(FORMAT_DATE_DISPLAY))));
        }
        if (numTasks == 0) {
            commandFeedback.add(MESSAGE_NO_TASKS);
        } else {
            commandFeedback.add(MESSAGE_SHOW_TASKS);
            for (int i = 0; i < taskList.getNumTasks(); i++) {
                if (queryDate == null || taskList.getTask(i).isMatchingDate(queryDate)) {
                    commandFeedback.add(String.format(MESSAGE_ITEMIZED_TASK, i + 1, taskList.getTask(i)));
                }
            }
        }
        return commandFeedback;
    }
}
