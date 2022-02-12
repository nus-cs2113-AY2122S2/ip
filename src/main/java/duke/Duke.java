package duke;

import duke.exception.*;
import duke.task.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Duke {

    // Data file location
    private static final String DATA_FOLDER_PATH = "./data/";
    private static final String DATA_FILE_PATH = DATA_FOLDER_PATH + "duke.txt";

    // Misc text elements
    private static final String HORIZONTAL_SEPARATOR = "------------------------------------------------------------";
    private static final String INPUT_PROMPT = "> ";
    private static final String LS = System.lineSeparator();
    private static final String FS = "`";

    // Messages
    private static final String MESSAGE_DIRECTORY_FOUND = "Data directory found!";
    private static final String MESSAGE_DIRECTORY_CREATED = "Data directory created successfully!";
    private static final String MESSAGE_DIRECTORY_ERROR = "Data directory could not be created...";
    private static final String MESSAGE_DATA_FILE_FOUND = "Data file found!";
    private static final String MESSAGE_DATA_FILE_CREATED = "Data file created successfully!";
    private static final String MESSAGE_DATA_FILE_ERROR = "Data file could not be created...";
    private static final String MESSAGE_MALFORMED_TASK = "Skipped malformed task data at line %d: %s";
    private static final String MESSAGE_DATA_SAVE_ERROR = "Error saving data to file. Your changes were NOT saved!";
    private static final String MESSAGE_DATA_LOADED = "Successfully loaded %d tasks from file.";
    private static final String MESSAGE_DATA_SKIPPED = "Too many tasks; skipped data starting from line %d.";
    private static final String MESSAGE_SKIP_WARNING = "WARNING: skipped tasks will NOT be saved on next write!";
    private static final String MESSAGE_CLOSE_TO_FIX = "Close the program NOW if you wish to fix this manually.";


    private static final String MESSAGE_WELCOME = "Hi, I'm Robit!" + LS + "What would you like me to do?";
    private static final String MESSAGE_SHOW_TASKS = "Here are your tasks:";
    private static final String MESSAGE_NO_TASKS = "You don't have any tasks!";
    private static final String MESSAGE_INCORRECT_COMMAND_FORMAT = "Incorrect command format for %s." + LS
                                                                    + "Usage: \"%s\"";
    private static final String MESSAGE_ITEMIZED_TASK = "%d) %s";
    private static final String MESSAGE_TODO_ADDED = "Todo successfully added:" + LS + "\t%s";
    private static final String MESSAGE_DEADLINE_ADDED = "Deadline successfully added:" + LS + "\t%s";
    private static final String MESSAGE_EVENT_ADDED = "Event successfully added:" + LS + "\t%s";
    private static final String MESSAGE_UNKNOWN_COMMAND = "I don't understand that command...";
    private static final String MESSAGE_TOO_MANY_TASKS = "I can't remember that many tasks...";
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

    // Program logic stuff
    private static final File directory = new File(DATA_FOLDER_PATH);
    private static final File dataFile = new File(DATA_FILE_PATH);
    private static final Scanner in = new Scanner(System.in);

    private static final int MAX_TASKS = 100;
    private static Task[] savedTasks = new Task[MAX_TASKS];
    private static int numSavedTasks = 0;

    private static boolean isExitRequested = false;

    /**
     * Main entry point of the program.
     */
    public static void main(String[] args) {
        if (!checkForDataFile()) {
            System.exit(-1);
        }
        loadTasks();
        printMessage(MESSAGE_WELCOME);
        while (!isExitRequested) {
            String[] input = getUserInput();
            String response = parseCommand(input);
            printMessage(response);
        }
    }

    private static boolean checkForDataFile() {
        // Using System.out.println here when I don't want to print a horizontal line after each output
        if (!directory.exists()) {
            if (directory.mkdir()) {
                System.out.println(MESSAGE_DIRECTORY_CREATED);
            } else {
                printMessage(MESSAGE_DIRECTORY_ERROR);
                return false;
            }
        } else {
            System.out.println(MESSAGE_DIRECTORY_FOUND);
        }
        try {
            if (dataFile.createNewFile()) {
                System.out.println(MESSAGE_DATA_FILE_CREATED);
            } else {
                System.out.println(MESSAGE_DATA_FILE_FOUND);
            }
        } catch (IOException e) {
            printMessage(MESSAGE_DATA_FILE_ERROR);
            return false;
        }
        return true;
    }

    private static void loadTasks() {
        try {
            Scanner sc = new Scanner(dataFile);
            int lineNum = 1;
            while (sc.hasNext() && numSavedTasks < MAX_TASKS) {
                Task t = parseSavedTask(sc.nextLine(), lineNum);
                if (t != null) {
                    savedTasks[numSavedTasks] = t;
                    numSavedTasks++;
                }
                lineNum++;
            }
            if (sc.hasNext()) {
                System.out.println(String.format(MESSAGE_DATA_SKIPPED, lineNum));
                System.out.println(MESSAGE_SKIP_WARNING);
                System.out.println(MESSAGE_CLOSE_TO_FIX);
            }
            printMessage(String.format(MESSAGE_DATA_LOADED, numSavedTasks));
        } catch (FileNotFoundException e) {
            // This branch should be unreachable because we create the file if it doesn't exist
            printMessage(MESSAGE_DATA_FILE_ERROR);
            System.exit(-2);
        }
    }

    private static Task parseSavedTask(String taskAsString, int lineNum) {
        String[] processedString = taskAsString.split(FS);
        try {
            final String taskType = processedString[0];
            final boolean taskMarked;
            if (processedString[1].equals("1")) {
                taskMarked = true;
            } else if (processedString[1].equals("0")) {
                taskMarked = false;
            } else {
                throw new MalformedTaskFormatException();
            }
            final String taskDescription = processedString[2];
            switch (taskType) {
            case "T":
                if (processedString.length != 3) {
                    throw new MalformedTaskFormatException();
                }
                Todo todo = new Todo(taskDescription);
                todo.setIsDone(taskMarked);
                return todo;
            case "D":
                if (processedString.length != 4) {
                    throw new MalformedTaskFormatException();
                }
                final String dueBy = processedString[3];
                Deadline deadline = new Deadline(taskDescription, dueBy);
                deadline.setIsDone(taskMarked);
                return deadline;
            case "E":
                if (processedString.length != 4) {
                    throw new MalformedTaskFormatException();
                }
                final String time = processedString[3];
                Event event = new Event(taskDescription, time);
                event.setIsDone(taskMarked);
                return event;
            default:
                throw new MalformedTaskFormatException();
            }
        } catch (IndexOutOfBoundsException | MalformedTaskFormatException e) {
            // Using System.out.println here because I don't want to print a horizontal line after each output
            System.out.println(String.format(MESSAGE_MALFORMED_TASK, lineNum, taskAsString));
            return null;
        }
    }

    private static void saveToFile() {
        try {
            FileWriter fw = new FileWriter(dataFile);
            for (int i = 0; i < numSavedTasks; i++) {
                fw.write(savedTasks[i].formatAsData(FS) + LS);
            }
            fw.close();
        } catch (IOException e) {
            // Using System.out.println here because I don't want to print a horizontal line after each output
            System.out.println(MESSAGE_DATA_SAVE_ERROR);
            System.out.println(MESSAGE_CLOSE_TO_FIX);
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
        // Strip file separator characters from user input to avoid writing malformed data to file
        String userInput = in.nextLine().replace(FS,"");
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
     * Attempts to add the specified task to the task list.
     * @param t task to be added
     * @param successMessage message to be displayed on success
     * @return command feedback
     */
    private static String addTask(Task t, String successMessage) {
        try {
            savedTasks[numSavedTasks] = t;
            numSavedTasks++;
            saveToFile();
            return String.format(successMessage, t);
        } catch (IndexOutOfBoundsException e) {
            return MESSAGE_TOO_MANY_TASKS;
        }
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
            if (numSavedTasks == 0) {
                return MESSAGE_NO_TASKS;
            }
            String result = MESSAGE_SHOW_TASKS;
            for (int i = 0; i < numSavedTasks; i++) {
                result += LS + String.format(MESSAGE_ITEMIZED_TASK, i + 1, savedTasks[i]);
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
            if (index < 1 || index > numSavedTasks) {
                throw new IndexOutOfBoundsException();
            }
            if (savedTasks[index - 1].getIsDone()) {
                return MESSAGE_TASK_ALREADY_MARKED;
            } else {
                savedTasks[index - 1].setIsDone(true);
                saveToFile();
                return String.format(MESSAGE_TASK_MARKED, index, savedTasks[index-1]);
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
            if (index < 1 || index > numSavedTasks) {
                throw new IndexOutOfBoundsException();
            }
            if (savedTasks[index - 1].getIsDone()) {
                savedTasks[index - 1].setIsDone(false);
                saveToFile();
                return String.format(MESSAGE_TASK_UNMARKED, index, savedTasks[index-1]);
            } else {
                return MESSAGE_TASK_ALREADY_UNMARKED;
            }
        } catch (NumberFormatException e) {
            return invalidCommandError(COMMAND_UNMARK, USAGE_UNMARK);
        } catch (IndexOutOfBoundsException e) {
            return MESSAGE_NO_SUCH_INDEX;
        }
    }
}
