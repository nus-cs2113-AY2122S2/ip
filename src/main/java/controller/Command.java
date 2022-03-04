package controller;

import exceptions.DukeExceptions;
import exceptions.IllegalFormatException;
import exceptions.IllegalTimeFormatException;
import exceptions.KeywordLossException;
import exceptions.TaskNameLossException;
import time.Time;

public class Command {
    protected static final String ADD_EVENT = "event";
    protected static final String ADD_DEADLINE = "deadline";
    protected static final String ADD_TODO = "todo";
    protected static final String UPDATE = "update";
    protected static final String SEARCH = "find";
    protected static final String EVENT = "event ";
    protected static final String TODO = "todo ";
    protected static final String DEADLINE = "deadline ";
    protected String taskName;
    protected String dateString;
    protected String rawInput;
    protected String[] parsedInput;
    private Time timeChecker;

    /**
     * Create a new instruction executor
     * @param parsedInput the array of the instruction after splitting by space;
     * @param rawInput the instruction that user given from the command line.
     */
    public Command(String[] parsedInput, String rawInput) {
        this.rawInput = rawInput;
        this.parsedInput = parsedInput;
    }

    /**
     * decomposes the instruction from user and store the information separately
     * @param instruction the instruction from user after analysed by Operation Analyst
     * @throws DukeExceptions if the instruction has incorrect format
     */
    public void decomposeInstruction(String instruction) throws DukeExceptions {
        switch (instruction) {
        case ADD_EVENT:
            decomposeAddWithTime("/at ", EVENT);
            break;
        case ADD_DEADLINE:
            decomposeAddWithTime("/by ", DEADLINE);
            break;
        case ADD_TODO:
            decomposeAddWithoutTime(TODO);
            break;
        case SEARCH:
            decomposeSearchByDescription();
            break;
        case UPDATE:
            decomposeUpdateDelete();
            break;
        default:
            break;
        }
    }

    /**
     * Decompose the instruction of adding task with time, and store the
     * task name and date in taskName and dateString for further access
     * @param regex the keyword for decomposition. For adding event,
     *              the regex is "/at ", and for adding deadline, the
     *              regex is "/by"
     * @param taskType the type of task that need to be added
     * @throws DukeExceptions if the task name is loss, wrong instruction
     * formatting or wrong time formatting
     */
    public void decomposeAddWithTime(String regex, String taskType) throws DukeExceptions {
        String[] parsedInputByTime;
        parsedInputByTime = rawInput.split(regex);
        try {
            this.taskName = parsedInputByTime[0].replace(taskType, "");
        } catch (Exception e) {
            throw new TaskNameLossException();
        }
        try {
            timeChecker = new Time(parsedInputByTime[1]);
            timeChecker.check();
            this.dateString = timeChecker.getDateString();
        } catch (IndexOutOfBoundsException e) {
            throw new IllegalFormatException();
        } catch (Exception e) {
            throw new IllegalTimeFormatException();
        }
    }

    /**
     * Decompose the instruction of searching task by task description,
     * and store the key information in taskName for further access
     * @throws DukeExceptions if the keyword is loss
     */
    public void decomposeSearchByDescription() throws DukeExceptions {
        String[] parseInputByKeywords;
        parseInputByKeywords = rawInput.split(" ", 2);
        try {
            this.taskName = parseInputByKeywords[1];
        } catch (IndexOutOfBoundsException e) {
            throw new KeywordLossException();
        }
    }

    /**
     * Decomposes decompose the instruction of adding task without time
     * @param taskType the type of task that is going to be added
     * @throws DukeExceptions if the task name is loss
     */
    public void decomposeAddWithoutTime(String taskType) throws DukeExceptions {
        try {
            this.taskName = this.rawInput.replace(taskType, "");
        } catch (Exception e) {
            throw new TaskNameLossException();
        }
    }

    /**
     * Decomposes the instruction for marking/unmarking and deleting task
     * @throws DukeExceptions if the format of instruction is wrong
     */
    public void decomposeUpdateDelete() throws DukeExceptions {
        try {
            this.taskName = this.parsedInput[1];
        } catch (IndexOutOfBoundsException e) {
            throw new IllegalFormatException();
        }
    }

    /**
     * Access and return the date stored in dateString in string format
     * @return the date in string format
     */
    public String getDateString() {
        return dateString;
    }

    /**
     * Access and return the information in taskName
     * @return the information stored in taskName in String format
     */
    public String getTaskName() {
        return taskName;
    }

}
