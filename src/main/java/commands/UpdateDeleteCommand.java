package commands;

import exceptions.DukeExceptions;
import exceptions.IllegalFormatException;

public class UpdateDeleteCommand extends Command{

    public UpdateDeleteCommand(String[] keywords, String rawInput){
        super(keywords, rawInput);
    }
    public void updateDeleteTask() throws DukeExceptions {
        try {
            this.taskName = this.keywords[1];
        } catch (IndexOutOfBoundsException e){
            throw new IllegalFormatException();
        }
    }
}
