package commands;

import exceptions.DukeExceptions;
import exceptions.IllegalFormatException;
import exceptions.IllegalTimeFormatException;
import exceptions.TaskNameLossException;
import time.Time;

public class AddCommand extends Command{
    public AddCommand(String[] keywords, String rawInput) {
        super(keywords, rawInput);
    }

    public void addTaskWithTime (String regex) throws DukeExceptions {
        String parsedInput[];
        parsedInput = rawInput.split(regex);
        try {
            this.taskName = keywords[1];
        } catch (Exception e){
            throw new TaskNameLossException();
        }
        try {
            timeChecker = new Time(parsedInput[1]);
            timeChecker.check();
            this.dateString = timeChecker.getDateString();
        } catch (IndexOutOfBoundsException e){
            throw new IllegalFormatException();
        } catch (Exception e){
            throw new IllegalTimeFormatException();
        }
    }

    public void addTaskWithoutTime() throws DukeExceptions{
        try {
            this.taskName = this.keywords[1];
        } catch (Exception e){
            throw new TaskNameLossException();
        }
    }


}
