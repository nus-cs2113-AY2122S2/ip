package duke;

import duke.taskmanagement.TaskManager;
import duke.userinterface.UserInterface;

public class Duke {
    public static void main(String[] args) {
        printGreeting();
        TaskManager tasks = new TaskManager();
        UserInterface ui = new UserInterface();
        ui.run(tasks);
    }

    private static void printGreeting() {
        System.out.println("Hello! I'm Duke.");
        System.out.println("What can I do for you?");
    }
}
