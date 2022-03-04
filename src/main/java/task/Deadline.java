package task;

import java.util.ArrayList;

public class Deadline extends Task {
    protected String by;
    protected String taskType;

    /**
     * Constructs a deadline task with the specified description.
     *
     * @param description The description of the deadline task.
     * @param by The time and date description of the deadline task.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        this.taskType = "deadline";
    }

    /**
     * Returns a string representation of the Event instance.
     *
     * @return A string representation of the Event instance.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    /**
     * Delete a Deadline instance and display the corresponding message.
     *
     * @param taskNumber numbering of the task being stored in the lost
     * @param taskList contains all the tasks stored in the list.
     */
    @Override
    public void deleteTask(int taskNumber,  ArrayList<Task> taskList) {
        System.out.print(LINE_SEPARATOR);
        System.out.println("Noted. I've removed this task:");
        System.out.println("[D]" + super.toString() + "(by: " + by + ")");
        taskList.remove(taskNumber-1);
        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
        System.out.print(LINE_SEPARATOR);
    }

    /**
     * Gets the task type of the Deadline instance.
     *
     * @return The task type of the Deadline instance.
     */
    @Override
    public String getTaskType() {
        return taskType;
    }
}
