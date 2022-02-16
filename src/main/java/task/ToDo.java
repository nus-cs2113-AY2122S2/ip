package task;

import java.util.ArrayList;

public class ToDo extends Task {
    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public void deleteTask(int taskNumber,  ArrayList<Task> taskList) {
        System.out.print(LINESEPARATOR);
        System.out.println("Noted. I've removed this task:");
        System.out.println("[T][" + taskList.get(taskNumber - 1).getStatusIcon() + "] " + taskList.get(taskNumber - 1).getDescription());
        taskList.remove(taskNumber-1);
        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
        System.out.print(LINESEPARATOR);
    }
}