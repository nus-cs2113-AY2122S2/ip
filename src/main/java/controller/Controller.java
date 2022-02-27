package controller;

import UI.*;
import exceptions.*;
import tasks.TaskManager;

import java.util.Scanner;

public class Controller {
    //greeting msg and exit msg
    protected String recvMsg;
    protected String replyMsg;
    protected static final String MARK_TASK_COMMAND = "mark";
    protected static final String UNMARK_TASK_COMMAND = "unmark";
    protected static final String DELETE_TASK_COMMAND = "delete";
    protected static final String ADD_TODO_TASK_COMMAND = "todo";
    protected static final String ADD_EVENT_TASK_COMMAND = "event";
    protected static final String ADD_DEADLINE_TASK_COMMAND = "deadline";
    protected static final String LIST_TASKS_COMMAND = "list";
    protected static final String EXIT_COMMAND = "bye";
    TaskManager manager = new TaskManager();
    OperationAnalyst analyst;
    UI userInterface = new UI();

    /**
     * Prints greeting msg
     */
    public void greet() {
        userInterface.greet();
    }

    /**
     * Prints goodbye msg and exits
     */
    public void bye() {
        userInterface.goodbye();
        System.exit(0);
    }

    /**
     * Unmarks task in the list
     */
    public String unmarkTask() throws DukeExceptions {
        try {
            int index= Integer.parseInt(analyst.taskName);
            return manager.unmarkTask(index);
        } catch (NumberFormatException e) {
            throw new TaskIndexLossException();
        }
    }

    public String markTask() throws DukeExceptions{
        try {
            int index = Integer.parseInt(analyst.taskName);
            return manager.markTask(index);
        } catch (NumberFormatException e) {
            throw new TaskIndexLossException();
        }
    }

    public String deleteTask() throws DukeExceptions {
        try {
            int index = Integer.parseInt(analyst.taskName);
            return manager.deleteTask(index);
        } catch (NumberFormatException e) {
            throw new TaskIndexLossException();
        }
    }

    public void createFile() throws DukeExceptions{
        manager.createFile();
    }

    public void loadTask() throws DukeExceptions{
        manager.loadTask();
    }

    /**
     * Listen the instruction and operate during the session
     */
    public void listen() throws Exception {
                Scanner msg = new Scanner(System.in);
                this.recvMsg = msg.nextLine();
                try {
                    analyst = new OperationAnalyst(this.recvMsg);
                    String command = analyst.getCommand();
                    switch (command) {
                    case EXIT_COMMAND:
                        this.bye();
                        break;
                    case LIST_TASKS_COMMAND:
                        this.replyMsg = manager.listTask();
                        break;
                    case MARK_TASK_COMMAND:
                        this.replyMsg = this.markTask();
                        break;
                    case UNMARK_TASK_COMMAND:
                        this.replyMsg = this.unmarkTask();
                        break;
                    case ADD_DEADLINE_TASK_COMMAND:
                        this.replyMsg = manager.addDeadline(analyst.taskName, analyst.time);
                        break;
                    case ADD_EVENT_TASK_COMMAND:
                        this.replyMsg = manager.addEvent(analyst.taskName, analyst.time);
                        break;
                    case ADD_TODO_TASK_COMMAND:
                        this.replyMsg = manager.addToDo(analyst.taskName);
                        break;
                    case DELETE_TASK_COMMAND:
                        this.replyMsg = this.deleteTask();
                        break;
                    default:
                        throw new IllegalInstructionException();
                    }
                    userInterface.printMsg(replyMsg);
                    manager.saveTask();
                } catch (DukeExceptions e) {
                    userInterface.printMsg(e.toString());
                }
            }


}