package Duke;

import java.io.IOException;

import static Duke.Parser.isBye;

public class Ui {
    /**
     * Prints the welcome message when user first launch Duke
     */
    public static void printWelcomeMessage() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
    }

    /**
     * Keeps checking for the user input and commands
     * @throws IOException
     * @throws InvalidInputException
     */
    public static void checkCommand() throws IOException {
        Parser.handleCommand();
    }

    /**
     * Exits Duke when user command is 'bye'
     */
    public static void exit() {
        if(isBye())
        System.out.println("Bye. Hope to see you again soon!");
    }

}
