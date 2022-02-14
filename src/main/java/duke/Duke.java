package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    //Messages
    public static final String MESSAGE_WELCOME = "Hi there! I'm Domo the chat bot.\nWhat would you like to do?";
    public static final String MESSAGE_BYE = "Goodbye. Hope to see you again soon!";
    public static final String MESSAGE_LIST = "Here is your list so far:";
    public static final String MESSAGE_NO_TASKS = "You don't seem to have any tasks so far!";
    public static final String MESSAGE_INSTRUCTION = "Type a valid command (bye, list, mark, unmark, delete, todo, deadline, event) or add a task to your list:";
    public static final String MESSAGE_MARK_SUCCESS = "Congrats! You've completed:\n";
    public static final String MESSAGE_UNMARK_SUCCESS = "Aw, you've marked this as undone:\n";
    public static final String MESSAGE_DELETE_SUCCESS = "Alright, deleting this task:\n";

    //Commands
    public static final String COMMAND_BYE = "bye";
    public static final String COMMAND_LIST = "list";
    public static final String COMMAND_MARK = "mark";
    public static final String COMMAND_UNMARK = "unmark";
    public static final String COMMAND_TODO = "todo";
    public static final String COMMAND_DEADLINE = "deadline";
    public static final String COMMAND_EVENT = "event";
    public static final String COMMAND_DELETE = "delete";

    //Errors
    public static final String ERROR_NO_INPUT = "Hmmmm... you didn't type anything. Please try again using a valid command!";
    public static final String ERROR_NOT_VALID_COMMAND = "Sorry, command is not recognised. Please try again using a valid command!";
    public static final String ERROR_INVALID_SYNTAX = "You've entered an invalid syntax for ";
    public static final String ERROR_INVALID_TASK_NUMBER = "Please enter a valid task number!";

    //Flags
    public static final String FLAG_DEADLINE = " /by ";
    public static final String FLAG_EVENT = " /at ";

    //Misc text
    public static final String SEPARATOR = "───────────────────────────────────";

    protected static final Scanner scan = new Scanner(System.in);
    protected static ArrayList<Task> taskList = new ArrayList<>();

    public static void main(String[] args) {
        greet();
        acceptCommand();
        exit();
    }

    public static void greet() {
        printLine();
        System.out.println(MESSAGE_WELCOME);
    }

    public static void exit() {
        printLine();
        System.out.println(MESSAGE_BYE);
    }

    public static void acceptCommand() {
        String input = getInput();
        while (!isBye(input)) {
            try {
                executeCommand(input);
            } catch (DukeException error){
                System.out.println(error.getMessage());
            }
            input = getInput();
        }
    }

    public static void executeCommand(String input) throws DukeException{
        if (input.length() == 0) {
            throw new DukeException(ERROR_NO_INPUT);
        }
        String command = getCommand(input);
        switch(command) {
        case COMMAND_LIST:
            System.out.println(MESSAGE_LIST);
            listTasks();
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
            throw new DukeException(ERROR_NOT_VALID_COMMAND);
        }
    }

    public static void executeDelete(String input) {
        try {
            String taskNumber = parseMarkOrUnmarkOrDelete(input);
            int taskIndex = Integer.parseInt(taskNumber) - 1;
            System.out.println(MESSAGE_DELETE_SUCCESS + taskList.get(taskIndex));
            taskList.remove(taskIndex);
        } catch (DukeException error) {
            System.out.println(error.getMessage() + COMMAND_DELETE + ".");
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            System.out.println(ERROR_INVALID_TASK_NUMBER);
        }
    }

    public static void executeMark(String input) {
        try {
            String taskNumber = parseMarkOrUnmarkOrDelete(input);
            int taskIndex = Integer.parseInt(taskNumber) - 1;
            taskList.get(taskIndex).markAsDone();
            System.out.println(MESSAGE_MARK_SUCCESS + taskList.get(taskIndex));
        } catch (DukeException error) {
            System.out.println(error.getMessage() + COMMAND_MARK + ".");
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            System.out.println(ERROR_INVALID_TASK_NUMBER);
        }
    }

    public static void executeUnmark(String input) {
        try {
            String taskNumber = parseMarkOrUnmarkOrDelete(input);
            int taskIndex = Integer.parseInt(taskNumber) - 1;
            taskList.get(taskIndex).markAsNotDone();
            System.out.println(MESSAGE_UNMARK_SUCCESS + taskList.get(taskIndex));
        } catch (DukeException error) {
            System.out.println(error.getMessage() + COMMAND_UNMARK + ".");
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            System.out.println(ERROR_INVALID_TASK_NUMBER);
        }
    }

    public static void executeTodo(String input) {
        if (!input.contains(" ")) { // Checks for presence of description
            System.out.println(ERROR_INVALID_SYNTAX + COMMAND_UNMARK + ".");
            return;
        }
        String description = getDescription(input);
        Todo task = new Todo(description);
        taskList.add(task);
        task.printAddToListMessage();
    }

    public static void executeDeadline(String input) {
        if (!input.contains(FLAG_DEADLINE)) { // Checks for presence of description
            System.out.println(ERROR_INVALID_SYNTAX + COMMAND_DEADLINE + ".");
            return;
        }
        String[] parsedCommand = parseDeadlineOrEvent(input);
        String description = parsedCommand[0];
        String by = parsedCommand[1];
        Deadline task = new Deadline(description, by);
        taskList.add(task);
        task.printAddToListMessage();
    }

    public static void executeEvent(String input) {
        if (!input.contains(FLAG_EVENT)) { // Checks for presence of description
            System.out.println(ERROR_INVALID_SYNTAX + COMMAND_EVENT + ".");
            return;
        }
        String[] parsedCommand = parseDeadlineOrEvent(input);
        String description = parsedCommand[0];
        String at = parsedCommand[1];
        Event task = new Event(description, at);
        taskList.add(task);
        task.printAddToListMessage();
    }

    //Returns a String array with the first element as the description and the second element as the deadline/time period
    public static String[] parseDeadlineOrEvent(String input) {
        String command = getCommand(input);
        String[] inputArray;
        if (command.equals(COMMAND_DEADLINE)) {
            inputArray = input.split(FLAG_DEADLINE);
        } else {
            inputArray = input.split(FLAG_EVENT);
        }
        inputArray[0] = inputArray[0].substring(inputArray[0].indexOf(" "));
        return inputArray;
    }

    //Returns a String representing the integer of the chosen task
    public static String parseMarkOrUnmarkOrDelete(String input) throws DukeException {
        String[] inputArray = input.split(" ");
        if (inputArray.length != 2) {
            throw new DukeException(ERROR_INVALID_SYNTAX);
        }
        return inputArray[1];
    }

    public static void listTasks() {
        if (taskList.size() == 0) {
            System.out.println(MESSAGE_NO_TASKS);
        } else {
            for (int i = 0; i < taskList.size(); i++) {
                System.out.println((i + 1) + ". " + taskList.get(i));
            }
        }
    }

    public static String getCommand(String input) {
        String[] arrayOfInput = input.split(" ", 0);
        return arrayOfInput[0];
    }

    public static String getDescription(String input) {
        return input.substring(input.indexOf(" "));
    }

    public static String getInput() {
        System.out.println(MESSAGE_INSTRUCTION);
        return scan.nextLine();
    }

    public static boolean isBye(String command) {
        return command.equals(COMMAND_BYE);
    }

    public static void printLine() {
        System.out.println(SEPARATOR);
    }
}
