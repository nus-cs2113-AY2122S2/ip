package task;

import java.util.ArrayList;

public class Task {
    protected String description;
    protected boolean isDone;
    protected String taskType;

    public static final String LINE_SEPARATOR = "____________________________________________________________\n";

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getDescription() {
        return description;
    }

    public String getTaskType() {
        return taskType;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public void markAsDone(int taskNumber, ArrayList<Task> taskList, Boolean isUserCommand) {
        isDone = true;
        if (isUserCommand) {
            System.out.print(LINE_SEPARATOR);
            System.out.println("OK, I've marked this task as done:");
            System.out.println(taskList.get(taskNumber - 1).toString());
            System.out.print(LINE_SEPARATOR);
        }
    }

    public void markAsUndone(int taskNumber, ArrayList<Task> taskList, Boolean isUserCommand) {
        isDone = false;
        if (isUserCommand) {
            System.out.print(LINE_SEPARATOR);
            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println(taskList.get(taskNumber - 1).toString());
            System.out.print(LINE_SEPARATOR);
        }
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
