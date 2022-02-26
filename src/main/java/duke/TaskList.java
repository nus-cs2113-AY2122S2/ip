package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class TaskList {
    protected static ArrayList<Task> tasks = new ArrayList<>();

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

    public static void addToDo(String description) {
        tasks.add(new ToDo(description));
        System.out.println("Todo added!");
        printTasks();
    }

    public static void addDeadline(String description, String by) {
        tasks.add(new Deadline(description, by));
        System.out.println("Deadline added!");
        printTasks();
    }

    public static void addEvent(String description, String at) {
        tasks.add(new Event(description, at));
        System.out.println("Event added!");
        printTasks();
    }

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

    public static void printTasks() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i += 1) {
            System.out.println(i + 1 + ". " + tasks.get(i));
        }
        System.out.println("You have " + tasks.size() + " task(s) on the list.\n");
    }

    public static void findTasksByString(String keyword) {
        ArrayList<Task> filteredTasks = tasks
                .stream()
                .filter((t) -> t.getTaskDescription().contains(keyword))
                .collect(Collectors.toCollection(ArrayList::new));
        System.out.println("Here are the matching tasks in your list:");
        for (int i = 0; i < filteredTasks.size(); i += 1) {
            System.out.println(i + 1 + ". " + filteredTasks.get(i));
        }
    }
}
