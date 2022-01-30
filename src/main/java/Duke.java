import java.util.Scanner;

public class Duke {

    public static final String EXIT_MESSAGE = "bye";
    public static final String PRINT_MESSAGE = "list";
    public static final String MARK_MESSAGE = "mark";
    public static final String UNMARK_MESSAGE = "unmark";

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
            System.out.println(listIndex + ".[" + list[i].getStatusIcon() + "] " + list[i].description);
        }
    }

    public static int getTaskIndex(String userInput) {
        String taskNumber = userInput.split(" ")[1];
        int taskIndex = Integer.parseInt(taskNumber) - 1;
        return taskIndex;
    }

    public static void addList(Task[] list, int listCounter, String line) {
        list[listCounter] = new Task(line);
        System.out.println("added: " + line);
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
