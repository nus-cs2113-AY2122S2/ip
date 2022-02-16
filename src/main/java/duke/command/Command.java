package duke.command;

public class Command {
    public enum CommandType {
        ADDTASK, EXITPROGRAM, PRINTLIST, UPDATETASKSTATUS, DELETETASKS
    }

    private CommandType type;

    public Command(CommandType type) {
        setType(type);
    }

    public CommandType getType() {
        return type;
    }

    public void setType(CommandType type) {
        this.type = type;
    }

}
