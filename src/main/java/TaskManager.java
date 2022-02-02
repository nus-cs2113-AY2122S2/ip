import java.util.ArrayList;
import java.util.List;

public class TaskManager {
    /**
     * list to store all reminders
     */
    private static List<Task> tasks = new ArrayList<Task>();

    /**
     * Add all the reminders in args
     *
     * @param args reminders to be added
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
     * Return the corresponding reminder index by its array index.
     * By conventional reminder index == array index + 1
     *
     * @param arrayID
     * @return
     */
    private static int getReminderID(int arrayID) {
        return arrayID + 1;
    }

    /**
     * Return the corresponding reminder index by its array index.
     * By conventional array index == reminder index - 1
     *
     * @param reminderID
     * @return
     */
    private static int getArrayID(int reminderID) {
        return reminderID - 1;
    }

    /**
     * List all the reminders
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
            System.out.printf("%d. %s\n", getReminderID(i), tasks.get(i));
        }
    }

    /**
     * Mark every reminder whose is in args[1..n) as done
     *
     * @param args the name of function and those reminder ID to be marked as done
     */
    public static void mark(String[] args) {
        if (!args[0].equals("mark")) {
            // there must be some error
            // TODO exception handle
            return;
        }
        System.out.println("Nice! I've marked these tasks as done\n");
        for (int i = 1; i < args.length; i++) {
            int reminderID = Integer.parseInt(args[i]);
            int arrayID = getArrayID(reminderID);
            tasks.get(arrayID).setDone();
            System.out.println(tasks.get(arrayID));
        }
    }


    /**
     * Mark every reminder whose is in args[1..n) as not done yet
     *
     * @param args the name of function and those reminder ID to be marked as not done yet
     */
    public static void unmark(String[] args) {
        if (!args[0].equals("unmark")) {
            // there must be some error
            // TODO exception handle
            return;
        }
        System.out.println("Nice! I've marked these tasks as not done yet\n");
        for (int i = 1; i < args.length; i++) {
            int reminderID = Integer.parseInt(args[i]);
            int arrayID = getArrayID(reminderID);
            tasks.get(arrayID).clearDone();
            System.out.println(tasks.get(arrayID));
        }
    }


    public static int getTaskNumber() {
        return tasks.size();
    }

    public static void printTaskNumber() {
        System.out.println("Now you have " + getTaskNumber() + " task(s) in the list.");
    }

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
