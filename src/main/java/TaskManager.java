public class TaskManager {

    private static final String MARKED_THIS_TASK_AS_DONE = "Nice! I've marked this task as done:";
    private static final String UNMARKED_THIS_TASK_AS_UNDONE = "Nice! I've unmarked this task as undone:";
    private static final String ADDED = "Got it. I have added this task: ";
    private static final String NUMBER_OF_TASKS_FIRST_HALF = "Now you have ";
    private static final String NUMBER_OF_TASKS_SECOND_HALF = " tasks in the list.";
    private static final String INVALID_INDEX = "The index that you have indicated is invalid, please try again.";
    private static final String LINE = "-----------------------------";

    private static Task[] listOfTasks = new Task[100];
    private static int index = 0;

    public static void addTask(String request, String typeOfTask) {
        String description = getDescription(request, typeOfTask);
        listOfTasks[index] = new ToDo(description);
    }

    public static void addTask(String request, String typeOfTask, String preposition) {
        String timing = getTiming(request, preposition);
        String description = getDescription(request, typeOfTask, preposition);
        switch (typeOfTask) {
        case "deadline":
            listOfTasks[index] = new Deadline(description, timing);
            break;
        case "event":
            listOfTasks[index] = new Event(description, timing);
            break;
        }
    }

    public static void incrementIndex() {
        index++;
    }

    public static void markItem(String[] words) {
        int indexToMark = getIndexToMarkOrUnmark(words);
        if (isIndexValid(indexToMark)) {
            listOfTasks[indexToMark].markAsDone();
            printMarkIsCompleted(listOfTasks[indexToMark]);
        } else {
            handleInvalidIndex();
        }
    }

    public static void unmarkItem(String[] words) {
        int indexToUnmark = getIndexToMarkOrUnmark(words);
        if (isIndexValid(indexToUnmark)) {
            listOfTasks[indexToUnmark].markAsUndone();
            printUnmarkIsCompleted(listOfTasks[indexToUnmark]);
        } else {
            handleInvalidIndex();
        }
    }

    public static void printList() {
        for (int i = 0; i < index; i++) {
            int numbering = i + 1;
            System.out.println(numbering + ". " + listOfTasks[i]);
        }
        System.out.println(LINE);
    }

    private static String getDescription(String request, String typeOfTask) {
        int lengthOfTypeOfTask = typeOfTask.length();
        int lengthOfRequest = request.length();
        String description = request.substring(lengthOfTypeOfTask, lengthOfRequest);
        return description.trim();
    }

    private static String getDescription(String request, String typeOfTask, String preposition) {
        int indexOfPreposition = request.indexOf(preposition);
        int lengthOfTypeOfTask = typeOfTask.length();
        String description = request.substring(lengthOfTypeOfTask, indexOfPreposition);
        return description.trim();
    }

    private static String getTiming(String request, String preposition) {
        int indexOfPreposition = request.indexOf(preposition);
        int lengthOfPreposition = preposition.length();
        int startingIndexOfTiming = indexOfPreposition + lengthOfPreposition;
        int lengthOfRequest = request.length();
        String timing = request.substring(startingIndexOfTiming, lengthOfRequest);
        return timing.trim();
    }

    private static void handleInvalidIndex() {
        System.out.println(INVALID_INDEX);
        System.out.println(LINE);
    }

    private static boolean isIndexValid(int indexToUnmark) {
        boolean isAboveZero = indexToUnmark >= 0;
        boolean isBelowMaximum = indexToUnmark < index;
        return isAboveZero && isBelowMaximum;
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

    private static void printUnmarkIsCompleted(Task task) {
        System.out.println(UNMARKED_THIS_TASK_AS_UNDONE);
        System.out.println(task);
        System.out.println(LINE);
    }

    private static void printMarkIsCompleted(Task task) {
        System.out.println(MARKED_THIS_TASK_AS_DONE);
        System.out.println(task);
        System.out.println(LINE);
    }

}
