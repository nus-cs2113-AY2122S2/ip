package parser;

import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;
import exceptions.UnknownCommandException;
import storage.Storage;
import tasklist.TaskList;
import ui.Ui;

import java.util.ArrayList;

public class Parser {
    private Ui ui;
    private TaskList tasks;
    private Storage storage;

    /**
     * Creates an instance of Parser that is used to interpret commands.
     * @param ui      the Ui object used for this particular instance.
     * @param tasks   the TaskList object for this instance.
     * @param storage the Storage object for this instance.
     */
    public Parser(Ui ui, TaskList tasks, Storage storage) {
        this.ui = ui;
        this.tasks = tasks;
        this.storage = storage;
    }

    /**
     * This method is used to interpret every input given by the user as a command.
     * Based on the input given by the user, another method will be called to carry out
     * a specific task.
     * <p>
     * These tasks include:
     * <ul>
     * <li>listing out all tasks with their statuses and details</li>
     * <li>adding of tasks (todo, event, deadline)</li>
     * <li>deletion of tasks (todo, event, deadline)</li>
     * <li>marking of tasks as completed</li>
     * <li>marking of tasks as incomplete</li>
     * <li>finding tasks based on a search term</li>
     * </ul>
     * </p>
     *
     * @param response input given by the user.
     * @param tasks the latest TaskList.
     * @param storage Storage object for this instance of Duke.
     * @throws UnknownCommandException
     */
    public void runCommand(String response, TaskList tasks, Storage storage) throws UnknownCommandException {
        String[] words = response.split(" ", 2);
        String command = words[0];
        String detail = (words.length > 1) ? words[1] : null;

        boolean hasAddedTask = false;
        boolean hasUpdate = false;

        switch (command) {
        case "list":
            listTasks(tasks);
            break;
        case "mark":
            hasUpdate = markTask(response);
            break;
        case "unmark":
            hasUpdate = unmarkTask(response);
            break;
        case "todo":
            addTask(new Todo(detail));
            hasAddedTask = true;
            break;
        case "deadline":
            addDeadline(detail);
            hasAddedTask = true;
            break;
        case "event":
            addEvent(detail);
            hasAddedTask = true;
            break;
        case "delete":
            deleteTask(response);
            hasUpdate = true;
            break;
        case "find":
            findTask(detail);
            break;
        default:
            throw new UnknownCommandException();
        }

        if (hasAddedTask || hasUpdate) {
            storage.updateAndSave(tasks.taskArrayList);
        }
    }

    /**
     * This method lists out the task in the TaskList.
     * @param tasks latest TaskList.
     */
    private void listTasks(TaskList tasks) {
        ui.printLine();

        if (tasks.getSize() == 0){
            System.out.println("There are currently no tasks in your list!");
            ui.printLine();
            return;
        }

        for (int i = 0; i < tasks.getSize(); i++) {
            System.out.print(i + 1);
            System.out.println("." + tasks.getTaskViaIndex(i));
        }

        ui.printLine();
    }

    /**
     * This method adds a task to the TaskList, with interaction with the user.
     * @param task task to be added.
     */
    private void addTask(Task task) {
        ui.printLine();

        tasks.addTaskToList(task);
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        System.out.println("Now you have " + tasks.getSize() + " task(s) in the list.");

        ui.printLine();
    }

    /**
     * This method adds a deadline to the TaskList. with interaction with the
     * user as well as error handling.
     * @param detail details of the deadline.
     */
    private void addDeadline(String detail) {
        int byIndex = detail.indexOf("/by");
        boolean isByPresent = byIndex != -1;
        if (isByPresent) {
            String by = detail.substring(byIndex + 3).trim();
            String deadlineDesc = detail.substring(0, byIndex).trim();
            addTask(new Deadline(deadlineDesc, by));
        } else {
            System.out.println("Sorry, missing /by argument...");
        }
    }

    /**
     * This method adds an event to the TaskList. with interaction with the
     * user as well as error handling.
     * @param detail details of the event.
     */
    private void addEvent(String detail) {
        int atIndex = detail.indexOf("/at");
        boolean isAtPresent = atIndex != -1;
        if (isAtPresent) {
            String at = detail.substring(atIndex + 3).trim();
            String eventDesc = detail.substring(0, atIndex).trim();
            addTask(new Event(eventDesc, at));
        } else {
            System.out.println("Sorry, missing /at argument...");
        }
    }

    /**
     * This method marks a task as complete, with interaction with the user.
     * @param response input by the user.
     * @return a boolean variable indicating if there has been any update or not.
     */
    private boolean markTask(String response) {
        ui.printLine();

        try {
            String[] words = response.split(" ");
            int taskIndex = Integer.parseInt(words[1]);
            boolean isNotIndex = taskIndex > tasks.getSize() || taskIndex == 0 || taskIndex < 0;

            if (isNotIndex) {
                System.out.println("Sorry, seems like there's no such task with that index.");
                ui.printLine();
                return false;
            } else {
                Task t = tasks.getTaskViaIndex(taskIndex - 1); //list indexing to the user starts from 1 but list indexing in fact starts from 0 internally
                boolean isNotDone = !t.getDoneStatus();

                if (isNotDone) {
                    t.markAsDone();
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println(t);
                } else {
                    System.out.println("Hmm, you've already marked this task.");
                }
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("☹ OOPS!!! You forgot to indicate an index!");
        }

        ui.printLine();
        return true;
    }

    /**
     * Marks a task as incomplete with interaction with the user.
     * @param response input by the user.
     * @return boolean variable indicating if there has been any update.
     */
    private boolean unmarkTask(String response) {
        ui.printLine();

        try {
            String[] words = response.split(" ");
            int taskIndex = Integer.parseInt(words[1]);
            boolean isNotIndex = taskIndex > tasks.getSize() || taskIndex == 0;

            if (isNotIndex) {
                System.out.println("Sorry, seems like there's no such task with that index.");
                ui.printLine();
                return false;
            } else {
                Task t = tasks.getTaskViaIndex(taskIndex - 1); //list indexing to the user starts from 1 but list indexing in fact starts from 0 internally
                boolean isDone = t.getDoneStatus();

                if (isDone) {
                    t.markAsNotDone();
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println(t);
                } else {
                    System.out.println("Hmm, this task is already unmarked.");
                }
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("☹ OOPS!!! You forgot to indicate an index!");
        }

        ui.printLine();
        return true;
    }

    /**
     * Deletes a task from TaskList with interaction with the user.
     * @param response input by the user.
     */
    private void deleteTask(String response) {
        ui.printLine();

        try {
            String[] words = response.split(" ");
            int taskIndex = Integer.parseInt(words[1]);
            boolean isNotIndex = taskIndex > tasks.getSize() || taskIndex == 0;

            if (isNotIndex) {
                System.out.println("Sorry, seems like there's no such task with that index.");
                ui.printLine();
                return;
            } else {
                Task t = tasks.getTaskViaIndex(taskIndex - 1);
                tasks.removeTaskFromList(t);
                System.out.println("Noted. I've removed this task:");
                System.out.println(t);
                System.out.println("Now you have " + tasks.getSize() + " tasks in the list");
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("☹ OOPS!!! You forgot to indicate an index!");
        }

        ui.printLine();
    }

    /**
     * Finds tasks based on search term and prints out the tasks that match
     * the search term.
     * @param detail the search term.
     */
    private void findTask(String detail) {
        ui.printLine();

        ArrayList<Task> filteredTasks = new ArrayList<>();

        filteredTasks = tasks.findFromList(detail);

        if (filteredTasks.size() == 0){
            System.out.println("There were no tasks found...");
            ui.printLine();
            return;
        }

        for (int i = 0; i < filteredTasks.size(); i++) {
            System.out.print(i + 1);
            System.out.println("." + filteredTasks.get(i));
        }

        ui.printLine();
    }
}
