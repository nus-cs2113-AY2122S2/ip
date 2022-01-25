import java.util.*;

/**
 * Duke, your personal terminal assistant
 */
public class Duke {
    /**
     *  How we call our guest
     */
    private static String userName = "Mr/Mrs Geek";

    /**
     * The main method of Duke, calling some other methods to run the entire program
     * @param args only takes {@value #userName}
     */
    public static void main(String[] args) {
        initializeDuke(args);
        printUserGreet();
        runDuke();
        printUserFarewell();
    }

    /**
     *  Assign the arguments from {@link #main(String[])} method
     * @param args a reference to arguments in {@link #main(String[])}
     */
    private static void initializeDuke(String[] args) {
        if(args.length >= 1) {
            userName = args[args.length-1];
        }
    }

    /**
     * Get command from user, parse the command and then call the corresponding method
     */
    protected static void runDuke() {
        while(true) {
            printUserPrompt();
            String rawCmd = Command.getRawCommand();
            String[] cmdTokens = Command.parseCommand(rawCmd);
            printLineDivider();
            for(String token:cmdTokens) {
                if(token.equalsIgnoreCase("bye")) {
                    return;
                }
            }
            Command.runCommand(cmdTokens);
            printLineDivider();
        }
    }

    /**
     * Print a line Divider
     */
    protected static void printLineDivider() {
        // TODO Beautify it more, or add more styles
        System.out.println("------------------------------");
    }

    /**
     * Greet the user
     */
    protected static void printUserGreet() {
        // TODO personalize the greeting information (maybe change DUKE to BECK or something else)
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        printLineDivider();
        System.out.println("Hello, I'm Duke");
        System.out.println("What can I do for you? " + userName);
        printLineDivider();
    }

    /**
     * Say goodbye to user
     */
    protected static void printUserFarewell() {
        System.out.println("Bye, " + userName + ". Hope to see you soon!");
        printLineDivider();
    }

    /**
     *  Print {@value #userName} and prompt symbol
     */
    protected static void printUserPrompt() {
        // TODO add more style (like bash style or zsh style)
        System.out.print(userName + " > ");
    }

}
