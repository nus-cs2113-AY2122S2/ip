package duke;

import java.util.ArrayList;
import duke.exceptions.InputLengthException;
import duke.exceptions.UnreachableTaskException;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.ToDo;
import duke.Storage;
import duke.Ui;

import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import java.util.Scanner;

public class Duke {

    public static int taskCounter = 0; //counts number of tasks
    public static Storage storage;
    public static Ui ui;

    public static void main(String[] args) throws InputLengthException {

        storage = new Storage();
        ui = new Ui();
        //Scanner sc = new Scanner(System.in);
        //ToDo[] toDos = new ToDo[100]; //holds all tasks given
        ArrayList<ToDo> toDos = new ArrayList<>();

        ui.printGreeting();

        taskCounter = storage.listCreate("./src/main/java/Duke/taskList.txt", toDos, taskCounter);

        while (true) {

            storage.fileWrite("./src/main/java/Duke/taskList.txt", toDos);

            ui.parseLine(toDos, taskCounter);


        }

    }
}
