package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.util.Scanner;

public class Duke {
    private static final String MARK_COMMAND = "mark";
    private static final String UNMARK_COMMAND = "unmark";
    private static final String LIST_COMMAND = "list";
    private static final String TODO_COMMAND = "todo";
    private static final String DEADLINE_COMMAND = "deadline";
    private static final String EVENT_COMMAND = "event";
    private static final String BYE_COMMAND = "bye";

    private static TaskList taskList = new TaskList();


    public static void printLine() {
        System.out.println("\t" + "-----------------------------------------");
    }

    public static void greeting() {
        String logo = "\t" + "  ____        _        \n"
                + "\t" + " |  _ \\ _   _| | _____ \n"
                + "\t" + " | | | | | | | |/ / _ \\\n"
                + "\t" + " | |_| | |_| |   <  __/\n"
                + "\t" + " |____/ \\__,_|_|\\_\\___|\n";
        System.out.println("\t" + " Hello from\n" + logo);
        printLine();

        System.out.println("\t" + " Hi! This is Duke!");
        System.out.println("\t" + " I'm glad to be at your service.");
        System.out.println("\t" + " What can I help you with?");
        printLine();
    }

    public static void bye() {
        System.out.println("\t" + " Bye. Hope to see you again soon!");
        printLine();
    }

    public static void AbleToAddTask(Task newtask, TaskList taskList) {
        System.out.println("\t" + " Got it. I've added this task:");
        System.out.println("\t" + "\t" + newtask);
        System.out.println("\t" + " Now you have " + taskList.getTaskCount() + " tasks in the list.");
        printLine();
    }

    public static void addTodo(String taskDetail) throws DukeException {
        printLine();
        try {
            checkTaskDetailEmpty(taskDetail, TODO_COMMAND);
            Task newTask = new Todo(taskDetail);
            taskList.addTask(newTask);
            AbleToAddTask(newTask, taskList);
        } catch (DukeException e) {
            System.out.println(e);
            guide();
        }
    }

    public static void addDeadline(String taskDetail) throws DukeException {
        printLine();
        try {
            checkTaskDetailEmpty(taskDetail, DEADLINE_COMMAND);
            int separator = taskDetail.indexOf("by");
            checkKeyword(separator);
            Task newTask = new Deadline(taskDetail.substring(0, separator - 1), taskDetail.substring(separator + 3));
            taskList.addTask(newTask);
            AbleToAddTask(newTask, taskList);
        } catch (DukeException e) {
            System.out.println(e);
            guide();
        }
    }

    public static void addEvent(String taskDetail) throws DukeException {
        printLine();
        try {
            checkTaskDetailEmpty(taskDetail, EVENT_COMMAND);
            int separator = taskDetail.indexOf("at");
            checkKeyword(separator);
            Task newTask = new Event(taskDetail.substring(0, separator - 1), taskDetail.substring(separator + 3));
            taskList.addTask(newTask);
            AbleToAddTask(newTask, taskList);
        } catch (DukeException e) {
            System.out.println(e);
            guide();
        }
    }

    public static void checkKeyword(int indexOfKeyword) throws DukeException {
        if (indexOfKeyword == -1) {
            throw new DukeException("You should give a time. Please refer to the command guide below.");
        }
    }

    public static void checkTaskDetailEmpty(String taskDetail, String command) throws DukeException {
        if (taskDetail.isEmpty()) {
            throw new DukeException("☹ OOPS!!! The description of a " + command + " cannot be empty.");
        }
    }

    public static void checkCommand() throws DukeException {

    }

    public static void guide() {
        System.out.println("\t" + " use \"list\" to show the task list");
        System.out.println("\t" + " use \"todo task\" to add a task without any date/time attached to it\"");
        System.out.println("\t" + " use \"deadline task by time\" to add a task that need to be done before a specific time/date");
        System.out.println("\t" + " use \"event task at time\" to add a task that tasks that start at a specific time and ends at a specific time");
        System.out.println("\t" + " use \"mark taskIndex\" to mark that task as done");
        System.out.println("\t" + " use \"unmark taskIndex\" to mark that task as not done");
        System.out.println("\t" + " use \"bye\" to exit the chatbot");
        printLine();
    }

    public static void main(String[] args) throws DukeException {

        greeting();

        Scanner in = new Scanner(System.in);
        String textIn = in.nextLine();
        String taskDetail;
        String command;
        while (!textIn.toLowerCase().equals(BYE_COMMAND)) {
            if (textIn.equals(LIST_COMMAND)) {
                taskList.printTaskList();
            } else if (textIn.startsWith(MARK_COMMAND)) {
                taskList.markDone(Integer.parseInt(textIn.substring(4).trim()));
            } else if (textIn.startsWith(UNMARK_COMMAND)) {
                taskList.unmark(Integer.parseInt(textIn.substring(6).trim()));
            } else if (textIn.startsWith(TODO_COMMAND)) {
                taskDetail = textIn.substring(4);
                addTodo(taskDetail);
            } else if (textIn.startsWith(DEADLINE_COMMAND)) {
                taskDetail = textIn.substring(8);
                addDeadline(taskDetail);
            } else if (textIn.startsWith(EVENT_COMMAND)) {
                taskDetail = textIn.substring(5);
                addEvent(taskDetail);
            } else {
                printLine();
                System.out.println("\t ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                System.out.println("\t Please refer to the command guide below.\n");
                guide();
            }
            in = new Scanner(System.in);
            textIn = in.nextLine();
        }

        bye();
    }
}
