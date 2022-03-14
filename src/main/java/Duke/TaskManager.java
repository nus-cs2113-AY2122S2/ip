package Duke;

import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TaskManager {
    /**
     * list to store all tasks
     */
    private final static List<Task> tasks = new ArrayList<>();

    /**
     * Add all the tasks in args
     *
     * @param args tasks to be added
     */
    public static void addTasks(String[] args) throws DukeException {
        throwIfArgsIsNotValid(args, "task");
        for (int i = 1; i < args.length; i++) {
            Task task = new Task(args[i]);
            tasks.add(task);
            System.out.println("Added: " + task);
        }
    }


    private static void throwIfArgsIsNotValid(String[] args, String commandName) throws DukeException {
        if (args.length <= 1) {
            throw new DukeException("☹ OOPS!!! The description of a " + commandName + " cannot be empty.");
        }
    }

    /**
     * Return the corresponding task index by its array index.
     * By conventional task index == array index + 1
     *
     * @param arrayID array id
     * @return task id of corresponding array id
     */
    private static int getTaskID(int arrayID) {
        return arrayID + 1;
    }

    /**
     * Return the corresponding task index by its array index.
     * By conventional array index == task index - 1
     *
     * @param taskID task id
     * @return array id of corresponding task id
     */
    private static int getArrayID(int taskID) {
        return taskID - 1;
    }

    /**
     * List all the tasks
     *
     * @param args dump variable, no use
     */
    public static void listTasks(String[] args) throws DukeException {
        // TODO args can be used to control the style of output
        // not throwing anything because no args is needed.
        if (tasks.isEmpty()) {
            System.out.println("Oooooops, you haven't added anything.");
            return;
        }
        for (int i = 0; i < tasks.size(); i++) {
            System.out.printf("%d. %s\n", getTaskID(i), tasks.get(i));
        }
    }

    /**
     * Mark every task whose is in args[1..n) as done
     * Using by > mark {task1 id} {task2 id} ...
     *
     * @param args arguments
     */
    public static void mark(String[] args, boolean isPrintingPrompt) throws DukeException {
        throwIfArgsIsNotValid(args, "mark");
        for (int i = 1; i < args.length; i++) {
            int taskID = Integer.parseInt(args[i]);
            int arrayID = getArrayID(taskID);
            if (arrayID < 0 || arrayID >= tasks.size()) {
                if (isPrintingPrompt) {
                    System.out.println("There is no " + taskID + " task.");
                }
                continue;
            }
            tasks.get(arrayID).setDone();
            if (isPrintingPrompt) {
                System.out.println(tasks.get(arrayID));
            }
        }
        if (isPrintingPrompt) {
            System.out.println("Nice! I've marked those valid tasks as done");
        }
    }


    /**
     * Mark every task whose is in args[1..n) as not done yet.
     * Using by > unmark {task1 id} {task2 id} ...
     *
     * @param args arguments
     */
    public static void unmark(String[] args) throws DukeException {
        throwIfArgsIsNotValid(args, "unmark");
        List<Task> unmarked = new ArrayList<>();
        for (int i = 1; i < args.length; i++) {
            Integer arrayID = tryParseIntAndThrow(args[i], "unmark");
            if (arrayID == null) {
                continue;
            }
            tasks.get(arrayID).clearDone();
            unmarked.add(tasks.get(arrayID));
        }
        if (!unmarked.isEmpty()) {
            System.out.println("Nice! I've marked these tasks as not done yet:");
            for (int i = 0; i < unmarked.size(); i++) {
                System.out.println("\t" + unmarked.get(i));
            }
        } else {
            System.out.println("No task is unmarked");
        }
    }

    private static Integer tryParseIntAndThrow(String args_i, String commandName) throws DukeException {
        int taskID;
        try {
            taskID = Integer.parseInt(args_i);
        } catch (NumberFormatException exception) {
            throw new DukeException(commandName + "'s arg is not number, please enter correct command\n\t"
                    + exception);
        }
        int arrayID = getArrayID(taskID);
        if (arrayID < 0 || arrayID >= tasks.size()) {
            System.out.println("There is no task " + taskID + ".");
            return null;
        }
        return arrayID;
    }


    /**
     * @return the total number of tasks
     */
    public static int getTaskNumber() {
        return tasks.size();
    }


    /**
     * Print the total number of tasks to user
     */
    public static void printTaskNumber() {
        System.out.println("Now you have " + getTaskNumber() + " task(s) in the list.");
    }

    /**
     * Add multiple ToDoes to the task array
     * Using by > todo {todo1 id} {todo2 id} ...
     *
     * @param args arguments
     */
    public static void addToDoes(String[] args, boolean isPrintingPrompt) throws DukeException {
        throwIfArgsIsNotValid(args, "todo");
        for (int i = 1; i < args.length; i++) {
            ToDo todo = new ToDo((args[i]));
            tasks.add(todo);
            if (isPrintingPrompt) {
                System.out.println("Added: " + todo);
            }
        }
    }

    /**
     * Add multiple deadlines to task array
     * Using by > deadline {ddl 1} {ddl 2} ... /by {ddl time for 1, 2...} {adl a} {ddl b} ... /by {ddl time for a, b...} ...
     *
     * @param args arguments
     */
    public static void addDeadlines(String[] args, boolean isPrintingPrompt) throws DukeException, DateTimeParseException {
        throwIfArgsIsNotValid(args, "deadline");
        List<String> contents = new ArrayList<>();
        String deadlineTime;
        for (int i = 1; i < args.length; i++) {
            if (args[i].equals("/by")) {
                if (i + 1 < args.length) {
                    deadlineTime = args[i + 1];
                    addMultipleDeadlines(contents, deadlineTime, isPrintingPrompt);
                    contents.clear();
                    i++;
                }
            } else {
                contents.add(args[i]);
            }
        }
        if (!contents.isEmpty() && isPrintingPrompt) {
            System.out.print("Deadline adding failed (no corresponding deadline time):\n");
            for (int i = 0; i < contents.size(); i++) {
                System.out.print((i + 1) + ". " + contents.get(i) + "\t");
            }
            System.out.println();
        }
    }

    private static void addMultipleDeadlines(List<String> contents, String deadlineTime, boolean isPrintingPrompt) throws DateTimeParseException {
        for (String content : contents) {
            Deadline ddl = new Deadline(content, deadlineTime);
            tasks.add(ddl);
            if (isPrintingPrompt) {
                System.out.println("Added: " + ddl);
            }
        }
    }

    /**
     * Add multiple events to task array.
     * Using by > event {evt 1} {evt 2} ... /at {evt time for 1, 2...} {evt a} {evt b} ... /at {evt time for a, b...} ...
     *
     * @param args arguments
     */
    public static void addEvents(String[] args, boolean isPrintingPrompt) throws DukeException, DateTimeParseException {
        throwIfArgsIsNotValid(args, "event");
        List<String> contents = new ArrayList<>();
        String schedule;
        for (int i = 1; i < args.length; i++) {
            if (args[i].equals("/at")) {
                if (i + 1 < args.length) {
                    schedule = args[i + 1];
                    addMultipleEvents(contents, schedule);
                    contents.clear();
                    i++;
                }
            } else {
                contents.add(args[i]);
            }
        }
        if (!contents.isEmpty() && isPrintingPrompt) {
            System.out.print("Event adding failed (no corresponding schedule time):\n");
            for (int i = 0; i < contents.size(); i++) {
                System.out.print((i + 1) + ". " + contents.get(i) + "\t");
            }
            System.out.println();
        }
    }

    public static void delete(String[] args) throws DukeException {
        throwIfArgsIsNotValid(args, "delete");
        List<Integer> toDelete = new ArrayList<>();
        for (int i = 1; i < args.length; i++) {
            Integer arrayID = tryParseIntAndThrow(args[i], "delete");
            if (arrayID == null) {
                continue;
            }
            if (arrayID < 0 || tasks.size() <= arrayID) {
                System.out.println("The " + getTaskID(arrayID) + " task doesn't exist");
                return;
            }
            System.out.println("Noted. I've removed this task:");
            System.out.println("\t" + tasks.get(arrayID));
            toDelete.add(arrayID);
        }
        Collections.sort(toDelete);
        for (int i = toDelete.size() - 1; i >= 0; i--) {
            tasks.remove((int) toDelete.get(i));
        }
    }

    public static void find(String[] args) throws DukeException {
        throwIfArgsIsNotValid(args, "find");
        for (int i = 0; i < tasks.size(); i++) {
            for (int j = 1; j < args.length; j++) {
                if (tasks.get(i).getContent().contains(args[j])) {
                    System.out.println(getTaskID(i) + ". " + tasks.get(i));
                    break;
                }
            }
        }
    }

    private static void addMultipleEvents(List<String> contents, String deadlineTime) throws DateTimeParseException {
        for (String content : contents) {
            Event ddl = new Event(content, deadlineTime);
            tasks.add(ddl);
            System.out.println("Added: " + ddl);
        }
    }

    public static Task[] getCurrentTasks() {
        return tasks.toArray(new Task[0]);
    }
}
