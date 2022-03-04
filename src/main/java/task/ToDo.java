package task;

import java.util.ArrayList;

public class ToDo extends Task {
    protected String taskType;

    /**
     * Constructs a Todo task with the specified description.
     *
     * @param description The description of the Todo task.
     */
    public ToDo(String description) {
        super(description);
        this.taskType = "todo";
    }

    /**
     * Returns a string representation of the Todo instance.
     *
     * @return A string representation of the Todo instance.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Delete a ToDo instance and display the corresponding message.
     *
     * @param taskNumber numbering of the task being stored in the lost
     * @param taskList contains all the tasks stored in the list.
     */
    @Override
    public void deleteTask(int taskNumber,  ArrayList<Task> taskList) {
        System.out.print(LINE_SEPARATOR);
        System.out.println("Noted. I've removed this task:");
        System.out.println("[T]" + super.toString());
        taskList.remove(taskNumber-1);
        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
        System.out.print(LINE_SEPARATOR);
    }

    /**
     * Gets the task type of the ToDo instance.
     *
     * @return The task type of the ToDo instance.
     */
    @Override
    public String getTaskType() {
        return taskType;
    }
}