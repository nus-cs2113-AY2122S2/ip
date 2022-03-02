package alexis.commands;

import alexis.storage.Storage;
import alexis.task.Task;
import alexis.taskList.TaskList;

import java.time.DateTimeException;
import java.time.LocalDate;

import static alexis.parser.Parser.parseDate;
import static alexis.parser.Parser.parseTiming;

/**
 * Shows the user all events and deadlines that fall on the date the user has specified.
 */
public class ShowCommand extends Command{

    public static final String SHOW_MESSAGE = "Here are the events/deadlines on ";
    public static final String NEGATIVE_SHOW_MESSAGE = "There are no events/deadlines on ";

    protected LocalDate date;

    /**
     * Sets up the Show Command.
     *
     * @param fullDescription Specified date by the user
     * @throws DateTimeException If fullDescription is not instance of Date
     */
    public ShowCommand(String fullDescription) throws DateTimeException {
        this.date = parseDate(fullDescription);
    }

    /**
     * Iterating through the task list, counts how many instances that the date of the task is equal to the user's
     * specified date.
     * Prints out all the matching tasks.
     * If no instances found, print a negative message.
     *
     * @param taskList Alexis.task
     * @param storage Alexis.storage
     */
    @Override
    public void execute(TaskList taskList, Storage storage) {
        boolean hasOccurance = false;
        int i = 0;
        for (Task task : taskList.taskArrayList) {
            if ((task.typeOfTask() == 'E' | task.typeOfTask() == 'D') && task.getDate().equals(date)) {
                if (i == 0) {
                    System.out.println(SHOW_MESSAGE + task.getTiming() + ":");
                }
                System.out.println((i + 1) + "." + task);
                hasOccurance = true;
                i++;
            }
        }

        if(!hasOccurance) {
            System.out.println(NEGATIVE_SHOW_MESSAGE + parseTiming(date));
        }
    }

}
