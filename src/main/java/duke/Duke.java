package duke;

import duke.parser.Parser;
import duke.taskmanagement.TaskManager;
import duke.userinterface.UserInterface;

public class Duke {
    public static void main(String[] args) {
        UserInterface ui = new UserInterface();
        Parser parser = new Parser();
        TaskManager tasks = new TaskManager();
        ui.printGreeting();
        parser.run(tasks);
    }
}