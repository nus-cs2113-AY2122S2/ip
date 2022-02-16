import java.util.ArrayList;
import java.util.Scanner;
import util.exception.NoDateException;
import util.exception.NoTaskException;
import util.exception.NoItemException;
import util.miscellaneous.Chatbot;
import util.miscellaneous.CommandType;
import util.task.Task;
import util.task.Todo;
import util.task.Deadline;
import util.task.Event;

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

    public static void printList(ArrayList<Task> tasks) {
        linePrinter();

        for (int i = 0; i < tasks.size(); i++) {
            System.out.println("\t" + " " + Integer.toString(i + 1) + " " + (tasks.get(i)).toString());
        }

        linePrinter();
    }

    public static void deleteItem(ArrayList<Task> tasks, int index) {
        linePrinter();
        System.out.println("\t" + DELETE_MSG);
        System.out.println("\t" + "   " + (tasks.get(index)).toString());
        linePrinter();

        tasks.remove(index);
    }

    public static void printMark(ArrayList<Task> tasks, int markedItem) {
        linePrinter();
        System.out.println("\t" + MARKED_MSG);
        System.out.println("\t" + "   " + (tasks.get(markedItem)).toString());
        linePrinter();

    }

    public static void printUnmark(ArrayList<Task> tasks, int unmarkedItem) {
        linePrinter();
        System.out.println("\t" + UNMARKED_MSG);
        System.out.println("\t" + "   " + (tasks.get(unmarkedItem)).toString());
        linePrinter();

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
        } else if (line.startsWith(DELETE_CMD)){
            c = CommandType.DEL;
        } else {
            c = CommandType.NIL;
        }

        return c;
    }

    public static void checkCommand(ArrayList<Task> tasks,String line, CommandType c) throws NoTaskException, NoDateException, NoItemException {
        switch (c) {
        case TODO:
            String todo = line.substring(TODO_TASK_INDEX);

            if ((todo.trim()).isEmpty()) {
                throw new NoTaskException();
            }

            break;
        case DEADLINE:
            String by = line.substring(line.indexOf(DEADLINE_OF_TASK_CMD) + TIME_INDEX);
            String deadline = line.substring(DEADLINE_TASK_INDEX, line.indexOf(DEADLINE_OF_TASK_CMD));

            if ((deadline.trim()).isEmpty()) {
                throw new NoTaskException();
            }

            if ((by.trim()).isEmpty()) {
                throw new NoDateException();
            }
            break;
        case EVENT:
            String at = line.substring(line.indexOf(DURATION_OF_EVENT_CMD) + TIME_INDEX);
            String event = line.substring(EVENT_TASK_INDEX, line.indexOf(DURATION_OF_EVENT_CMD));

            if ((event.trim()).isEmpty()) {
                throw new NoTaskException();
            }

            if ((at.trim()).isEmpty()) {
                throw new NoDateException();
            }

            break;
        case MARK:
            int markedItem = Integer.parseInt(line.substring(MARKED_ITEM_INDEX)) - 1;

            if ((markedItem < 0) || (markedItem >= tasks.size())) {
                throw new NoItemException();
            }

            break;
        case UNMARK:
            int unmarkedItem = Integer.parseInt(line.substring(UNMARKED_ITEM_INDEX)) - 1;

            if ((unmarkedItem < 0) || (unmarkedItem >= tasks.size())) {
                throw new NoItemException();
            }

            break;
        case DEL:
            int index = Integer.parseInt(line.substring(DELETE_INDEX)) - 1;

            if ((index < 0) || (index >= tasks.size())) {
                throw new NoItemException();
            }

            break;
        default:
            break;
        }
    }

    public static int handleError(ArrayList<Task> tasks,String line, CommandType c) {
        try {
            checkCommand(tasks, line, c);
        } catch (IndexOutOfBoundsException e01) {
            echo(ITEM_NOT_EXIST_MSG);
            return 1;
        } catch (NoDateException e02) {
            echo(NO_DATE_MSG);
            return 1;
        } catch (NoTaskException e03) {
            echo(NO_TASK_MSG);
            return 1;
        } catch (NoItemException e04) {
            echo(ITEM_NOT_EXIST_MSG);
            return 1;
        } finally {
            return 0;
        }
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String line;

        ArrayList<Task> tasks = new ArrayList<>();

        CommandType command;

        System.out.println("Hello from\n" + LOGO);

        printGreeting();
        line = input.nextLine();

        while(!line.equalsIgnoreCase("bye")) {
            command = findCommandType(line);

            int i = handleError(tasks, line, command);
            if (i == ERROR_INDICATION_NUMBER) {
                continue;
            }

            switch (command) {
            case TODO:
                tasks.add(new Todo(line.substring(TODO_TASK_INDEX))) ;
                echo("Added " + tasks.get(tasks.size() - 1) + " to the list");
                break;
            case DEADLINE:
                String by = line.substring(line.indexOf(DEADLINE_OF_TASK_CMD) + TIME_INDEX);
                String deadline = line.substring(DEADLINE_TASK_INDEX, line.indexOf(DEADLINE_OF_TASK_CMD));
                tasks.add(new Deadline(deadline, by));
                echo("Added " + tasks.get(tasks.size() - 1) + " to the list");
                break;
            case EVENT:
                String at = line.substring(line.indexOf(DURATION_OF_EVENT_CMD) + TIME_INDEX);
                String event = line.substring(EVENT_TASK_INDEX, line.indexOf(DURATION_OF_EVENT_CMD));
                tasks.add(new Deadline(event, at));
                echo("Added " + tasks.get(tasks.size() - 1) + " to the list");
                break;
            case MARK:
                int markedItem = Integer.parseInt(line.substring(MARKED_ITEM_INDEX)) - 1;
                (tasks.get(markedItem)).mark();
                printMark(tasks, markedItem);
                break;
            case UNMARK:
                int unmarkedItem = Integer.parseInt(line.substring(UNMARKED_ITEM_INDEX)) - 1;
                (tasks.get(unmarkedItem)).unmark();
                printUnmark(tasks, unmarkedItem);
                break;
            case LIST:
                printList(tasks);
                break;
            case DEL:
                int index = Integer.parseInt(line.substring(DELETE_INDEX)) - 1;
                deleteItem(tasks, index);
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