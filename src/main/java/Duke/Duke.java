package Duke;

import java.io.IOException;


public class Duke {

    public static void run() throws IOException, InvalidInputException {
        Ui.printWelcomeMessage();
        Storage.initializeData();
        Ui.checkCommand();
        Ui.exit();
    }
    public static void main(String[] args) throws IOException, InvalidInputException {
        run();
    }

}
