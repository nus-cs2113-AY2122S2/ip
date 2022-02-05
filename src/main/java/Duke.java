import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Duke {
    private static final String MARK_CMD = "mark";
    private static final String UNMARK_CMD = "unmark";
    private static final String LIST_CMD = "list";

    private static List<Task> toDoList = new ArrayList<Task>();

    private static void listToDo(List<Task> tdList) {
        System.out.println("Here are the tasks in your list:");
        int idx = 1;
        for (Task todo : tdList) {
            System.out.println(String.format("%d. %s", idx, todo.getStatus()));
            idx += 1;
        }
    }

    public static void printHello() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello, I'm \n" + logo);
        System.out.println("What can I do for you?\n");
    }

    public static void printBye() {
        System.out.println("Bye. Hope to see you again soon!\n");
    }

    public static void updateMark(String isMark, String[] cmd) {
        int idx = Integer.parseInt(cmd[1]) - 1;
        if (isMark.equals(MARK_CMD)) {
            System.out.println("Nice! I've marked this task as done: ");
            toDoList.get(idx).markDone();
        } else {
            System.out.println("OK, I've marked this task as not done yet: ");
            toDoList.get(idx).unmarkDone();
        }
        System.out.println(toDoList.get(idx).getStatus());

    }

    public static Task addTodo(String params) throws DukeException {
        if (params.length() == 0) {
            throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
        }
        Task newTask = new ToDo(params);
        return newTask;
    }

    public static Task addDeadline(String params) throws DukeException {
        if (params.length() == 0) {
            throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
        }
        String[] params2 = params.split("/");
        if (params2.length != 2) {
            throw new DukeException("☹ OOPS!!! Deadlines should have two parts");
        }
        return new Deadline(params2[0], params2[1]);
    }

    public static Task addEvent(String params) throws DukeException {
        if (params.length() == 0) {
            throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
        }
        String[] params2 = params.split("/");
        if (params2.length != 2) {
            throw new DukeException("☹ OOPS!!! Events should have two parts");
        }
        return new Event(params2[0], params2[1]);
    }

    public static void handleAdd(String input, String main_cmd) throws DukeException {
        System.out.println("Got it. I've added this task: ");
        Task newTask;
        String params = input.substring(main_cmd.length());
        if (main_cmd.equals("todo")) {
            newTask = addTodo(params);
        } else if (main_cmd.equals("deadline")) {
            newTask = addDeadline(params);
        } else if (main_cmd.equals("event")) {
            newTask = addEvent(params);
        } else {
            throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
        toDoList.add(newTask);
        System.out.println(newTask.getStatus());
        System.out.println(String.format("Now you have %d tasks in the list.", toDoList.size()));
    }

    public static void main(String[] args) throws DukeException {
        printHello();
        Scanner in = new Scanner(System.in);
        while (true) {
            String input = in.nextLine();
            String[] cmd = input.split(" ");
            String main_cmd = cmd[0];
            if (cmd.length == 0) {
                System.out.println("Please type something\n");
                continue;
            }
            if (main_cmd.equals("bye")) {
                break;
            } else if (main_cmd.equals("list")) {
                listToDo(toDoList);
            } else if (main_cmd.equals(MARK_CMD)) {
                updateMark(MARK_CMD, cmd);
            } else if (main_cmd.equals(UNMARK_CMD)) {
                updateMark(UNMARK_CMD, cmd);
            } else {
                handleAdd(input, main_cmd);
            }
        }
        printBye();
    }
}
