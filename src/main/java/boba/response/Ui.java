package boba.response;

import boba.task.Task;
import boba.task.TaskList;
import java.util.Scanner;

public class Ui {

    /** Scanner to input commands*/
    private Scanner scan;

    /**
     * Constructor sets up Scanner
     */
    public Ui() {
        scan = new Scanner(System.in);
    }

    /**
     * Reads input from Scanner
     * @return String of input
     */
    public String readCommand() {
        return scan.nextLine();
    }

    /**
     * The initial response the bot gives starting up
     */
    public void giveIntroduction() {
        BobaResponse.addResponse("Hello! I'm Boba.");
        BobaResponse.addResponse("I am a bot 'tasked' to manage your tasks");
        BobaResponse.addResponse("What can I do for you?");
        BobaResponse.addResponse("Type 'help' to get the list commands I response to");
        BobaResponse.printResponse();
    }

    /**
     * The final response after saying bye
     */
    public void sayGoodbye() {
        BobaResponse.printThis("Bye. Hope to see you again soon!");
    }

    /**
     * Prints the current list of tasks.
     */
    public void printAllTasks(TaskList taskList) {
        if (taskList.size() == 0){
            BobaResponse.addResponse("The list empty!");
        }
        for (int i = 0; i < taskList.size(); i++) {
            BobaResponse.addResponse(i + 1 + ". " + taskList.getTask(i).toString());
        }
        BobaResponse.printResponse();
    }

    /**
     * Print out all the commands the bot will respond to.
     * Activates when user enters <code>help</code>
     */
    public void printHelpOptions() {
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
        BobaResponse.addResponse("\t" + helpCount++ + ". find <keyword>");
        BobaResponse.addResponse("\t" + helpCount++ + ". help");
        BobaResponse.printResponse();
    }

    /**
     * Response by Boba when command is invalid or inputted improperly
     * @param errorMessage Extra message to better clarify error
     */
    public void printInvalidCommand(String errorMessage) {
        BobaResponse.addResponse("Uh oh... Something went wrong!");
        BobaResponse.addResponse("Invalid Command. Please try again!");
        BobaResponse.addResponse(errorMessage);
        BobaResponse.printResponse();
    }

    /**
     * Response for successful addition to taskList
     * @param task Task added to the list
     * @param taskCount Number of items in taskList
     */
    public void printAddSuccess(Task task, int taskCount) {
        BobaResponse.addResponse("Got it. I've added this task:");
        BobaResponse.addResponse("\t" + task.toString());
        BobaResponse.addResponse("Now you have " + taskCount + " tasks in your list.");
        BobaResponse.printResponse();
    }

    /**
     * Response for successful removal of taskList
     * @param task Task removed to the list
     * @param taskCount Number of items in taskList
     */
    public void printRemoveSuccess(Task task, int taskCount) {
        BobaResponse.addResponse("Noted. I've removed this task:");
        BobaResponse.addResponse("\t" + task.toString());
        BobaResponse.addResponse("Now you have " + taskCount + " tasks in the list.");
        BobaResponse.printResponse();
    }

    /**
     * Response for successful marking of task in taskList
     * @param isDone If task is mark done or not done
     * @param task Task that was marked
     */
    public void printMarkSuccess(boolean isDone, Task task) {
        if (isDone) {
            BobaResponse.addResponse("Beep boop! I've marked this task as done:");
        } else {
            BobaResponse.addResponse("Boop beep! I've marked this task as not done:");
        }
        BobaResponse.addResponse("\t" + task.toString());
        BobaResponse.printResponse();
    }

    /**
     * Response for when taskList is full
     */
    public void printLimitError() {
        BobaResponse.addResponse("The list is full");
        BobaResponse.addResponse("Task could not be added");
        BobaResponse.printResponse();
    }

    /**
     * Response when attempt to index out of bounds
     */
    public void printOutOfBounds() {
        BobaResponse.addResponse("Out of bounds!! Please provide a valid number");
        BobaResponse.printResponse();
    }

    /**
     * Response for using find command
     * @param taskList
     */
    public void printFindTask(TaskList taskList) {
        if (taskList.size() == 0) {
            BobaResponse.addResponse("Sorry! Looks like there is no task with that keyword");
        } else {
            BobaResponse.addResponse("Here are the matching tasks in your list:");
            for (Task task : taskList) {
                BobaResponse.addResponse("\t" + task.toString());
            }
        }
        BobaResponse.printResponse();
    }
}
