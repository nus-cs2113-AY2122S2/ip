package duke;


import duke.exceptions.InputLengthException;
import duke.tasks.TaskList;

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

            ui.parseLine(toDos);
        }
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run("./src/main/java/Duke/taskList.txt");

    }
}
