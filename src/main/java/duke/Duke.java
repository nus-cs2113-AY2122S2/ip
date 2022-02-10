package duke;

import java.util.Scanner;

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

    /**
     * Prints exit message
     */
    public static void printExit() {
        System.out.println(EXIT_MESSAGE);
    }

    /**
     * Runs function to start up application
     * @param args not used
     */
    public static void main(String[] args) {
        printIntro();
        loopWhileInput();
        printExit();
    }

    /**
     * Takes in input and passes input to a Parser.
     * Subsequently, passes the Parser object to a Handler, which executes the commands.
     */
    public static void loopWhileInput() {
        boolean hasInput = true;
        Scanner scannerInput = new Scanner(System.in);
        Handler handler = new Handler();
        while (hasInput) {
            String userInput = scannerInput.nextLine();
            Parser argumentParser = new Parser();
            argumentParser.parseInput(userInput);
            if (argumentParser.isBye()) {
                break;
            }
            handler.execute(argumentParser);
        }
        scannerInput.close();
    }

}
