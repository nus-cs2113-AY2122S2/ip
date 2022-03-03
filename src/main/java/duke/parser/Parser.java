package duke.parser;

import duke.exceptions.ChangeStatusException;
import duke.exceptions.DukeException;
import duke.task.Event;
import duke.task.Deadline;
import duke.task.Task;
import duke.task.Todo;
import duke.taskList.TaskList;
import duke.ui.Ui;


public class Parser {
    private final String input;
    private final Ui ui = new Ui();

    public Parser(String input) {
        this.input = input;
    }

    public static String getCommandFromUserInput(String input) {
        if (input == "bye" ||input == "list") return input;
        return input.split(" ")[0];
    }

    public static int getTaskIndex(String input) {
        int indexOfSpace = input.indexOf(" ");
        int indexOfTask = Integer.parseInt(input.substring(indexOfSpace + 1));
        return indexOfTask;
    }

    public static void runCommand(String input, String command, TaskList taskList) {
        try {
            switch (command) {
            case "mark": {
                int taskIndex = getTaskIndex(input);
                taskList.markTask(taskIndex, command);
                break;
            }
            case "unmark": {
                int taskIndex = getTaskIndex(input);
                taskList.unmarkTask(taskIndex, command);
                break;
            }
            case "list":
                taskList.printAllTasks();
                break;
            case "deadline":
                taskList.addDeadline(input);
                break;
            case "todo":
                taskList.addTodo(input);
                break;
            case "event":
                taskList.addEvent(input);
                break;
            case "bye":
                break;
            case "delete":
                taskList.deleteTask(getTaskIndex(input));
                break;
            default:
                throw new DukeException(" ", " ");
            }
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-( ");
            System.out.println("----------------------------------------------------------------");
        }
        catch (NullPointerException e) {
            System.out.println("OOPS!!! I'm sorry, but seems like there is no such task :-( ");
            System.out.println("----------------------------------------------------------------");
        } catch (DukeException e) {
            System.out.print("OOPS!!! The description of a ");
            System.out.print(e);
            System.out.println(" cannot be empty");
            System.out.println("----------------------------------------------------------------");
        }
    }


    public Task getTaskFromLocalFile() {

        try {

            char curTask = input.charAt(1);
            switch (curTask) {
            case 'T': {
              return generateTodo(input);
            }

            case 'D': {
                return generateDeadline(input);
            }

            case 'E': {
                return generateEvent(input);
            }
            }
        } catch (StringIndexOutOfBoundsException e) {
            ui.PrintStringIndexOutOfBoundsException();
        }
        return new Task("Default task");
    }


    //1.[T][ ] borrow book
    public Todo generateTodo(String input) {
        String description = input.substring(7);
        return new Todo(description);
    }

    //1.[D][ ] return book (by: June 6th)
    public Deadline generateDeadline(String input) {
        String description = input.substring(7, input.indexOf("("));
        String by = input.substring(input.indexOf(":"));
        return new Deadline(description, by);
    }

    //3.[E][ ] project meeting (at: Aug 6th 2-4pm)
    public Event generateEvent(String input) {
        String description = input.substring(7, input.indexOf("("));
        String at = input.substring(input.indexOf(":"));
        return new Event(description, at);
    }
}


