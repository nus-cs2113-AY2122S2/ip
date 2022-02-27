package alexis.commands;

import alexis.storage.Storage;
import alexis.task.Task;
import alexis.taskList.TaskList;

import java.time.DateTimeException;
import java.time.LocalDate;

import static alexis.parser.Parser.parseDate;
import static alexis.parser.Parser.parseTiming;

public class ShowCommand extends Command{

    public static final String SHOW_MESSAGE = "Here are the events/deadlines on ";
    public static final String NEGATIVE_SHOW_MESSAGE = "There are no events/deadlines on ";

    protected LocalDate date;

    public ShowCommand(String fullDescription) throws DateTimeException {
        this.date = parseDate(fullDescription);
    }

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
