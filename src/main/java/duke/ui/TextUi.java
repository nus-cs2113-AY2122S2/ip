package duke.ui;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

import static duke.common.Strings.*;
import static duke.common.Strings.FS;

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

    public void printMessage(String message) {
        out.println(message);
    }

    public void printMessageWithSeparator(String message) {
        printMessage(message);
        printMessage(HORIZONTAL_SEPARATOR);
    }

    public void printCommandResult(ArrayList<String> messageList) {
        for (String s : messageList) {
            if (s != null) {
                printMessage(s);
            }
        }
        printMessage(HORIZONTAL_SEPARATOR);
    }

    public void showWelcomeMessage() {
        printMessageWithSeparator(MESSAGE_WELCOME);
    }

    public void showDataSaveErrorMessage() {
        printMessage(MESSAGE_DATA_SAVE_ERROR);
        printMessage(MESSAGE_CLOSE_TO_FIX);
    }

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
