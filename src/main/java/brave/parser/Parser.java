package brave.parser;

import brave.IllegalArgumentException;
import brave.data.Deadline;
import brave.data.Event;
import brave.data.TaskManager;
import brave.data.Todo;
import brave.storage.Storage;
import brave.ui.Ui;

public class Parser {

    private static boolean isExit;
    private Ui ui;

    public Parser(Ui ui) {
        this.ui = ui;
    }

    public void setExit(boolean exit) {
        isExit = exit;
    }

    public boolean getExit() {
        return isExit;
    }

    public void parse(String fullCommand, TaskManager tasks) {
        String[] splitInputs = fullCommand.split(" ", 2);
        String command = splitInputs[0]; //e.g. mark 2 -> take the first word as the command -> "mark"

        if (command.equals("bye")) {
            Storage.encode(tasks.getTasks(), "data/brave.txt");
            setExit(true);
            return;
        }

        String description;
        switch (command) {
        case "list":
            ui.printTaskList(tasks.getTasks());
            break;
        case "mark":
            try {
                tasks.markTask(Integer.parseInt(splitInputs[1]) - 1); // 0 indexing
            } catch (NumberFormatException e) {
                System.out.println("Please put in integer value");
            } catch (NullPointerException | ArrayIndexOutOfBoundsException e) {
                System.out.println("Please put in valid number of task");
            }
            break;
        case "unmark":
            try {
                tasks.unmarkTask(Integer.parseInt(splitInputs[1]) - 1); // 0 indexing
            } catch (NumberFormatException e) {
                System.out.println("Please put in integer value");
            } catch (NullPointerException | ArrayIndexOutOfBoundsException e) {
                System.out.println("Please put in valid number of task");
            }
            break;
        case "todo":
            try {
                description = splitInputs[1];
                tasks.addTask(new Todo(description));
            } catch (IndexOutOfBoundsException e) {
                System.out.println(" ☹ OOPS!!! The description of a todo cannot be empty.");
            }
            break;
        case "deadline":
            // To-do validate arguments~
            String[] arguments = splitInputs[1].split(" /by ", 2);
            description = arguments[0];
            String by = arguments[1];
            tasks.addTask(new Deadline(description, by));
            break;
        case "event":
            arguments = splitInputs[1].split(" /at ", 2);
            description = arguments[0];
            String eventTime = arguments[1];
            tasks.addTask(new Event(description, eventTime));
            break;
        case "delete":
            try {
                tasks.deleteTask(Integer.parseInt(splitInputs[1]) - 1); // 0 indexing
            } catch (NumberFormatException e) {
                System.out.println("Please put in integer value");
            } catch (NullPointerException | ArrayIndexOutOfBoundsException e) {
                System.out.println("Please put in valid number of task");
            }
            break;
        default:
            try {
                throw new IllegalArgumentException();
            } catch (IllegalArgumentException e) {
                System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                System.out.println("Available command are -> list/mark/unmark/todo/deadline/event");
            }
            break;
        }
    }
}
