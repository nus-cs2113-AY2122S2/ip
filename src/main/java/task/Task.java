package task;

import java.util.ArrayList;

public class Task {
    protected String description;
    protected boolean isDone;
    protected String taskType;

    public static final String LINE_SEPARATOR = "____________________________________________________________\n";

    /**
     * Constructs a Task instance with a specified description.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Gets the description of the Task instance.
     *
     * @return The description of the Task instance.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Gets the task type of the Task instance.
     *
     * @return The task type of the Task instance.
     */
    public String getTaskType() {
        return taskType;
    }

    /**
     * Gets the completion status of the Task instance.
     * [] as incomplete and [x] as completed
     *
     * @return The completion status of the Task instance.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }


    /**
     * Mark a task instance as done.
     * Task is being marked as completed and reflected in the list.
     * After marking of the task, a message will be display to indicate
     * the task is marked.
     *
     * @param taskNumber numbering of the task being stored in the lost and needs to be marked.
     * @param taskList contains all the tasks stored in the list.
     * @param isUserCommand state whether the command being marked is from terminal or file.
     */
    public void markAsDone(int taskNumber, ArrayList<Task> taskList, Boolean isUserCommand) {
        isDone = true;
        if (isUserCommand) {
            System.out.print(LINE_SEPARATOR);
            System.out.println("OK, I've marked this task as done:");
            System.out.println(taskList.get(taskNumber - 1).toString());
            System.out.print(LINE_SEPARATOR);
        }
    }

    /**
     * Mark a task instance as undone.
     * Task is being marked as incomplete and reflected in the list.
     * After task is unmarked, a message will be display to indicate
     * the task is unmarked.
     *
     * @param taskNumber numbering of the task being stored in the lost and needs to be unmarked.
     * @param taskList contains all the tasks stored in the list.
     * @param isUserCommand state whether the command being marked is from terminal or file.
     */
    public void markAsUndone(int taskNumber, ArrayList<Task> taskList, Boolean isUserCommand) {
        isDone = false;
        if (isUserCommand) {
            System.out.print(LINE_SEPARATOR);
            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println(taskList.get(taskNumber - 1).toString());
            System.out.print(LINE_SEPARATOR);
        }
    }

    /**
     * Delete a Task instance.
     *
     * @param taskNumber numbering of the task being stored in the lost
     * @param taskList contains all the tasks stored in the list.
     */
    public void deleteTask(int taskNumber, ArrayList<Task> taskList) {
    }

    /**
     * Returns a string representation of the Task instance.
     *
     * @return A string representation of the Task instance.
     */
    public String toString() {
        if (isDone) {
            return "[X] " + description;
        } else {
            return "[ ] " + description;
        }
    }
}
