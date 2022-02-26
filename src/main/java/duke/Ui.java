package duke;

import duke.task.Task;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

/**
 * Deals with the interactions between the program and the user.
 * Handles reading of commands from the user and printing of message
 * to the user.
 */

public class Ui {

    private static Scanner in;

    public Ui() {
        this(System.in);
    }

    /**
     * Creates an Ui object to handle reading from the Scanner object and printing
     * of messages to standard output.
     *
     * @param in Scanner object that takes user input
     */
    public Ui(InputStream in) {
        this.in = new Scanner(in);
    }

    public static String getNewInput() throws DukeException, IOException {
        String inputCommand = in.nextLine();
        return inputCommand;
    }

    /**
     * Prints welcome message to standard output.
     */
    public void printWelcomeMessage() {
        System.out.println("Hello! I'm Hage");
        System.out.println("What can I do for you?");
    }

    /**
     * Prints goodbye message to standard out.
     */
    public void printGoodbyeMessage() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Takes in a task object, and it's item number on the list
     * and prints a confirmation to standard output that the
     * task was successfully added with the number of task in
     * the list.
     *
     * @param taskToAcknowledge The task that has been added
     * @param itemNumber The item number of the task on the list
     */
    public void printAcknowledgeAddMessage(Task taskToAcknowledge, int itemNumber) {
        System.out.println("Got it. I've added this task:\n" + taskToAcknowledge);
        System.out.println("Now you have " + itemNumber +  " tasks in the list.");
    }

    /**
     * Takes in a task object, and it's item number on the list
     * and prints a confirmation to standard output that the
     * task was successfully deleted with the number of task
     * remaining in the list.
     *
     * @param taskToAcknowledge The task that has been deleted
     * @param itemNumber The number of items left in the list
     */
    public void printAcknowledgeDeleteMessage(Task taskToAcknowledge, int itemNumber) {
        System.out.println("Noted. I've removed this task:\n" + taskToAcknowledge +
                "\nNow you have " + itemNumber + " tasks in the list.");
    }

    /**
     * Prints an error message if any of the event input is empty.
     */
    public void printEventEmptyException() {
        System.out.println("OOPS!!! The description and time of event cannot be empty.");
    }

    /**
     * Prints an error message if the event input is invalid.
     */
    public void printInvalidEventException() {
        System.out.println("Invalid event input! Please try again.");
    }

    /**
     * Prints an error message if any of the deadline input is empty.
     */
    public void printDeadLineEmptyException() {
        System.out.println("OOPS!!! The description and time of Deadline cannot be empty.");
    }

    /**
     * Prints an error message if the deadline input is invalid.
     */
    public void printInvalidDeadLineException() {
        System.out.println("Invalid deadline input! Please try again.");
    }

    /**
     * Prints an error message if any of the todo description is empty.
     */
    public void printTodoEmptyException() {
        System.out.println("OOPS!!! The description and time of todo cannot be empty.");
    }

    /**
     * Prints error message if command is unknown.
     */
    public void printUnknownCommand() {
        System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(");
    }

    /**
     * Prints error message if index given is not in the list.
     */
    public void itemNotInListMessage() {
        System.out.println("The item you want to delete is not in the list!");
    }

    /**
     * Prints out error message if list is empty and "list" command is entered.
     */
    public void printListEmptyMessage() {
        System.out.println("List is empty");
    }

    /**
     * Takes in the task object of the item to be mark as done
     * and prints out if task is succesfully marked as done.
     *
     * @param markDoneTask The task to be marked as done
     */
    public void printMarkDoneMessage(Task markDoneTask) {
        System.out.println("Nice! I've marked this task as done:\n" + markDoneTask);
    }

    /**
     * Takes in the task object to be mark as not done yet
     * and prints our message if the task is successfully marked
     * as not done.
     *
     * @param markDoneTask The task to be marked as not done yet
     */
    public void printUnMarkDoneMessage(Task markDoneTask) {
        System.out.println("Nice! I've marked this task as not done yet:\n" + markDoneTask);
    }

    /**
     * Prints error message if mark index given is out of the list range.
     */
    public void printMarkIndexOutOfBounds() {
        System.out.println("Mark index is out of bounds");
    }

    /**
     * Prints error message if unmark index given is out of the list range.
     */
    public void printUnMarkIndexOutOfBounds() {
        System.out.println("Unmark index is out of bounds!!");
    }

    /**
     * Prints error message if given task index is already marked as not done.
     */
    public void printAlreadyMarkNotDoneMessage() {
        System.out.println("Task is already marked as not done yet");
    }

    /**
     * Prints error message if given task index is already marked as done.
     */
    public void printAlreadyMarkDoneMessage() {
        System.out.println("Task is already marked as done ");
    }

    /**
     * Prints message if file loaded is empty.
     */
    public void printFileEmptyMessage() {
        System.out.println("Empty file");
    }

    /**
     * Prints message if a directory is not found and is newly created.
     */
    public void printCreateDirectoryMessage() {
        System.out.println("New Directory created!");
    }

    /**
     * Prints message if file is not found and a new one is created.
     */
    public void newFileCreatedMessage() {
        System.out.println("new file Hage.txt created");
    }

    /**
     * Prints message if file is found.
     */
    public void printFileExistsMessage() {
        System.out.println("Hage.txt exists");
    }

    /**
     * Prints error if file cannot be created.
     */
    public void printCreateFileError() {
        System.out.println("Cannot create file!!");
    }

    public void printFoundItems(int listNum, Task t) {
        System.out.println(listNum + ". " + t);
    }

    public void printAllTaskInList(int listNum, Task singleTask) {
        System.out.println(listNum + ". " + singleTask);
    }
  
    public void printInvalidDateException() {
        System.out.println("Invalid Date input");
    }
}
