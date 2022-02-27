package duke;

import duke.task.Task;
import duke.task.TaskList;

import java.io.IOException;

public class Ui {
    public static void printLine() {
        System.out.println("-----------------------------------------");
    }

    public static void greeting() {
        String logo = "  ____        _        \n"
                + " |  _ \\ _   _| | _____ \n"
                + " | | | | | | | |/ / _ \\\n"
                + " | |_| | |_| |   <  __/\n"
                + " |____/ \\__,_|_|\\_\\___|\n";
        System.out.println(" Hello from\n" + logo);
        printLine();

        System.out.println(" Hi! This is Duke!");
        System.out.println(" I'm glad to be at your service.");
        System.out.println(" What can I help you with?");
        printLine();
    }

    public static void bye() {
        printLine();
        System.out.println(" Bye. Hope to see you again soon!");
        printLine();
    }

    public static void printGuide() {
        System.out.println(" use \"list\" to show the task list");
        System.out.println(" use \"todo task\" to add a task without any date/time attached to it\"");
        System.out.println(" use \"deadline task /by time\" to add a task that need to be done before a specific time/date");
        System.out.println(" use \"event task /at time\" to add a task that tasks that start at a specific time and ends at a specific time");
        System.out.println(" use \"mark taskIndex\" to mark that task as done");
        System.out.println(" use \"unmark taskIndex\" to mark that task as not done");
        System.out.println(" use \"delete taskIndex\" to delete a task");
        System.out.println(" use \"find keywords\" to find tasks");
        System.out.println(" use \"bye\" to exit the chatbot");
        printLine();
    }

    public static void checkCommand(TaskList taskList) throws DukeException, IOException {
        Parser.handleCommand(taskList);
    }


}
