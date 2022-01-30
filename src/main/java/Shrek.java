import java.util.Scanner;

public class Shrek {
    private static final int NUMBER_OF_INPUT = 100;
    private static final UserContent[] lists = new UserContent[NUMBER_OF_INPUT];
    private static int listIndex = 1;
    private static final String LINE = PrintStrings.LINE;
    private static final int INDEX_OF_TASK_CONTENT = 0;
    private static final int INDEX_OF_TASK_NAME = 1;
    private static final int INDEX_OF_TASK_COMMAND = 0;
    private static final int INDEX_OF_TASK_INPUT = 1;
    public static final String NEW_LINE = System.lineSeparator();

    public static void printGreeting() {
        String logo = "███████╗██╗  ██╗██████╗ ███████╗██╗  ██╗\n" +
                "██╔════╝██║  ██║██╔══██╗██╔════╝██║ ██╔╝\n" +
                "███████╗███████║██████╔╝█████╗  █████╔╝ \n" +
                "╚════██║██╔══██║██╔══██╗██╔══╝  ██╔═██╗ \n" +
                "███████║██║  ██║██║  ██║███████╗██║  ██╗\n" +
                "╚══════╝╚═╝  ╚═╝╚═╝  ╚═╝╚══════╝╚═╝  ╚═╝\n" +
                "                                        \n";

        String shrekLogo = "⢀⡴⠑⡄⠀⠀⠀⠀⠀⠀⠀⣀⣀⣤⣤⣤⣀⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀ \n" +
                "⠸⡇⠀⠿⡀⠀⠀⠀⣀⡴⢿⣿⣿⣿⣿⣿⣿⣿⣷⣦⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀ \n" +
                "⠀⠀⠀⠀⠑⢄⣠⠾⠁⣀⣄⡈⠙⣿⣿⣿⣿⣿⣿⣿⣿⣆⠀⠀⠀⠀⠀⠀⠀⠀ \n" +
                "⠀⠀⠀⠀⢀⡀⠁⠀⠀⠈⠙⠛⠂⠈⣿⣿⣿⣿⣿⠿⡿⢿⣆⠀⠀⠀⠀⠀⠀⠀ \n" +
                "⠀⠀⠀⢀⡾⣁⣀⠀⠴⠂⠙⣗⡀⠀⢻⣿⣿⠭⢤⣴⣦⣤⣹⠀⠀⠀⢀⢴⣶⣆ \n" +
                "⠀⠀⢀⣾⣿⣿⣿⣷⣮⣽⣾⣿⣥⣴⣿⣿⡿⢂⠔⢚⡿⢿⣿⣦⣴⣾⠁⠸⣼⡿ \n" +
                "⠀⢀⡞⠁⠙⠻⠿⠟⠉⠀⠛⢹⣿⣿⣿⣿⣿⣌⢤⣼⣿⣾⣿⡟⠉⠀⠀⠀⠀⠀ \n" +
                "⠀⣾⣷⣶⠇⠀⠀⣤⣄⣀⡀⠈⠻⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡇⠀⠀⠀⠀⠀⠀ \n" +
                "⠀⠉⠈⠉⠀⠀⢦⡈⢻⣿⣿⣿⣶⣶⣶⣶⣤⣽⡹⣿⣿⣿⣿⡇⠀⠀⠀⠀⠀⠀ \n" +
                "⠀⠀⠀⠀⠀⠀⠀⠉⠲⣽⡻⢿⣿⣿⣿⣿⣿⣿⣷⣜⣿⣿⣿⡇⠀⠀⠀⠀⠀⠀ \n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⢸⣿⣿⣷⣶⣮⣭⣽⣿⣿⣿⣿⣿⣿⣿⠀⠀⠀⠀⠀⠀⠀ \n" +
                "⠀⠀⠀⠀⠀⠀⣀⣀⣈⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠇⠀⠀⠀⠀⠀⠀⠀ \n" +
                "⠀⠀⠀⠀⠀⠀⢿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠃⠀⠀⠀⠀⠀⠀⠀⠀ \n" +
                "⠀⠀⠀⠀⠀⠀⠀⠹⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡿⠟⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀ \n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠉⠛⠻⠿⠿⠿⠿⠛⠉";
        String greet = " Oh! Hello there! I'm Shrek" + NEW_LINE + " What can I do for you?";
        System.out.print(LINE);
        System.out.println(logo + NEW_LINE + shrekLogo + NEW_LINE + LINE + greet);
    }

    public static void printGoodbye() {
        String bye = " Bye bye! See you later!";
        System.out.print(LINE + bye + NEW_LINE + LINE);
    }

    public static void printList() {
        System.out.println("Go finish these tasks, NOW:");
        for (int i = 1; i < listIndex; i++) {
            System.out.println(lists[i]);
        }
    }

    public static void addToList(String input, String taskName) {
        String[] chunkOfInput;
        boolean isSuccessful = true;
        switch (taskName) {
        case "todo":
            lists[listIndex] = new ToDo(input, listIndex);
            break;
        case "deadline":
            if (input.contains("/by ")) {
                chunkOfInput = input.split("/by ", 2);
                lists[listIndex] = new Deadlines(chunkOfInput[INDEX_OF_TASK_CONTENT],
                        chunkOfInput[INDEX_OF_TASK_NAME], listIndex);
                break;
            }
        case "event":
            if (input.contains("/at ")) {
                chunkOfInput = input.split("/at ", 2);
                lists[listIndex] = new Events(chunkOfInput[INDEX_OF_TASK_CONTENT],
                        chunkOfInput[INDEX_OF_TASK_NAME], listIndex);
                break;
            }
        default:
            System.out.println("Did you type the task properly? Re-enter your task");
            isSuccessful = false;
        }
        if (isSuccessful) {
            System.out.println("Done putting this in the list:");
            System.out.println(lists[listIndex]);
            System.out.println("Go do the " + listIndex + " task(s)!!");
            listIndex++;
        }
    }

    public static void markTask(String indexOfList) {
        System.out.println("So you've done this task, that's great I guess?");
        lists[Integer.parseInt(indexOfList)].setMark();
        System.out.println(lists[Integer.parseInt(indexOfList)]);
    }

    public static void unmarkTask(String indexOfList) {
        System.out.println("What do you mean you've undone");
        lists[Integer.parseInt(indexOfList)].setUnmark();
        System.out.println(lists[Integer.parseInt(indexOfList)]);
    }

    public static void takeInput(String userInput) {
        System.out.println(LINE);
        String[] words = userInput.split(" ", 2);
        switch (words[INDEX_OF_TASK_COMMAND]) {
        case "list":
            printList();
            break;
        case "unmark":
            unmarkTask(words[INDEX_OF_TASK_INPUT]);
            break;
        case "mark":
            markTask(words[INDEX_OF_TASK_INPUT]);
            break;
        case "todo":
            addToList(words[INDEX_OF_TASK_INPUT], "todo");
            break;
        case "deadline":
            addToList(words[INDEX_OF_TASK_INPUT], "deadline");
            break;
        case "event":
            addToList(words[INDEX_OF_TASK_INPUT], "event");
            break;
        case "":
            System.out.println("Type something ya prick");
            break;
        default:
            addToList(userInput, "");
        }
        System.out.print(LINE);
    }

    public static void main(String[] args) {
        printGreeting();
        Scanner in = new Scanner(System.in);
        String userInput = in.nextLine();
        while (!userInput.equals("bye")) {
            takeInput(userInput);
            userInput = in.nextLine();
        }
        printGoodbye();
    }
}
