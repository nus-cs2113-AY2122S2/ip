package task;

import java.util.ArrayList;

public class Event extends Task {
    protected String at;
    protected String taskType;

    public Event(String description, String at) {
        super(description);
        this.at = at;
        this.taskType = "event";
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }

    @Override
    public String getTaskType() {
        return taskType;
    }

    @Override
    public void deleteTask(int taskNumber,  ArrayList<Task> taskList) {
        System.out.print(LINESEPARATOR);
        System.out.println("Noted. I've removed this task:");
        System.out.println("[E]" + super.toString() + "(at: " + at + ")");
        taskList.remove(taskNumber-1);
        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
        System.out.print(LINESEPARATOR);
    }
}
