package duke;

import duke.task.Task;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class Ui {

    private final Scanner in;
    private final PrintStream out;

    public Ui() {
        this(System.in, System.out);
    }

    public Ui(InputStream in, PrintStream out) {
        this.in = new Scanner(in);
        this.out = out;
    }

    public void printWelcomeMessage() {
        System.out.println("Hello! I'm Hage");
        System.out.println("What can I do for you?");
    }

    public void printGoodbyeMessage() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public void printAcknowledgeAddMessage(Task taskToAcknowledge, int itemNumber) {
        System.out.println("Got it. I've added this task:\n" + taskToAcknowledge);
        System.out.println("Now you have " + itemNumber +  " tasks in the list.");
    }

    public void printAcknowledgeDeleteMessage(Task taskToAcknowledge, int itemNumber) {
        System.out.println("Noted. I've removed this task:\n" + taskToAcknowledge +
                "\nNow you have " + itemNumber + " tasks in the list.");
    }

    public void printEventEmptyException() {
        System.out.println("OOPS!!! The description and time of event cannot be empty.");
    }

    public void printInvalidEventException() {
        System.out.println("Invalid event input! Please try again.");
    }

    public void printDeadLineEmptyException() {
        System.out.println("OOPS!!! The description and time of Deadline cannot be empty.");
    }

    public void printInvalidDeadLineException() {
        System.out.println("Invalid deadline input! Please try again.");
    }

    public void printTodoEmptyException() {
        System.out.println("OOPS!!! The description and time of todo cannot be empty.");
    }

    public void printUnknownCommand() {
        System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(");
    }

    public void itemNotInListMessage() {
        System.out.println("The item you want to delete is not in the list!");
    }

    public void printListEmptyMessage() {
        System.out.println("List is empty");
    }

    public void printMarkDoneMessage(Task markDoneTask) {
        System.out.println("Nice! I've marked this task as done:\n" + markDoneTask);
    }

    public void printUnMarkDoneMessage(Task markDoneTask) {
        System.out.println("Nice! I've marked this task as not done yet:\n" + markDoneTask);
    }

    public void printMarkIndexOutOfBounds() {
        System.out.println("Mark index is out of bounds");
    }

    public void printUnMarkIndexOutOfBounds() {
        System.out.println("Unmark index is out of bounds!!");
    }

    public void printAlreadyMarkNotDoneMessage() {
        System.out.println("Task is already marked as not done yet");
    }

    public void printAlreadyMarkDoneMessage() {
        System.out.println("Task is already marked as done ");
    }

    public void printFileEmptyMessage() {
        System.out.println("Empty file");
    }

    public void printCreateDirectoryMessage() {
        System.out.println("New Directory created!");
    }

    public void newFileCreatedMessage() {
        System.out.println("new file Hage.txt created");
    }

    public void printFileExistsMessage() {
        System.out.println("Hage.txt exists");
    }

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
