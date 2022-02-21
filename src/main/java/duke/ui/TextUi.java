package duke.ui;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

import static duke.common.Strings.*;

/**
 * Text UI of the application.
 */
public class TextUi {
    private Scanner in;
    private PrintStream out;

    public TextUi() {
        this(System.in, System.out);
    }

    public TextUi(InputStream in, PrintStream out) {
        this.in = new Scanner(in);
        this.out = out;
    }

    /**
     * Prints a message to the console.
     * @param message the message to be printed.
     */
    public void printMessage(String message) {
        out.println(message);
    }

    /**
     * Prints a message to the console, followed by a horizontal separator.
     * @param message the message to be printed.
     */
    public void printMessageWithSeparator(String message) {
        printMessage(message);
        printMessage(HORIZONTAL_SEPARATOR);
    }

    /**
     * Prints a sequence of messages to the console, followed by a horizontal separator.
     * @param messageList the list of messages to be printed. Each element will be printed on a new line.
     */
    public void printCommandResult(ArrayList<String> messageList) {
        for (String s : messageList) {
            if (s != null) {
                printMessage(s);
            }
        }
        printMessage(HORIZONTAL_SEPARATOR);
    }

    /**
     * Prints the welcome message.
     */
    public void showWelcomeMessage() {
        printMessageWithSeparator(MESSAGE_WELCOME);
    }

    /**
     * Prints a message relating to storage file save-related errors.
     */
    public void showDataSaveErrorMessage() {
        printMessage(MESSAGE_DATA_SAVE_ERROR);
        printMessage(MESSAGE_CLOSE_TO_FIX);
    }

    /**
     * Prompts for and parses user command input.
     * @return a String array of the form {command word, command arguments}.
     */
    public String[] getUserInput() {
        System.out.print(INPUT_PROMPT);
        // Strip file separator characters from user input to avoid writing malformed data to file
        String userInput = in.nextLine().replace(FS,"");
        System.out.println(HORIZONTAL_SEPARATOR);
        String[] userInputTokenized = userInput.trim().split(" ", 2);
        if (userInputTokenized.length == 2) {
            return userInputTokenized;
        }
        return new String[] {userInputTokenized[0], ""};
    }
}
