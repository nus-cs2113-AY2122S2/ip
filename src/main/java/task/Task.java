package task;

import java.util.ArrayList;

public class Task {
    protected String description;

    protected boolean isDone;
    public static final String LINESEPARATOR = "____________________________________________________________\n";


    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getDescription() {
        return description;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public void markAsDone(int taskNumber, ArrayList<Task> taskList) {
        isDone = true;
        System.out.print(LINESEPARATOR);
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(taskList.get(taskNumber - 1).toString());
        System.out.print(LINESEPARATOR);
    }

    public void markAsUndone(int taskNumber, ArrayList<Task> taskList) {
        isDone = false;
        System.out.print(LINESEPARATOR);
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(taskList.get(taskNumber - 1).toString());
        System.out.print(LINESEPARATOR);
    }

    public void deleteTask(int taskNumber, ArrayList<Task> taskList) {
    }

    public String toString() {
        if (isDone) {
            return "[X] " + description;
        } else {
            return "[ ] " + description;
        }
    }
}
