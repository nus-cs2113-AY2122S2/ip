package duke;

import duke.exception.AdditionalException;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Duke {
    private static final String LOGO = "\n" +
            "  .-')     _ (`-.                           .-. .-')               \n" +
            " ( OO ).  ( (OO  )                          \\  ( OO )              \n" +
            "(_)---\\_)_.`     \\ .-'),-----.  .-'),-----. ,--. ,--.   ,--.   ,--.\n" +
            "/    _ |(__...--''( OO'  .-.  '( OO'  .-.  '|  .'   /    \\  `.'  / \n" +
            "\\  :` `. |  /  | |/   |  | |  |/   |  | |  ||      /,  .-')     /  \n" +
            " '..`''.)|  |_.' |\\_) |  |\\|  |\\_) |  |\\|  ||     ' _)(OO  \\   /   \n" +
            ".-._)   \\|  .___.'  \\ |  | |  |  \\ |  | |  ||  .   \\   |   /  /\\_  \n" +
            "\\       /|  |        `'  '-'  '   `'  '-'  '|  |\\   \\  `-./  /.__) \n" +
            " `-----' `--'          `-----'      `-----' `--' '--'    `--'      \n";
    private static final String FIRST_GREETING = "Hello from\n";
    private static final String SECOND_GREETING = "Hi there! I'm Spooky :D";
    private static final String PROVIDE_SERVICE = "What can I do for you?";
    private static final String LINE = "-----------------------------";
    private static final String GOODBYE = "Bye! Hope to see you soon :D";
    private static final String FILE_PATH = "./duke.txt";

    public static void main(String[] args) {
        printGreeting();
        retrieveTasks();
        getRequest();
        printGoodbye();
    }

    private static void getRequest() {
        Scanner in = new Scanner(System.in);
        boolean hasExited = false;
        while (!hasExited) {
            String request = in.nextLine();
            request = request.trim();
            hasExited = isCompleted(request);
        }
    }

    public static void retrieveTasks() {
        try {
            readFromFile();
        } catch (FileNotFoundException error) {
            System.out.println("Duke.txt doesn't exist so I'M GOING TO CREATE ONE FOR YOU");
        }
    }

    private static void readFromFile() throws FileNotFoundException {
        int taskNumber = 1;
        File file = new File(FILE_PATH);
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            String nextLine = scanner.nextLine();
            readLineByLineFromFile(nextLine, taskNumber);
            taskNumber++;
        }
    }

    private static void readLineByLineFromFile(String nextLine, int taskNumber) {
        try {
            RequestProcessor.filterRequestsFromFile(nextLine, taskNumber);
        } catch(AdditionalException error) {
            System.out.println("Error transferring from file to tasks");
        }
    }

    private static boolean isCompleted(String request) {
        boolean hasExited = false;
        try {
            hasExited = RequestProcessor.filterNewRequest(request);
        } catch(AdditionalException error) {
            System.out.println(error.getMessage());
            System.out.println(LINE);
        }
        return hasExited;
    }

    private static void printGreeting() {
        System.out.println(FIRST_GREETING + LOGO);
        System.out.println(LINE);
        System.out.println(SECOND_GREETING);
        System.out.println(PROVIDE_SERVICE);
        System.out.println(LINE);
    }

    private static void printGoodbye() {
        System.out.println(GOODBYE);
        System.out.println(LINE);
    }
}
