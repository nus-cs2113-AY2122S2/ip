package duke;

import duke.parser.Parser;
import duke.taskmanagement.TaskManager;

public class Duke {
    public static void main(String[] args) {
        printGreeting();
        TaskManager tasks = new TaskManager();
        Parser parser = new Parser();
        parser.run(tasks);
    }

    private static void printGreeting() {
        System.out.println("Hello! I'm Duke.");
        System.out.println("What can I do for you?");
    }
}
