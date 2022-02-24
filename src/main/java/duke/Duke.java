package duke;

import task.Event;
import task.Task;
import errors.Errors;
import task.Todo;
import ui.DukeUi;

import java.util.Scanner;

import static duke.Parser.getFirstWordOfCommand;
import static duke.TaskList.*;
import static duke.Storage.populateFromTaskFile;
import static ui.DukeUi.takeInputAndProcess;


public class Duke {

    public static void main(String[] args)  {
        DukeUi ui = new DukeUi();
        Storage storage = new Storage();
        TaskList tasklist = new TaskList(populateFromTaskFile());
        takeInputAndProcess();
    }




}
