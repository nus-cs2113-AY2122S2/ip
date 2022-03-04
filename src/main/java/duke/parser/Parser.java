package duke.parser;

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

    /**
     * @param input
     */
    public Parser(String input) {
        this.input = input;
    }

    /**
     * @param input
     * @return
     */
    public static String getCommandFromUserInput(String input) {
        if (input == "bye" ||input == "list") return input;
        return input.split(" ")[0];
    }

    /**
     * @param input
     * @return
     */
    public static int getTaskIndex(String input) {
        int indexOfSpace = input.indexOf(" ");
        int indexOfTask = Integer.parseInt(input.substring(indexOfSpace + 1));
        return indexOfTask;
    }

    /**
     * @param input
     * @param command
     * @param taskList
     */
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
            case "find":
                taskList.findTask(input);
                break;
            default:
                throw new DukeException("OOPS!!! I'm sorry, but I cannot understand you, please type in the correct format");
            }
        } catch (NullPointerException e) {
            System.out.println("OOPS!!! I'm sorry, but seems like there is no such task :-( ");
            Ui.printLine();
        } catch (DukeException e) {
            System.out.println("OOPS!!! I'm sorry, but I cannot understand you, please type in the correct format");
            Ui.printLine();
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println("OOPS!!! I'm sorry, but I cannot understand you, please type in the correct format");
            Ui.printLine();
        }
    }


    /**
     * @return
     */
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


    /**
     * @param input
     * @return
     */
    //1.[T][ ] borrow book
    public Todo generateTodo(String input) {
        String description = input.substring(7);
        return new Todo(description);
    }

    /**
     * @param input
     * @return
     */
    //1.[D][ ] return book (by: June 6th)
    public Deadline generateDeadline(String input) {
        String description = input.substring(7, input.indexOf("("));
        String by = input.substring(input.indexOf(":"));
        return new Deadline(description, by);
    }

    /**
     * @param input
     * @return
     */
    //3.[E][ ] project meeting (at: Aug 6th 2-4pm)
    public Event generateEvent(String input) {
        String description = input.substring(7, input.indexOf("("));
        String at = input.substring(input.indexOf(":"));
        return new Event(description, at);
    }
}


