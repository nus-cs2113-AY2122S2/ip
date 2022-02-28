package duke;

import duke.task.Task;
import duke.task.TaskList;

import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    private Scanner in = new Scanner(System.in);

    public Ui() {}

    public void greeting() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("You are entering the\n" + logo + "\nZone...\n");

        displayLine();
        System.out.println("Hey there! Duke here!");
        System.out.println("How can I serve you today?");
        displayLine();
    }

    public void goodbye() {
        System.out.println("Goodbye. See you in the funny papers.");
        displayLine();
    }

    public void printKeywordError(){
        System.out.println("Keyword is missing. Enter valid keyword.");
        displayLine();
    }

    public void printList(TaskList taskList) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskList.getTaskCount(); i++) {
            System.out.print(String.format("%d. ", i + 1));
            System.out.println(taskList.get(i));
        }
        displayLine();
    }

    public void printSelectiveList(TaskList taskList, String keyword) {
        System.out.println("Here are the matching tasks in your list:");
        for (int i = 0; i < taskList.getTaskCount(); i++) {
            if (taskList.get(i).getDescription().contains(keyword)) {
                System.out.print(String.format("%d. ", i + 1));
                System.out.println(taskList.get(i));
            }
        }
        displayLine();
    }

    public void printAddedTask(TaskList taskList) {
        System.out.println("Noted. I've added:");
        System.out.println(taskList.get(taskList.getTaskCount() - 1));
        System.out.println("Now you have " + Integer.toString(taskList.getTaskCount()) + " tasks in the list.");
        displayLine();
    }

    public void printDeletedTask(TaskList taskList, int taskNumber) {
        System.out.println("Noted. I've removed:");
        System.out.println(taskList.get(taskNumber - 1));
        System.out.println("Now you have " + Integer.toString(taskList.getTaskCount() - 1) + " tasks in the list.");
        displayLine();
    }

    public void printInvalidTodo() {
        System.out.println("OOPS! The description for todo cannot be empty.");
        displayLine();
    }

    public void printInvalidDeadline() {
        System.out.println("OOPS! Either the description or due date or both are empty for this deadline. Please try again.");
        displayLine();
    }

    public void printInvalidEvent() {
        System.out.println("OOPS! Either the description or event time or both are empty for this event. Please try again.");
        displayLine();
    }

    public void printInvalidCommand() {
        System.out.println("OOPS! I'm sorry but I don't know what you mean :(");
    }

    public void printMarkTaskAsDone(TaskList taskList, int taskNumber) {
        System.out.println("Great Job! I've marked the following task as done:");
        // display updated task entry in list.
        System.out.println(taskList.get(taskNumber - 1));
        displayLine();
    }

    public void printInvalidTaskNumber() {
        System.out.println("Please provide a valid task number");
    }

    public void printUnmarkTaskAsDone(TaskList taskList, int taskNumber) {
        System.out.println("Ok, I've marked the following task as yet to be done:");
        // display updated task entry in list.
        System.out.println(taskList.get(taskNumber - 1));
        displayLine();
    }

    public void printMkDirError() {
        System.out.println("Error making directory \"data\".");
    }

    public void printMkFileError() {
        System.out.println("An error occurred making file.");
    }

    public void printWrFileError() {
        System.out.println("Something went wrong writing to file.");
    }

    public void printNumOfLoadedTasks(TaskList taskList) {
        System.out.println("Now you have " + Integer.toString(taskList.getTaskCount()) + " tasks in the list.");
        displayLine();
    }

    public void printCorruptedFileError() {
        System.out.println("Corrupted entry detected in file.");
    }

    public void displayLine() {
        System.out.println("____________________________________________________________");
    }

    public String getUserInput() {
        String userInput = in.nextLine();
        displayLine();
        return userInput;
    }
}
