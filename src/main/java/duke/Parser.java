package duke;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Set;

import static duke.Duke.markAsDone;
import static duke.Duke.showList;
import static duke.Duke.storage;
import static duke.Duke.deleteTask;

public class Parser {
    /**
     * Identifies the type of command passed and based on the
     * command input and decide what type of command to return.
     */
    private static final String BYE = "bye";
    private static final String LIST = "list";
    protected static ArrayList<String> tasks = new ArrayList<>();
    protected static ArrayList<Boolean> dones = new ArrayList<>();
    protected static ArrayList<String> types = new ArrayList<>();
    protected static ArrayList<String> dates = new ArrayList<>();

    public static void getCommand() {
        String input;
        Scanner in = new Scanner(System.in);
        // get user input
        input = in.nextLine();
        while (!input.equals(BYE)) {
            if (input.equals(LIST)) {
                showList();
                input = in.nextLine();
                continue;
            }
            if (input.startsWith("done")) {
                int number;
                try {
                    number = getChoiceNumber(input, "done");
                } catch (InvalidCommandException e) {
                    input = in.nextLine();
                    continue;
                }
                markAsDone(number);
                storage.saveTasks();
                input = in.nextLine();
                continue;
            }
            if (input.startsWith("delete")) {
                int number;
                try {
                    number = getChoiceNumber(input, "delete");
                } catch (InvalidCommandException e) {
                    input = in.nextLine();
                    continue;
                }
                deleteTask(number);
                storage.saveTasks();
                input = in.nextLine();
                continue;
            }
            if (input.startsWith("find")) {
                try {
                    checkCommand(input);
                } catch (InvalidCommandException | EmptyDescriptionException e) {
                    input = in.nextLine();
                    continue;
                }
                findTasks(input);
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
     * @param line input entered by user.
     * @throws EmptyDescriptionException
     * @throws InvalidCommandException
     * @throws NumberFormatException
     */
    private static void checkCommand(String line) throws EmptyDescriptionException, InvalidCommandException, NumberFormatException {
        Set<String> validCommands = Set.of("todo", "deadline", "event", "find", "done", "delete");
        String[] splitLine = line.split(" ");
        String type = splitLine[0];
        if (splitLine.length == 1) {
            throw new EmptyDescriptionException();
        }
        if (line.startsWith("event") && !line.contains("/at")) {
            throw new InvalidCommandException();
        }
        if (line.startsWith("deadline") && !line.contains("/by")) {
            throw new InvalidCommandException();
        }
        if (!validCommands.contains(type)) {
            throw new InvalidCommandException();
        }
    }

    /**
     * @param line input entered by user.
     */
    private static void handleCommand(String line) {
        dones.add(false);
        if (line.startsWith("todo")) {
            addTodo(line);
        } else if (line.startsWith("deadline")) {
            addDeadline(line);
        } else if (line.startsWith("event")) {
            addEvent(line);
        } else {
            types.add("unknown");
        }
    }

    /**
     *
     * @param commandString input entered by user.
     * @param wishType      to derive delete, tick, un-tick word length for filtering.
     * @return choice number.
     * @throws InvalidCommandException if choice is invalid.
     */
    private static int getChoiceNumber(String commandString, String wishType) throws InvalidCommandException {
        String choice = commandString.substring(wishType.length()).trim();
        try {
            return Integer.parseInt(choice);
        } catch (NumberFormatException numError) {
            throw new InvalidCommandException();
        }
    }

    /**
     * @param input input entered by user.
     */
    private static void findTasks(String input) {
        System.out.println("    ____________________________________________________________");
        System.out.println("    Here are the matching tasks in your list:");
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
    }

    /**
     * @param input input entered by user.
     */
    private static void addTodo(String input) {
        String description;
        types.add("T");
        description = input.substring(5);
        Todo todo = new Todo(description);
        tasks.add(description);
        dates.add("");
        System.out.println("       " + todo.toString(false));
    }

    /**
     * @param line input entered by user.
     */
    private static void addDeadline(String line) {
        String description;
        String date;
        types.add("D");
        int separator = line.indexOf("/by");
        description = line.substring(9, separator - 1);
        date = line.substring(separator + 4);
        Deadline deadline = new Deadline(description, date);
        tasks.add(description);
        dates.add(date);
        System.out.println("       " + deadline.toString(false));
    }

    /**
     * @param line input entered by user.
     */
    private static void addEvent(String line) {
        String description;
        String date;
        types.add("E");
        int separator = line.indexOf("/at");
        description = line.substring(6, separator - 1);
        date = line.substring(separator + 4);
        Event event = new Event(description, date);
        tasks.add(description);
        dates.add(date);
        System.out.println("       " + event.toString(false));
    }
}
