import java.util.Scanner;
import duke.task.*;
import duke.exception.*;
import java.util.ArrayList;

public class Duke {

    private static Scanner sc = new Scanner(System.in);
    private static ArrayList<Task> list = new ArrayList<Task>();
    private static int taskCounter = 0;
    private static String HORIZONTAL_LINE = "____________________________________________________________";

    private static void printFormat(String... args) {
        System.out.println(HORIZONTAL_LINE);
        for (String arg : args) {
            System.out.println(arg);
        }
        System.out.println(HORIZONTAL_LINE);
    }

    private static void intro() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        printFormat("Hello! I'm Duke",
                "What can I do for you?",
                "type 'commands' for the list of commands");
    }

    private static void handleInput(String input) 
        throws SingleWordCommandException, IndexOutOfBoundsException, NoSlashException, AlreadyMarkedException,
        AlreadyUnmarkedException, InvalidNumberException {
        if (input.contains("list")) {
            if (input.equals("list")) {
                handleList();
            } else {
                throw new SingleWordCommandException();
            }

        } else if (input.contains("unmark")) {
            handleUnmark(input);

        } else if (input.contains("mark")) {
            handleMark(input);

        } else if (input.contains("todo")) {
            handleToDo(input);

        } else if (input.contains("deadline")) {
            if (input.contains("/")) {
                handleDeadline(input);
            } else {
                throw new NoSlashException();
            }

        } else if (input.contains("event")) {
            if (input.contains("/")) {
                handleEvent(input);
            } else {
                throw new NoSlashException();
            }

        } else if (input.contains("commands")) {
            if (input.equals("commands")) {
                handleCommand();
            } else {
                throw new SingleWordCommandException();
            } 
            
        } else {
            printFormat("OOPS!!! I'm sorry, but I don't know what that means :-(",
                    "Please type in 'commands' if you are not sure of the commands");

        }
    }

    private static void handleList() {
        System.out.println(HORIZONTAL_LINE);
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < list.size(); i++) {
            System.out.println(String.valueOf(i + 1) + "." + list.get(i).toString());
        }
        System.out.println(HORIZONTAL_LINE);
    }

    private static void handleUnmark(String input) throws AlreadyUnmarkedException, InvalidNumberException {
        int markInt = Integer.parseInt(input.substring(7)) - 1;
        if (markInt + 1 > taskCounter) {
            throw new InvalidNumberException();
        } else if (list.get(markInt).isDone()) {
            list.get(markInt).setDone(false);
            printFormat("OK, I've marked this task as not done yet:",
                list.get(markInt).toString());
        } else {
            throw new AlreadyUnmarkedException();
        }
    }

    private static void handleMark(String input) throws AlreadyMarkedException, InvalidNumberException {
        int markInt = Integer.parseInt(input.substring(5)) - 1;
        if (markInt + 1> taskCounter) {
            throw new InvalidNumberException();
        } else if (list.get(markInt).isDone()) {
            throw new AlreadyMarkedException();
        } else {
            list.get(markInt).setDone(true);
            printFormat("Nice! I've marked this task as done:",
                    list.get(markInt).toString());
        }
    }

    private static void handleToDo(String input) {
        String[] subStrings = input.split(" ", 2);
        Task newTask = new ToDo(subStrings[1]);
        list.add(newTask);
        taskCounter++;
        printFormat("Got it. I've added this task:", 
                "  " + newTask.toString(),
                "Now you have " + String.valueOf(taskCounter) + " tasks in the list.");
    }

    private static void handleDeadline(String input) {
        String[] subStrings = input.split(" ", 2);
        subStrings = subStrings[1].split("/", 2);
        String inputString = subStrings[0];
        subStrings = subStrings[1].split(" ", 2);
        String inputPreposition = subStrings[0];
        String inputDate = subStrings[1];
        Task newTask = new Deadline(inputString, inputDate);
        list.add(newTask);
        taskCounter++;
        printFormat("Got it. I've added this task:", 
                "  " + newTask.toString(),
                "Now you have " + String.valueOf(taskCounter) + " tasks in the list.");
    }

    private static void handleEvent(String input) {
        String[] subStrings = input.split(" ", 2);
        subStrings = subStrings[1].split("/", 2);
        String inputString = subStrings[0];
        subStrings = subStrings[1].split(" ", 2);
        String inputPreposition = subStrings[0];
        String inputDate = subStrings[1];
        Task newTask = new Event(inputString, inputDate);
        list.add(newTask);
        taskCounter++;
        printFormat("Got it. I've added this task:", 
                "  " + newTask.toString(),
                "Now you have " + String.valueOf(taskCounter) + " tasks in the list.");
    }

    private static void handleCommand() {
        printFormat("List of valid commands:",
                "'list' - lists out all tasks and its details",
                "'mark (X)' - marks item X on the list as done",
                "   (X is a number) e.g. 'mark 3' marks item 3 on the list",
                "'unmark (X)' - unmarks item X on the list as done",
                "   (X is a number) e.g. 'unmark 3' unmarks item 3 on the list",
                "'todo (task)' - ToDos are tasks without specific deadlines",
                "   (task is a string) e.g. 'todo buy shampoo' adds the task 'buy shampoo' to the list",
                "'deadline (task) /by (date/time)' - Deadlines are tasks that need to be done before a specific date/time",
                "   e.g. 'deadline math homework /by tues 2pm' adds a task with a deadline to the list",
                "'event (task) /at (date/time)' - Events are tasks that start at a specific time and ends at a specific time",
                "   e.g. 'event project meeting /at sunday 8-10pm' adds a task with a time range");
    }

    private static void bye() {
        printFormat("Bye. Hope to see you again soon!");
    }

    public static void main(String[] args) {
        intro();
        while (sc.hasNext()) {
            String input = sc.nextLine();
            if (input.equals("bye")) {
                break;
            } else {
                try {
                    handleInput(input);
                } catch (SingleWordCommandException e) {
                    printFormat("Oh no! There should not be any words after '" + input.split(" ")[1] + "'");
                } catch (IndexOutOfBoundsException e) {
                    printFormat("Oh no! You have to include the details of the task '" + input + "'");
                } catch (NoSlashException e) {
                    printFormat("Oh no! You need to include '/' in your task '" + input + "'");
                } catch (AlreadyMarkedException e) {
                    printFormat("Oh no! The item is already marked!");
                } catch (AlreadyUnmarkedException e) {
                    printFormat("Oh no! The item is already unmarked!");
                } catch (InvalidNumberException e) {
                    printFormat("Oh no! The number you have chosen is not valid!");
                }
            }
        }
        bye();
    }
}