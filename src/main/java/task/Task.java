package task;

import java.io.Serializable;
import java.util.ArrayList;

public class Task implements Serializable {

    protected String taskName;
    protected boolean isDone;
    protected String type = "Y";

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Task(String taskName) {
        this.taskName = taskName;
        this.isDone = false;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public boolean isDone() {
        return this.isDone;
    }

    public void setDone(boolean isDone) {
        isDone = isDone;
    }

    public String getStatusIcon() {
        if (isDone) {
            return "X";
        } else {
            return " ";
        }
    }

    @Override
    public String toString() {

        return "[" + this.type + "][" + this.getStatusIcon() + "] " + this.taskName;
    }

    public String getTaskDescription(String input) {
        return " ";
    }

    public static void printList(ArrayList<Task> list, int counter) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < counter; i++) {
            System.out.print(i + 1 + ".");
            printTask(list.get(i));
        }
        printNumberOfTasksInList(counter);
    }

    public static void printTask(Task t) {
        System.out.println(t.toString());
    }

    public static void printNumberOfTasksInList(int taskCounter) {
        System.out.println("Now you have " + taskCounter + " tasks in the list.");
    }


}

