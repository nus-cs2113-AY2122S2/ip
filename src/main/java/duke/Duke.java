package duke;

import duke.storage.FileReader;

public class Duke {

    public static void main(String[] args) {
        Ui.printGreeting();
        FileReader.retrieveTasks();
        Ui.getRequest();
        Ui.printGoodbye();
    }

}
