package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;
import duke.task.TaskList;

import java.io.*;
import java.util.Scanner;

public class Duke {

    private static Ui ui = new Ui();
    private static Storage storage = new Storage();

    private static TaskList taskList = new TaskList();

    public static void printLine() {
        System.out.println("-----------------------------------------");
    }


    public static void main(String[] args) throws DukeException, IOException {
        ui.greeting();
        storage.checkFileExists(taskList);
        Ui.checkCommand(taskList);
    }
}

