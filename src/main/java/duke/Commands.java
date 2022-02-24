package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.io.IOException;
import java.util.ArrayList;

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
    public static final String COMMAND_FIND = "find";

    /**
     * Parses user input and calls the correct method to execute command if command is valid.
     *
     * @param input input by the user
     * @throws DukeException If there is no input by the user or command is invalid
     * @throws IOException If there is an IOException
     */
    public static void parseCommand(String input) throws DukeException, IOException {
        if (input.length() == 0) {
            throw new DukeException(Ui.ERROR_NO_INPUT);
        }
        String command = Parser.getCommand(input);
        switch(command) {
        case COMMAND_LIST:
            executeList();
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
        case COMMAND_FIND:
            executeFind(input);
            break;
        default:
            throw new DukeException(Ui.ERROR_NOT_VALID_COMMAND);
        }
    }

    /**
     * Prints the list of tasks in taskList.
     */
    public static void executeList() {
        if (Ui.taskList.size() > 0) {
            System.out.println(Ui.MESSAGE_LIST);
            Parser.listTasks(Ui.taskList);
        } else {
            System.out.println(Ui.MESSAGE_NO_TASKS);
        }
    }

    /**
     * Finds all tasks with descriptions matching the user's requested content and prints the
     * corresponding tasks.
     *
     * @param input full find command provided by the user
     */
    public static void executeFind(String input) {
        try {
            String content = Parser.parseOneParameter(input);
            ArrayList<Task> resultantList = new ArrayList<>();
            for (Task task: duke.Ui.taskList) {
                if (task.getDescription().contains(content)) {
                    resultantList.add(task);
                }
            }
            if (resultantList.size() > 0) {
                System.out.println(Ui.MESSAGE_FIND_RESULT + "'" + content + "':");
                Parser.listTasks(resultantList);
            } else {
                System.out.println(Ui.MESSAGE_NO_FIND_RESULT + " '" + content + "'.");
            }
        } catch (DukeException | ArrayIndexOutOfBoundsException error) {
            System.out.println(Ui.ERROR_INVALID_SYNTAX + COMMAND_FIND + ". " + Ui.ERROR_NO_CONTENT);
        }

    }

    /**
     * Deletes a specified task from the list.
     *
     * @param input task number
     */
    public static void executeDelete(String input) {
        try {
            String taskNumber = Parser.parseOneParameter(input);
            int taskIndex = Integer.parseInt(taskNumber) - 1;
            System.out.println(Ui.MESSAGE_DELETE_SUCCESS + Ui.taskList.get(taskIndex));
            Ui.taskList.remove(taskIndex);
        } catch (DukeException error) {
            System.out.println(error.getMessage() + COMMAND_DELETE + ".");
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            System.out.println(Ui.ERROR_INVALID_TASK_NUMBER);
        }
    }

    /**
     * Marks a specified task as done.
     *
     * @param input task number
     */
    public static void executeMark(String input) {
        try {
            String taskNumber = Parser.parseOneParameter(input);
            int taskIndex = Integer.parseInt(taskNumber) - 1;
            Ui.taskList.get(taskIndex).markAsDone();
            System.out.println(Ui.MESSAGE_MARK_SUCCESS + Ui.taskList.get(taskIndex));
        } catch (DukeException | NumberFormatException | IndexOutOfBoundsException error) {
            System.out.println(Ui.ERROR_INVALID_SYNTAX + COMMAND_MARK + ". " + Ui.ERROR_INVALID_TASK_NUMBER);
        }
    }

    /**
     * Marks a specified task as undone.
     *
     * @param input task number
     */
    public static void executeUnmark(String input) {
        try {
            String taskNumber = Parser.parseOneParameter(input);
            int taskIndex = Integer.parseInt(taskNumber) - 1;
            Ui.taskList.get(taskIndex).markAsNotDone();
            System.out.println(Ui.MESSAGE_UNMARK_SUCCESS + Ui.taskList.get(taskIndex));
        } catch (DukeException | NumberFormatException | IndexOutOfBoundsException error) {
            System.out.println(Ui.ERROR_INVALID_SYNTAX + COMMAND_UNMARK + ". " + Ui.ERROR_INVALID_TASK_NUMBER);
        }
    }

    /**
     * Adds a Todo task specified by the user to the list of tasks.
     *
     * @param input user's Todo command
     */
    public static void executeTodo(String input) {
        try {
            String description = Parser.parseOneParameter(input);
            Todo task = new Todo(description);
            Ui.taskList.add(task);
            task.printAddToListMessage();
        } catch (DukeException | ArrayIndexOutOfBoundsException error) {
            System.out.println(Ui.ERROR_INVALID_SYNTAX + COMMAND_TODO + ". " + Ui.ERROR_NO_DESCRIPTION);
        }

    }

    /**
     * Adds a Deadline task specified by the user to the list of tasks.
     *
     * @param input user's Deadline command
     */
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

    /**
     * Adds an Event task specified by the user to the list of tasks.
     *
     * @param input user's Event command
     */
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
