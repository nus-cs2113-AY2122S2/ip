package commands;

import exceptions.DukeExceptions;
import time.Time;

public class Command {
    protected String taskName;
    protected String dateString;
    protected String rawInput;
    protected String[] keywords;
    protected static final String ADD_EVENT = "event";
    protected static final String ADD_DEADLINE = "deadline";
    protected static final String ADD_TODO = "todo";
    protected static final String UPDATE = "update";
    protected static final String SEARCH = "find";
    Time timeChecker;
    AddCommand add;
    SearchCommand search;
    UpdateDeleteCommand update;

    public Command(String[] keywords, String rawInput) {
        this.rawInput = rawInput;
        this.keywords = keywords;
        add = new AddCommand(keywords, rawInput);
        search = new SearchCommand(keywords, rawInput);
        update = new UpdateDeleteCommand(keywords, rawInput);
    }

    public void execute(String Instruction) throws DukeExceptions {
        switch (Instruction) {
        case ADD_EVENT:
            add.addTaskWithTime("/at ");
            break;
        case ADD_DEADLINE:
            add.addTaskWithTime("/by ");
            break;
        case ADD_TODO:
            add.addTaskWithoutTime();
            break;
        case SEARCH:
            search.searchByDescription();
            break;
        case UPDATE:
            update.updateDeleteTask();
        default:
            break;
        }
    }

    public String getDateString() {
        return dateString;
    }

    public String getTaskName() {
        return taskName;
    }
}
