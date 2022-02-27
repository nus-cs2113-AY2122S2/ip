package duke.task;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> taskList = new ArrayList<>();

    public void printLine() {
        System.out.println("-----------------------------------------");
    }

    public void readTask(Task newTask) {
        taskList.add(newTask);
    }

    public void addTask(Task newTask) {
        taskList.add(newTask);
        System.out.println(" Got it. I've added this task:");
        System.out.println("\t" + newTask);
        System.out.println(" Now you have " + taskList.size() + " tasks in the list.");
        printLine();
    }

    public void printTaskList() {
        printLine();
        if (taskList.size() == 0) {
            System.out.println(" List is empty! No task is here!");
        } else {
            System.out.println(" Here are the tasks in your list:");
            for (int i = 0; i < taskList.size(); i++) {
                System.out.println(" " + (i + 1) + "." + taskList.get(i));
            }
        }
        printLine();
    }

    public int getSize() {
        return taskList.size();
    }

    public Task getTask(int index) {
        return taskList.get(index);
    }

    public void markDone(int taskId) {
        taskList.get(taskId - 1).setDone();
        printLine();
        System.out.println(" Nice! I've marked this task as done:");
        System.out.println("\t" + taskList.get(taskId - 1));
        printLine();
    }

    public void readStatus(int taskId) {
        taskList.get(taskId - 1).setDone();
    }

    public void unmark(int taskId) {
        taskList.get(taskId - 1).setNotDone();
        printLine();
        System.out.println(" OK, I've marked this task as not done yet:");
        System.out.println("\t" + taskList.get(taskId - 1));
        printLine();
    }

    public void deleteTask(int taskId) {
        printLine();
        System.out.println(" Noted. I've removed this task: ");
        System.out.println("\t" + taskList.get(taskId - 1));
        taskList.remove(taskId - 1);
        System.out.println(" Now you have " + taskList.size() + " tasks in the list.");
        printLine();
    }
}
