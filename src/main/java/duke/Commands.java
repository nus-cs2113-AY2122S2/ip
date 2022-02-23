package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Todo;

import java.io.IOException;

public class Commands {
    //Commands
    public static final String COMMAND_BYE = "bye";
    public static final String COMMAND_LIST = "list";
    public static final String COMMAND_MARK = "mark";
    public static final String COMMAND_UNMARK = "unmark";
    public static final String COMMAND_TODO = "todo";
    public static final String COMMAND_DEADLINE = "deadline";
    public static final String COMMAND_EVENT = "event";
    public static final String COMMAND_DELETE = "delete";

    public static void parseCommand(String input) throws DukeException, IOException {
        if (input.length() == 0) {
            throw new DukeException(Ui.ERROR_NO_INPUT);
        }
        String command = Parser.getCommand(input);
        switch(command) {
        case COMMAND_LIST:
            System.out.println(Ui.MESSAGE_LIST);
            Parser.listTasks();
            break;
        case COMMAND_MARK:
            executeMark(input);
            break;
        case COMMAND_UNMARK:
            executeUnmark(input);
            break;
        case COMMAND_DELETE:
            executeDelete(input);
            break;
        case COMMAND_TODO:
            executeTodo(input);
            break;
        case COMMAND_DEADLINE:
            executeDeadline(input);
            break;
        case COMMAND_EVENT:
            executeEvent(input);
            break;
        default:
            throw new DukeException(Ui.ERROR_NOT_VALID_COMMAND);
        }
    }

    public static void executeDelete(String input) {
        try {
            String taskNumber = Parser.parseMarkOrUnmarkOrDelete(input);
            int taskIndex = Integer.parseInt(taskNumber) - 1;
            System.out.println(Ui.MESSAGE_DELETE_SUCCESS + Ui.taskList.get(taskIndex));
            Ui.taskList.remove(taskIndex);
        } catch (DukeException error) {
            System.out.println(error.getMessage() + COMMAND_DELETE + ".");
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            System.out.println(Ui.ERROR_INVALID_TASK_NUMBER);
        }
    }

    public static void executeMark(String input) {
        try {
            String taskNumber = Parser.parseMarkOrUnmarkOrDelete(input);
            int taskIndex = Integer.parseInt(taskNumber) - 1;
            Ui.taskList.get(taskIndex).markAsDone();
            System.out.println(Ui.MESSAGE_MARK_SUCCESS + Ui.taskList.get(taskIndex));
        } catch (DukeException error) {
            System.out.println(error.getMessage() + COMMAND_MARK + ".");
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            System.out.println(Ui.ERROR_INVALID_TASK_NUMBER);
        }
    }

    public static void executeUnmark(String input) {
        try {
            String taskNumber = Parser.parseMarkOrUnmarkOrDelete(input);
            int taskIndex = Integer.parseInt(taskNumber) - 1;
            Ui.taskList.get(taskIndex).markAsNotDone();
            System.out.println(Ui.MESSAGE_UNMARK_SUCCESS + Ui.taskList.get(taskIndex));
        } catch (DukeException error) {
            System.out.println(error.getMessage() + COMMAND_UNMARK + ".");
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            System.out.println(Ui.ERROR_INVALID_TASK_NUMBER);
        }
    }

    public static void executeTodo(String input) {
        if (!input.contains(" ")) { // Checks for presence of description
            System.out.println(Ui.ERROR_INVALID_SYNTAX + COMMAND_UNMARK + ".");
            return;
        }
        String description = Parser.getDescription(input);
        Todo task = new Todo(description);
        Ui.taskList.add(task);
        task.printAddToListMessage();
    }

    public static void executeDeadline(String input) {
        if (!input.contains(Parser.FLAG_DEADLINE)) { // Checks for presence of description
            System.out.println(Ui.ERROR_INVALID_SYNTAX + COMMAND_DEADLINE + ".");
            return;
        }
        String[] parsedCommand = Parser.parseDeadlineOrEvent(input);
        String description = parsedCommand[0];
        String by = parsedCommand[1];
        Deadline task = new Deadline(description, by);
        Ui.taskList.add(task);
        task.printAddToListMessage();
    }

    public static void executeEvent(String input) {
        if (!input.contains(Parser.FLAG_EVENT)) { // Checks for presence of description
            System.out.println(Ui.ERROR_INVALID_SYNTAX + COMMAND_EVENT + ".");
            return;
        }
        String[] parsedCommand = Parser.parseDeadlineOrEvent(input);
        String description = parsedCommand[0];
        String at = parsedCommand[1];
        Event task = new Event(description, at);
        Ui.taskList.add(task);
        task.printAddToListMessage();
    }
}
