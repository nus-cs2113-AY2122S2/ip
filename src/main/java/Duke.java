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
    private static final String ADDED = "Added: ";
    private static final String INVALID_INDEX = "The index that you have indicated is invalid, please try again.";
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
        if (indexToUnmark >= 0 && indexToUnmark < index) {
            return true;
        }
        return false;
    }

    private static int getIndex(String[] words) {
        return Integer.parseInt(words[1]) - 1;
    }

    private static void addTask(String input) {
        Task t = new Task(input);
        list[index] = t;
        index++;
        System.out.println(ADDED + input + System.lineSeparator() + LINE);
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
        printTask(task);
        System.out.println(LINE);
    }

    private static void printMarkIsCompleted(Task task) {
        System.out.println(MARKED);
        printTask(task);
        System.out.println(LINE);
    }

    private static void printList() {
        for (int i = 0; i < index; i++) {
            int numbering = i + 1;
            System.out.print(numbering + ". ");
            printTask(list[i]);
        }
        System.out.println(LINE);
    }

    private static void printTask(Task task) {
        System.out.println('[' + task.getStatusIcon() + ']' + task.description);
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
