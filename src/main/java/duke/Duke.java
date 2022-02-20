package duke;

/**
 * Entry point of the Duke application.
 * Initializes the application and starts the interaction with the user.
 */
public class Duke {
    private UI ui;
    protected LocalStorage localStorage;
    protected TaskList taskList;

    public Duke(String fileStoragePath) {
        this.localStorage = new LocalStorage(fileStoragePath);
        this.taskList = new TaskList(localStorage);
    }

    /** Runs the program until termination.  */
    public void run() {
        start();
        runCommandUntilExitCommand();
        exit();
    }

    /** Sets up the required objects, and prints the intro message.   */
    public void start() {
        this.ui = new UI();
        ui.printIntro();
    }

    /** Reads the user command and executes it, until the user issues the bye command.  */
    public void runCommandUntilExitCommand() {
        String command;
        do {
            String userInput = ui.getUserCommand();
            command = CommandParser.getCommandFromUserInput(userInput);
            CommandParser.executeCommand(userInput, command, taskList);
        } while (!command.equalsIgnoreCase("bye"));
    }

    /** Prints the Goodbye message and exits. */
    public void exit() {
        ui.printOutro();
        System.exit(0);
    }

    /**
     * The main function to start running Duke application
     *
     * @param args Arguments.
     */
    public static void main(String[] args) {
        new Duke("localStorage.csv").run();
    }
}
