package duke;

import duke.parser.Parser;
import duke.taskmanagement.TaskManager;
import duke.userinterface.UserInterface;

public class Duke {
    public static void main(String[] args) {
        UserInterface ui = new UserInterface();
        TaskManager tasks = new TaskManager();
        Parser parser = new Parser();
        ui.printGreeting();
        parser.run(tasks);
    }
}