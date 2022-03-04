package task;

import java.util.ArrayList;

public class Event extends Task {
    protected String at;
    protected String taskType;

    /**
     * Constructs a Event task with the specified description.
     *
     * @param description The description of the Event task.
     * @param at The time and date description of the Event task.
     */
    public Event(String description, String at) {
        super(description);
        this.at = at;
        this.taskType = "event";
    }

    /**
     * Returns a string representation of the Event instance.
     *
     * @return A string representation of the Event instance.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }

    /**
     * Gets the task type of the Event instance.
     *
     * @return The task type of the Event instance.
     */
    @Override
    public String getTaskType() {
        return taskType;
    }

    /**
     * Delete an Event instance and display the corresponding message.
     *
     * @param taskNumber numbering of the task being stored in the lost
     * @param taskList contains all the tasks stored in the list.
     */
    @Override
    public void deleteTask(int taskNumber,  ArrayList<Task> taskList) {
        System.out.print(LINE_SEPARATOR);
        System.out.println("Noted. I've removed this task:");
        System.out.println("[E]" + super.toString() + "(at: " + at + ")");
        taskList.remove(taskNumber-1);
        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
        System.out.print(LINE_SEPARATOR);
    }
}
