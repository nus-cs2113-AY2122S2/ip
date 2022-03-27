import tasks.*;
import java.util.*;
import java.io.*;

public class Duke {

    private static Storage storage;
    private static ArrayList<Task> allTasks = new ArrayList<Task>();
    private static Ui ui;

    /**
     * Read txt fild for the initialization of tasks
     *
     * @param filePath
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);

        try {
            File dataDir = new File("./data");
            File dataText = new File("./data/duke.txt");
            if (!dataDir.exists()){
                dataDir.mkdirs();
            }
            if (!dataText.exists()){
                dataText.createNewFile();
            }
            allTasks = new ArrayList<Task>(storage.load());
        } catch (IOException e) {
            ui.showLoadingError();
            allTasks = new ArrayList<Task>();

        }
    }

    /**
     * quit the program
     *
     * @throws IOException
     */
    public static void quit() throws IOException {
        ui.sayGoobye();
        storage.writeToDukeFile(allTasks);
    }

    /**
     * mark a task as done by index
     *
     * @param command
     */
    public static void mark(String command) {
        try {
            int index = Integer.parseInt(command.split(" ")[1].trim()) - 1;
            if (index >= allTasks.size() || index < 0) {
                throw new DukeException();
            }
            Task t = allTasks.get(index);
            ui.markAndDisplayTask(t);
        } catch (NumberFormatException e) {
            ui.raiseExceptionInIndex();
        }
        catch (ArrayIndexOutOfBoundsException e) {
            ui.raiseExceptionInIndex();
        }
        catch (DukeException e) {
            ui.raiseExceptionInIndex();
        }
    }

    /**
     * mark a task as undone by index
     *
     * @param command
     */
    public static void undoneMark(String command) {
        try {
            int index = Integer.parseInt(command.split(" ")[1].trim()) - 1;
            if (index >= allTasks.size() || index < 0) {
                throw new DukeException();
            }
            Task t = allTasks.get(index);
            ui.unmarkAndDisplayTask(t);
        } catch (NumberFormatException e) {
            ui.raiseExceptionInIndex();
        }
        catch (DukeException e) {
            ui.raiseExceptionInIndex();
        }
        catch (ArrayIndexOutOfBoundsException e) {
            ui.raiseExceptionInIndex();
        }
    }

    /**
     * find all tasks containing a specific string
     *
     * @param command
     */
    public static void find(String command) {
        try{
        ArrayList<Task> foundTasks = new ArrayList<Task>();
        String keyword = command.split(" ")[1].trim();
        for (int i = 0; i < allTasks.size(); i++){
            Task task = allTasks.get(i);
            if(task.description.contains(keyword)){
                foundTasks.add(task);
            }
        }
        ui.displayFoundTasks(foundTasks);
        } catch (ArrayIndexOutOfBoundsException e) {
            ui.raiseExceptionInIndex();
        }
    }

    /**
     * delete a task by index
     *
     * @param command
     */
    public static void delete(String command) {
        try {
            int index = Integer.parseInt(command.split(" ")[1].trim()) - 1;
            if (index >= allTasks.size() || index < 0) {
                throw new DukeException();
            }
            ui.deleteAndDisplayTask(allTasks.get(index), allTasks.size() - 1);
            allTasks.remove(index);
        } catch (NumberFormatException e) {
            ui.raiseExceptionInIndex();
        }
        catch (DukeException e) {
            ui.raiseExceptionInIndex();
        }
        catch (ArrayIndexOutOfBoundsException e) {
            ui.raiseExceptionInIndex();
        }
    }

    /**
     * add a Todo
     *
     * @param command
     */
    public static void addTodo(String command) {
        String description;
        try {
            String[] str = command.split(" ");
            if (str.length < 2) {
                throw new DukeException();
            }
            description = command.replace("todo ", "");
            Task t = new Todo(description);
            allTasks.add(allTasks.size(), t);
            ui.displayTask(t, allTasks.size());
        } catch (DukeException e) {
            ui.raiseExceptionInTodo();
        }
    }

    /**
     * add a Deadline
     *
     * @param command
     */
    public static void addDeadline(String command) {
        String detail, description, by;
        try {
            String[] str = command.split(" ");
            if (str.length < 2) {
                throw new DukeException();
            }
            detail = command.replace("deadline ", "");
            description = detail.split("/by")[0];
            by = detail.split("/by")[1];
            if (by.replace(" ", "") == "") {
                throw new DukeException();
            }
            Task t = new Deadline(description, by);
            allTasks.add(t);
            ui.displayTask(t, allTasks.size());
        } catch (DukeException e) {
            ui.raiseExceptionInDeadline();
        } catch (ArrayIndexOutOfBoundsException e) {
            ui.raiseExceptionInDeadline();
        }
    }

    /**
     * add an Event
     *
     * @param command
     */
    public static void addEvent(String command) {
        String detail, description, at;
        try {
            String[] str = command.split(" ");
            if (str.length < 2) {
                throw new DukeException();
            }
            detail = command.replace("event ", "");
            description = detail.split("/at")[0];
            at = detail.split("/at")[1];
            if (at.replace(" ", "") == "") {
                throw new DukeException();
            }
            Task t = new Event(description, at);
            allTasks.add(t);
            ui.displayTask(t, allTasks.size());
        } catch (DukeException e) {
            ui.raiseExceptionInEvent();
        } catch (ArrayIndexOutOfBoundsException e) {
            ui.raiseExceptionInEvent();
        }
    }

    /**
     * analyse user input
     *
     * @throws IOException
     */
    public void run() throws IOException {
        boolean notQuit = true;
        ui.greeting();

        while (notQuit) {
            String command = ui.readCommand().replaceAll("( )+", " ");
            switch (command.split(" ")[0]) {
            case "bye":
                quit();
                notQuit = false;
                break;

            case "mark":
                mark(command);
                break;

            case "unmark":
                undoneMark(command);
                break;

            case "list":
                ui.displayListWithStatus(allTasks, allTasks.size());
                break;

            case "find":
                find(command);
                break;

            case "delete":
                delete(command);
                break;

            case "todo":
                addTodo(command);
                break;

            case "deadline":
                addDeadline(command);
                break;

            case "event":
                addEvent(command);
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

    /**
     * main function of the project
     *
     * @param args
     * @throws DukeException
     * @throws IOException
     */
    public static void main(String[] args) throws DukeException, IOException {
        new Duke("./data/duke.txt").run();
    }
}
