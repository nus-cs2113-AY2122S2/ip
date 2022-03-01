package duke;


import duke.cmds.Cmds;
import duke.deadline.Deadline;
import duke.duke_exception.DukeException;
import duke.event.Event;
import duke.event.ToDo;
import duke.task.Task;
import duke.Ui;
import netscape.javascript.JSObject;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

/**
 * Duke class that runs highest level logic of todo app
 */
public class Duke {

    private static final Boolean isDebugMode = false;

    /**
     * Main function where highest level logic runs
     * @param args Command line arguments
     * @throws DukeException
     */
    public static void main(String[] args) throws DukeException {
        Ui.printHello();
        TaskList tasks = new TaskList();
        Parser parser = new Parser(tasks);
        parser.enterInputLoop();
        tasks.saveTaskList();
        Ui.printBye();
    }
}
