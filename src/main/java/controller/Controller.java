package controller;

import java.util.Scanner;

import UI.UI;
import exceptions.DukeExceptions;
import exceptions.IllegalInstructionException;
import exceptions.TaskIndexLossException;
import tasks.TaskManager;

public class Controller {
    protected static final String MARK_TASK_COMMAND = "mark";
    protected static final String UNMARK_TASK_COMMAND = "unmark";
    protected static final String DELETE_TASK_COMMAND = "delete";
    protected static final String ADD_TODO_TASK_COMMAND = "todo";
    protected static final String ADD_EVENT_TASK_COMMAND = "event";
    protected static final String ADD_DEADLINE_TASK_COMMAND = "deadline";
    protected static final String LIST_TASKS_COMMAND = "list";
    protected static final String EXIT_COMMAND = "bye";
    protected static final String SEARCH_COMMAND = "find";
    protected String recvMsg;
    protected String replyMsg;
    private TaskManager manager = new TaskManager();
    private OperationAnalyst analyst;
    private UI userInterface = new UI();

    /**
     * Prints greeting msg on the interface
     */
    public void greet() {
        userInterface.greet();
    }

    /**
     * Prints goodbye msg on the interface and exits
     */
    public void bye() {
        userInterface.goodbye();
        System.exit(0);
    }

    /**
     * Checks if the input to indicate the index of the task for operation is a number
     * @param indexOfTask is the index of the task in String format
     * @return the index of the task in Integer format
     * @throws DukeExceptions if the input is not a number
     */
    private int parseInt(String indexOfTask) throws DukeExceptions {
        try {
            int index = Integer.parseInt(indexOfTask);
            return index;
        } catch (NumberFormatException e) {
            throw new TaskIndexLossException();
        }
    }

    /**
     * Calls task manager to create a new file for saving task
     * @throws DukeExceptions if the creation is failed
     */
    public void createFile() throws DukeExceptions {
        manager.createFile();
    }

    /**
     * Calls task manager to load tasks from the file
     * @throws DukeExceptions if the loading action is failed
     */
    public void loadTask() throws DukeExceptions {
        manager.loadTask();
    }

    /**
     * listen to the instruction from user and do operation after user open a session
     * @throws DukeExceptions if it is failed to operate the instruction user given
     */
    public void listen() throws DukeExceptions {
        Scanner msg = new Scanner(System.in);
        this.recvMsg = msg.nextLine();
        int indexOfTask;
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
                indexOfTask = parseInt(analyst.taskName);
                this.replyMsg = manager.markTask(indexOfTask);
                break;
            case UNMARK_TASK_COMMAND:
                indexOfTask = parseInt(analyst.taskName);
                this.replyMsg = manager.unmarkTask(indexOfTask);
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
                indexOfTask = parseInt(analyst.taskName);
                this.replyMsg = manager.deleteTask(indexOfTask);
                break;
            case SEARCH_COMMAND:
                this.replyMsg = manager.searchTask(analyst.taskName);
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