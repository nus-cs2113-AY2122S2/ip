package commands;

import exceptions.DukeExceptions;
import exceptions.KeywordLossException;

public class SearchCommand extends Command{

    public SearchCommand(String[] keywords, String rawInput) {
        super(keywords, rawInput);
    }

    public void searchByDescription() throws DukeExceptions {
        String[] command;
        command = rawInput.split(" ",2);
        try {
            this.taskName = command[1];
        } catch (IndexOutOfBoundsException e) {
            throw new KeywordLossException();
        }
    }
}
