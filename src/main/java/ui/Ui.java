package ui;
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

    public String getUserCommand() {
        String fullInputLine = sc.nextLine();
        return fullInputLine;
    }

    public void showNewTask (Task task) {
        System.out.println(Messages.LINE);
        System.out.println("\t Got it. I've added this task:");
        System.out.println("\t\t" + task.toString());
        System.out.println("\t Now you have " + taskManager.getNoOfTasks() + " tasks in the list.");
        System.out.println(Messages.LINE);
    }

    public void listAllTasks(ArrayList<Task> tasks) {
        System.out.println(Messages.LINE);
        System.out.println("\t Here are the tasks in your list:");
        if(tasks.size() == 0) {
            System.out.println("\t No task recorded.");
            System.out.println(Messages.LINE);
            return;
        }

        for(int i = 0;i < tasks.size(); i++) {
            System.out.println("\t\t " + (i + 1) + "." + tasks.get(i).toString());
        }
        System.out.println(Messages.LINE);
    }

}
