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

        linePrinter();
        System.out.println("\t" + MARKED_MSG);
        System.out.println("\t" + "   " + tasks[markedItem].toString());
        linePrinter();

    }

    public static void printUnmark(Task[] tasks, int unmarkedItem) {
        linePrinter();
        System.out.println("\t" + UNMARKED_MSG);
        System.out.println("\t" + "   " + tasks[unmarkedItem].toString());
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

    public static void checkCommand(Task[] tasks,String line, CommandType c) throws NoTaskException, NoDateException, NoItemException {
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

            tasks[itemCount] = new Deadline(deadline, by);
        } catch (NoTaskException e) {
            echo(NO_TASK_MSG);
        } catch (NoDateException e) {
            echo(NO_DATE_MSG);
        }
        finally {
            echo("Added " + tasks[itemCount].toString() + " to the list");
            itemCount++;


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
            String ans4Mark = tasks[markedItem].toString();

            if (tasks[markedItem] == null) {
                throw new NoItemException();
            }

            break;
        case UNMARK:
            int unmarkedItem = Integer.parseInt(line.substring(UNMARKED_ITEM_INDEX)) - 1;
            String ans4Unmark= tasks[unmarkedItem].toString();

            if (tasks[unmarkedItem] == null) {
                throw new NoItemException();
            }

            break;
        default:
            break;
>>>>>>> c5fdf5189d4016e146950f95b3f449509fc41348
        }
    }

    public static int handleError(Task[] tasks,String line, CommandType c) {
        try {
<<<<<<< HEAD
            String at = line.substring(line.indexOf(DURATION_OF_EVENT_CMD) + TIME_INDEX);
            String event = line.substring(EVENT_TASK_INDEX, line.indexOf(DURATION_OF_EVENT_CMD));
            tasks[itemCount] = new Event(event, at);
        } catch (NoTaskException e) {
            echo(NO_TASK_MSG);
        } catch (NoDateException e) {

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

        Task[] tasks = new Todo[MAX_NUM_OF_TASKS];
        int itemCount = 0;

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
