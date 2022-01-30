import java.util.Scanner;

public class Duke {
    public static void linePrinter() {
        System.out.print("\t");
        for(int i = 0; i < 70; i++) {
            System.out.print("\u2500");
        }
        System.out.println("\u2500");
    }

    public static void greeting() {
        linePrinter();
        System.out.println("\t" + " I have been waiting for you, Mister Winters");
        System.out.println("\t" + " Forgive my manners, call me the Duke. Now to business. " +
                "Weapons, ammunition, healing salves – anything you desire, I can provide.");
        linePrinter();

    }

    public static void printList(Todo[] tasks, int itemCount) {
        linePrinter();

        for (int i = 0; i < itemCount; i++) {
            System.out.println("\t" + " " + Integer.toString(i + 1) + " " + tasks[i].toString());
        }

        linePrinter();
    }

    public static void printMark(Todo[] tasks, int markedItem) {
        if (tasks[markedItem] == null) {
            echo("Ah...The task you choose doesn't exist on your list.");
        } else {
            linePrinter();
            System.out.println("\t" + " Aha! An interesting selection!");
            System.out.println("\t" + "   " + tasks[markedItem].toString());
            linePrinter();
        }
    }

    public static void printUnmark(Todo[] tasks, int unmarkedItem) {
        if (tasks[unmarkedItem] == null) {
            echo("Ah...The task you choose doesn't exist on your list.");
        } else {
            linePrinter();
            System.out.println("\t" + " Ah...");
            System.out.println("\t" + "   " + tasks[unmarkedItem].toString());
            linePrinter();
        }
    }

    public static void exitLine() {
        linePrinter();
        System.out.println("\t" + " Good day, then!");
        linePrinter();
    }

    public static void echo (String line) {
        linePrinter();
        System.out.println("\t" + " " + line);
        linePrinter();
    }

    public static CommandType findCommandType(String line) {
        CommandType c;

        if (line.startsWith("Todo ")) {
            c = CommandType.TODO;
        } else if (line.startsWith("Deadline ")) {
            c = CommandType.DEADLINE;
        } else if (line.startsWith("Event ")) {
            c = CommandType.EVENT;
        } else if (line.startsWith("Mark ")) {
            c= CommandType.MARK;
        } else if (line.startsWith("Unmark ")){
            c = CommandType.UNMARK;
        } else if (line.equals("List")){
            c = CommandType.LIST;
        } else {
            c = CommandType.NIL;
        }

        return c;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String line;

        Todo[] tasks = new Todo[100];
        int itemCount = 0;

        CommandType command;

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        System.out.println("Hello from\n" + logo);

        greeting();
        line = input.nextLine();

        while(!line.equalsIgnoreCase("bye")) {
            command = findCommandType(line);

            switch (command) {
            case TODO:
                tasks[itemCount] = new Todo(line.substring(4));
                echo("Added " + tasks[itemCount].toString() + " to the list");
                itemCount++;
                break;
            case DEADLINE:
                String by = line.substring(line.indexOf("/by") + 4);
                String deadline = line.substring(8, line.indexOf("/by"));
                tasks[itemCount] = new Deadline(deadline, by);
                echo("Added " + tasks[itemCount].toString() + " to the list");
                itemCount++;
                break;
            case EVENT:
                String at = line.substring(line.indexOf("/at") + 4);
                String event = line.substring(5, line.indexOf("/at"));
                tasks[itemCount] = new Event(event, at);
                echo("Added " + tasks[itemCount].toString() + " to the list");
                itemCount++;
                break;
            case MARK:
                int markedItem = Integer.parseInt(line.substring(5)) - 1;
                tasks[markedItem].mark();
                printMark(tasks, markedItem);
                break;
            case UNMARK:
                int unmarkedItem = Integer.parseInt(line.substring(7)) - 1;
                tasks[unmarkedItem].unmark();
                printUnmark(tasks, unmarkedItem);
                break;
            case LIST:
                printList(tasks, itemCount);
                break;
            case NIL:
                echo(line);
                break;
            default:
                break;
            }

            line = input.nextLine();
        }

        exitLine();
    }
}
