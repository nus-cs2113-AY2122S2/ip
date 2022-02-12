package duke;

import duke.exception.*;
import duke.task.*;

import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    // Misc text elements
    private static final String HORIZONTAL_SEPARATOR = "------------------------------------------------------------";
    private static final String INPUT_PROMPT = "> ";
    private static final String LS = System.lineSeparator();

    // Messages
    private static final String MESSAGE_WELCOME = "Hi, I'm Robit!" + LS + "What would you like me to do?";
    private static final String MESSAGE_SHOW_TASKS = "Here are your tasks:";
    private static final String MESSAGE_NO_TASKS = "You don't have any tasks!";
    private static final String MESSAGE_INCORRECT_COMMAND_FORMAT = "Incorrect command format for %s." + LS
                                                                    + "Usage: \"%s\"";
    private static final String MESSAGE_ITEMIZED_TASK = "%d) %s";
    private static final String MESSAGE_TODO_ADDED = "Todo successfully added:" + LS + "\t%s";
    private static final String MESSAGE_DEADLINE_ADDED = "Deadline successfully added:" + LS + "\t%s";
    private static final String MESSAGE_EVENT_ADDED = "Event successfully added:" + LS + "\t%s";
    private static final String MESSAGE_TASK_DELETED = "Task successfully deleted:" + LS + "\t%s";
    private static final String MESSAGE_UNKNOWN_COMMAND = "I don't understand that command...";
    private static final String MESSAGE_NO_SUCH_INDEX = "There's no task with that index...";
    private static final String MESSAGE_TASK_MARKED = "I've marked this task as done. Yay!" + LS + "%d) %s";
    private static final String MESSAGE_TASK_UNMARKED = "I've unmarked this task." + LS + "%d) %s";
    private static final String MESSAGE_TASK_ALREADY_MARKED = "That task is already marked.";
    private static final String MESSAGE_TASK_ALREADY_UNMARKED = "That task is already unmarked.";
    private static final String MESSAGE_GOODBYE = "Goodbye!";

    // Commands
    private static final String COMMAND_BYE = "bye";
    private static final String COMMAND_LIST = "list";
    private static final String COMMAND_TODO = "todo";
    private static final String COMMAND_DEADLINE = "deadline";
    private static final String COMMAND_EVENT = "event";
    private static final String COMMAND_MARK = "mark";
    private static final String COMMAND_UNMARK = "unmark";
    private static final String COMMAND_DELETE = "delete";

    // Argument separators
    private static final String DEADLINE_SEPARATOR = " /by ";
    private static final String EVENT_SEPARATOR = " /at ";

    // Usage examples
    private static final String USAGE_BYE = COMMAND_BYE;
    private static final String USAGE_LIST = COMMAND_LIST;
    private static final String USAGE_TODO = COMMAND_TODO + " <your task>";
    private static final String USAGE_DEADLINE = COMMAND_DEADLINE + " <your task>"
                                                + DEADLINE_SEPARATOR + "<task deadline>";
    private static final String USAGE_EVENT = COMMAND_EVENT + " <your event>" + EVENT_SEPARATOR + "<time>";
    private static final String USAGE_MARK = COMMAND_MARK + " <task index>";
    private static final String USAGE_UNMARK = COMMAND_UNMARK + " <task index>";
    private static final String USAGE_DELETE = COMMAND_DELETE + " <task index>";

    // Program logic stuff
    private static final Scanner in = new Scanner(System.in);

    private static final ArrayList<Task> savedTasks = new ArrayList<>();

    private static boolean isExitRequested = false;

    /**
     * Main entry point of the program.
     */
    public static void main(String[] args) {
        printMessage(MESSAGE_WELCOME);
        while (!isExitRequested) {
            String[] input = getUserInput();
            String response = parseCommand(input);
            printMessage(response);
        }
    }

    /**
     * Prints the specified message, followed by a horizontal separator on the next line.
     * @param message the message to be printed
     */
    private static void printMessage(String message) {
        System.out.println(message);
        System.out.println(HORIZONTAL_SEPARATOR);
    }

    /**
     * Reads user input and separates the command being invoked from its arguments.
     * @return a String array of the form {command, args}
     */
    private static String[] getUserInput() {
        System.out.print(INPUT_PROMPT);
        String userInput = in.nextLine();
        System.out.println(HORIZONTAL_SEPARATOR);
        String[] userInputTokenized = userInput.trim().split(" ", 2);
        if (userInputTokenized.length == 2) {
            return userInputTokenized;
        }
        return new String[] {userInputTokenized[0], ""};
    }

    /**
     * Generates an error message for an invalid command.
     * @param usageExample string showing the correct command format
     * @return generated error message
     */
    private static String invalidCommandError(String command, String usageExample) {
        return String.format(MESSAGE_INCORRECT_COMMAND_FORMAT, command, usageExample);
    }

    /**
     * Adds the specified task to the task list.
     * @param t task to be added
     * @param successMessage message to be displayed on success
     * @return command feedback
     */
    private static String addTask(Task t, String successMessage) {
        savedTasks.add(t);
        return String.format(successMessage, t);
    }

    /**
     * Deletes the task with the specified index from the task list.
     * @param index index of the task to be removed
     * @param successMessage message to be displayed on success
     * @return command feedback
     */
    private static String removeTask(int index, String successMessage) {
        String feedback = String.format(successMessage, savedTasks.get(index));
        savedTasks.remove(index);
        return feedback;
    }

    /**
     * Invokes the correct command function based on the command string inputted by the user.
     * @param input user input, as an array {requested_command, command_args}
     * @return output from running requested_command with command_args
     */
    private static String parseCommand(String[] input) {
        final String command = input[0];
        final String args = input[1];
        switch (command) {
        case COMMAND_BYE:
            return byeCommand(args);
        case COMMAND_LIST:
            return listCommand(args);
        case COMMAND_TODO:
            return todoCommand(args);
        case COMMAND_DEADLINE:
            return deadlineCommand(args);
        case COMMAND_EVENT:
            return eventCommand(args);
        case COMMAND_MARK:
            return markCommand(args);
        case COMMAND_UNMARK:
            return unmarkCommand(args);
        case COMMAND_DELETE:
            return deleteCommand(args);
        default:
            return MESSAGE_UNKNOWN_COMMAND;
        }
    }

    /**
     * Request to exit the program.
     * @param args command arguments
     * @return command feedback
     */
    private static String byeCommand(String args) {
        try {
            if (!args.equals("")) {
                throw new InvalidCommandFormatException();
            }
            isExitRequested = true;
            return MESSAGE_GOODBYE;
        } catch (InvalidCommandFormatException e) {
            return invalidCommandError(COMMAND_BYE, USAGE_BYE);
        }
    }

    /**
     * Lists all saved tasks.
     * @param args command arguments
     * @return command feedback
     */
    private static String listCommand(String args) {
        try {
            if (!args.equals("")) {
                throw new InvalidCommandFormatException();
            }
            int numSavedTasks = savedTasks.size();
            if (numSavedTasks == 0) {
                return MESSAGE_NO_TASKS;
            }
            String result = MESSAGE_SHOW_TASKS;
            for (int i = 0; i < numSavedTasks; i++) {
                result += LS + String.format(MESSAGE_ITEMIZED_TASK, i + 1, savedTasks.get(i));
            }
            return result;
        } catch (InvalidCommandFormatException e) {
            return invalidCommandError(COMMAND_LIST, USAGE_LIST);
        }
    }

    /**
     * Adds a new to-do task.
     * @param args command arguments
     * @return command feedback
     */
    private static String todoCommand(String args) {
        try {
            if (args.equals("")) {
                throw new InvalidCommandFormatException();
            }
            Todo newTodo = new Todo(args.trim());
            return addTask(newTodo, MESSAGE_TODO_ADDED);
        } catch (InvalidCommandFormatException e) {
            return invalidCommandError(COMMAND_TODO, USAGE_TODO);
        }
    }

    /**
     * Adds a new deadline task. Parsing of the deadline separator occurs in the function body.
     * @param args command arguments
     * @return command feedback
     */
    private static String deadlineCommand(String args) {
        try {
            String[] parsedArgs = args.trim().split(DEADLINE_SEPARATOR, 2);
            if (parsedArgs.length != 2) {
                throw new InvalidCommandFormatException();
            }
            final String taskDescription = parsedArgs[0].trim();
            final String taskDeadline = parsedArgs[1].trim();
            Deadline newDeadline = new Deadline(taskDescription, taskDeadline);
            return addTask(newDeadline, MESSAGE_DEADLINE_ADDED);
        } catch (InvalidCommandFormatException e) {
            return invalidCommandError(COMMAND_DEADLINE, USAGE_DEADLINE);
        }
    }

    /**
     * Adds a new event task. Parsing of the event separator occurs in the function body.
     * @param args command arguments
     * @return command feedback
     */
    private static String eventCommand(String args) {
        try {
            String[] parsedArgs = args.trim().split(EVENT_SEPARATOR, 2);
            if (parsedArgs.length != 2) {
                throw new InvalidCommandFormatException();
            }
            final String eventDescription = parsedArgs[0].trim();
            final String eventTime = parsedArgs[1].trim();
            Event newEvent = new Event(eventDescription, eventTime);
            return addTask(newEvent, MESSAGE_EVENT_ADDED);
        } catch (InvalidCommandFormatException e) {
            return invalidCommandError(COMMAND_EVENT, USAGE_EVENT);
        }
    }

    /**
     * Marks the specified task as completed.
     * @param args command arguments
     * @return command feedback
     */
    private static String markCommand(String args) {
        try {
            int index = Integer.parseInt(args);
            if (index < 1 || index > savedTasks.size()) {
                throw new IndexOutOfBoundsException();
            }
            if (savedTasks.get(index - 1).getIsDone()) {
                return MESSAGE_TASK_ALREADY_MARKED;
            } else {
                savedTasks.get(index - 1).setIsDone(true);
                return String.format(MESSAGE_TASK_MARKED, index, savedTasks.get(index - 1));
            }
        } catch (NumberFormatException e) {
            return invalidCommandError(COMMAND_MARK, USAGE_MARK);
        } catch (IndexOutOfBoundsException e) {
            return MESSAGE_NO_SUCH_INDEX;
        }
    }

    /**
     * Marks the specified task as incomplete.
     * @param args command arguments
     * @return command feedback
     */
    private static String unmarkCommand(String args) {
        try {
            int index = Integer.parseInt(args);
            if (index < 1 || index > savedTasks.size()) {
                throw new IndexOutOfBoundsException();
            }
            if (savedTasks.get(index - 1).getIsDone()) {
                savedTasks.get(index - 1).setIsDone(false);
                return String.format(MESSAGE_TASK_UNMARKED, index, savedTasks.get(index-1));
            } else {
                return MESSAGE_TASK_ALREADY_UNMARKED;
            }
        } catch (NumberFormatException e) {
            return invalidCommandError(COMMAND_UNMARK, USAGE_UNMARK);
        } catch (IndexOutOfBoundsException e) {
            return MESSAGE_NO_SUCH_INDEX;
        }
    }

    private static String deleteCommand(String args) {
        try {
            int index = Integer.parseInt(args);
            if (index < 1 || index > savedTasks.size()) {
                throw new IndexOutOfBoundsException();
            }
            return removeTask(index - 1, MESSAGE_TASK_DELETED);
        } catch (NumberFormatException e) {
            return invalidCommandError(COMMAND_DELETE, USAGE_DELETE);
        } catch (IndexOutOfBoundsException e) {
            return MESSAGE_NO_SUCH_INDEX;
        }
    }
}
