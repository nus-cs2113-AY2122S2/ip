package jrobo.task;

import jrobo.exception.InvalidTypeException;

import java.util.ArrayList;

/**
 * TaskManager is the class that manages everything regarding Task, Deadline, Event, Todo classes
 *
 * @author Ege Demirkirkan
 */

public class TaskManager {
    ArrayList<Task> tasks;


    public TaskManager() {
        this.tasks = new ArrayList<>();
    }

    public void printWithSeparator(String... strings) {
        System.out.println("\t____________________________________________________________");
        for (String s : strings) {
            System.out.println("\t" + s);
        }
        System.out.println("\t____________________________________________________________");
    }

    public void welcomeUser() {
        printWithSeparator("Hello from JRobo! I'm your personal assistant!",
                "Nice to meet you. What can I do for you?");
    }

    /**
     * This method displays all the tasks kept in the storage.
     */
    public void displayTaskList() {
        if (tasks.size() == 0) {
            printWithSeparator("You have no tasks to list.");
            return;
        }
        System.out.println("\t____________________________________________________________");
        System.out.println("\tHere are the tasks in your list:");
        int taskCount = tasks.size();
        for (int i = 0; i < taskCount; i++) {
            Task t = tasks.get(i);
            System.out.println("\t" + (i + 1) + "." + t);
        }
        System.out.println("\t____________________________________________________________");
    }

    /**
     * This method is used for handle 'mark' command. It changes the status icon of the task.
     *
     * @param input String object representing the command in the following format; 'mark {int}'
     */
    public void markTask(String input) {
        String numberString = input.trim();
        try {
            int taskNumber = Integer.parseInt(numberString);
            Task task = tasks.get(taskNumber - 1);
            if (task.getStatusIcon().equals("X")) {
                printWithSeparator("This task is already marked");
                return;
            }
            task.markAsDone();
            printWithSeparator("Nice! I've marked this task as done:", "\t[" + task.getStatusIcon() + "] "
                    + task.getDescription());
        } catch (NumberFormatException e) {
            printWithSeparator("Invalid command");
        } catch (IndexOutOfBoundsException e) {
            printWithSeparator("Please enter a valid task number to mark");
        }
    }

    /**
     * This method is used for handle 'unmark' command. It changes the status icon of the task.
     *
     * @param input String object representing the command in the following format; 'unmark {int}'
     */
    public void unmarkTask(String input) {
        String numberString = input.trim();
        try {
            int taskNumber = Integer.parseInt(numberString);
            Task task = tasks.get(taskNumber - 1);
            if (!task.getStatusIcon().equals("X")) {
                printWithSeparator("This task is already unmarked");
                return;
            }
            task.markAsUndone();
            printWithSeparator("Nice! I've marked this task as undone:", "\t[" + task.getStatusIcon() + "] "
                    + task.getDescription());
        } catch (NumberFormatException e) {
            printWithSeparator("Invalid command");
        } catch (IndexOutOfBoundsException e) {
            printWithSeparator("Please enter a valid task number to unmark");
        }
    }

    /**
     * This method add a task to the storage of the program.
     *
     * @param description main text of the task
     * @param detail      time detail of the task, null if the command is not the type deadline or event.
     * @param type        type of the task
     * @param loadFlag    boolean value that represents whether the task should be loaded
     * @throws InvalidTypeException if the type of the command is wrong,
     *                              displays the related error text
     */
    public void addTask(String description, String detail, String type, boolean loadFlag)
            throws InvalidTypeException {
        Task task;
        switch (type) {
        case "todo":
            task = new Todo(description);
            break;
        case "deadline":
            task = new Deadline(description, detail);
            break;
        case "event":
            task = new Event(description, detail);
            break;
        default:
            throw new InvalidTypeException("Invalid command!");
        }
        tasks.add(task);
        if (!loadFlag) {
            printWithSeparator("Got it. I've added this task:", "\t" + task,
                    "Now, you have " + tasks.size() + " in the list.");
        }
    }

    /**
     * This method delete a task from the storage of the program.
     *
     * @param index specifies the task to remove
     */
    public void deleteTask(int index) {
        if (tasks.size() == 0) {
            printWithSeparator("Invalid command! Nothing to delete.");
            return;
        }
        printWithSeparator("Noted. I've removed this task:", "\t" + tasks.get(index - 1).toString(),
                "Now you have " + (tasks.size() - 1) + " tasks in the list.");
        tasks.remove(index - 1);
    }

    public void farewellUser() {
        printWithSeparator("Bye. Hope to see you again soon!");
    }

    public void giveError() {
        printWithSeparator("Invalid command!");
    }

    /**
     * Getter for the tasks field.
     *
     * @return ArrayList<Task>
     */
    public ArrayList<Task> getTaskList() {
        return tasks;
    }

    /**
     * This method search through the storage to find the related task to the search key
     *
     * @param toFind search key
     */
    public void findTask(String toFind) {
        System.out.println("\t____________________________________________________________");
        System.out.println("\tHere are the matching tasks in your list:");
        int index = 1;
        for (Task t : tasks) {
            if (t.getDescription().toLowerCase().contains(toFind.toLowerCase())) {
                System.out.println("\t" + (index++) + "." + t);
            }
        }
        System.out.println("\t____________________________________________________________");
    }
}
