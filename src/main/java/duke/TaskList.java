package duke;

import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.util.ArrayList;

/**
 * Handles the list of tasks.
 */
public class TaskList {
    private final ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Stores a task in the list of tasks.
     * @param pieces ArrayList<String> containing input from stdin. The first String in task should represent the type of
     *                   Task that must be added to the list of Tasks. Supported tasks: todo, deadline, event
     */
    public void addTask(ArrayList<String> pieces) {
        try {
            tasks.add(buildTask(pieces));
            System.out.println("Got it. I've added this task:\n  " + this.tasks.get(this.tasks.size() - 1));
            System.out.println("Now you have " + tasks.size()
                    + (tasks.size() == 1 ? " task" : " tasks") + " in the list.");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    /**
     * Constructs a subclass of Task from an ArrayList<String>.
     *
     * @param pieces the ArrayList<String> containing the user's input, split up by whitespace.
     *                        The first element of this list should contain the subclass of task to be constructed.
     * @return a subclass of Task determined by the first element of pieces.
     */
    public static Task buildTask(ArrayList<String> pieces) throws DukeException {
        String taskType = pieces.get(0);
        ArrayList<StringBuilder> taskParts = splitTask(pieces);
        validateTask(taskType, taskParts);
        switch (taskType) {
        case Parser.COMMAND_TODO:
            return new ToDo(String.valueOf(taskParts.get(0)));
        case Parser.COMMAND_DEADLINE:
            return new Deadline(String.valueOf(taskParts.get(0)),
                    String.valueOf(taskParts.get(1)));
        case Parser.COMMAND_EVENT:
            return new Event(String.valueOf(taskParts.get(0)),
                    String.valueOf(taskParts.get(1)));
        }
        return null;
    }

    /**
     * Validates the input of the task to check for user error.
     *
     * @param taskType  the type of task (todo, deadline etc).
     * @param taskList the list of components that form the task.
     * @throws DukeException error message if task is invalid
     */
    private static void validateTask(String taskType, ArrayList<StringBuilder> taskList) throws DukeException {
        switch (taskType) {
        case Parser.COMMAND_TODO:
            if (taskList.isEmpty()) {
                throw new DukeException("Task details missing.");
            }
            break;
        case Parser.COMMAND_EVENT:
        case Parser.COMMAND_DEADLINE:
            if (taskList.size() < 2) {
                throw new DukeException("Task details missing.");
            }
            String firstPart = String.valueOf(taskList.get(0));
            String secondPart = String.valueOf(taskList.get(1));
            if (firstPart.equals("") || secondPart.equals("")) {
                throw new DukeException("Task details missing.");
            }
            break;
        }
    }

    /**
     * Splits an ArrayList<String> into an ArrayList<StringBuilder>, where each element
     * can be used to construct a subclass of Task. Use this in conjunction with buildTask to construct a
     * subclass of Task.
     *
     * @param pieces ArrayList of Strings that represent the user's pieces split by whitespace.
     * @return an ArrayList<StringBuilder> containing the pieces split by the '/' character.
     * Each element represents a String used to construct the task.
     * e.g.: deadline return book /by Sunday returns the following Arraylist:
     * {"return book", "Sunday"}
     * returns an empty list if pieces contains only a command.
     */
    private static ArrayList<StringBuilder> splitTask(ArrayList<String> pieces) {
        ArrayList<StringBuilder> taskParts = new ArrayList<>();
        taskParts.add(new StringBuilder(""));
        taskParts.add(new StringBuilder(""));
        if (pieces.size() <= 1) {
            return taskParts;
        }
        for (int i = 0, j = 1; i < 2; ++i) {
            while (j < pieces.size() && !pieces.get(j).startsWith("/")) {
                taskParts.get(i).append(pieces.get(j));
                taskParts.get(i).append(" ");
                ++j;
            }
            ++j;
        }
        return taskParts;
    }

    /**
     * Prints all tasks stored in memory by addTask(Task) to stdout
     */
    public void listTasks() {
        System.out.println("Here are the tasks in your list:");
        printTasks();
    }

    public void printTasks() {
        if (tasks.isEmpty()) {
            return;
        }
        for (int i = 1; i <= this.tasks.size(); i++) {
            System.out.println(i + "." + this.tasks.get(i - 1));
        }
    }

    /**
     * Marks the task selected by the user as done. Tasks are selected by their visual index on the list
     * (starting from 1, not 0) and not by name.
     *
     * @param task The index of the task to be marked done.
     */
    public void doTask(String task) {
        if (task == null) {
            System.out.println(("Task to be done is null."));
            return;
        }
        try {
            this.tasks.get(Integer.parseInt(task) - 1).doTask();
            System.out.println("Nice! I've marked this task as done:");
            System.out.println(this.tasks.get(Integer.parseInt(task) - 1));
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    /**
     * Marks the task selected by the user as undone. Tasks are selected by their visual index on the list
     * (starting from 1, not 0) and not by name.
     *
     * @param task The index of the task to be marked done.
     */
    public void undoTask(String task) {
        if (task == null) {
            System.out.println("Error: Task to be undone is null.");
            return;
        }
        try {
            this.tasks.get(Integer.parseInt(task) - 1).undoTask();
            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println(this.tasks.get(Integer.parseInt(task) - 1));
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    /**
     * Lists tasks containing the supplied keyword in the description.
     * @param task String keyword to search for.
     */
    public void findTask(String task) {
        int taskCounter = 1;
        System.out.println("Here are the matching tasks in your list:");
        for (Task i : tasks) {
            if (i.getDescription().contains(task)) {
                System.out.print(taskCounter + ".");
                System.out.println(i);
                taskCounter++;
            }
        }
    }

    /**
     * Deletes a task based on its visual index in the list.
     * @param task Visual index in the list, starting from 1.
     */
    public void deleteTask(String task) {
        if (task == null) {
            System.out.println(("Task to be done is null."));
            return;
        }
        try {
            Task removedTask = tasks.get(Integer.parseInt(task) -1);
            System.out.println("Noted. I've removed this task:");
            System.out.println(removedTask);
            tasks.remove(Integer.parseInt(task) - 1);
            System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
