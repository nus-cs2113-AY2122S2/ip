package controller;

import java.util.Locale;

import exceptions.DukeExceptions;

public class OperationAnalyst {
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
    //the key information array after splitting raw input
    protected String[] keyInfo;
    //the raw input from user
    protected String rawInput;
    //the information of task time
    protected String time;
    //the information of task description
    protected String taskName;
    //the instruction after standardizing
    protected String instruction;
    private Command command;

    /**
     * Compute and parse the instruction into several parts and store them in different
     * format into different parameters.
     * @param input the raw input that user type in the command line
     * @throws DukeExceptions if there's any unacceptable condition exist while
     * parsing the instruction
     */
    public OperationAnalyst(String input) throws DukeExceptions {
        //split the raw input into several keywords
        this.keyInfo = input.split(" ");
        this.rawInput = input;
        //Standardize the instruction
        this.instruction = keyInfo[0].toLowerCase(Locale.ROOT);
        //Create a command for further parsing
        command = new Command(keyInfo, rawInput);
        parseInstruction();
    }

    /**
     * @return the command, whether it is deadline, todo, event,
     * list, mark, unmark, delete or find
     */
    public String getCommand() {
        return this.instruction;
    }

    /**
     * Parses the command and store the task name, index of task and time
     * in different parameters.
     * @throws DukeExceptions if there's unacceptable condition exist
     */
    public void parseInstruction() throws DukeExceptions {
        switch (this.instruction) {
        case ADD_DEADLINE_TASK_COMMAND:
            command.decomposeInstruction(ADD_DEADLINE_TASK_COMMAND);
            this.time = command.getDateString();
            this.taskName = command.getTaskName();
            break;
        case ADD_EVENT_TASK_COMMAND:
            command.decomposeInstruction(ADD_EVENT_TASK_COMMAND);
            this.time = command.getDateString();
            this.taskName = command.getTaskName();
            break;
        case ADD_TODO_TASK_COMMAND:
            command.decomposeInstruction(ADD_TODO_TASK_COMMAND);
            this.taskName = command.getTaskName();
            break;
        case MARK_TASK_COMMAND:
        case UNMARK_TASK_COMMAND:
        case DELETE_TASK_COMMAND:
            command.decomposeInstruction(UPDATE);
            this.taskName = command.getTaskName();
            break;
        case SEARCH_COMMAND:
            command.decomposeInstruction(SEARCH_COMMAND);
            this.taskName = command.getTaskName();
            break;
        case LIST_TASKS_COMMAND:
        case EXIT_COMMAND:
            break;
        default:
            this.taskName = rawInput;
        }

    }

    /**
     * Returns the information stored in time
     * @return the date in string format
     */
    public String getTime() {
        return this.time;
    }
}
