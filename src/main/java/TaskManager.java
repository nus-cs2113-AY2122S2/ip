import java.util.ArrayList;
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
    public static void addTasks(String[] args) {
        if (!args[0].equals("add")) {
            // there must be some error
            // TODO exception handle
            return;
        }
        for (int i = 1; i < args.length; i++) {
            Task task = new Task(args[i]);
            tasks.add(task);
            System.out.println("Added: " + task);
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
    public static void listTasks(String[] args) {
        // TODO args can be used to control the style of output
        if (!args[0].equals("list")) {
            // there must be some error
            // TODO exception handle
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
    public static void mark(String[] args) {
        if (!args[0].equals("mark")) {
            // there must be some error
            // TODO exception handle
            return;
        }
        System.out.println("Nice! I've marked these tasks as done\n");
        for (int i = 1; i < args.length; i++) {
            int taskID = Integer.parseInt(args[i]);
            int arrayID = getArrayID(taskID);
            if(arrayID < 0 || arrayID >= tasks.size()) {
                System.out.println("There is no " + taskID + " task.");
                continue;
            }
            tasks.get(arrayID).setDone();
            System.out.println(tasks.get(arrayID));
        }
    }


    /**
     * Mark every task whose is in args[1..n) as not done yet.
     * Using by > unmark {task1 id} {task2 id} ...
     *
     * @param args arguments
     */
    public static void unmark(String[] args) {
        if (!args[0].equals("unmark")) {
            // there must be some error
            // TODO exception handle
            return;
        }
        System.out.println("Nice! I've marked these tasks as not done yet\n");
        for (int i = 1; i < args.length; i++) {
            int taskID = Integer.parseInt(args[i]);
            int arrayID = getArrayID(taskID);
            if(arrayID < 0 || arrayID >= tasks.size()) {
                System.out.println("There is no " + taskID + " task.");
                continue;
            }
            tasks.get(arrayID).clearDone();
            System.out.println(tasks.get(arrayID));
        }
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
     * @param args arguments
     */
    public static void addToDoes(String[] args) {
        if (!args[0].equals("todo")) {
            // there must be some error
            // TODO exception handle
            return;
        }
        for (int i = 1; i < args.length; i++) {
            ToDo todo = new ToDo((args[i]));
            tasks.add(todo);
            System.out.println("Added: " + todo);
        }
    }

    /**
     * Add multiple deadlines to task array
     * Using by > deadline {ddl 1} {ddl 2} ... /by {ddl time for 1, 2...} {adl a} {ddl b} ... /by {ddl time for a, b...} ...
     * @param args arguments
     */
    public static void addDeadlines(String[] args) {
        if (!args[0].equals("deadline")) {
            // there must be some error
            // TODO exception handle
            return;
        }
        List<String> contents = new ArrayList<>();
        String deadlineTime;
        for (int i = 1; i < args.length; i++) {
            if (args[i].equals("/by")) {
                if (i + 1 < args.length) {
                    deadlineTime = args[i + 1];
                    addMultipleDeadlines(contents, deadlineTime);
                    contents.clear();
                    i++;
                }
            } else {
                contents.add(args[i]);
            }
        }
        if (!contents.isEmpty()) {
            System.out.print("Deadline adding failed (no corresponding deadline time):\n");
            for (int i = 0; i < contents.size(); i++) {
                System.out.print((i+1) + ". "+ contents.get(i) + "\t");
            }
            System.out.println();
        }
    }

    private static void addMultipleDeadlines(List<String> contents, String deadlineTime) {
        for (String content : contents) {
            Deadline ddl = new Deadline(content, deadlineTime);
            tasks.add(ddl);
            System.out.println("Added: " + ddl);
        }
    }

    /**
     * Add multiple events to task array.
     * Using by > event {evt 1} {evt 2} ... /at {evt time for 1, 2...} {evt a} {evt b} ... /at {evt time for a, b...} ...
     * @param args arguments
     */
    public static void addEvents(String[] args) {
        if (!args[0].equals("event")) {
            // there must be some error
            // TODO exception handle
            return;
        }
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
        if (!contents.isEmpty()) {
            System.out.print("Event adding failed (no corresponding schedule time):\n");
            for (int i = 0; i < contents.size(); i++) {
                System.out.print((i+1) + ". "+ contents.get(i) + "\t");
            }
            System.out.println();
        }
    }

    private static void addMultipleEvents(List<String> contents, String deadlineTime) {
        for (String content : contents) {
            Event ddl = new Event(content, deadlineTime);
            tasks.add(ddl);
            System.out.println("Added: " + ddl);
        }
    }
}
