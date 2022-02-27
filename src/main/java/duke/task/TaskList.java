package duke.task;

import duke.Parser;
import duke.exception.DukeEmptyDescriptionException;
import duke.exception.DukeMissingTimeSeparator;
import duke.exception.DukeTaskOutOfRangeException;

import java.util.ArrayList;

public class TaskList {
    private static ArrayList<Task> taskLists = new ArrayList<>();
    private static Parser parser = new Parser();

    public TaskList() {

    }

    public static void printList() {
        for (int i = 0; i < Task.getNumberOfTasks(); i++) {
            System.out.println((i + 1) + "."+ taskLists.get(i).toString());
        }
    }

    public static Task addTask(String userInput) throws DukeEmptyDescriptionException {
        String extractedTaskDescription = parser.validateAndExtractTaskDescription(userInput);
        Task newTask = new Todo(extractedTaskDescription);
        taskLists.add(newTask);
        return newTask;
    }

    public static Task addTaskWithTime(String userInput, String stringSeparator) throws DukeEmptyDescriptionException, DukeMissingTimeSeparator {
        String extractedStringsWithoutCommandType = parser.validateAndExtractTaskDescription(userInput);
        if ((extractedStringsWithoutCommandType.split(stringSeparator).length) <= 1) {
            throw new DukeMissingTimeSeparator();
        }

        String extractedTaskDescription = extractedStringsWithoutCommandType.split(stringSeparator)[0];
        String extractedTaskDeadlineTime = extractedStringsWithoutCommandType.split(stringSeparator)[1];
        Task newTask;
        if (stringSeparator.equals("/by ")) {
            newTask = new Deadline(extractedTaskDescription, extractedTaskDeadlineTime);
        } else if (stringSeparator.equals("/at ")) {
            newTask = new Event(extractedTaskDescription, extractedTaskDeadlineTime);
        } else {
            throw new DukeMissingTimeSeparator();
        }
        taskLists.add(newTask);
        return newTask;
    }

    public static int markTask(boolean isMarked, String userInput) throws DukeEmptyDescriptionException, NumberFormatException, DukeTaskOutOfRangeException {
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

    public static void addTaskPrintMessage(Task newTask) {
        System.out.println("Task added:\n\t" + newTask.toString());
        System.out.println("Now you have " + Task.getNumberOfTasks() + " tasks in your list!");
    }

    public static void markStatusPrintMessage(int taskNumberMarked, boolean isMarked) {
        if (isMarked) {
            System.out.println("Fantastic! This task is done:\n" + taskLists.get(taskNumberMarked - 1).toString());
        } else {
            System.out.println("Uh oh! This task is undone:\n" + taskLists.get(taskNumberMarked - 1).toString());
        }
    }
}
