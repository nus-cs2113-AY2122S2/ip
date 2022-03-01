package duke;


import duke.duke_exception.DukeException;
import duke.task.Task;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * Parses user input and handles them.
 * */
public class Parser {
    /**
     * String constants of main command
     * */
    private static final String MARK_CMD = "mark";
    private static final String UNMARK_CMD = "unmark";
    private static final String LIST_CMD = "list";
    private static final String ADD_CMD = "add";
    private static final String DELETE_CMD = "delete";
    private static final String FIND_CMD = "find";
    private static final String HELP_CMD = "help";
    private Scanner in;
    private TaskList tasks;
    public Parser (TaskList tasks) throws DukeException {
        this.tasks = tasks;
        this.in = new Scanner(System.in);
    }

    /**
     * Begins user input loop, returns when "bye" command issued
     *
     * @throws DukeException
     * */
    public void enterInputLoop() throws DukeException {
        while (true) {
            String input = this.in.nextLine();
            String[] cmd = input.split(" ");
            String main_cmd = cmd[0];
            System.out.println(main_cmd);
            if (cmd.length == 0) {
                Ui.printEmptyInput();
                continue;
            }
            if (main_cmd.equals("bye")) {
                break;
            } else if (main_cmd.equals(LIST_CMD)) {
                Ui.printTasks(tasks.getTaskList());
            } else if (main_cmd.equals(MARK_CMD)) {
                updateMark(MARK_CMD, cmd);
            } else if (main_cmd.equals(UNMARK_CMD)) {
                updateMark(UNMARK_CMD, cmd);
            } else if (main_cmd.equals(ADD_CMD)) {
                this.handleAdd(input, cmd);
            } else if (main_cmd.equals(DELETE_CMD)) {
                this.handleDelete(input, cmd);
            } else if (main_cmd.equals(FIND_CMD)) {
                this.handleFind(input, cmd);
            } else if (main_cmd.equals(HELP_CMD)){
                Ui.printHelp();
            } else {
                Ui.printInvalidCommand();
            }
        }
    }


    /**
     * Updates the mark status of a task is tasklist by index
     *
     * @param isMark the mark status of task
     * @param cmd Command parameters, may include sub commands
     * */
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
    /**
     * Handles the main add command
     * @param input Full string user input
     * @param cmd list of cmdlline pararms and comamands
     * */
    private void handleAdd(String input, String[] cmd) throws DukeException {
        System.out.println("handleAdd");
        System.out.println(cmd);
        if (cmd.length < 2) {
//            throw new DukeException("☹ OOPS!!! I'm sorry, but delete requires an index");
            System.out.println("☹ OOPS!!! I'm sorry, but add requires more params");
            return;
        }
        String sub_cmd = cmd[1];
        System.out.println(sub_cmd.length());
        Task newTask;
        Integer removeSubstrlength = cmd[0].length() + sub_cmd.length() + 2;
        if (input.length() <= removeSubstrlength){
            Ui.printParamMissing("handleAdd");
            return;
        }
        String params = input.substring(removeSubstrlength);
        if (sub_cmd.equals("todo")) {
            newTask = tasks.addTodo(params);
        } else if (sub_cmd.equals("deadline")) {
            newTask = tasks.addDeadline(params);
        } else if (sub_cmd.equals("event")) {
            newTask = tasks.addEvent(params);
        } else {
            System.out.println("☹ OOPS!!! I'm sorry, but sub command has to be either todo, deadline or event");
            return;
        }
        if (newTask != null){
            Ui.printTaskAdded(tasks,newTask);
        }

    }

    private void handleDelete(String input, String[] cmd) throws DukeException {
        if (cmd.length < 2) {
//            throw new DukeException("☹ OOPS!!! I'm sorry, but delete requires an index");
            System.out.println("☹ OOPS!!! I'm sorry, but delete requires an index");
            return;
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

    private void handleFind(String input, String[] cmd) throws DukeException {
        if (cmd.length < 2) {
            System.out.println("☹ OOPS!!! I'm sorry, but delete require something to search for");
            return;
        }
        String[] params = Arrays.copyOfRange(cmd, 1, cmd.length);

        String query =  Arrays.stream(params).collect(Collectors.joining(" "));
        List<Task> results = tasks.findTasks(query);
        Ui.printTasks(results);
    }
}
