package duke;// import libraries here
import duke.Deadline;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Set;

public class Duke {
    private static ArrayList<String> descriptions = new ArrayList<>();
    private static ArrayList<Boolean> dones = new ArrayList<>();
    private static ArrayList<String> types = new ArrayList<>();
    private static ArrayList<String> dates = new ArrayList<>();

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        greeting();
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
            System.out.println("     Now you have " + descriptions.size() + " tasks in the list.");
            System.out.println("    ____________________________________________________________");
            input = in.nextLine();
        }
        System.out.println("    ____________________________________________________________");
        System.out.println("     Bye. Hope to see you again soon!");
        System.out.println("    ____________________________________________________________");
    }

    private static void checkCommand(String line) throws EmptyDescriptionException, InvalidCommandException {
        Set<String> validCommands = Set.of("todo", "deadline", "event");
        String[] splitLine = line.split(" ");
        String type = splitLine[0];
        if (!validCommands.contains(type)) {
            throw new InvalidCommandException();
        }
        if (splitLine.length == 1) {
            throw new EmptyDescriptionException();
        }
    }


    private static void handleCommand(String line) {
        String description;
        String date;
        dones.add(false);
        if (line.startsWith("todo")) {
            types.add("T");
            description = line.substring(5);
            Todo todo = new Todo(description);
            descriptions.add(description);
            dates.add("");
            System.out.println("       " + todo.toString(false));
        } else if (line.startsWith("deadline")) {
            types.add("D");
            int separator = line.indexOf("/by");
            description = line.substring(9, separator - 1);
            date = line.substring(separator + 4);
            Deadline deadline = new Deadline(description, date);
            descriptions.add(description);
            dates.add(date);
            System.out.println("       " + deadline.toString(false));
        } else if (line.startsWith("event")) {
            types.add("E");
            int separator = line.indexOf("/at");
            description = line.substring(6, separator - 1);
            date = line.substring(separator + 4);
            Event event = new Event(description, date);
            descriptions.add(description);
            dates.add(date);
            System.out.println("       " + event.toString(false));
        } else {
            types.add("unknown");
        }
    }

    private static void showList() {
        System.out.println("    ____________________________________________________________");
        System.out.println("     Here are the tasks in your list:");
        for (int i = 0; i < descriptions.size(); i++) {
            String description = descriptions.get(i);
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

    private static void markAsDone(int number) {
        System.out.println("    ____________________________________________________________");
        System.out.println("     Nice! I've marked this task as done:");
        int index = number - 1;
        String description = descriptions.get(index);
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

    private static void greeting() {
        System.out.println("    ____________________________________________________________");
        System.out.println("     Hello! I'm duke.Duke");
        System.out.println("     What can I do for you?");
        System.out.println("    ____________________________________________________________");
        System.out.println();
    }

}