package duke;


import duke.duke_exception.DukeException;
import duke.task.Task;

import java.util.Scanner;

/**
 * Parses user input and handles them.
 * */
public class Parser {
    private static final String MARK_CMD = "mark";
    private static final String UNMARK_CMD = "unmark";
    private static final String LIST_CMD = "list";
    private static final String ADD_CMD = "add";
    private static final String DELETE_CMD = "delete";
    private static final String HELP_CMD = "help";
    private Scanner in;
    private TaskList tasks;
    public Parser (TaskList tasks) throws DukeException {
        this.tasks = tasks;
        this.in = new Scanner(System.in);
        enterInputLoop();
    }

    private void enterInputLoop() throws DukeException {
        while (true) {
            String input = this.in.nextLine();
            String[] cmd = input.split(" ");
            String main_cmd = cmd[0];
            if (cmd.length == 0) {
                Ui.printEmptyInput();
                continue;
            }
            if (main_cmd.equals("bye")) {
                break;
            } else if (main_cmd.equals(LIST_CMD)) {
                Ui.printTasks(tasks);
            } else if (main_cmd.equals(MARK_CMD)) {
                updateMark(MARK_CMD, cmd);
            } else if (main_cmd.equals(UNMARK_CMD)) {
                updateMark(UNMARK_CMD, cmd);
            } else if (main_cmd.equals(ADD_CMD)) {
                this.handleAdd(input, main_cmd);
            } else if (main_cmd.equals(DELETE_CMD)) {
                this.handleDelete(input, cmd);
            } else if (main_cmd.equals(HELP_CMD)){
                Ui.printHelp();
            } else {
                Ui.printInvalidCommand();
            }
        }
    }



    private void updateMark(String isMark, String[] cmd) {
        int idx;
        String param = cmd[1];
        try {
            idx = Integer.parseInt(param) - 1;
        } catch (NumberFormatException e) {
            Ui.printNaNError(param);
            return;
        }

        try {
            Boolean isDone = isMark.equals(MARK_CMD);
            Task curTask = tasks.getTaskByIdx(idx);
            if (isDone) {
                curTask.markDone();
            } else {
                curTask.unmarkDone();
            }
            Ui.printMarkTaskStatus(isDone,curTask.getStatus());
        } catch (IndexOutOfBoundsException e) {
            Ui.printIndexError(idx, tasks.getTaskListSize());
            return;
        }
    }
    private void handleAdd(String input, String main_cmd) throws DukeException {
        Task newTask;
        String params = input.substring(main_cmd.length());
        if (main_cmd.equals("todo")) {
            newTask = tasks.addTodo(params);
        } else if (main_cmd.equals("deadline")) {
            newTask = tasks.addDeadline(params);
        } else if (main_cmd.equals("event")) {
            newTask = tasks.addEvent(params);
        } else {
            throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
        Ui.printTaskAdded(tasks,newTask);
    }

    private void handleDelete(String input, String[] cmd) throws DukeException {
        if (cmd.length < 2) {
            throw new DukeException("☹ OOPS!!! I'm sorry, but delete requires an index");
        }
        int idx = 0;
        try {
            idx = Integer.parseInt(cmd[1]) - 1;
        } catch (NumberFormatException e) {
            Ui.printNaNError(input);
        }
        Task removed = null;
        try {
            removed = tasks.getTaskByIdx(idx);
            tasks.removeTaskByIdx(idx);
            Ui.printRemovedTask(removed.getStatus(),tasks.getTaskListSize());
        } catch (IndexOutOfBoundsException e) {
            Ui.printIndexError(idx, tasks.getTaskListSize());
        }
    }
}
