import java.util.Scanner;

public class Duke {
    public static final String HORIZONTAL_LINE = "───────────────────────────────────────────────────────────────────────";
    public static final String GREETING_MSG_01 = " I have been waiting for you, Mister Winters.";
    public static final String GREETING_MSG_02 = " Forgive my manners, call me the Duke. Now to business.";
    public static final String ITEM_NOT_EXIST_MSG = "Ah...The task you choose doesn't exist on your list.";
    public  static final String MARKED_MSG = " Aha! An interesting selection!";
    public  static final String UNMARKED_MSG = " Ah... What a shame!";
    public static final String GOODBYE_MSG = " Good day, then!";

    public static final String ADD_TODO_CMD = "Todo ";
    public static final String ADD_DEADLINE_CMD = "Deadline ";
    public static final String ADD_EVENT_CMD = "Event ";
    public static final String MARK_TASK_CMD = "Mark ";
    public static final String UNMARK_TASK_CMD = "Unmark ";
    public static final String LIST_TASKS_CMD = "List ";
    public static final String DEADLINE_OF_TASK_CMD = "/by";
    public static final String DURATION_OF_EVENT_CMD = "/at";

    public static final int MAX_NUM_OF_TASKS = 100;
    public static final int TODO_TASK_INDEX = 4;
    public static final int DEADLINE_TASK_INDEX = 8;
    public static final int EVENT_TASK_INDEX = 5;
    public static final int TIME_INDEX = 4;
    public static final int MARKED_ITEM_INDEX = 5;
    public static final int UNMARKED_ITEM_INDEX = 7;

    public static void linePrinter() {
        System.out.print("\t");
        System.out.println(HORIZONTAL_LINE);
    }

    public static void printGreeting() {
        linePrinter();
        System.out.println("\t" + GREETING_MSG_01);
        System.out.println("\t" + GREETING_MSG_02);
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
            echo(ITEM_NOT_EXIST_MSG);
        } else {
            linePrinter();
            System.out.println("\t" + MARKED_MSG);
            System.out.println("\t" + "   " + tasks[markedItem].toString());
            linePrinter();
        }
    }

    public static void printUnmark(Todo[] tasks, int unmarkedItem) {
        if (tasks[unmarkedItem] == null) {
            echo(ITEM_NOT_EXIST_MSG);
        } else {
            linePrinter();
            System.out.println("\t" + UNMARKED_MSG);
            System.out.println("\t" + "   " + tasks[unmarkedItem].toString());
            linePrinter();
        }
    }

    public static void exitLine() {
        linePrinter();
        System.out.println("\t" + GOODBYE_MSG);
        linePrinter();
    }

    public static void echo (String line) {
        linePrinter();
        System.out.println("\t" + " " + line);
        linePrinter();
    }

    public static CommandType findCommandType(String line) {
        CommandType c;

        if (line.startsWith(ADD_TODO_CMD)) {
            c = CommandType.TODO;
        } else if (line.startsWith(ADD_DEADLINE_CMD)) {
            c = CommandType.DEADLINE;
        } else if (line.startsWith(ADD_EVENT_CMD)) {
            c = CommandType.EVENT;
        } else if (line.startsWith(MARK_TASK_CMD)) {
            c= CommandType.MARK;
        } else if (line.startsWith(UNMARK_TASK_CMD)){
            c = CommandType.UNMARK;
        } else if (line.equals(LIST_TASKS_CMD)){
            c = CommandType.LIST;
        } else {
            c = CommandType.NIL;
        }

        return c;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String line;

        Todo[] tasks = new Todo[MAX_NUM_OF_TASKS];
        int itemCount = 0;

        CommandType command;

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        System.out.println("Hello from\n" + logo);

        printGreeting();
        line = input.nextLine();

        while(!line.equalsIgnoreCase("bye")) {
            command = findCommandType(line);

            switch (command) {
            case TODO:
                tasks[itemCount] = new Todo(line.substring(TODO_TASK_INDEX));
                echo("Added " + tasks[itemCount].toString() + " to the list");
                itemCount++;
                break;
            case DEADLINE:
                String by = line.substring(line.indexOf(DEADLINE_OF_TASK_CMD) + TIME_INDEX);
                String deadline = line.substring(DEADLINE_TASK_INDEX, line.indexOf(DEADLINE_OF_TASK_CMD));
                tasks[itemCount] = new Deadline(deadline, by);
                echo("Added " + tasks[itemCount].toString() + " to the list");
                itemCount++;
                break;
            case EVENT:
                String at = line.substring(line.indexOf(DURATION_OF_EVENT_CMD) + TIME_INDEX);
                String event = line.substring(EVENT_TASK_INDEX, line.indexOf(DURATION_OF_EVENT_CMD));
                tasks[itemCount] = new Event(event, at);
                echo("Added " + tasks[itemCount].toString() + " to the list");
                itemCount++;
                break;
            case MARK:
                int markedItem = Integer.parseInt(line.substring(MARKED_ITEM_INDEX)) - 1;
                tasks[markedItem].mark();
                printMark(tasks, markedItem);
                break;
            case UNMARK:
                int unmarkedItem = Integer.parseInt(line.substring(UNMARKED_ITEM_INDEX)) - 1;
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
