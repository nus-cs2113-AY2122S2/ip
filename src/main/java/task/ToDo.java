package task;

import java.util.ArrayList;

public class ToDo extends Task {
    protected String taskType;

    public ToDo(String description) {
        super(description);
        this.taskType = "todo";
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public void deleteTask(int taskNumber,  ArrayList<Task> taskList) {
        System.out.print(LINE_SEPARATOR);
        System.out.println("Noted. I've removed this task:");
        System.out.println("[T]" + super.toString());
        taskList.remove(taskNumber-1);
        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
        System.out.print(LINE_SEPARATOR);
    }
    @Override
    public String getTaskType() {
        return taskType;
    }
}