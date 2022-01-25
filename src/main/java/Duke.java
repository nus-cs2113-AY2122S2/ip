import java.util.Scanner;

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
            String rawCmd = getRawCommand();
            String[] cmdTokens = parseCommand(rawCmd);
            printLineDivider();
            for(String token:cmdTokens) {
                if(token.toLowerCase().equals("bye")) {
                    return;
                }
            }
            runCommand(parseCommand(rawCmd));
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
        // TODO personalize the greeting information (maybe change DUKE to BECK or someting else)
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
     *  Print {@value #userName} and prompt symbol, used before {@link #getRawCommand()}
     */
    protected static void printUserPrompt() {
        // TODO add more style (like bash style or zsh style)
        System.out.print(userName + " > ");
    }


    /**
     *  Get the input string from user
     * @return the string from user (without any changes)
     */
    private static String getRawCommand() {
        Scanner sc = new Scanner(System.in);
        String raw = sc.nextLine();
        return raw;
    }

    /**
     * Split the raw string from {@link #getRawCommand()} into tokens
     * @param raw raw string from {@link #getRawCommand()}
     * @return a string array containing tokens
     */
    protected static String[] parseCommand(String raw) {
        // TODO should be able to recognize the content in "" as a whole string
        return raw.trim().split("\\s+");
    }


    /**
     * Run the corresponding command/method regarding args, default command is echo
     * @param args a list of tokens, args[0] should be the name of the command, and the rest is the arguemnts of that command
     */
    protected static void runCommand(String[] args) {
        switch (args[0]) {
        default:
            String[] defaultArgs = new String[args.length+1];
            defaultArgs[0] = "echo";
            for(int i = 1; i < defaultArgs.length; i++) {
                defaultArgs[i] = args[i-1];
            }
            echo(defaultArgs);
        }

    }

    /**
     * Print every token
     * @param args tokens to print
     */
    protected static void echo(String[] args) {
        for(int i = 1; i < args.length; i++) {
            System.out.print(args[i]);
            if(i != args.length - 1) {
                System.out.print(" ");
            } else {
                System.out.print("\n");
            }
        }
    }
}
