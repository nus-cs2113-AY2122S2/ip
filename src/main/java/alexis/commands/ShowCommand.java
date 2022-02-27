package alexis.commands;

import alexis.storage.Storage;
import alexis.task.Task;
import alexis.taskList.TaskList;

import java.time.DateTimeException;
import java.time.LocalDate;

import static alexis.parser.Parser.parseDate;
import static alexis.parser.Parser.parseTiming;
import static alexis.task.Task.BUFFER;

public class ShowCommand extends Command{
    protected LocalDate date;

    public ShowCommand(String fullDescription) throws DateTimeException {
        this.date = parseDate(fullDescription);
    }

    @Override
    public void execute(TaskList taskList, Storage storage) {
        boolean hasOccurance = false;
        for (Task task : taskList.taskArrayList) {
            if (task.typeOfTask() == 'E' | task.typeOfTask() == 'D') {
                if (task.getDate().equals(date)) {
                    System.out.println(BUFFER + task);
                    hasOccurance = true;
                }
            }
        }

        if(!hasOccurance) {
            System.out.println("There are no events/deadlines on " + parseTiming(date));
        }
    }

}
