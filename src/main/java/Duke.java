import java.util.Scanner;

public class Duke {

    public static final String EXIT_MESSAGE = "bye";
    public static final String PRINT_MESSAGE = "list";
    public static final String MARK_MESSAGE = "mark";
    public static final String UNMARK_MESSAGE = "unmark";
    public static final String TODO_MESSAGE = "todo";
    public static final String DEADLINE_MESSAGE = "deadline";
    public static final String EVENT_MESSAGE = "event";

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

    public static void addList(Task[] list, int listCounter, String userInput) {
        String[] parsedUserInput = userInput.split(" ", 2);

        if (parsedUserInput[0].equals(TODO_MESSAGE)) {
            list[listCounter] = new ToDo(parsedUserInput[1]);
        } else if (parsedUserInput[0].equals(DEADLINE_MESSAGE)) {
            String[] deadlineInput = parsedUserInput[1].split("/by", 2);
            list[listCounter] = new Deadline(deadlineInput[0], deadlineInput[1]);
        } else if (parsedUserInput[0].equals(EVENT_MESSAGE)) {
            String[] eventInput = parsedUserInput[1].split("/at", 2);
            list[listCounter] = new Event(eventInput[0], eventInput[1]);
        }

        System.out.println("Got it. I've added this task:");
        System.out.println(list[listCounter]);
        listCounter++;

        if (listCounter == 1) {
            System.out.println("Now you have 1 task in the list");
        } else {
            System.out.println("Now you have " + listCounter + " tasks in the list.");
        }

    }

    private static void acceptInput() {
        Task[] list = new Task[100];
        int listCounter = 0;
        String userInput;
        Scanner in = new Scanner(System.in);
        userInput = in.nextLine();
        while(!userInput.equals(EXIT_MESSAGE)){
            if (userInput.equals(PRINT_MESSAGE)) {
                printList(list, listCounter);
            } else if (userInput.startsWith(MARK_MESSAGE)) {
                int taskIndex = getTaskIndex(userInput);
                list[taskIndex].markAsDone();
            } else if (userInput.startsWith(UNMARK_MESSAGE)) {
                int taskIndex = getTaskIndex(userInput);
                list[taskIndex].markAsUndone();
            } else {
                addList(list, listCounter, userInput);
                listCounter++;
            }
            userInput = in.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!\n");
    }

    public static void main(String[] args) {
        printWelcomeMessage();
        acceptInput();
    }

}
