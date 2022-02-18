package duke.tasklist;

import duke.storage.Storage;
import duke.task.Task;

public class MarkOrDeleteTask extends TaskList {

    private static final String INVALID_INDEX = "The index is missing or out of range, please try again.";
    private static final String NOT_AN_INTEGER = "The index should be an integer, please try again.";
    private static final String WRONG_TYPE_OF_TASK = "Something is wrong with the typeOfTask";
    private static final String LINE = "-----------------------------";

    public static void markOrDeleteItem(String[] words, String typeOfTask, boolean isNewRequest) {
        try {
            int indexToMarkOrDelete = Integer.parseInt(words[1]) - 1;
            Task taskToMarkOrDelete = listOfTasks.get(indexToMarkOrDelete);
            markOrDelete(typeOfTask, taskToMarkOrDelete, isNewRequest);
        } catch(IndexOutOfBoundsException error) {
            System.out.println(INVALID_INDEX + System.lineSeparator() + LINE);
        } catch (NumberFormatException error) {
            System.out.println(NOT_AN_INTEGER + System.lineSeparator()+ LINE);
        }
    }

    private static void markOrDelete(String typeOfTask, Task taskToMarkOrDelete, boolean isNewRequest) {
        switch (typeOfTask) {
        case "mark":
            taskToMarkOrDelete.markAsDone();
            printMarkOrUnmarkIsCompleted(taskToMarkOrDelete, "mark", isNewRequest);
            break;
        case "unmark":
            taskToMarkOrDelete.markAsUndone();
            printMarkOrUnmarkIsCompleted(taskToMarkOrDelete, "unmark", isNewRequest);
            break;
        case "delete":
            listOfTasks.remove(taskToMarkOrDelete);
            printConfirmationForDeletingTask(taskToMarkOrDelete, isNewRequest);
            break;
        default:
            System.out.println(WRONG_TYPE_OF_TASK);
        }
        if (isNewRequest) {
            Storage.saveData("markOrDelete", taskToMarkOrDelete, listOfTasks);
        }
    }

}
