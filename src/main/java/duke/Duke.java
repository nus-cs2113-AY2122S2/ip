package duke;

import duke.Ui.CommandLineInputHandler;
import duke.Ui.CommandLineOutputUtil;

/**
 * Runs the Duke chat-bot which takes in user input,
 * and performs certain actions for specific commands.
 */
public class Duke {

    public static void main(String[] args) {
        CommandLineOutputUtil.greet();
        CommandLineInputHandler.runDuke();
    }

}
