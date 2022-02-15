package duke.task;

import duke.exception.DukeEmptyDescriptionException;
import duke.exception.DukeMaxTaskException;
import duke.exception.DukeMissingTimeSeparator;
import duke.exception.DukeTaskOutOfRangeException;

public class TaskManager {
    protected static final int MAX_TASKS = 100;
    private static Task[] taskLists = new Task[MAX_TASKS];

    public TaskManager() {

    }

    public static void printList() {
        for (int i = 0; i < Task.getNumberOfTasks(); i++) {
            System.out.println((i + 1) + "."+ taskLists[i].toString());
        }
    }

    public static String validateAndExtractTaskDescription(String userInput) throws DukeEmptyDescriptionException {
        String[] arrayOfTaskStrings = userInput.split(" ");
        if (arrayOfTaskStrings.length <= 1) {
            throw new DukeEmptyDescriptionException();
        }

        String extractTaskDescription = "";
        for (int i = 1; i < arrayOfTaskStrings.length; i++) {
            extractTaskDescription += arrayOfTaskStrings[i] + " ";
        }

        return extractTaskDescription;
    }

    public static void checkTaskListSpaceAvailability() throws DukeMaxTaskException {
        if (Task.getNumberOfTasks() >= MAX_TASKS) {
            throw new DukeMaxTaskException();
        }
    }

    public static Task addTask(String userInput) throws DukeEmptyDescriptionException, DukeMaxTaskException {
        checkTaskListSpaceAvailability();
        String extractedTaskDescription = validateAndExtractTaskDescription(userInput);
        Task newTask = new Todo(extractedTaskDescription);
        taskLists[Task.getNumberOfTasks() - 1] = newTask;
        return newTask;
    }

    public static Task addTaskWithTime(String userInput, String stringSeparator) throws DukeEmptyDescriptionException, DukeMaxTaskException, DukeMissingTimeSeparator {
        checkTaskListSpaceAvailability();

        String extractedStringsWithoutCommandType = validateAndExtractTaskDescription(userInput);
        if ((extractedStringsWithoutCommandType.split(stringSeparator).length) <= 1) {
            throw new DukeMissingTimeSeparator();
        }

        String extractedTaskDescription = extractedStringsWithoutCommandType.split(stringSeparator)[0];
        String extractedTaskDeadlineTime = extractedStringsWithoutCommandType.split(stringSeparator)[1];
        Task newTask;
        if (stringSeparator.equals("/by ")) {
            newTask = new Deadline(extractedTaskDescription, extractedTaskDeadlineTime);
        } else {
            newTask = new Event(extractedTaskDescription, extractedTaskDeadlineTime);
        }

        taskLists[Task.getNumberOfTasks() - 1] = newTask;
        return newTask;
    }

    public static boolean isWithinTaskRange(int taskNumber) {
        if (taskNumber > Task.getNumberOfTasks() || taskNumber <= 0) {
            return false;
        }
        return true;
    }

    public static int markTask(boolean isMarked, String userInput) throws DukeEmptyDescriptionException, NumberFormatException, DukeTaskOutOfRangeException {
        if ((userInput.split(" ")).length <= 1) {
            throw new DukeEmptyDescriptionException();
        }

        int taskNumber = Integer.parseInt(userInput.split(" ")[1]);
        if (!isWithinTaskRange(taskNumber)) {
            throw new DukeTaskOutOfRangeException();
        }

        String markStatusMessage = "";
        if (isMarked) {
            taskLists[taskNumber - 1].markAsDone();
        } else {
            taskLists[taskNumber - 1].markAsUndone();
        }
        return taskNumber;

    }

    public static void addTaskPrintMessage(Task newTask) {
        System.out.println("Task added:\n\t" + newTask.toString());
        System.out.println("Now you have " + Task.getNumberOfTasks() + " tasks in your list!");
    }

    public static void markStatusPrintMessage(int taskNumberMarked, boolean isMarked) {
        if (isMarked) {
            System.out.println("Fantastic! This task is done:\n" + taskLists[taskNumberMarked - 1].toString());
        } else {
            System.out.println("Uh oh! This task is undone:\n" + taskLists[taskNumberMarked - 1].toString());
        }
    }
}
