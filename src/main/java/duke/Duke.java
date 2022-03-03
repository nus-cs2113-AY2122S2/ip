package duke;

import java.util.ArrayList;
import duke.exceptions.InputLengthException;
import duke.exceptions.UnreachableTaskException;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.ToDo;
import duke.Storage;
import duke.Ui;
import duke.tasks.TaskList;

import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import java.util.Scanner;

public class Duke {

    public static int taskCounter = 0; //counts number of tasks
    private static Storage storage;
    private static Ui ui;
    private static TaskList toDos;

    public Duke() {
        storage = new Storage();
        ui = new Ui();
        toDos = new TaskList();
    }

    public void run(String filePath) {
        ui.printGreeting();

        taskCounter = storage.listCreate(filePath, toDos, taskCounter);

        while (true) {
            storage.fileWrite(filePath, toDos);

            ui.parseLine(toDos, taskCounter);
        }
    }

    public static void main(String[] args) throws InputLengthException {
        Duke duke = new Duke();
        duke.run("./src/main/java/Duke/taskList.txt");

    }
}
