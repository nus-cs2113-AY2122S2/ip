package duke;

import duke.exception.AdditionalException;

import java.util.Scanner;

public class Ui {

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

    public static void printGreeting() {
        System.out.println(FIRST_GREETING + LOGO);
        System.out.println(LINE);
        System.out.println(SECOND_GREETING);
        System.out.println(PROVIDE_SERVICE);
        System.out.println(LINE);
    }

    public static void getRequest() {
        Scanner in = new Scanner(System.in);
        boolean hasExited = false;
        while (!hasExited) {
            String request = in.nextLine();
            request = request.trim();
            hasExited = isCompleted(request);
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

    public static void printGoodbye() {
        System.out.println(GOODBYE);
        System.out.println(LINE);
    }
}
