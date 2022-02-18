package duke.tasklist;

import duke.exception.AdditionalException;
import duke.task.Task;
import java.util.ArrayList;

public class TaskList {

    private static final String MARKED_THIS_TASK_AS_DONE = "Nice! I've marked this task as done:";
    private static final String UNMARKED_THIS_TASK_AS_UNDONE = "Nice! I've unmarked this task as undone:";
    private static final String ADDED = "Got it. I have added this task: ";
    private static final String NUMBER_OF_TASKS_FIRST_HALF = "Now you have ";
    private static final String NUMBER_OF_TASKS_SECOND_HALF = " tasks in the list.";
    private static final String LINE = "-----------------------------";
    private static final String WRONG_TYPE_OF_TASK = "Something is wrong with the typeOfTask";
    private static final String DELETED = "Noted. I've removed this task: ";

    protected static ArrayList<Task> listOfTasks = new ArrayList<>();

    protected static String getDescription(String request, String typeOfTask) throws AdditionalException {
        int lengthOfTypeOfTask = typeOfTask.length();
        int lengthOfRequest = request.length();
        return checkLength(request, lengthOfTypeOfTask, lengthOfRequest, "description");
    }

    public static String getDescription(String request, String typeOfTask, String preposition)
                throws AdditionalException {
        int indexOfPreposition = checkIndexOfPreposition(request, preposition);
        int lengthOfTypeOfTask = typeOfTask.length();
        return checkLength(request, lengthOfTypeOfTask, indexOfPreposition, "description");
    }

    public static String getTiming(String request, String preposition) throws AdditionalException {
        int indexOfPreposition = checkIndexOfPreposition(request, preposition);
        int lengthOfPreposition = preposition.length();
        int startingIndexOfTiming = indexOfPreposition + lengthOfPreposition;
        int lengthOfRequest = request.length();
        return checkLength(request, startingIndexOfTiming, lengthOfRequest, "timing");
    }

    private static int checkIndexOfPreposition(String request, String preposition) throws AdditionalException {
        if (request.indexOf(preposition) == -1) {
            throw new AdditionalException("OOPS!!! You seem to have forgotten your preposition.");
        } else {
            return request.indexOf(preposition);
        }
    }

    private static String checkLength(String request, int lowerBound, int UpperBound, String typeOfDetail)
                throws AdditionalException {
        String descriptionOrTiming = request.substring(lowerBound, UpperBound);
        String trimmedDescriptionOrTiming = descriptionOrTiming.trim();
        if (trimmedDescriptionOrTiming.length() < 1) {
            raiseException(typeOfDetail);
        }
        return trimmedDescriptionOrTiming;
    }

    private static void raiseException(String typeOfDetail) throws AdditionalException {
        switch(typeOfDetail) {
        case "description":
            throw new AdditionalException("OOPS!!! The description cannot be empty.");
        case "timing":
            throw new AdditionalException("OOPS!!! The timing cannot be empty");
        }
    }

    public static void printConfirmationForAddingTasks() {
        System.out.println(ADDED);
        int indexOfLastItem = listOfTasks.size() - 1;
        System.out.println(listOfTasks.get(indexOfLastItem));
        System.out.println(NUMBER_OF_TASKS_FIRST_HALF + listOfTasks.size() + NUMBER_OF_TASKS_SECOND_HALF);
        System.out.println(LINE);
    }

    public static void printConfirmationForDeletingTask(Task taskToMarkOrDelete, boolean isNewRequest) {
        if (!isNewRequest) {
            return;
        }
        System.out.println(DELETED);
        System.out.println(taskToMarkOrDelete);
        System.out.println(NUMBER_OF_TASKS_FIRST_HALF + listOfTasks.size() + NUMBER_OF_TASKS_SECOND_HALF);
        System.out.println(LINE);
    }

    public static void printMarkOrUnmarkIsCompleted(Task task, String typeOfTask, boolean isNewRequest) {
        if (!isNewRequest) {
            return;
        }
        switch(typeOfTask) {
        case "mark":
            System.out.println(MARKED_THIS_TASK_AS_DONE);
            break;
        case "unmark":
            System.out.println(UNMARKED_THIS_TASK_AS_UNDONE);
            break;
        default:
            System.out.println(WRONG_TYPE_OF_TASK);
        }
        System.out.println(task + System.lineSeparator() + LINE);
    }

}
