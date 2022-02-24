package task;

import java.util.ArrayList;

public class Deadline extends Task {
    protected String by;
    protected String taskType;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        this.taskType = "deadline";
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    @Override
    public void deleteTask(int taskNumber,  ArrayList<Task> taskList) {
        System.out.print(LINE_SEPARATOR);
        System.out.println("Noted. I've removed this task:");
        System.out.println("[D]" + super.toString() + "(by: " + by + ")");
        taskList.remove(taskNumber-1);
        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
        System.out.print(LINE_SEPARATOR);
    }

    @Override
    public String getTaskType() {
        return taskType;
    }
}
