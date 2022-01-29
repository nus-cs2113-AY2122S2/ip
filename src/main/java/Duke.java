import java.util.Scanner;

public class Duke {
    private static final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    private static final String LINE = "-----------------------------";
    private static final String FIRST_GREETING = "Hello from\n";
    private static final String SECOND_GREETING = "Hello there! I'm Duke :D";
    private static final String PROVIDE_SERVICE = "What can I do for you?";
    private static final String GOODBYE = "Bye! Hope to see you soon :D";
    private static final String BYE = "bye";
    private static final String LIST = "list";
    private static final String MARK = "mark";
    private static final String MARKED = "Nice! I've marked this task as done:";
    private static final String UNMARK = "unmark";
    private static final String UNMARKED = "Nice! I've unmarked this task as undone:";
    private static final String SPACEBAR = " ";
    private static final String ADDED = "Got it. I have added this task: ";
    private static final String NUMBER_OF_TASKS_FIRST_HALF = "Now you have ";
    private static final String NUMBER_OF_TASKS_SECOND_HALF = " tasks in the list.";
    private static final String INVALID_INDEX = "The index that you have indicated is invalid, please try again.";
    private static final String TODO = "todo";
    private static final String DEADLINE = "deadline";
    private static final String BY = "/by";
    private static final String EVENT = "event";
    private static final String AT = "/at";
    private static final int START_INDEX_OF_TODO = 5;
    private static final int START_INDEX_OF_DEADLINE = 9;
    private static final int START_INDEX_OF_TIMING = 4;
    private static final int START_INDEX_OF_EVENT = 6;
    private static final int LENGTH_OF_SPACE = 1;
    private static final int MINIMUM_INDEX = 0;

    private static Task[] list = new Task[100];
    private static int index = 0;

    public static void main(String[] args) {
        printGreeting();
        processRequest();
    }

    private static void processRequest() {
        getRequest();
    }

    private static void getRequest() {
        Scanner in = new Scanner(System.in);
        String input;
        boolean hasExited = false;
        while (!hasExited) {
            input = in.nextLine();
            input = input.trim();
            hasExited = filterRequest(input);
        }
    }

    private static boolean filterRequest(String input) {
        if (isBye(input)) {
            printGoodbye();
            return true;
        }
        if (isList(input)) {
            printList();
        } else {
            addOrMarkItem(input);
        }
        return false;
    }

    private static void addOrMarkItem(String input) {
        String[] words = input.split(SPACEBAR);
        if (isMark(words)) {
            markItem(words);
        } else if (isUnmark(words)) {
            unmarkItem(words);
        } else {
            addTask(input);
        }
    }

    private static void markItem(String[] words) {
        int indexToMark = getIndex(words);
        if (isIndexValid(indexToMark)) {
            list[indexToMark].markAsDone();
            printMarkIsCompleted(list[indexToMark]);
        } else {
            handleInvalidIndex();
        }
    }

    private static void unmarkItem(String[] words) {
        int indexToUnmark = getIndex(words);
        if (isIndexValid(indexToUnmark)) {
            list[indexToUnmark].markAsUndone();
            printUnmarkIsCompleted(list[indexToUnmark]);
        } else {
            handleInvalidIndex();
        }
    }

    private static void handleInvalidIndex() {
        System.out.println(INVALID_INDEX);
        System.out.println(LINE);
    }

    private static boolean isIndexValid(int indexToUnmark) {
        if (indexToUnmark >= MINIMUM_INDEX && indexToUnmark < index) {
            return true;
        }
        return false;
    }

    private static int getIndex(String[] words) {
        return Integer.parseInt(words[1]) - 1;
    }

    private static void addTask(String input) {
        String typeOfTask = getTypeOfTask(input);
        if (isToDo(typeOfTask)) {
            addToDo(input);
        } else if (isDeadline(typeOfTask)) {
            addDeadline(input);
        } else if (isEvent(typeOfTask)) {
            addEvent(input);
        } else {
            addNormalTask(input);
        }
        printConfirmation();
        incrementIndex();
    }

    private static void incrementIndex() {
        index++;
    }

    private static void printConfirmation() {
        int numberOfTasks = index + 1;
        System.out.println(ADDED);
        System.out.println(list[index]);
        System.out.println(NUMBER_OF_TASKS_FIRST_HALF + numberOfTasks + NUMBER_OF_TASKS_SECOND_HALF);
        System.out.println(LINE);
    }

    private static void addNormalTask(String input) {
        list[index] = new Task(input);
    }

    private static String getTypeOfTask(String input) {
        String[] words = input.split(SPACEBAR);
        String typeOfTask = words[0];
        return typeOfTask;
    }

    private static void addEvent(String input) {
        int indexOfAt = getIndexOfAt(input);
        String description = getDescriptionOfEvent(input, indexOfAt);
        String at = getTimingOfEvent(input, indexOfAt);
        list[index] = new Event(description, at);
    }

    private static String getTimingOfEvent(String input, int indexOfAt) {
        int length = input.length();
        String at = input.substring(indexOfAt + START_INDEX_OF_TIMING, length);
        return at;
    }

    private static String getDescriptionOfEvent(String input, int indexOfAt) {
        String description = input.substring(START_INDEX_OF_EVENT, indexOfAt - LENGTH_OF_SPACE);
        return description;
    }

    private static int getIndexOfAt(String input) {
        int indexOfAt = input.indexOf(AT);
        return indexOfAt;
    }

    private static void addDeadline(String input) {
        int indexOfBy = getIndexOfBy(input);
        String description = getDescriptionOfDeadline(input, indexOfBy);
        String by = getTimingOfDeadline(input, indexOfBy);
        list[index] = new Deadline(description, by);
    }

    private static String getTimingOfDeadline(String input, int indexOfBy) {
        int length = input.length();
        String by = input.substring(indexOfBy + START_INDEX_OF_TIMING, length);
        return by;
    }

    private static String getDescriptionOfDeadline(String input, int indexOfBy) {
        String description = input.substring(START_INDEX_OF_DEADLINE, indexOfBy - LENGTH_OF_SPACE);
        return description;
    }

    private static int getIndexOfBy(String input) {
        int indexOfBy = input.indexOf(BY);
        return indexOfBy;
    }

    private static void addToDo(String input) {
        String description;
        description = getToDoDescription(input);
        list[index] = new ToDo(description);
    }

    private static String getToDoDescription(String input) {
        int length = input.length();
        String description = input.substring(START_INDEX_OF_TODO, length);
        return description;
    }

    private static boolean isToDo(String typeOfTask) {
        return typeOfTask.equalsIgnoreCase(TODO);
    }

    private static boolean isEvent(String typeOfTask) {
        return typeOfTask.equalsIgnoreCase(EVENT);
    }

    private static boolean isDeadline(String typeOfTask) {
        return typeOfTask.equalsIgnoreCase(DEADLINE);
    }

    private static boolean isUnmark(String[] words) {
        String word = words[0];
        return word.equalsIgnoreCase(UNMARK);
    }

    private static boolean isMark(String[] words) {
        String word = words[0];
        return word.equalsIgnoreCase(MARK);
    }

    private static void printUnmarkIsCompleted(Task task) {
        System.out.println(UNMARKED);
        System.out.println(task);
        System.out.println(LINE);
    }

    private static void printMarkIsCompleted(Task task) {
        System.out.println(MARKED);
        System.out.println(task);
        System.out.println(LINE);
    }

    private static void printList() {
        for (int i = 0; i < index; i++) {
            int numbering = i + 1;
            System.out.println(numbering + ". " + list[i]);
        }
        System.out.println(LINE);
    }

    private static boolean isList(String input) {
        return input.equalsIgnoreCase(LIST);
    }

    private static boolean isBye(String input) {
        return input.equalsIgnoreCase(BYE);
    }

    private static void printGoodbye() {
        System.out.println(GOODBYE);
        System.out.println(LINE);
    }

    private static void printGreeting() {
        System.out.println(FIRST_GREETING + LOGO);
        System.out.println(LINE);
        System.out.println(SECOND_GREETING);
        System.out.println(PROVIDE_SERVICE);
        System.out.println(LINE);
    }
}
