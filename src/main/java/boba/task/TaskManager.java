package boba.task;

import boba.command.Command;
import boba.data.FileManager;
import boba.exception.BobaException;
import boba.exception.ErrorHandler;
import boba.response.BobaResponse;

import java.util.ArrayList;

/**
 * Class that manages the Commands inputted and does
 * the proper instructions based on the input.
 * Changes anything relating to Tasks.
 */
public class TaskManager {

    /** The limit on how many tasks can be in the list */
    private static final int TASK_LIMIT = 100;

    /** Keeps track on how many tasks are in the list*/
    private int taskCount;
    /** The list of tasks. Limited to 100 */
    private ArrayList<Task> taskList;
    /** Manages the reading and writing from our save file*/
    private FileManager fileManager;

    public TaskManager() {
        fileManager = new FileManager("data/boba.txt");
        taskList = fileManager.readFile();
        taskCount = taskList.size();
        if (taskCount != 0) {
            BobaResponse.addResponse("Here is your currently save list!");
            printAllTasks();
        }
    }

    /**
     * Runs the given command and calls the right method
     * to print the proper message.
     * @param operation The Command the user entered
     * @param arguments The arguments that follows the command
     */
    public void run(Command operation, String[] arguments) {
        try {
            switch (operation) {
            case LIST:
                printAllTasks();
                break;
            case MARK:
                markTask(true, arguments[0]);
                break;
            case UNMARK:
                markTask(false, arguments[0]);
                break;
            case TODO:
                addTask(new Todo(arguments[0]));
                break;
            case DEADLINE:
                addTask(new Deadline(arguments[0], arguments[1]));
                break;
            case EVENT:
                addTask(new Event(arguments[0], arguments[1]));
                break;
            case HELP:
                printHelpOptions();
                break;
            case DELETE:
                deleteTask(arguments[0]);
                break;
            case EXIT:
                break;
            case NONE:
            default:
                throw new BobaException(Command.NONE);
            }
        } catch (BobaException e) {
            ErrorHandler.printErrorMessage(e.getOperation());
        }
    }

    /**
     * Prints the current list of tasks.
     */
    private void printAllTasks() {
        if (taskCount == 0){
            BobaResponse.addResponse("The list empty!");
        }
        for (int i = 0; i < taskCount; i++) {
            BobaResponse.addResponse(i + 1 + ". " + taskList.get(i));
        }
        BobaResponse.printResponse();
    }

    /**
     * Marks a task as complete or incomplete.
     * @param isDone Whether task is completed
     * @param taskIndex Index of the task we want to mark
     * @throws BobaException Index out of bounds
     */
    private void markTask(boolean isDone, String taskIndex) throws BobaException {
        // The task list is 1 base indexing while the array itself is 0 base indexing
        int index = Integer.parseInt(taskIndex) - 1;
        if (index < 0 || index >= taskCount) {
            // Marking outside the range is not allowed
            throw new BobaException(Command.MARK);
        }
        Task selectedTask = taskList.get(index);
        if (isDone) {
            selectedTask.markAsDone();
            BobaResponse.addResponse("Beep boop! I've marked this task as done:");
        } else {
            selectedTask.markAsNotDone();
            BobaResponse.addResponse("Boop beep! I've marked this task as not done:");
        }
        BobaResponse.addResponse(selectedTask.toString());
        BobaResponse.printResponse();
        fileManager.writeFile(taskList);
    }

    /**
     * Add a task to the list of tasks.
     * Does not add a task if we are at the limit.
     * @param newTask The new task to be added to the list
     */
    private void addTask(Task newTask) {
        if (taskCount == TASK_LIMIT) {
            BobaResponse.addResponse("The list is full");
            BobaResponse.addResponse("Task could not be added");
        } else {
            taskList.add(newTask);
            taskCount++;
            BobaResponse.addResponse("Got it. I've added this task:");
            BobaResponse.addResponse("\t" + newTask.toString());
            BobaResponse.addResponse("Now you have " + taskCount + " tasks in your list.");
        }
        BobaResponse.printResponse();
        fileManager.writeFile(taskList);
    }

    /**
     * Removes the Task at a given index
     * @param taskIndex Index of task we want to remove
     * @throws BobaException Index out of bounds
     */
    private void deleteTask(String taskIndex) throws BobaException{
        int index = Integer.parseInt(taskIndex) - 1;
        if (index < 0 || index >= taskCount) {
            // deleting outside the range is not allowed
            throw new BobaException(Command.DELETE);
        }
        BobaResponse.addResponse("Noted. I've removed this task:");
        BobaResponse.addResponse("\t" + taskList.get(index).toString());
        taskList.remove(index);
        taskCount--;
        BobaResponse.addResponse("Now you have " + taskCount + " tasks in the list.");
        BobaResponse.printResponse();
        fileManager.writeFile(taskList);
    }

    /**
     * Print out all the commands the bot will respond to.
     * Activates when user enters <code>help</code>
     */
    private void printHelpOptions() {
        BobaResponse.addResponse("Here are all the possible commands:");
        int helpCount = 1;
        BobaResponse.addResponse("\t" + helpCount++ + ". bye");
        BobaResponse.addResponse("\t" + helpCount++ + ". list");
        BobaResponse.addResponse("\t" + helpCount++ + ". todo <description>");
        BobaResponse.addResponse("\t" + helpCount++ + ". deadline <description> /by <time>");
        BobaResponse.addResponse("\t" + helpCount++ + ". event <description> /at <time>");
        BobaResponse.addResponse("\t" + helpCount++ + ". mark <number>");
        BobaResponse.addResponse("\t" + helpCount++ + ". unmark <number>");
        BobaResponse.addResponse("\t" + helpCount++ + ". delete <number>");
        BobaResponse.addResponse("\t" + helpCount++ + ". help");
        BobaResponse.printResponse();
    }
}
