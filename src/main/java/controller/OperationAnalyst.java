package controller;

import exceptions.*;
import time.*;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.Locale;
import java.util.Date;

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
    Time timeChecker;

    public OperationAnalyst(String input) throws DukeExceptions {
        this.keywords = input.split(" ");
        this.rawInput = input;
        this.instruction = keywords[0].toLowerCase(Locale.ROOT);
        parseInstruction();
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
        //String name;
        String[] command;
        switch (this.instruction) {
        case ADD_DEADLINE_TASK_COMMAND:
            command = rawInput.split("/by ");
            try {
                this.taskName = keywords[1];
            } catch (Exception e){
                throw new TaskNameLossException();
            }
            try {
                timeChecker = new Time(command[1]);
                timeChecker.check();
                this.time = timeChecker.getDateString();
            } catch (IndexOutOfBoundsException e){
                throw new IllegalFormatException();
            } catch (Exception e){
                throw new IllegalTimeFormatException();
            }
            //System.out.println(this.time);
            break;
        case ADD_EVENT_TASK_COMMAND:
            command = rawInput.split("/at ");
            try {
                this.taskName = keywords[1];
            } catch (Exception e){
                throw new TaskNameLossException();
            }
            try {
                timeChecker = new Time(command[1]);
                timeChecker.check();
                this.time = timeChecker.getDateString();
            } catch (IndexOutOfBoundsException e){
                throw new IllegalFormatException();
            } catch (Exception e){
                throw new IllegalTimeFormatException();
            }
            break;
        case ADD_TODO_TASK_COMMAND:
            try {
                this.taskName = this.keywords[1];
            } catch (Exception e){
                throw new TaskNameLossException();
            }
            break;
        case MARK_TASK_COMMAND:
        case UNMARK_TASK_COMMAND:
        case DELETE_TASK_COMMAND:
            try {
                this.taskName = this.keywords[1];
            } catch (IndexOutOfBoundsException e){
                throw new IllegalFormatException();
            }
            break;
        case SEARCH_COMMAND:
            command = rawInput.split(" ",2);
            try {
                System.out.println(command.length);
                this.taskName = command[1];
            } catch (IndexOutOfBoundsException e) {
                throw new KeywordLossException();
            }
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
