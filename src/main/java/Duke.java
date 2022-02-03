import java.util.Scanner;

public class Duke {

    public static final String EXIT_MESSAGE = "bye";
    public static final String PRINT_MESSAGE = "list";
    public static final String MARK_MESSAGE = "mark";
    public static final String UNMARK_MESSAGE = "unmark";
    public static final String TODO_MESSAGE = "todo";
    public static final String DEADLINE_MESSAGE = "deadline";
    public static final String EVENT_MESSAGE = "event";
    public static final int MAX_TASK = 100;

    private static void printWelcomeMessage() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo + "Hello! I'm Duke\nWhat can I do for you?\n");
    }

    public static void printList(Task[] list, int listCounter) {
        for (int i = 0; i < listCounter; i++) {
            int listIndex = i + 1;
            System.out.println(listIndex + "." + list[i]);
        }
    }

    public static int getTaskIndex(String userInput) {
        String taskNumber = userInput.split(" ")[1];
        int taskIndex = Integer.parseInt(taskNumber) - 1;
        return taskIndex;
    }

    private static void printAddToList(Task[] list, int listCounter) {
        System.out.println("Got it. I've added this task:" + System.lineSeparator() + list[listCounter]);
        listCounter++;
        if (listCounter == 1) {
            System.out.println("Now you have 1 task in the list");
        } else {
            System.out.println("Now you have " + listCounter + " tasks in the list.");
        }
    }

    //Check what kind of task the user intends to add and process accordingly
    public static void parseInput(Task[] list, int listCounter, String userInput) {
        String[] parsedUserInputs = userInput.split(" ", 2);
        parsedUserInputs[0].toLowerCase();

        switch (parsedUserInputs[0]) {
        case TODO_MESSAGE:
            list[listCounter] = new ToDo(parsedUserInputs[1]);
            break;
        case DEADLINE_MESSAGE:
            String[] deadlineInput = parsedUserInputs[1].split("/by", 2);
            list[listCounter] = new Deadline(deadlineInput[0], deadlineInput[1]);
            break;
        case EVENT_MESSAGE:
            String[] eventInput = parsedUserInputs[1].split("/at", 2);
            list[listCounter] = new Event(eventInput[0], eventInput[1]);
            break;
        }

        printAddToList(list, listCounter);
    }

    private static void processInput(String userInput, Scanner in) {
        Task[] list = new Task[MAX_TASK];
        int listCounter = 0;
        while(!userInput.equalsIgnoreCase(EXIT_MESSAGE)){
            if (userInput.equalsIgnoreCase(PRINT_MESSAGE)) {
                printList(list, listCounter);
            } else if (userInput.startsWith(MARK_MESSAGE)) {
                int taskIndex = getTaskIndex(userInput);
                list[taskIndex].markAsDone();
            } else if (userInput.startsWith(UNMARK_MESSAGE)) {
                int taskIndex = getTaskIndex(userInput);
                list[taskIndex].markAsUndone();
            } else {
                parseInput(list, listCounter, userInput);
                listCounter++;
            }
            userInput = in.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!\n");
    }

    private static void acceptInput() {
        Scanner in = new Scanner(System.in);
        String userInput = in.nextLine();
        processInput(userInput, in);
    }

    public static void main(String[] args) {
        printWelcomeMessage();
        acceptInput();
    }

}
