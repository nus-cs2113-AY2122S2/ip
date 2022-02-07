package shrek;

import shrek.task.UserContent;
import shrek.task.Deadlines;
import shrek.task.Events;
import shrek.task.ToDo;
import shrek.constant.PrintStrings;
import shrek.exception.InvalidCommandException;

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
    private static final int NUMBER_OF_TERMS_IN_SPLIT = 2;
    public static final String NEW_LINE = System.lineSeparator();
    private static int errorCount = 0;
    private static final String[] listOfCommands = {"todo", "deadline", "event", "mark", "unmark"};
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
    public static final String ANSI_RESET = "\u001B[0m";

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
        System.out.println(ANSI_GREEN + logo + NEW_LINE);
        System.out.println(shrekLogo + NEW_LINE + ANSI_RESET);
        System.out.println(LINE + greet);
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

    public static boolean isCommandInList(String input) {
        for (String str : listOfCommands) {
            if (str.equals(input)) {
                return true;
            }
        }
        return false;
    }

    public static void addDeadlineOrEventToList(String input, String taskTimeReference)
            throws InvalidCommandException {
        String[] chunkOfInput;
        try {
            chunkOfInput = input.split(taskTimeReference);
            if (chunkOfInput.length > NUMBER_OF_TERMS_IN_SPLIT) {
                throw new InvalidCommandException("Did you add in more than one \""
                        + taskTimeReference + "\"?", errorCount);
            } else if (chunkOfInput[INDEX_OF_TASK_CONTENT].equals("") || chunkOfInput[INDEX_OF_TASK_NAME].equals("")) {
                throw new InvalidCommandException("Did you forget to add in the time or task?", errorCount);
            }
            if (taskTimeReference.equals("/at ")) {
                lists[listIndex] = new Events(chunkOfInput[INDEX_OF_TASK_CONTENT],
                        chunkOfInput[INDEX_OF_TASK_NAME], listIndex);
            } else {
                lists[listIndex] = new Deadlines(chunkOfInput[INDEX_OF_TASK_CONTENT],
                        chunkOfInput[INDEX_OF_TASK_NAME], listIndex);
            }
        } catch (ArrayIndexOutOfBoundsException err) {
            if (!input.contains(taskTimeReference)) {
                throw new InvalidCommandException("Did you forget \"" + taskTimeReference + "\"?", errorCount);
            }
            throw new InvalidCommandException("Did you forget to add in the time or task?", errorCount);
        }
    }

    public static void addToList(String input, String taskName) {
        boolean isTaskRanSuccessful = true;
        switch (taskName) {
        case "todo":
            lists[listIndex] = new ToDo(input, listIndex);
            break;
        case "deadline":
            addDeadlineOrEventToList(input, "/by ");
            break;
        case "event":
            addDeadlineOrEventToList(input, "/at ");
            break;
        default:
            System.out.println("Did you type the task properly? Re-enter your task");
            isTaskRanSuccessful = false;
        }
        if (isTaskRanSuccessful) {
            System.out.println("Done putting this in the list:");
            System.out.println(lists[listIndex]);
            System.out.println("Go do the " + listIndex + " task(s)!!");
            listIndex++;
        }
    }

    public static void markTask(String indexOfList) throws InvalidCommandException {
        try {
            if (!lists[Integer.parseInt(indexOfList)].getMark()) {
                lists[Integer.parseInt(indexOfList)].setMark();
                System.out.println("So you've done this task, that's great I guess?");
            } else {
                System.out.println("You have done this task already!");
            }
        } catch (NumberFormatException e) {
            throw new InvalidCommandException("Input of mark must be a number!", errorCount);
        } catch (NullPointerException e) {
            throw new InvalidCommandException("You do not have that many items in the list!", errorCount);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new InvalidCommandException("Please enter a number from 1 to 100", errorCount);
        }

        System.out.println(lists[Integer.parseInt(indexOfList)]);
    }

    public static void unmarkTask(String indexOfList) throws InvalidCommandException {
        try {
            if (lists[Integer.parseInt(indexOfList)].getMark()) {
                System.out.println("What do you mean you've undone");
                lists[Integer.parseInt(indexOfList)].setUnmark();
            } else {
                System.out.println("How can you undo something you've never did?");
            }
        } catch (NumberFormatException e) {
            throw new InvalidCommandException("Input of unmark must be a number!", errorCount);
        } catch (NullPointerException e) {
            throw new InvalidCommandException("You do not have that many items in the list!", errorCount);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new InvalidCommandException("Please enter a number from 1 to 100", errorCount);
        }
        System.out.println(lists[Integer.parseInt(indexOfList)]);
    }

    public static void takeInput(String userInput) throws InvalidCommandException {
        System.out.println(LINE);
        try {
            String[] words = userInput.split(" ", NUMBER_OF_TERMS_IN_SPLIT);
            if (words[INDEX_OF_TASK_COMMAND].equals("list")) {
                printList();
                System.out.print(LINE);
                return;
            } else if (!isCommandInList(words[INDEX_OF_TASK_COMMAND])) {
                throw new InvalidCommandException("Input a command from the list", errorCount);
            } else if (words.length < NUMBER_OF_TERMS_IN_SPLIT) {
                throw new InvalidCommandException("Missing input after the command!", errorCount);
            }
            switch (words[INDEX_OF_TASK_COMMAND]) {
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
            default:
                throw new InvalidCommandException("How did you get here?", errorCount);
            }
        } catch (InvalidCommandException err) {
            errorCount++;
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
