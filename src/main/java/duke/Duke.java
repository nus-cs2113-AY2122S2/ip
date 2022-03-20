package duke;// import libraries here

import static duke.Parser.dones;
import static duke.Parser.tasks;
import static duke.Parser.dates;
import static duke.Parser.types;

public class Duke {
    protected static Storage storage = new Storage("data/tasks.txt");
    private static Ui ui = new Ui();
    private static Parser parser = new Parser();

    /**
     *
     * @throws InvalidCommandException
     */
    public static void main(String[] args) throws InvalidCommandException {
        ui.greeting();
        storage.loadTasks();
        parser.getCommand();
    }

    public static void showList() {
        System.out.println("    ____________________________________________________________");
        System.out.println("     Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            String description = tasks.get(i);
            String date = dates.get(i);
            String type = types.get(i);
            boolean isDone = dones.get(i);
            System.out.printf("     %d.", i + 1);
            switch (type) {
            case "T":
                Todo todo = new Todo(description);
                System.out.println(todo.toString(isDone));
                break;
            case "D":
                Deadline deadline = new Deadline(description, date);
                System.out.println(deadline.toString(isDone));
                break;
            case "E":
                Event event = new Event(description, date);
                System.out.println(event.toString(isDone));
                break;
            default:
                System.out.println("       Unknown Type");
            }
        }
        System.out.println("    ____________________________________________________________");
    }

    /**
     *
     * @param number
     */
    public static void markAsDone(int number) {
        System.out.println("    ____________________________________________________________");
        System.out.println("     Nice! I've marked this task as done:");
        int index = number - 1;
        String description = tasks.get(index);
        String date = dates.get(index);
        String type = types.get(index);
        // mark as done
        dones.set(index, true);
        switch (type) {
        case "T":
            Todo todo = new Todo(description);
            System.out.println("       " + todo.toString(true));
            break;
        case "D":
            Deadline deadline = new Deadline(description, date);
            System.out.println("       " + deadline.toString(true));
            break;
        case "E":
            Event event = new Event(description, date);
            System.out.println("       " + event.toString(true));
            break;
        default:
            System.out.println("       Unknown Type");
        }
        System.out.println("    ____________________________________________________________");
    }

    /**
     *
     * @param number
     */
    public static void deleteTask(int number) {
        System.out.println("    ____________________________________________________________");
        System.out.println("     Noted. I've removed this task:");
        int index = number - 1;
        String description = tasks.get(index);
        String date = dates.get(index);
        String type = types.get(index);
        boolean done = dones.get(index);
        switch (type) {
        case "T":
            Todo todo = new Todo(description);
            System.out.println("       " + todo.toString(done));
            break;
        case "D":
            Deadline deadline = new Deadline(description, date);
            System.out.println("       " + deadline.toString(done));
            break;
        case "E":
            Event event = new Event(description, date);
            System.out.println("       " + event.toString(done));
            break;
        default:
            System.out.println("       Unknown Type");
        }
        tasks.remove(index);
        dates.remove(index);
        types.remove(index);
        dones.remove(index);
        System.out.println("     Now you have " + tasks.size() + " tasks in the list.");
        System.out.println("    ____________________________________________________________");
    }
}