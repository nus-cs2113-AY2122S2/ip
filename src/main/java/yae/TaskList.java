package yae;

import yae.task.Deadline;
import yae.task.Event;
import yae.task.Task;
import yae.task.ToDo;

import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * Stores and operates on an arraylist of tasks.
 */
public class TaskList {
    protected static ArrayList<Task> tasks = new ArrayList<>();

    /**
     * Deletes the task with the given task number.
     *
     * @param taskNumber Task number of task. (Starting from 1)
     */
    public static void deleteTask(int taskNumber) {
        int taskIndex = taskNumber - 1;
        if (taskNumber > tasks.size()) {
            System.out.println("You don't have that many tasks ><!");
            return;
        }
        System.out.println("I have removed:\n" + tasks.get(taskIndex));
        tasks.remove(taskIndex);
        System.out.println("Now you have " + tasks.size() + " task(s) on the list.\n");
    }

    /**
     * Adds a ToDo type task.
     *
     * @param description Description of the ToDo task.
     */
    public static void addToDo(String description) {
        tasks.add(new ToDo(description));
        System.out.println("Todo added!");
        printTasks();
    }

    /**
     * Adds a Deadline type task.
     *
     * @param description Description of the Deadline type task.
     * @param by Date the Deadline task needs to be completed by.
     */
    public static void addDeadline(String description, String by) {
        tasks.add(new Deadline(description, by));
        System.out.println("Deadline added!");
        printTasks();
    }

    /**
     * Adds an Event type task.
     *
     * @param description Description of Event type task.
     * @param at Date the Event task is at.
     */
    public static void addEvent(String description, String at) {
        tasks.add(new Event(description, at));
        System.out.println("Event added!");
        printTasks();
    }

    /**
     * Marks the task with the given task number completed.
     *
     * @param taskNumber Task number of task. (Starting from 1)
     */
    public static void markTask(int taskNumber) {
        int taskIndex = taskNumber - 1;
        if (taskNumber > tasks.size()) {
            System.out.println("You don't have that many tasks ><!");
            return;
        }
        tasks.get(taskIndex).setDone();
        System.out.println("I have marked the task as done!");
        System.out.println(tasks.get(taskIndex).getStatusIcon() + tasks.get(taskIndex).getTaskDescription() + "\n");
    }

    /**
     * Marks the task with the given task number uncompleted.
     *
     * @param taskNumber Task number of task. (Starting from 1)
     */
    public static void unmarkTask(int taskNumber) {
        int taskIndex = taskNumber - 1;
        if (taskNumber > tasks.size()) {
            System.out.println("You don't have that many tasks ><!");
            return;
        }
        tasks.get(taskIndex).setUndone();
        System.out.println("I have marked the task as not done!");
        System.out.println(tasks.get(taskIndex).getStatusIcon() + tasks.get(taskIndex).getTaskDescription() + "\n");
    }

    /**
     * Prints all tasks.
     */
    public static void printTasks() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i += 1) {
            System.out.println(i + 1 + ". " + tasks.get(i));
        }
        System.out.println("You have " + tasks.size() + " task(s) on the list.\n");
    }

    /**
     * Filters and prints out tasks matching given keyword.
     *
     * @param keyword String to match in task list.
     */
    public static void findTasksByString(String keyword) {
        ArrayList<Task> filteredTasks = tasks
                .stream()
                .filter((t) -> t.getTaskDescription().contains(keyword))
                .collect(Collectors.toCollection(ArrayList::new));
        if (!filteredTasks.isEmpty()) {
            System.out.println("Here are the matching tasks in your list:");
            for (int i = 0; i < filteredTasks.size(); i += 1) {
                System.out.println(i + 1 + ". " + filteredTasks.get(i));
            }
            return;
        }
        System.out.println("No matching tasks found.");
    }
}
