package duke.task;

import duke.Parser;
import duke.exception.DukeEmptyDescriptionException;
import duke.exception.DukeMissingTimeSeparator;
import duke.exception.DukeTaskOutOfRangeException;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class TaskList {
    private static ArrayList<Task> taskLists = new ArrayList<>();
    private static Parser parser = new Parser();

    public TaskList() {

    }

    /**
     * This method prints all the tasks in the taskLists array.
     */
    public static void printList() {
        for (int i = 0; i < Task.getNumberOfTasks(); i++) {
            System.out.println((i + 1) + "."+ taskLists.get(i).toString());
        }
    }

    /**
     * This method add the task without deadline (Todo) into the taskLists array.
     *
     * @param userInput the input entered by user on terminal.
     * @return the new task added into the taskLists array.
     * @throws DukeEmptyDescriptionException If description of task is empty.
     */
    public static Task addTask(String userInput) throws DukeEmptyDescriptionException {
        String extractedTaskDescription = parser.validateAndExtractTaskDescription(userInput);
        Task newTask = new Todo(extractedTaskDescription);
        taskLists.add(newTask);
        return newTask;
    }

    /**
     * This method add the task with deadlines (Event, Deadlines) into the taskLists array.
     * Also converts string deadline time to LocalDateTime time.
     *
     * @param userInput the input entered by user on terminal.
     * @param stringSeparator
     * @return the new task added into the taskLists array.
     * @throws DukeEmptyDescriptionException If description of task is empty.
     * @throws DukeMissingTimeSeparator If time separators (e.g. '/at') for each task type is missing.
     * @throws DateTimeParseException If time could not be parsed properly.
     */
    public static Task addTaskWithTime(String userInput, String stringSeparator) throws
            DukeEmptyDescriptionException, DukeMissingTimeSeparator, DateTimeParseException {
        String extractedStringsWithoutCommandType = parser.validateAndExtractTaskDescription(userInput);
        if ((extractedStringsWithoutCommandType.split(stringSeparator).length) <= 1) {
            throw new DukeMissingTimeSeparator();
        }

        String extractedTaskDescription = extractedStringsWithoutCommandType.split(stringSeparator)[0];
        String extractedTaskDeadlineTime = extractedStringsWithoutCommandType.split(stringSeparator)[1];
        LocalDateTime convertToLocalDateTime = parser.parseDateTime(extractedTaskDeadlineTime);

        Task newTask;
        if (stringSeparator.equals("/by ")) {
            newTask = new Deadline(extractedTaskDescription, convertToLocalDateTime);
        } else if (stringSeparator.equals("/at ")) {
            newTask = new Event(extractedTaskDescription, convertToLocalDateTime);
        } else {
            throw new DukeMissingTimeSeparator();
        }
        taskLists.add(newTask);
        return newTask;
    }

    /**
     * This method changes the mark status of the task to be marked/unmarked in the taskLists array.
     *
     * @param isMarked true if task is to be marked, false if it is to be unmarked.
     * @param userInput the input entered by user on terminal.
     * @return the task number to be marked.
     * @throws DukeEmptyDescriptionException If description of task is empty.
     * @throws NumberFormatException If task number could not be parsed into an integer.
     * @throws DukeTaskOutOfRangeException If the task number supplied is less or more than the current task list.
     */
    public static int markTask(boolean isMarked, String userInput) throws
            DukeEmptyDescriptionException, NumberFormatException, DukeTaskOutOfRangeException {
        if ((userInput.split(" ")).length <= 1) {
            throw new DukeEmptyDescriptionException();
        }
        int taskNumber = parser.fetchTaskNumber(userInput);
        if (isMarked) {
            taskLists.get(taskNumber - 1).markAsDone();
        } else {
            taskLists.get(taskNumber - 1).markAsUndone();
        }
        return taskNumber;
    }

    /**
     * This method removes the task to be deleted in the taskLists array.
     *
     * @param userInput the input entered by user on terminal.
     * @return the task number to be deleted.
     * @throws DukeEmptyDescriptionException If description of task is empty.
     * @throws DukeTaskOutOfRangeException If the task number supplied is less or more than the current task list.
     */
    public static int deleteTask(String userInput) throws DukeEmptyDescriptionException, DukeTaskOutOfRangeException {
        if ((userInput.split(" ")).length <= 1) {
            throw new DukeEmptyDescriptionException();
        }
        int taskNumber = parser.fetchTaskNumber(userInput);
        System.out.println("Noted. Task removed:\n" + taskLists.get(taskNumber - 1).toString());
        taskLists.remove(taskNumber - 1);
        Task.reduceNumberOfTask();
        System.out.println("Now you have " + Task.getNumberOfTasks() + " tasks in your list!");
        return taskNumber;
    }

    /**
     * This method prints the message of tasks added to the taskLists array.
     *
     * @param newTask the new task to be added.
     */
    public static void addTaskPrintMessage(Task newTask) {
        System.out.println("Task added:\n\t" + newTask.toString());
        System.out.println("Now you have " + Task.getNumberOfTasks() + " tasks in your list!");
    }

    /**
     * This method prints the message of tasks marked/unmarked.
     *
     * @param taskNumberMarked the task number to be marked/unmarked.
     * @param isMarked true if task is to be marked, false if it is to be unmarked.
     */
    public static void markStatusPrintMessage(int taskNumberMarked, boolean isMarked) {
        if (isMarked) {
            System.out.println("Fantastic! This task is done:\n" + taskLists.get(taskNumberMarked - 1).toString());
        } else {
            System.out.println("Uh oh! This task is undone:\n" + taskLists.get(taskNumberMarked - 1).toString());
        }
    }

    /**
     * This method finds the task that contains the search keyword supplied
     * by the user in the taskLists array.
     *
     * @param userInput the input entered by user on terminal.
     * @throws DukeEmptyDescriptionException If description of task is empty.
     */
    public static void findTask(String userInput) throws DukeEmptyDescriptionException {
        String extractedFindKeyword = parser.validateAndExtractTaskDescription(userInput).toLowerCase();
        System.out.println("Here are the matching task(s) in your list:");
        int countNumberOfTasks = 0;
        for (int i = 0; i < Task.getNumberOfTasks(); i++) {
            if (taskLists.get(i).toString().toLowerCase().contains(extractedFindKeyword)) {
                System.out.println((i + 1) + "."+ taskLists.get(i).toString());
                countNumberOfTasks += 1;
            }
        }

        if (countNumberOfTasks == 0) {
            System.out.println("No task(s) found from keyword...:(");
        }
    }
}
