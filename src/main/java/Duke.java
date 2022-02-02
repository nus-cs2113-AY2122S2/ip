/**
 * Duke, your personal terminal assistant
 */
public class Duke {
    /**
     * The appellation for the guest
     */
    private static String userName = "Mr/Mrs Geek";
    /**
     * Exit commands
     */
    private static String[] exitCommands = {"bye", "exit", "quit"};

    /**
     * The main method of Duke, do program initialization and then start running Duke
     *
     * @param args the appellation for the guest, should only be assigned to {@value #userName} or None
     */
    public static void main(String[] args) {
        initializeDuke(args);
        printUserGreet();
        runDuke();
        printUserFarewell();
    }

    /**
     * Assign the appellation since this in the only thing to initialize
     *
     * @param args the appellation for the guest, should only be assigned to {@value #userName} or None
     */
    private static void initializeDuke(String[] args) {
        if (args.length >= 1) {
            userName = args[args.length - 1];
        }
    }

    /**
     * Return only when user's command containing word "bye"
     * Get command from user, parse the command and then execute the corresponding command.
     */
    private static void runDuke() {
        while (true) {
            printUserPrompt();
            Command command = CommandManager.getCommand();
            printLineDivider();
            for (String token : command.getCommandTokens()) {
                if (isExiting(token)) {
                    return;
                }
            }
            CommandManager.runCommand(command);
            printLineDivider();
        }
    }

    /**
     * Print a line Divider, making the layout more clear
     */
    private static void printLineDivider() {
        // TODO Beautify
        System.out.println("------------------------------");
    }

    /**
     * Greet the user, called right after initialization
     */
    private static void printUserGreet() {
        // TODO Personalize the greeting information (maybe change DUKE to BECK or something else)
        String logo = """
                 ____        _       \s
                |  _ \\ _   _| | _____\s
                | | | | | | | |/ / _ \\
                | |_| | |_| |   <  __/
                |____/ \\__,_|_|\\_\\___|
                """;
        System.out.println("Hello from\n" + logo);
        printLineDivider();
        System.out.println("Hello, I'm Duke");
        System.out.println("What can I do for you? " + userName);
        printLineDivider();
    }

    /**
     * Say goodbye to user, called when program is ready to exit
     */
    private static void printUserFarewell() {
        System.out.println("Bye, " + userName + ". Hope to see you soon!");
        printLineDivider();
    }

    /**
     * Print {@value #userName} and prompt symbol, making the layout more clear
     */
    private static void printUserPrompt() {
        // TODO add more prompt style
        System.out.print(userName + " > ");
    }


    /**
     * Check whether the token is contained in exit command
     *
     * @param token the command token to be checked
     * @return whether the token is contained in exit command
     */
    private static boolean isExiting(String token) {
        for (String str : exitCommands) {
            if (token.equalsIgnoreCase(str)) {
                return true;
            }
        }
        return false;
    }

}
