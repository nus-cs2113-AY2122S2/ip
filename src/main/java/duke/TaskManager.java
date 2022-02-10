package duke;

import duke.exception.AdditionalException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

public class TaskManager {

    private static final String MARKED_THIS_TASK_AS_DONE = "Nice! I've marked this task as done:";
    private static final String UNMARKED_THIS_TASK_AS_UNDONE = "Nice! I've unmarked this task as undone:";
    private static final String ADDED = "Got it. I have added this task: ";
    private static final String NUMBER_OF_TASKS_FIRST_HALF = "Now you have ";
    private static final String NUMBER_OF_TASKS_SECOND_HALF = " tasks in the list.";
    private static final String INVALID_INDEX = "The index that you have indicated is invalid, please try again.";
    private static final String LINE = "-----------------------------";
    public static final String WRONG_TYPE_OF_TASK = "Something is wrong with the typeOfTask";

    private static Task[] listOfTasks = new Task[100];
    private static int index = 0;

    public static void addTask(String request, String typeOfTask) throws AdditionalException {
        String description = getDescription(request, typeOfTask);
        listOfTasks[index] = new ToDo(description);
    }

    public static void addTask(String request, String typeOfTask, String preposition) throws AdditionalException {
        String description = getDescription(request, typeOfTask, preposition);
        String timing = getTiming(request, preposition);
        switch (typeOfTask) {
        case "deadline":
            listOfTasks[index] = new Deadline(description, timing);
            break;
        case "event":
            listOfTasks[index] = new Event(description, timing);
            break;
        default:
            System.out.println("Oh no D: There seems to be a problem creating the task");
        }
    }

    public static void incrementIndex() {
        index++;
    }

    public static void markItem(String[] words, String typeOfTask) {
        int indexToMark = getIndexToMarkOrUnmark(words);
        try {
            markOrUnmark(typeOfTask, indexToMark);
        } catch(NullPointerException error) {
            System.out.println(INVALID_INDEX);
            System.out.println(LINE);
        } catch(ArrayIndexOutOfBoundsException error) {
            System.out.println(INVALID_INDEX);
            System.out.println(LINE);
        }
    }

    private static void markOrUnmark(String typeOfTask, int indexToMark) {
        switch (typeOfTask) {
        case "mark":
            listOfTasks[indexToMark].markAsDone();
            printMarkOrUnmarkIsCompleted(listOfTasks[indexToMark], "mark");
            break;
        case "unmark":
            listOfTasks[indexToMark].markAsUndone();
            printMarkOrUnmarkIsCompleted(listOfTasks[indexToMark], "unmark");
            break;
        default:
            System.out.println(WRONG_TYPE_OF_TASK);
        }
    }

    public static void printList() throws AdditionalException {
        if (index == 0) {
            throw new AdditionalException("YAY!!! you do not have any tasks at the moment hehe");
        }
        for (int i = 0; i < index; i++) {
            int numbering = i + 1;
            System.out.println(numbering + ". " + listOfTasks[i]);
        }
        System.out.println(LINE);
    }

    private static String getDescription(String request, String typeOfTask) throws AdditionalException {
        int lengthOfTypeOfTask = typeOfTask.length();
        int lengthOfRequest = request.length();
        String description = checkLength(request, lengthOfTypeOfTask, lengthOfRequest, "description");
        return description;
    }

    private static String getDescription(String request, String typeOfTask, String preposition)
                throws AdditionalException {
        int indexOfPreposition = checkIndexOfPreposition(request, preposition);
        int lengthOfTypeOfTask = typeOfTask.length();
        String description = checkLength(request, lengthOfTypeOfTask, indexOfPreposition, "description");
        return description;
    }

    private static String getTiming(String request, String preposition) throws AdditionalException {
        int indexOfPreposition = checkIndexOfPreposition(request, preposition);
        int lengthOfPreposition = preposition.length();
        int startingIndexOfTiming = indexOfPreposition + lengthOfPreposition;
        int lengthOfRequest = request.length();
        String timing = checkLength(request, startingIndexOfTiming, lengthOfRequest, "timing");
        return timing;
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

    private static int getIndexToMarkOrUnmark(String[] words) {
        return Integer.parseInt(words[1]) - 1;
    }

    public static void printConfirmationForAddingTasks() {
        int numberOfTasks = index + 1;
        System.out.println(ADDED);
        System.out.println(listOfTasks[index]);
        System.out.println(NUMBER_OF_TASKS_FIRST_HALF + numberOfTasks + NUMBER_OF_TASKS_SECOND_HALF);
        System.out.println(LINE);
    }

    private static void printMarkOrUnmarkIsCompleted(Task task, String typeOfTask) {
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
        System.out.println(task);
        System.out.println(LINE);
    }

}
