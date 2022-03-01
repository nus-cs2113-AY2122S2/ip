package controller;

import commands.Command;
import exceptions.*;

import java.util.Locale;

public class OperationAnalyst {
    protected String[] keywords;
    protected String rawInput;
    protected String time;
    protected String taskName;
    protected String instruction;
    protected static final String MARK_TASK_COMMAND = "mark";
    protected static final String UNMARK_TASK_COMMAND = "unmark";
    protected static final String DELETE_TASK_COMMAND = "delete";
    protected static final String ADD_TODO_TASK_COMMAND = "todo";
    protected static final String ADD_EVENT_TASK_COMMAND = "event";
    protected static final String ADD_DEADLINE_TASK_COMMAND = "deadline";
    protected static final String LIST_TASKS_COMMAND = "list";
    protected static final String EXIT_COMMAND = "bye";
    protected static final String SEARCH_COMMAND = "find";
    protected static final String UPDATE = "update";
    Command command;

    public OperationAnalyst(String input) throws DukeExceptions {
        this.keywords = input.split(" ");
        this.rawInput = input;
        this.instruction = keywords[0].toLowerCase(Locale.ROOT);
        parseInstruction();
        command = new Command(keywords, rawInput);
    }

    /**
     * Computes the command for further operation, which is the first element in the keywords array
     * @return the command, whether it is deadline, todo, event, list, mark or unmark
     */
    public String getCommand() {
        return this.instruction;
    }

    /**
     * Analyses raw input to determine the task name and time
     */
    public void parseInstruction() throws DukeExceptions{
        switch (this.instruction) {
        case ADD_DEADLINE_TASK_COMMAND:
            command.execute(ADD_DEADLINE_TASK_COMMAND);
            this.time = command.getDateString();
            this.taskName = command.getTaskName();
            break;
        case ADD_EVENT_TASK_COMMAND:
            command.execute(ADD_EVENT_TASK_COMMAND);
            this.time = command.getDateString();
            this.taskName = command.getTaskName();
            break;
        case ADD_TODO_TASK_COMMAND:
            command.execute(ADD_TODO_TASK_COMMAND);
            this.taskName = command.getTaskName();
            break;
        case MARK_TASK_COMMAND:
        case UNMARK_TASK_COMMAND:
        case DELETE_TASK_COMMAND:
            command.execute(UPDATE);
            this.taskName = command.getTaskName();
            break;
        case SEARCH_COMMAND:
            command.execute(SEARCH_COMMAND);
            this.taskName = command.getTaskName();
            break;
        case LIST_TASKS_COMMAND:
        case EXIT_COMMAND:
            break;
        default:
            this.taskName = rawInput;
        }

    }

    public String getTaskName() {
        return this.taskName;
    }

    public String getTime() {
        return this.time;
    }
}
