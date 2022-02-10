package Managers;

import Components.Task;
import Components.Todo;
import Components.Deadline;
import Components.Event;
import Exceptions.*;

import static Constants.TaskManagerConstants.MAX_TASKS;
import static Constants.TaskManagerConstants.TODO_LENGTH;
import static Constants.TaskManagerConstants.DEADLINE_LENGTH;
import static Constants.TaskManagerConstants.EVENT_LENGTH;
import static Constants.TaskManagerConstants.DATETIME_DELIMITER_LENGTH;

public class TaskManager {
    private static Task[] tasks = new Task[MAX_TASKS];
    private static int numTasks = 0;

    public static void addTask(Task task) throws MaxTaskException {
        try {
            if (numTasks == MAX_TASKS) {
                throw new MaxTaskException("Max tasks reached");
            }

            tasks[numTasks++] = task;
        } catch (Exception e) {
            throw e;
        }

    }

    public static void addToDo(String msg) throws NoTaskDescriptionException, MaxTaskException {
        String description;
        description = msg.substring(TODO_LENGTH).trim();
        Task todo = new Todo(description);

        try {
            if (description.isBlank()){
                throw new NoTaskDescriptionException("Task description cannot be blank.");
            }
            addTask(todo);
        } catch (Exception e) {
            throw e;
        }
    }

    // String parser for Deadline and Task
    private static String[] descWithDateTimeParse(String msg) throws BadDateTimeFormatException,
            NoTaskDescriptionException, NoDateTimeException {
        int dateTimeDelimiterIndex, descDelimiterIndex;
        String[] strings = new String[2];
        boolean isDeadline = msg.indexOf("/by") > -1;

        try {
            // Get delimiters
            if (isDeadline) {
                dateTimeDelimiterIndex = msg.indexOf("/by");
                descDelimiterIndex = DEADLINE_LENGTH;
            } else {
                dateTimeDelimiterIndex = msg.indexOf("/at");
                descDelimiterIndex = EVENT_LENGTH;
            }

            if (dateTimeDelimiterIndex == -1) {
                throw new BadDateTimeFormatException("No delimiter.");
            }

            // Parse message (0 - Description, 1 - DateTime)
            strings[0] = msg.substring(descDelimiterIndex, dateTimeDelimiterIndex).trim(); // Task description
            strings[1] = msg.substring(dateTimeDelimiterIndex + DATETIME_DELIMITER_LENGTH).trim(); // DateTime

            if (strings[0].isBlank()) {
                throw new NoTaskDescriptionException("Task description cannot be blank.");
            }

            if (strings[1].isBlank()) {
                throw new NoDateTimeException("Task DateTime is empty.");
            }

            return strings;
        } catch (Exception e) {
            throw e;
        }
    }

    public static void addDeadline(String msg) throws BadDateTimeFormatException, NoTaskDescriptionException,
            NoDateTimeException, MaxTaskException {
        try {
            String[] msgParsed = descWithDateTimeParse(msg);
            Task deadline = new Deadline(msgParsed[0], msgParsed[1]);

            addTask(deadline);
        } catch (Exception e) {
            throw e;
        }
    }

    public static Task addEvent(String msg) throws BadDateTimeFormatException, NoTaskDescriptionException,
            NoDateTimeException, MaxTaskException {
        try {
            String[] msgParsed = descWithDateTimeParse(msg);
            Task event = new Event(msgParsed[0], msgParsed[1]);

            addTask(event);
            return event;
        } catch (Exception e) {
            throw e;
        }
    }

    // Return the index of task to mark/unmark
    private static int getMarkIndex(String msg) throws NumberFormatException, BadIndexException {
        try {
            // Extract Task number as String and parse into int
            int ind = Integer.parseInt( msg.substring(msg.indexOf(' ') + 1) );

            // Return index of Task number
            if (ind > numTasks){
                throw new BadIndexException("No task number " + ind);
            }

            return --ind;
        } catch (Exception e) {
            throw e;
        }
    }

    public static void markTask(String msg) throws NumberFormatException, BadIndexException {
        try {
            int ind = getMarkIndex(msg);
            tasks[ind].setIsDone(true);
            System.out.println(tasks[ind].toString());
        } catch (Exception e) {
            throw e;
        }
    }

    public static void unmarkTask(String msg) throws NumberFormatException, BadIndexException {
        try {
            int ind = getMarkIndex(msg);
            tasks[ind].setIsDone(false);
            System.out.println(tasks[ind].toString());
        } catch (Exception e) {
            throw e;
        }
    }

    public static void listTasks() {
        for (int i = 0; i < numTasks; i++) {
            System.out.println(i+1 + ". " + tasks[i].toString());
        }
    }

    public static int getNumTasks() {
        return numTasks;
    }

    public static Task getLastTask() {
        return tasks[numTasks - 1];
    }
}
