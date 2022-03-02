package duke;// import libraries here
import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Set;
import java.io.FileWriter;
import java.io.IOException;

public class Duke {
    private static Storage storage = new Storage("data/tasks.txt");
    private static Ui ui = new Ui();
    protected static ArrayList<String> tasks = new ArrayList<>();
    protected static ArrayList<Boolean> dones = new ArrayList<>();
    protected static ArrayList<String> types = new ArrayList<>();
    protected static ArrayList<String> dates = new ArrayList<>();

    public static void main(String[] args) {
        ui.greeting();
        storage.loadTasks();
        getCommand();
    }

    private static void getCommand() {
        String input;
        Scanner in = new Scanner(System.in);
        // get user input
        input = in.nextLine();
        while (!input.equals("bye")) {
            if (input.equals("list")) {
                showList();
                input = in.nextLine();
                continue;
            }
            if (input.startsWith("done")) {
                int number = Integer.parseInt(input.substring(input.length() - 1));
                markAsDone(number);
                storage.saveTasks();
                input = in.nextLine();
                continue;
            }
            if (input.startsWith("delete")) {
                int number = Integer.parseInt(input.substring(input.length() - 1));
                deleteTask(number);
                storage.saveTasks();
                input = in.nextLine();
                continue;
            }
            if (input.startsWith("find")) {
                System.out.println("    ____________________________________________________________");
                System.out.println("Here are the matching tasks in your list:");
                String keyword = input.split(" ")[1].trim();
                for (int i = 0; i < tasks.size(); i++) {
                    String description = tasks.get(i);
                    String type = types.get(i);
                    boolean isDone = dones.get(i);
                    String date = dates.get(i);
                    if (description.contains(keyword)) {
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
                        }
                    }
                }
                System.out.println("    ____________________________________________________________");
                input = in.nextLine();
                continue;
            }
            try {
                checkCommand(input);
            } catch (InvalidCommandException | EmptyDescriptionException e) {
                input = in.nextLine();
                continue;
            }
            // Command is valid, handle the command
            System.out.println("    ____________________________________________________________");
            System.out.println("     Got it. I've added this task: ");
            handleCommand(input);
            storage.saveTasks();
            System.out.println("     Now you have " + tasks.size() + " tasks in the list.");
            System.out.println("    ____________________________________________________________");
            input = in.nextLine();
        }
        System.out.println("    ____________________________________________________________");
        System.out.println("     Bye. Hope to see you again soon!");
        System.out.println("    ____________________________________________________________");
    }

    /**
     *
     * @param line
     * @throws EmptyDescriptionException
     * @throws InvalidCommandException
     */
    private static void checkCommand(String line) throws EmptyDescriptionException, InvalidCommandException {
        Set<String> validCommands = Set.of("todo", "deadline", "event", "find");
        String[] splitLine = line.split(" ");
        String type = splitLine[0];
        if (!validCommands.contains(type)) {
            throw new InvalidCommandException();
        }
        if (splitLine.length == 1) {
            throw new EmptyDescriptionException();
        }
    }

    /**
     *
     * @param line
     */
    private static void handleCommand(String line) {
        String description;
        String date;
        dones.add(false);
        if (line.startsWith("todo")) {
            types.add("T");
            description = line.substring(5);
            Todo todo = new Todo(description);
            tasks.add(description);
            dates.add("");
            System.out.println("       " + todo.toString(false));
        } else if (line.startsWith("deadline")) {
            types.add("D");
            int separator = line.indexOf("/by");
            description = line.substring(9, separator - 1);
            date = line.substring(separator + 4);
            Deadline deadline = new Deadline(description, date);
            tasks.add(description);
            dates.add(date);
            System.out.println("       " + deadline.toString(false));
        } else if (line.startsWith("event")) {
            types.add("E");
            int separator = line.indexOf("/at");
            description = line.substring(6, separator - 1);
            date = line.substring(separator + 4);
            Event event = new Event(description, date);
            tasks.add(description);
            dates.add(date);
            System.out.println("       " + event.toString(false));
        } else {
            types.add("unknown");
        }
    }

    private static void showList() {
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
    private static void markAsDone(int number) {
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
    private static void deleteTask(int number) {
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
    }l
}