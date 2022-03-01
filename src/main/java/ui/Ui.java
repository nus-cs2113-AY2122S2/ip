package ui;
import common.DukeException;
import common.Messages;
import data.Task;
import data.TaskManager;

import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    private final Scanner sc;
    private final TaskManager taskManager;

    public Ui(TaskManager taskManager) {
        sc = new Scanner(System.in);
        this.taskManager = taskManager;
    }

    public void showGreetingMessage() {
        System.out.println(Messages.GREETING);
    }

    public void showByeMessage() {
        System.out.println(Messages.BYE);
    }

    public void showExitMessage() {
        System.out.println(Messages.Exit);
    }

    public void showLine() {
        System.out.println(Messages.LINE);
    }

    public String getUserCommand() {
        String fullInputLine = sc.nextLine();
        return fullInputLine;
    }

    public void showNewTask(Task task) {
        System.out.println("\t Got it. I've added this task:");
        System.out.println("\t\t" + task.toString());
        System.out.println("\t Now you have " + taskManager.getNoOfTasks() + " tasks in the list.");
    }

    public void showRemovedTask(Task task) {
        System.out.println("\t Noted. I've removed this task:");
        System.out.println("\t\t " + task.toString());
        System.out.println("\t Now you have " + taskManager.getNoOfTasks() + " tasks in the list.");
    }

    public void listAllTasks(ArrayList<Task> tasks) {
        System.out.println("\t Here are the tasks in your list:");
        if(tasks.size() == 0) {
            System.out.println("\t No task recorded.");
            return;
        }

        for(int i = 0;i < tasks.size(); i++) {
            System.out.println("\t\t " + (i + 1) + "." + tasks.get(i).toString());
        }
    }

    public void showMarkedTask(int idx) throws DukeException {
        System.out.println("\t Nice! I've marked this task as done:");
        System.out.println("\t\t " + idx + "." + taskManager.getTask(idx).toString());
    }

    public void showUnmarkedTask(int idx) throws DukeException{
        System.out.println("\t OK, I've marked this task as not done yet:");
        System.out.println("\t\t " + idx + "." + taskManager.getTask(idx).toString());

    }

    public void showSupportedCommand() {
        System.out.println("\t Supported commands:");
        System.out.println("\t Type \"help\" to view supported command");
        System.out.println("\t Type \"todo <task>\" to add a todo task");
        System.out.println("\t Type \"deadline <task> /by <time>\" to add a deadline task");
        System.out.println("\t Type \"event <task> /at <time>\" to add a event task");
        System.out.println("\t Type \"list\" to list all tasks");
        System.out.println("\t Type \"delete <task number>\" to delete a task");
        System.out.println("\t Type \"mark <task number>\" to mark a task");
        System.out.println("\t Type \"unmark <task number>\" to unmark a task");
        System.out.println("\t Type \"bye\" to exit");
    }

    public void showError(String message) {
        System.out.println("\t " + message);
        System.out.println("\t Please try again.");
    }

    public void showLoadingError(String message) {
        System.out.println("\t Error: Failed to load data.");
        System.out.println("\t " + message);
    }
}
