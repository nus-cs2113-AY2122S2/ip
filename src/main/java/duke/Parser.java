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
    private static final String FIND_COMMAND = "find";
    private static final String BYE_COMMAND = "bye";

    public static void printLine() {
        System.out.println("-----------------------------------------");
    }

    /**
     * Check whether the todo type task detail is valid.
     * Add todo type task to the taskList.
     *
     * @param taskList the list to store all the tasks.
     * @param taskDetail the description of the task.
     */
    private static void addTodo(String taskDetail, TaskList taskList) {
        printLine();
        try {
            checkTaskDetailEmpty(taskDetail);
            Task newTask = new Todo(taskDetail);
            taskList.addTask(newTask);
        } catch (DukeException e) {
            System.out.println(e);
            Ui.printGuide();
        }
    }

    /**
     * Check whether the deadline type task detail is valid.
     * Add deadline type task to the taskList.
     *
     * @param taskList the list to store all the tasks.
     * @param taskDetail the description of the task.
     */
    private static void addDeadline(String taskDetail, TaskList taskList) {
        printLine();
        try {
            checkTaskDetailEmpty(taskDetail);
            int separatorIndex = taskDetail.indexOf("/by");
            checkKeyword(separatorIndex, taskDetail);
            Task newTask = new Deadline(taskDetail.substring(0, separatorIndex - 1), taskDetail.substring(separatorIndex + 4));
            taskList.addTask(newTask);
        } catch (DukeException e) {
            System.out.println(e);
            Ui.printGuide();
        }
    }

    /**
     * Check whether the event type task detail is valid.
     * Add event type task to the taskList.
     *
     * @param taskList the list to store all the tasks.
     * @param taskDetail the description of the task.
     */
    private static void addEvent(String taskDetail, TaskList taskList) {
        printLine();
        try {
            checkTaskDetailEmpty(taskDetail);
            int separatorIndex = taskDetail.indexOf("/at");
            checkKeyword(separatorIndex, taskDetail);
            Task newTask = new Event(taskDetail.substring(0, separatorIndex - 1), taskDetail.substring(separatorIndex + 4));
            taskList.addTask(newTask);
        } catch (DukeException e) {
            System.out.println(e);
            Ui.printGuide();
        }
    }

    /**
     * Check whether the task detail for deadline type and event type has
     * keyword for date.
     * Check whether the date exist.
     *
     * @param indexOfKeyword the index of /by or /at.
     * @param taskDetail the description of the task.
     * @throws DukeException If task detail is not valid.
     */
    private static void checkKeyword(int indexOfKeyword, String taskDetail) throws DukeException {
        if (indexOfKeyword == -1) {
            throw new DukeException("You should give a time. Please refer to the command guide below.");
        }
        if (taskDetail.length() < indexOfKeyword + 5) {
            throw new DukeException("You should give a time. Please refer to the command guide below.");
        }
    }

    /**
     * Check whether the command detail is empty
     *
     * @param taskDetail the description of the task.
     * @throws DukeException If there is not command detail.
     */
    public static void checkTaskDetailEmpty(String taskDetail) throws DukeException {
        if (taskDetail.isEmpty()) {
            throw new DukeException("Command detail cannot be empty.");
        }
    }

    /**
     * Check whether the input index can convert from string into integer.
     * Check whether the input index is in the range of total task number.
     * @param description the description of the task.
     * @param taskList the list to store all the tasks.
     * @return whether the index is valid.
     */
    public static boolean formatIndex(String description, TaskList taskList) {
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

    /**
     * Check whether the input index is in the range of total task number.
     * @param index the input index.
     * @param taskList the list to store all the tasks.
     * @throws DukeException If index is smaller than 1 or greater than total number of tasks.
     */
    public static void checkIndexRange(int index, TaskList taskList) throws DukeException {
        if (index > taskList.getSize() || index < 1) {
            throw new DukeException("This index is invalid");
        }
    }

    /**
     * Check whether the command detail is empty.
     * Find the task which contains keyword in the taskList
     * @param taskDetail the keyword to search.
     * @param taskList the list to store all the tasks.
     */
    public static void findKeywords(String taskDetail, TaskList taskList) {
        printLine();
        try {
            checkTaskDetailEmpty(taskDetail);
            taskList.findTask(taskDetail);
        } catch (DukeException e) {
            System.out.println(e);
            Ui.printGuide();
        }

    }

    /**
     * Ask user to type in commands.
     * Check which command is going to handle.
     * Extract the command/task details according to the command.
     *
     * @param taskList the list to store all the tasks.
     * @return whether the index is valid.
     */
    public static void handleCommand(TaskList taskList) {
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
                taskDetail = textIn.substring(4).trim();
                addTodo(taskDetail, taskList);
            } else if (textIn.startsWith(DEADLINE_COMMAND)) {
                taskDetail = textIn.substring(8).trim();
                addDeadline(taskDetail, taskList);
            } else if (textIn.startsWith(EVENT_COMMAND)) {
                taskDetail = textIn.substring(5).trim();
                addEvent(taskDetail, taskList);
            } else if (textIn.startsWith(DELETE_COMMAND)) {
                if (formatIndex(textIn.substring(6).trim(), taskList)) {
                    taskList.deleteTask(Integer.parseInt(textIn.substring(6).trim()));
                }
            } else if (textIn.startsWith(FIND_COMMAND)) {
                taskDetail = textIn.substring(4).trim();
                findKeywords(taskDetail, taskList);
            } else {
                printLine();
                System.out.println(" ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                System.out.println(" Please refer to the command guide below.\n");
                Ui.printGuide();
            }
            Storage.writeToFile(taskList);
            in = new Scanner(System.in);
            textIn = in.nextLine();

        }
        Ui.bye();
    }
}
