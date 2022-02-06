import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.ArrayList;

public class Duke {

    // Regex string to extract commands and flags
    public static final String COMMAND_FORMAT = "(\\S+)(.*)";

    // Fixed string output for startup and exit
    public static final String LOGO =
            " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    public static final String WELCOME_BANNER =
            "____________________________________________________________"
            + "\nHello! I'm Dook!"
            + "\nWhat can I do for you?"
            + "\n____________________________________________________________";
    public static final String EXIT_MESSAGE =
            "____________________________________________________________"
            + "\nBye. Hope to see you again soon!"
            + "\n____________________________________________________________";

    /**
     * Prints introduction message
     */
    public static void printIntro() {
        System.out.println("Hello from\n" + LOGO);
        System.out.println(WELCOME_BANNER);
    }

    public static void main(String[] args) {

        // Intro message
        printIntro();

        //
        Handler commandHandler = new Handler();
        Scanner scannerInput = new Scanner(System.in);
        boolean hasInput = true;
        Parser argumentParser = new Parser();
        while (hasInput) {
            String userInput = scannerInput.nextLine();
            if (!argumentParser.parseInput(userInput)) {
                continue;
            }
            hasInput = commandHandler.execute(argumentParser.getUserCommand(), argumentParser.getArgumentList());
        }
        scannerInput.close();
        System.out.println(EXIT_MESSAGE);
    }
}
