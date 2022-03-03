package duke.parser;

import duke.DukeException;
import duke.tasklist.task.Deadline;
import duke.tasklist.task.Event;
import duke.tasklist.task.Task;
import duke.tasklist.task.Todo;

/**
 * Parses tasks provided by user input or the data file.
 */
public class TaskString {

    /**
     * Returns index of task in the task list using number given by user.
     *
     * @param line String of original user input.
     * @return taskInd Index of task.
     */
    public static int parseTaskNum(String line) {
        String stringOfTaskNum = line.split(" ", 0)[1];
        int taskInd = Integer.parseInt(stringOfTaskNum) - 1;
        return taskInd;
    }

    private static String getTimeSplitArgument(TaskType taskType) throws DukeException {
        switch (taskType) {
        case DEADLINE:
            return " /by ";
        // Fallthrough
        case EVENT:
            return " /at ";
        // Fallthrough
        default:
            throw new DukeException("Trying to parse time of Todo task");
        }
    }

    private static Task parseDeadlineOrEvent(TaskType taskType, String description) throws DukeException {
        String time;
        try {
            String regexArg = getTimeSplitArgument(taskType);
            String[] breakdown = description.split(regexArg, 2);
            description = breakdown[0];
            time = breakdown[1];
        } catch (DukeException e) {
            throw new DukeException(e.getMessage());
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("You need to provide a time for your deadline or event");
        }

        Task task;
        if (taskType.equals(TaskType.DEADLINE)) {
            task = new Deadline(description, time);
        }
        else if (taskType.equals(TaskType.EVENT)) {
            task = new Event(description, time);
        } else {
            throw new DukeException("Trying to parse time of Todo task");
        }
        return task;
    }

    private static void missingDescriptionCheck(String description) throws DukeException {
        if (description.equals("")) {
            throw new DukeException("Please provide a task description!");
        }
    }

    /**
     * Returns Task object after parsing user input.
     *
     * @param type String indicating type of task from original user input.
     * @param description String containing task description and potential time information.
     * @return task Task parsed from user input.
     * @throws DukeException If user input contains invalid task type.
     */
    public static Task parseTask(String type, String description) throws DukeException {
        Task task;
        switch (type) {
        case "todo":
            missingDescriptionCheck(description);
            task = new Todo(description);
            break;
        case "deadline":
            missingDescriptionCheck(description);
            task = parseDeadlineOrEvent(TaskType.DEADLINE, description);
            break;
        case "event":
            missingDescriptionCheck(description);
            task = parseDeadlineOrEvent(TaskType.EVENT, description);
            break;
        default:
            throw new DukeException("I don't understand what you want to do, big sad :(");
        }

        return task;
    }

    private static Boolean decodeStatus(Integer taskStatusNum) throws DukeException {
        switch (taskStatusNum) {
        case 1:
            return true;
            // Fallthrough
        case 0:
            return false;
            // Fallthrough
        default:
            throw new DukeException("Data file contains invalid status :(");
        }
    }

    private static Task decodeTaskParsing(String[] details, String description,
                                         Boolean status) throws DukeException {
        Task t;
        switch (details[0]) {
        case "T":
            t = new Todo(description);
            break;
        case "D":
            String by = details[3];
            t = new Deadline(description, by);
            break;
        case "E":
            String at = details[3];
            t = new Event(description, at);
            break;
        default:
            throw new DukeException("Invalid task type in data file :(");
        }

        t.setDone(status);
        return t;
    }

    /**
     * Decodes a task stored in the data file.
     *
     * @param line String of original file input.
     * @return task Task decoded from file input.
     * @throws DukeException If not enough fields are provided in the data file.
     */
    public static Task decodeTask(String line) throws DukeException {
        String[] taskDetails = line.split(" \\| ");

        try {
            Integer taskStatusNum = Integer.parseInt(taskDetails[1]);
            Boolean taskStatus = decodeStatus(taskStatusNum);
            String taskDescription = taskDetails[2];
            Task task = decodeTaskParsing(taskDetails, taskDescription, taskStatus);
            return task;
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Missing task information in data file :(");
        }
    }
}
