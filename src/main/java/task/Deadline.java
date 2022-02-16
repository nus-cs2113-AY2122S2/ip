package task;

import java.util.ArrayList;

public class Deadline extends Task {
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    @Override
    public void deleteTask(int taskNumber,  ArrayList<Task> taskList) {
        System.out.print(LINESEPARATOR);
        System.out.println("Noted. I've removed this task:");
        System.out.println("[D][" + taskList.get(taskNumber - 1).getStatusIcon() + "] "
                + taskList.get(taskNumber - 1).getDescription() + " (by: " + by + ")");
        taskList.remove(taskNumber-1);
        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
        System.out.print(LINESEPARATOR);
    }
}
