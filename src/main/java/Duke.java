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
    private static final String SECOND_GREETING = "Hello there! I'm Spooky :D";
    private static final String PROVIDE_SERVICE = "What can I do for you?";
    private static final String LINE = "-----------------------------";
    private static final String GOODBYE = "Bye! Hope to see you soon :D";

    public static void main(String[] args) {
        printGreeting();
        getRequest();
        printGoodbye();
    }

    private static void getRequest() {
        boolean hasExited = false;
        while (!hasExited) {
            hasExited = isCompleted();
        }
    }

    private static boolean isCompleted() {
        Scanner in = new Scanner(System.in);
        String request = in.nextLine();
        request = request.trim();
<<<<<<< HEAD
        boolean hasExited = false;
        try {
            hasExited = RequestProcessor.filterRequest(request);
        } catch(AdditionalException error) {
            System.out.println(error.getMessage());
            System.out.println(LINE);
        }
=======
        boolean hasExited;
        hasExited = RequestProcessor.filterRequest(request);
>>>>>>> 798b7cee3680aa228d25a6a3b90e13594330c8d5
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
