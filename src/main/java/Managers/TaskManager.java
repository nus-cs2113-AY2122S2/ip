package Managers;

import Components.Task;
import Components.Todo;
import Components.Deadline;
import Components.Event;

import static Constants.TaskManagerConstants.TODO_LENGTH;
import static Constants.TaskManagerConstants.DEADLINE_LENGTH;
import static Constants.TaskManagerConstants.EVENT_LENGTH;
import static Constants.TaskManagerConstants.DATETIME_DELIMITER_LENGTH;

public class TaskManager {
    private static Task[] tasks = new Task[100];
    private static int numTasks = 0;

    public static void addTask(Task task) {
        tasks[numTasks++] = task;
    }

    public static Task addToDo(String msg) {
        String description;
        description = msg.substring(TODO_LENGTH).trim();
        Task todo = new Todo(description);

        addTask(todo);
        return todo;
    }

    // String parser for Deadline and Task
    private static String[] descWithDateTimeParse(String msg) {
        int dateTimeDelimiterIndex, descDelimiterIndex;
        String[] strings = new String[2];
        boolean isDeadline = msg.indexOf("/by") > -1;

        // Get delimiters
        if (isDeadline) {
            dateTimeDelimiterIndex = msg.indexOf("/by");
            descDelimiterIndex = DEADLINE_LENGTH;
        } else {
            dateTimeDelimiterIndex = msg.indexOf("/at");
            descDelimiterIndex = EVENT_LENGTH;
        }

        // Parse message (0 - Description, 1 - DateTime)
        strings[0] = msg.substring(descDelimiterIndex, dateTimeDelimiterIndex).trim(); // Task description
        strings [1] = msg.substring(dateTimeDelimiterIndex + DATETIME_DELIMITER_LENGTH).trim(); // DateTime

        return strings;
    }

    public static Task addDeadline(String msg) {
        String[] msgParsed = descWithDateTimeParse(msg);
        Task deadline = new Deadline(msgParsed[0], msgParsed[1]);

        addTask(deadline);
        return deadline;
    }

    public static Task addEvent(String msg) {
        String[] msgParsed = descWithDateTimeParse(msg);
        Task event = new Event(msgParsed[0], msgParsed[1]);

        addTask(event);
        return event;
    }

    // Return the index of task to mark/unmark
    private static int getMarkIndex(String msg) {
        // Extract Task number as String and parse into int
        int ind = Integer.parseInt( msg.substring(msg.indexOf(' ') + 1) );

        // Return index of Task number
        return --ind;
    }

    public static void markTask(String msg) {
        int ind = getMarkIndex(msg);
        tasks[ind].setIsDone(true);
        System.out.println(tasks[ind].toString());
    }

    public static void unmarkTask(String msg) {
        int ind = getMarkIndex(msg);
        tasks[ind].setIsDone(false);
        System.out.println(tasks[ind].toString());
    }

    public static void listTasks() {
        for (int i = 0; i < numTasks; i++) {
            System.out.println(i+1 + ". " + tasks[i].toString());
        }
    }

    public static int getNumTasks() {
        return numTasks;
    }
}
