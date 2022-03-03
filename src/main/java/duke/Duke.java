package duke;

import java.io.File;

/**
 * Start the application and the interaction with the user.
 */
public class Duke {

    public static void main(String[] args) {
        Ui.printWelcomeMessage();

        File duke = new File("./Duke.txt");
        new FileManager(duke);
        new TaskManager(duke);

        Ui.printExitProgram();
    }
}
