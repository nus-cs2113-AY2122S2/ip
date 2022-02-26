package duke;

import duke.exceptions.DeadlineFormatException;
import duke.exceptions.EventFormatException;
import duke.exceptions.GeneralException;
import duke.exceptions.TaskEmptyException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.time.LocalDate;
import java.util.ArrayList;

public class TaskList {
    public static String boundary = "____________________________________________________________" + System.lineSeparator();
    protected ArrayList<Task> taskList = new ArrayList<>();
    protected int countTask;

    public TaskList() {
        countTask = 0;
    }

    /* Get the task of the given index.
     * @param index The index of the task to be returned.
     * @return The task of the index requested.
     */
    public Task getTask(int index) {
        return taskList.get(index);
    }

    /* Add the given task into the task list.
     * @param request The input request given by user.
     */
    public void addTask(String request) throws GeneralException,
                                                              TaskEmptyException, DeadlineFormatException, EventFormatException {
        if (request.toLowerCase().startsWith("deadline")) {
            if ((!request.contains("/by"))) {
                throw new DeadlineFormatException();
            } else if (request.substring(9, (request.indexOf("/by"))).trim().equals("")) {
                throw new TaskEmptyException();
            }
            int byPosition = request.indexOf("/");
            taskList.add(new Deadline(request.substring(9, byPosition - 1), request.substring(byPosition + 4)));
        } else if (request.toLowerCase().startsWith("event")) {
            if (!request.contains("/at")) {
                throw new EventFormatException();
            } else if (request.substring(6, (request.indexOf("/at"))).trim().equals("")) {
                throw new TaskEmptyException();
            }
            int atPosition = request.indexOf("/");
            taskList.add(new Event(request.substring(6, atPosition - 1), request.substring(atPosition + 4)));
        } else if (request.toLowerCase().startsWith("todo")) {
            taskList.add(new Todo(request.substring(5)));
        } else {
            throw new GeneralException();
        }

        countTask++;

        System.out.println(boundary + "Got it. I've added this task: " + System.lineSeparator() + taskList.get(countTask - 1));
        System.out.print("Now you have " + countTask + " tasks in the list."+ System.lineSeparator() + boundary);
    }

    public void addTodo(String description) {
        taskList.add(new Todo(description));
    }

    public void addDeadline(String description, String by) {
        taskList.add(new Deadline(description, by));
    }

    public void addEvent(String description, String at) {
        taskList.add(new Event(description, at));
    }

    /* Mark the given task as done
     * @param toMark The index of the task to be marked as done.
     */
    public void markTask(int toMark) {
        taskList.get(toMark).markDone();
        System.out.println(boundary + "Nice! I've marked this task as done:"
                                   + System.lineSeparator() + taskList.get(toMark));
        System.out.print(boundary);
    }

    /* Mark the given task as not done yet.
     * @param toUnmark The index of the task to be unmarked.
     */
    public void unmarkTask(int toUnmark) {
        taskList.get(toUnmark).markNotDone();
        System.out.println(boundary + "OK, I've marked this task as not done yet:"
                                   + System.lineSeparator() + taskList.get(toUnmark));
        System.out.print(boundary);
    }

    // Print out the current tasks.
    public void printList() {
        System.out.println(boundary + "Here are the tasks in your list:");
        for (int i = 0; i < countTask; i++) {
            System.out.println((i + 1) + ". " + getTask(i));
        }
        System.out.print("Now you have " + countTask + " tasks in the list."+ System.lineSeparator() + boundary);
    }

    /* Find tasks with the given keyword and print them out.
     * @param keyword The keyword to search for in existing tasks.
     */
    public void findTask(String keyword) {
        System.out.println(boundary + "Here are the matching tasks in your list:");
        for (int i = 0; i < countTask; i++) {
            if (taskList.get(i).getDescription().contains(keyword)) {
                System.out.println(taskList.get(i));
            }
        }
        System.out.print(boundary);
    }

    /* Delete the given task from task list.
     * @param index The index of the task to be deleted.
     */
    public void deleteTask(int index) {
        System.out.println(boundary + "Noted. I've removed this task:");
        System.out.println(taskList.get(index));
        System.out.print("Now you have " + (countTask - 1) + " tasks in the list." + System.lineSeparator() + boundary);
        taskList.remove(index);
        countTask -= 1;
    }
}
