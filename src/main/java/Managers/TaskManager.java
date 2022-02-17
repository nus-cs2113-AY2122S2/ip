package Managers;

import java.util.ArrayList;

import Components.Task;
import Components.Todo;
import Components.Deadline;
import Components.Event;

import Exceptions.BadDateTimeFormatException;
import Exceptions.BadIndexException;
import Exceptions.MaxTaskException;
import Exceptions.NoDateTimeException;
import Exceptions.NoTaskDescriptionException;


import java.io.IOException;

import static Constants.TaskManagerConstants.MAX_TASKS;
import static Constants.TaskManagerConstants.TODO_LENGTH;
import static Constants.TaskManagerConstants.DEADLINE_LENGTH;
import static Constants.TaskManagerConstants.EVENT_LENGTH;
import static Constants.TaskManagerConstants.DATETIME_DELIMITER_LENGTH;

public class TaskManager {
    private static ArrayList<Task> tasks = new ArrayList<>();
    private static int numTasks = 0;

    public static void TaskManager() {
        numTasks = 0;
    }

    private static void addTask(Task task) throws MaxTaskException {
        try {
            if (numTasks == MAX_TASKS) {
                throw new MaxTaskException("Max tasks reached");
            }

            numTasks++;
            tasks.add(task);
            saveTasklist();
        } catch (Exception e) {
            throw e;
        }
    }

    static Task deleteTask(String msg) throws BadIndexException, NumberFormatException {
        try {
            int ind = getIndex(msg);
            Task deletedTask = tasks.remove(ind);
            numTasks--;
            saveTasklist();
            return deletedTask;
        } catch (Exception e) {
            throw e;
        }
    }

    static void addToDo(String msg) throws NoTaskDescriptionException, MaxTaskException {
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

    static void addDeadline(String msg) throws BadDateTimeFormatException, NoTaskDescriptionException,
            NoDateTimeException, MaxTaskException {
        try {
            String[] msgParsed = descWithDateTimeParse(msg);
            Task deadline = new Deadline(msgParsed[0], msgParsed[1]);

            addTask(deadline);
        } catch (Exception e) {
            throw e;
        }
    }

    static Task addEvent(String msg) throws BadDateTimeFormatException, NoTaskDescriptionException,
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
    private static int getIndex(String msg) throws NumberFormatException, BadIndexException {
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

    static void markTask(String msg) throws NumberFormatException, BadIndexException {
        try {
            int ind = getIndex(msg);
            tasks.get(ind).setIsDone(true);
            System.out.println(tasks.get(ind).toString());
            saveTasklist();
        } catch (Exception e) {
            throw e;
        }
    }

    static void unmarkTask(String msg) throws NumberFormatException, BadIndexException {
        try {
            int ind = getIndex(msg);
            tasks.get(ind).setIsDone(false);
            System.out.println(tasks.get(ind).toString());
            saveTasklist();
        } catch (Exception e) {
            throw e;
        }
    }

    static void listTasks() {
        for (int i = 0; i < numTasks; i++) {
            System.out.println(i+1 + ". " + tasks.get(i).toString());
        }
    }

    static int getNumTasks() {
        return numTasks;
    }

    static Task getLastTask() {
        return tasks.get(numTasks - 1);
    }

    public static void loadTasklist() {
        try {
            numTasks = TaskListFileManager.loadTasklist(tasks);
        } catch (IOException e) {
            System.out.println("Loading failed.");
        }
    }

    public static void saveTasklist() {
        try {
            TaskListFileManager.saveTasklist(tasks);
        } catch (IOException e) {
            System.out.println("Saving failed.");
        }
    }
}
