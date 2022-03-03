package duke.Parser;

import duke.DukeException;
import duke.TaskList.task.Deadline;
import duke.TaskList.task.Event;
import duke.TaskList.task.Task;
import duke.TaskList.task.Todo;

public class TaskString {

    static String getTimeSplitArgument(TaskType taskType) throws DukeException {
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

    static  Task parseDeadlineOrEvent(TaskType taskType, String description) throws DukeException {
        String time;
        try {
            String regexArg = getTimeSplitArgument(taskType);
            String[] breakdown = description.split(regexArg, 2);
            description = breakdown[0];
            time = breakdown[1];
        } catch (DukeException e) {
            throw new DukeException(e.msg);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Your deadline or event doesn't have a time");
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

    public static Task parseTask(String type, String description) throws DukeException {
        Task t;
        switch (type) {
        case "todo":
            if (description.equals("")) {
                throw new DukeException("Please provide a task description!");
            }
            t = new Todo(description);
            break;
        case "deadline":
            t = parseDeadlineOrEvent(TaskType.DEADLINE, description);
            break;
        case "event":
            t = parseDeadlineOrEvent(TaskType.EVENT, description);
            break;
        default:
            throw new DukeException("I don't understand what you want to do, big sad :(");
        }

        return t;
    }

    public static Boolean decodeStatus(Integer taskStatusNum) throws DukeException {
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

    public static Task decodeTaskParsing(String[] details, String description,
                                         Boolean status) throws DukeException {
        Task t;
        switch (details[0]) {
        case "T":
            t = new Todo(description);
            t.setDone(status);
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

        return t;
    }

    public static Task decodeTask(String line) throws DukeException {
        String[] taskDetails = line.split(" \\| ");

        try {
            Integer taskStatusNum = Integer.parseInt(taskDetails[1]);
            Boolean taskStatus = decodeStatus(taskStatusNum);
            String taskDescription = taskDetails[2];
            return decodeTaskParsing(taskDetails, taskDescription, taskStatus);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Missing task information in data file :(");
        }
    }
}
