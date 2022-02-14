import java.util.Scanner;

public class Duke implements Chatbot {
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

    public static void printList(Task[] tasks, int itemCount) {
        linePrinter();

        for (int i = 0; i < itemCount; i++) {
            System.out.println("\t" + " " + Integer.toString(i + 1) + " " + tasks[i].toString());
        }

        linePrinter();
    }

    public static void printMark(Task[] tasks, int markedItem) {
        try {
            String ans = tasks[markedItem].toString();
        } catch (IndexOutOfBoundsException e) {
            echo(ITEM_NOT_EXIST_MSG);
        } finally {
            if (tasks[markedItem] == null) {
                echo(ITEM_NOT_EXIST_MSG);
            } else {
                linePrinter();
                System.out.println("\t" + MARKED_MSG);
                System.out.println("\t" + "   " + tasks[markedItem].toString());
                linePrinter();
            }
        }
    }

    public static void printUnmark(Task[] tasks, int unmarkedItem) {
        try {
            String ans = tasks[unmarkedItem].toString();
        } catch (IndexOutOfBoundsException e) {
            echo(ITEM_NOT_EXIST_MSG);
        } finally {
            if (tasks[unmarkedItem] == null) {
                echo(ITEM_NOT_EXIST_MSG);
            } else {
                linePrinter();
                System.out.println("\t" + UNMARKED_MSG);
                System.out.println("\t" + "   " + tasks[unmarkedItem].toString());
                linePrinter();
            }
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

    public static void addTodo(Task[] tasks, String line, int itemCount) {
        try {
            tasks[itemCount] = new Todo(line.substring(TODO_TASK_INDEX));
        } catch (NoTaskException e) {
            echo(NO_TASK_MSG);
        } finally {
            echo("Added " + tasks[itemCount].toString() + " to the list");
            itemCount++;
        }
    }

    public static void addDeadline(Task[] tasks, String line, int itemCount) {
        try {
            String by = line.substring(line.indexOf(DEADLINE_OF_TASK_CMD) + TIME_INDEX);
            String deadline = line.substring(DEADLINE_TASK_INDEX, line.indexOf(DEADLINE_OF_TASK_CMD));
            tasks[itemCount] = new Deadline(deadline, by);
        } catch (NoTaskException e) {
            echo(NO_TASK_MSG);
        } catch (NoDateException e) {
            echo(NO_DATE_MSG);
        }
        finally {
            echo("Added " + tasks[itemCount].toString() + " to the list");
            itemCount++;
        }
    }

    public static void addEvent(Task[] tasks, String line, int itemCount) {
        try {
            String at = line.substring(line.indexOf(DURATION_OF_EVENT_CMD) + TIME_INDEX);
            String event = line.substring(EVENT_TASK_INDEX, line.indexOf(DURATION_OF_EVENT_CMD));
            tasks[itemCount] = new Event(event, at);
        } catch (NoTaskException e) {
            echo(NO_TASK_MSG);
        } catch (NoDateException e) {
            echo(NO_DATE_MSG);
        }
        finally {
            echo("Added " + tasks[itemCount].toString() + " to the list");
            itemCount++;
        }
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String line;

        Task[] tasks = new Todo[MAX_NUM_OF_TASKS];
        int itemCount = 0;

        CommandType command;

        System.out.println("Hello from\n" + LOGO);

        printGreeting();
        line = input.nextLine();

        while(!line.equalsIgnoreCase("bye")) {
            command = findCommandType(line);

            switch (command) {
            case TODO:
                addTodo(tasks, line, itemCount);
                break;
            case DEADLINE:
                addDeadline(tasks, line, itemCount);
                break;
            case EVENT:
                addEvent(tasks, line, itemCount);
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
