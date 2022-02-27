package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;
import duke.task.TaskList;


import java.io.IOException;
import java.util.Scanner;


public class Parser {

    private static final String MARK_COMMAND = "mark";
    private static final String UNMARK_COMMAND = "unmark";
    private static final String LIST_COMMAND = "list";
    private static final String TODO_COMMAND = "todo";
    private static final String DEADLINE_COMMAND = "deadline";
    private static final String EVENT_COMMAND = "event";
    private static final String DELETE_COMMAND = "delete";
    private static final String BYE_COMMAND = "bye";

    public static void printLine() {
        System.out.println("-----------------------------------------");
    }

    public static void addTodo(String taskDetail, TaskList taskList) {
        printLine();
        try {
            checkTaskDetailEmpty(taskDetail, TODO_COMMAND);
            Task newTask = new Todo(taskDetail);
            taskList.addTask(newTask);
        } catch (DukeException e) {
            System.out.println(e);
            Ui.printGuide();
        }
    }

    public static void addDeadline(String taskDetail, TaskList taskList) {
        printLine();
        try {
            checkTaskDetailEmpty(taskDetail, DEADLINE_COMMAND);
            int separatorIndex = taskDetail.indexOf("/by");
            checkKeyword(separatorIndex);
            Task newTask = new Deadline(taskDetail.substring(0, separatorIndex - 1), taskDetail.substring(separatorIndex + 4));
            taskList.addTask(newTask);
        } catch (DukeException e) {
            System.out.println(e);
            Ui.printGuide();
        }
    }

    public static void addEvent(String taskDetail, TaskList taskList) {
        printLine();
        try {
            checkTaskDetailEmpty(taskDetail, EVENT_COMMAND);
            int separatorIndex = taskDetail.indexOf("/at");
            checkKeyword(separatorIndex);
            Task newTask = new Event(taskDetail.substring(0, separatorIndex - 1), taskDetail.substring(separatorIndex + 4));
            taskList.addTask(newTask);
        } catch (DukeException e) {
            System.out.println(e);
            Ui.printGuide();
        }
    }

    public static void checkKeyword(int indexOfKeyword) throws DukeException {
        if (indexOfKeyword == -1) {
            throw new DukeException("You should give a time. Please refer to the command guide below.");
        }
    }

    public static void checkTaskDetailEmpty(String taskDetail, String command) throws DukeException {
        if (taskDetail.isEmpty()) {
            throw new DukeException("The description of a " + command + " cannot be empty.");
        }
    }

    public static boolean formatIndex(String description, TaskList taskList){
        try {
            int taskId = Integer.parseInt(description);
            checkIndexRange(taskId, taskList);
            return true;
        } catch (DukeException e) {
            printLine();
            System.out.println(e);
            Ui.printGuide();
        } catch (NumberFormatException e) {
            printLine();
            System.out.println(" Cannot convert it to a number");
            Ui.printGuide();
        }
        return false;
    }

    public static void checkIndexRange(int index, TaskList taskList) throws DukeException {
        if (index > taskList.getSize() || index < 1) {
            throw new DukeException("This index is invalid");
        }
    }

    public static void handleCommand(TaskList taskList) throws IOException {
        Scanner in = new Scanner(System.in);
        String textIn = in.nextLine();
        String taskDetail;
        String command;

        while (!textIn.toLowerCase().equals(BYE_COMMAND)) {
            if (textIn.trim().equals(LIST_COMMAND)) {
                taskList.printTaskList();
            } else if (textIn.startsWith(MARK_COMMAND)) {
                if (formatIndex(textIn.substring(4).trim(), taskList)) {
                    taskList.markDone(Integer.parseInt(textIn.substring(4).trim()));
                }
            } else if (textIn.startsWith(UNMARK_COMMAND)) {
                if (formatIndex(textIn.substring(6).trim(), taskList)) {
                    taskList.unmark(Integer.parseInt(textIn.substring(6).trim()));
                }
            } else if (textIn.startsWith(TODO_COMMAND)) {
                taskDetail = textIn.substring(4);
                addTodo(taskDetail, taskList);
            } else if (textIn.startsWith(DEADLINE_COMMAND)) {
                taskDetail = textIn.substring(8);
                addDeadline(taskDetail, taskList);
            } else if (textIn.startsWith(EVENT_COMMAND)) {
                taskDetail = textIn.substring(5);
                addEvent(taskDetail, taskList);
            } else if (textIn.startsWith(DELETE_COMMAND)) {
                if (formatIndex(textIn.substring(6).trim(), taskList)) {
                    taskList.deleteTask(Integer.parseInt(textIn.substring(6).trim()));
                }
            } else {
                printLine();
                System.out.println(" ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                System.out.println(" Please refer to the command guide below.\n");
                Ui.printGuide();
            }
            in = new Scanner(System.in);
            textIn = in.nextLine();

        }
        Ui.bye();
        Storage.writeToFile(taskList);

    }
}
