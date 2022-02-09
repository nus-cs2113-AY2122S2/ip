import java.util.Scanner;

public class Duke {

    public static final String WELCOME_MESSAGE = " ____        _        \n"
                                               + "|  _ \\ _   _| | _____ \n"
                                               + "| | | | | | | |/ / _ \\\n"
                                               + "| |_| | |_| |   <  __/\n"
                                               + "|____/ \\__,_|_|\\_\\___|\n"
                                               + "Hello! I'm Duke\n"
                                               + "What can I do for you?";
    public static final int MAX_TASK = 100;
    public static final String EXIT_MESSAGE = "bye";
    public static final String PRINT_MESSAGE = "list";
    public static final String MARK_MESSAGE = "mark";
    public static final String UNMARK_MESSAGE = "unmark";
    public static final String TODO_MESSAGE = "todo";
    public static final String DEADLINE_MESSAGE = "deadline";
    public static final String DEADLINE_INDICATOR = " /by ";
    public static final String EVENT_MESSAGE = "event";
    public static final String EVENT_INDICATOR = " /at ";
    public static final String WRONG_FORMAT_MESSAGE = "OOPS!!! One or more parameters are missing. The correct format is:\n"
                                                    + "todo [description]\n"
                                                    + "deadline [description] /by [deadline]\n"
                                                    + "event [description] /at [time]";
    public static final String WRONG_INPUT_MESSAGE = "OOPS!!! I'm sorry, but I don't know what that means :-(\n"
                                                   + "The accepted inputs are:\n"
                                                   + "> todo [description]\n"
                                                   + "> deadline [description] /by [deadline]\n"
                                                   + "> event [description] /at [time]\n"
                                                   + "> list\n"
                                                   + "> mark [Task#]\n"
                                                   + "> unmark [Task#]\n"
                                                   + "> bye";

    public static void printList(Task[] list, int listCounter) {
        if (listCounter == 0) {
            System.out.println("There are no tasks yet!");
        } else {
            for (int i = 0; i < listCounter; i++) {
                int listIndex = i + 1;
                System.out.println(listIndex + "." + list[i]);
            }
        }
    }

    public static int getTaskIndex(String userInput) {
        String taskNumber = userInput.split(" ")[1];
        return Integer.parseInt(taskNumber) - 1;
    }

    private static void markTask(Task[] list, String userInput) {
        int taskIndex;
        try {
            taskIndex = getTaskIndex(userInput);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Please specify task number");
            return;
        }
        try {
            list[taskIndex].markAsDone();
        } catch (NullPointerException e) {
            System.out.println("OOPS!!! This task does not exist.");
        }
    }

    private static void unmarkTask(Task[] list, String userInput) {
        int taskIndex;
        try {
            taskIndex = getTaskIndex(userInput);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Please specify task number");
            return;
        }
        try {
            list[taskIndex].markAsUndone();
        } catch (NullPointerException e) {
            System.out.println("OOPS!!! This task does not exist.");
        }
    }

    public static String[] parseAdditionalParameters (String parsedUserInput, String indicator) {
        String[] additionalParameters = parsedUserInput.split(indicator, 2);
        additionalParameters[0] = additionalParameters[0].trim();
        additionalParameters[1] = additionalParameters[1].trim();
        return additionalParameters;
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
    public static void parseInput(Task[] list, int listCounter, String userInput) throws DukeException {
        String[] parsedUserInputs = userInput.split(" ", 2);
        parsedUserInputs[0] = parsedUserInputs[0].toLowerCase();
        switch (parsedUserInputs[0]) {
        case TODO_MESSAGE:
            parsedUserInputs[1] = parsedUserInputs[1].trim();
            if (parsedUserInputs[1].length() == 0) {
                throw new IndexOutOfBoundsException();
            }
            list[listCounter] = new ToDo(parsedUserInputs[1]);
            break;
        case DEADLINE_MESSAGE:
            String[] deadlineInput = parseAdditionalParameters(parsedUserInputs[1], DEADLINE_INDICATOR);
            if (deadlineInput[0].length() == 0 || deadlineInput[1].length() == 0){
                throw new IndexOutOfBoundsException();
            }
            list[listCounter] = new Deadline(deadlineInput[0], deadlineInput[1]);
            break;
        case EVENT_MESSAGE:
            String[] eventInput = parseAdditionalParameters(parsedUserInputs[1], EVENT_INDICATOR);
            if (eventInput[0].length() == 0 || eventInput[1].length() == 0){
                throw new IndexOutOfBoundsException();
            }
            list[listCounter] = new Event(eventInput[0], eventInput[1]);
            break;
        default:
            throw new DukeException();
        }
        printAddToList(list, listCounter);
    }

    private static void processInput(String userInput, Scanner in) {
        Task[] list = new Task[MAX_TASK];
        int listCounter = 0;
        while(!userInput.equalsIgnoreCase(EXIT_MESSAGE)){
            if (userInput.startsWith(PRINT_MESSAGE)) {
                printList(list, listCounter);
            } else if (userInput.startsWith(MARK_MESSAGE)) {
                markTask(list, userInput);
            } else if (userInput.startsWith(UNMARK_MESSAGE)) {
                unmarkTask(list, userInput);
            } else {
                try {
                    parseInput(list, listCounter, userInput);
                    listCounter++;
                } catch (DukeException e) {
                    System.out.println(WRONG_INPUT_MESSAGE);
                } catch (IndexOutOfBoundsException e) {
                    System.out.println(WRONG_FORMAT_MESSAGE);
                }
            }
            userInput = in.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");
    }

    private static void acceptInput() {
        Scanner in = new Scanner(System.in);
        String userInput = in.nextLine();
        processInput(userInput, in);
    }

    public static void main(String[] args) {
        System.out.println(WELCOME_MESSAGE);
        acceptInput();
    }

}
