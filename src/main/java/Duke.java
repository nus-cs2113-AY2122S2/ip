import tasks.*;
import java.util.Scanner;
import java.util.*;
import java.io.*;

public class Duke {

    private Storage storage;
    private ArrayList<Task> allTasks = new ArrayList<Task>();
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);

        try {
            allTasks = new ArrayList<Task>(storage.load());
        } catch (IOException e) {
            ui.showLoadingError();
            allTasks = new ArrayList<Task>();
        }
    }

    public void run() throws IOException {
        boolean notQuit = true;
        int index;
        String description, detail, by, at;
        Task t;

        ui.greeting();

        while (notQuit) {
            String command = ui.readCommand();
            switch (command.split(" ")[0]) {
                case "bye":
                    ui.sayGoobye();
                    notQuit = false;
                    break;

                case "mark":
                    index = Integer.parseInt(command.split(" ")[1]) - 1;
                    t = allTasks.get(index);
                    ui.markAndDisplayTask(t);
                    break;

                case "unmark":
                    index = Integer.parseInt(command.split(" ")[1]) - 1;
                    t = allTasks.get(index);
                    ui.unmarkAndDisplayTask(t);
                    break;

                case "list":
                    ui.displayListWithStatus(allTasks, allTasks.size());
                    break;

                case "delete":
                    index = Integer.parseInt(command.split(" ")[1]) - 1;
                    ui.deleteAndDisplayTask(allTasks.get(index), allTasks.size() - 1);
                    allTasks.remove(index);
                    storage.writeToDukeFile(allTasks);
                    break;

                case "todo":
                    try {
                        String[] str = command.split(" ");
                        if (str.length < 2) {
                            throw new DukeException();
                        }
                        description = command.replace("todo ", "");
                    } catch (DukeException e) {
                        ui.raiseExceptionInTodo();
                        break;
                    }

                    t = new Todo(description);
                    allTasks.add(allTasks.size(), t);
                    ui.displayTask(t, allTasks.size());
                    storage.writeToDukeFile(allTasks);
                    break;

                case "deadline":
                    try {
                        String[] str = command.split(" ");
                        if (str.length < 2) {
                            throw new DukeException();
                        }
                        detail = command.replace("deadline ", "");
                        description = detail.split("/by")[0];
                        by = detail.split("/by")[1];
                        /**
                         * To avoid content with only spacing.
                         */
                        if (by.replace(" ", "") == "") {
                            throw new DukeException();
                        }
                    } catch (DukeException e) {
                        ui.raiseExceptionInDeadline();
                        break;
                    } catch (ArrayIndexOutOfBoundsException e) {
                        ui.raiseExceptionInDeadline();
                        break;
                    }

                    t = new Deadline(description, by);
                    allTasks.add(t);
                    ui.displayTask(t, allTasks.size());
                    storage.writeToDukeFile(allTasks);
                    break;

                case "event":
                    try {
                        String[] str = command.split(" ");
                        if (str.length < 2) {
                            throw new DukeException();
                        }
                        detail = command.replace("event ", "");
                        description = detail.split("/at")[0];
                        at = detail.split("/at")[1];
                        /**
                         * To avoid content with only spacing.
                         */
                        if (at.replace(" ", "") == "") {
                            throw new DukeException();
                        }
                    } catch (DukeException e) {
                        ui.raiseExceptionInEvent();
                        break;
                    } catch (ArrayIndexOutOfBoundsException e) {
                        ui.raiseExceptionInEvent();
                        break;
                    }

                    t = new Event(description, at);
                    allTasks.add(t);
                    ui.displayTask(t, allTasks.size());
                    storage.writeToDukeFile(allTasks);
                    break;

                default:
                    try {
                        throw new DukeException();
                    } catch (DukeException e) {
                        ui.raiseExceptionInCommand();
                    }
                    break;
            }

        }
    }

    public static void main(String[] args) throws DukeException, IOException {
        new Duke("./data/duke.txt").run();
    }
}
