package bob.util.controller;

import bob.util.exception.BobInvalidIdException;
import bob.util.task.Deadlines;
import bob.util.task.Events;
import bob.util.task.Task;
import bob.util.task.ToDos;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * A helper that executes the user's commands and handles the program's functionalities.
 */
public class Command {

    // Delimiters
    public static final String DELIMIT_COMMAND = " ";
    public static final String DELIMIT_DEADLINE = " /by ";
    public static final String DELIMIT_EVENT = " /at ";

    // Magic numbers
    public static final int MIN_TASK_ID = 1;
    public static final int MAX_TASK_ID = 100;

    /**
     * Ensures that the given task id is currently in use.
     *
     * @param id   The task id to be checked for validity
     * @param list The list to check the id against
     * @throws BobInvalidIdException if id is not currently in use.
     */
    public static void isValidCurrentId(ArrayList<Task> list, int id) throws BobInvalidIdException {
        if (id >= MIN_TASK_ID && id <= MAX_TASK_ID) {
            if (id <= list.size()) {
                return;
            }
        }
        throw new BobInvalidIdException();
    }
    
    /**
     * Prints the formatted actual id of a task.
     *
     * @param id the id to be formatted.
     */
    public static void printListId(int id) {
        String formatId = String.format("%-4s", (id + 1) + ".");
        UI.printTab(formatId);
    }

    /**
     * Displays the current tasks and their statuses.
     *
     * @param list a Task class list.
     */
    public static void displayList(ArrayList<Task> list) {
        UI.printBorder();
        int count = list.size();

        if (count > 0) {
            UI.printlnTab(UI.MESSAGE_TASK_LIST);
            for (int i = 0; i < count; i++) {
                printListId(i);
                System.out.println(list.get(i));
            }
        } else {
            UI.printlnTab(UI.MESSAGE_NO_TASKS);
        }
        UI.printBorder();
    }

    /**
     * Updates the completion status of an indicated task.
     *
     * @param list    a Task class list.
     * @param command the command containing the task id to be updated.
     * @param isDone  the status to be updated to.
     */
    public static void updateStatus(ArrayList<Task> list, String command, boolean isDone) {
        UI.printBorder();
        String[] commandToken = Parser.parseCommand(command, DELIMIT_COMMAND);

        if (commandToken[1] == null) {
            UI.printError(UI.MESSAGE_INVALID_ARGC);
            return;
        }
        try {
            int id = Parser.stringToInt(commandToken[1]);
            isValidCurrentId(list, id);
            Task target = list.get(id - 1);
            target.setDone(isDone);
            LoadSave.saveData(list);

            if (isDone) {
                UI.printlnTab(UI.MESSAGE_TASK_MARKED);
            } else {
                UI.printlnTab(UI.MESSAGE_TASK_UNMARKED);
            }
            System.out.println("\t" + target);
            UI.printBorder();
        } catch (NumberFormatException e) {
            UI.printError(UI.MESSAGE_INVALID_ID_FORMAT);
        } catch (BobInvalidIdException e) {
            UI.printError(UI.MESSAGE_INVALID_ID_NUMBER);
        }
    }

    /**
     * Delete the task indicated by an id.
     *
     * @param list    a Task class list.
     * @param command the command containing the task id to be deleted.
     */
    public static void deleteTask(ArrayList<Task> list, String command) {
        UI.printBorder();
        String[] commandToken = Parser.parseCommand(command, DELIMIT_COMMAND);

        if (commandToken[1] == null) {
            UI.printError(UI.MESSAGE_INVALID_ARGC);
            return;
        }
        try {
            int id = Parser.stringToInt(commandToken[1]);
            isValidCurrentId(list, id);
            Task target = list.get(id - 1);
            list.remove(id - 1);
            LoadSave.saveData(list);

            UI.printlnTab(UI.MESSAGE_DELETE_SUCCESS);
            System.out.println("\t" + target);
            UI.printlnTab(UI.MESSAGE_TASK_COUNT + list.size());
            UI.printBorder();
        } catch (NumberFormatException e) {
            UI.printError(UI.MESSAGE_INVALID_ID_FORMAT);
        } catch (BobInvalidIdException e) {
            UI.printError(UI.MESSAGE_INVALID_ID_NUMBER);
        }
    }

    /**
     * Appends a Task to the task list.
     *
     * @param list a Task class list.
     * @param task the Task to be added.
     */
    public static void addTaskToList(ArrayList<Task> list, Task task) {
        list.add(task);
        System.out.println("\t" + task);
        UI.printlnTab(UI.MESSAGE_TASK_COUNT + list.size());
    }

    /**
     * Creates a new undated task and appends it to the task list.
     *
     * @param list        a Task class list.
     * @param taskDetails the details of the new undated task to be created.
     */
    public static void addToDosTask(ArrayList<Task> list, String taskDetails) {
        Task task = new ToDos(taskDetails);
        addTaskToList(list, task);
        UI.printBorder();
    }

    /**
     * Creates a new dated deadline task and appends it to the task list.
     *
     * @param list        a Task class list.
     * @param taskDetails the details of the new dated task to be created.
     */
    public static void addDeadlineTask(ArrayList<Task> list, String taskDetails) {
        String[] tokenDetails = Parser.parseCommand(taskDetails, DELIMIT_DEADLINE);

        // check that the deadline is not empty
        if (tokenDetails[1] != null) {
            Task task = new Deadlines(tokenDetails[0], Parser.parseDate(tokenDetails[1]));
            addTaskToList(list, task);
            UI.printBorder();
        } else {
            UI.printError(UI.MESSAGE_DEADLINE_USAGE);
        }
    }

    /**
     * Creates a new dated event task and appends it to the task list.
     *
     * @param list        a Task class list.
     * @param taskDetails the details of the new dated task to be created.
     */
    public static void addEventTask(ArrayList<Task> list, String taskDetails) {
        String[] tokenDetails = Parser.parseCommand(taskDetails, DELIMIT_EVENT);

        // check that the event period is not empty
        if (tokenDetails[1] != null) {
            Task task = new Events(tokenDetails[0], Parser.parseDate(tokenDetails[1]));
            addTaskToList(list, task);
            UI.printBorder();
        } else {
            UI.printError(UI.MESSAGE_EVENT_USAGE);
        }
    }

    /**
     * Validates the details of a task before creating and adding it into the list.
     *
     * @param list    a Task class list.
     * @param command the command containing the type of task and its details.
     */
    public static void addNewValidTask(ArrayList<Task> list, String command) {
        UI.printBorder();
        if (list.size() >= MAX_TASK_ID) {
            UI.printError(UI.MESSAGE_TASK_LIMIT_REACHED);
            return;
        }
        String[] commandToken = Parser.parseCommand(command, DELIMIT_COMMAND);

        // check that command details are not empty
        if (commandToken[1] != null) {
            switch (commandToken[0]) {
            case "todo":
                addToDosTask(list, commandToken[1]);
                break;
            case "deadline":
                addDeadlineTask(list, commandToken[1]);
                break;
            case "event":
                addEventTask(list, commandToken[1]);
                break;
            }
            LoadSave.saveData(list);
        } else {
            UI.printError(UI.MESSAGE_INVALID_ARGC);
        }
    }

    /**
     * Listen and execute user commands until exit command is issued.
     */
    public static void listenAndExecuteCommands() {
        Scanner in = new Scanner(System.in);
        ArrayList<Task> list = new ArrayList<>();
        LoadSave.loadData(list);

        String command;
        do {
            System.out.println();
            command = in.nextLine().trim();

            // check for valid command
            switch (Parser.parseCommand(command, DELIMIT_COMMAND)[0]) {
            case "list":
                displayList(list);
                break;
            case "mark":
                updateStatus(list, command, true);

                break;
            case "unmark":
                updateStatus(list, command, false);
                break;
            case "todo":
            case "deadline":
            case "event":
                addNewValidTask(list, command);
                break;
            case "delete":
                deleteTask(list, command);
                break;
            case "bye":
                break;
            default:
                UI.printlnTab(UI.MESSAGE_INVALID_COMMAND);
            }
        } while (!Parser.parseCommand(command, DELIMIT_COMMAND)[0].equals("bye"));
    }
}
